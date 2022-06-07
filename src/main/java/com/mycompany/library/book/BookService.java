package com.mycompany.library.book;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.mycompany.library.regrasnegocio.RegraNegocioException;

@Stateless
public class BookService {
    
    @PersistenceContext(unitName = "LibraryPU")
    private EntityManager entityManager;
    
    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }
    
    public Book add(Book book) throws RegraNegocioException {
        valida(book);
        entityManager.persist(book);
        return book;
    }
    
    public void remove(Book book) {
        this.entityManager.remove(findById(book.getId()));
    }
    
    public Book update(Book bookUpdated) throws RegraNegocioException {
        valida(bookUpdated);
        entityManager.merge(bookUpdated);
        return bookUpdated;
    }
    
    private void valida(Book book) throws RegraNegocioException {
        validaExistenciaReferencia(book);
        validaExistenciaBook(book);
    }
    
    private void validaExistenciaReferencia(Book book) throws RegraNegocioException {
        List<Book> resultList = entityManager
                .createQuery("SELECT b FROM Book b WHERE LOWER(b.referencia) = :referencia", Book.class)
                .setParameter("referencia", book.getReferencia().toLowerCase())
                .getResultList();
        if(resultList != null && !resultList.isEmpty()) {
            throw new RegraNegocioException("A referencia já está cadastrada em nossa base");
        }
    }

    private void validaExistenciaBook(Book book) throws RegraNegocioException {
        List<Book> resultList = entityManager
                .createQuery("SELECT b FROM Book b WHERE LOWER(b.nome) = :nome", Book.class)
                .setParameter("nome", book.getNome().toLowerCase())
                .getResultList();
        if(resultList != null && !resultList.isEmpty()) {
            throw new RegraNegocioException("O livro já está cadastrado em nossa base");
        }
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