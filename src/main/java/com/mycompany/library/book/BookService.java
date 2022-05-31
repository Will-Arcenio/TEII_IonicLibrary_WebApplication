package com.mycompany.library.book;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BookService {
    
    @PersistenceContext(unitName = "LibraryPU")
    private EntityManager entityManager;
    
    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }
    
    public Book add(Book book) {
        entityManager.persist(book);
        return book;
    }
    
    public void remove(Book book) {
        this.entityManager.remove(findById(book.getId()));
    }
    
    public Book update(Book bookUpdated) {
        entityManager.merge(bookUpdated);
        return bookUpdated;
    }
    
    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
    
    public List<Book> search(String nome) {
        return entityManager
                .createQuery("SELECT b FROM Book nome b WHERE LOWER(b.nome) LIKE :nome", Book.class)
                .setParameter("nome", "%" + nome.toLowerCase() + "%")
                .getResultList();
    }
    
}