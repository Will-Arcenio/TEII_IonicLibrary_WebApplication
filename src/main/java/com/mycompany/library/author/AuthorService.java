
package com.mycompany.library.author;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuthorService {
    
    @PersistenceContext(unitName = "LibraryPU")
    private EntityManager entityManager;
    
    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }
    
    public Author add(Author author) {
        entityManager.persist(author);
        return author;
    }
    
    public void remove(Author author) {
        this.entityManager.remove(findById(author.getId()));
    }
    
    public Author update(Author authorUpdated) {
        entityManager.merge(authorUpdated);
        return authorUpdated;
    }
    
    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }
    
    public List<Author> search(String nome) {
        return entityManager
                .createQuery("SELECT a FROM Author nome a WHERE LOWER(a.nome) LIKE :nome", Author.class)
                .setParameter("nome", "%" + nome.toLowerCase() + "%")
                .getResultList();
    }
    
}
