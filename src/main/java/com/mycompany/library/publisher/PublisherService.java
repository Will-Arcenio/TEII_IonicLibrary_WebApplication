
package com.mycompany.library.resources;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PublisherService {
    
    @PersistenceContext(unitName = "LibraryPU")
    private EntityManager entityManager;

    public Publisher findById(Long id) {
        return entityManager.find(Publisher.class, id);
    }
    
    public Publisher add(Publisher publisher) {
        entityManager.persist(publisher);
        return publisher;
    }
    
     public void remove(Publisher publisher) {
        entityManager.remove(findById(publisher.getId()));
    }
     
     public Publisher update(Publisher publisherAtualizada) {
        entityManager.merge(publisherAtualizada);
        return publisherAtualizada;
    }
     
    public List<Publisher> findAll() {
       return entityManager
               .createQuery("SELECT p FROM Publisher p", Publisher.class)
               .getResultList();
   }
    
    public List<Publisher> search(String email) {
         return entityManager
                .createQuery("SELECT p FROM Publisher email p WHERE LOWER(p.email) LIKE :email", Publisher.class)
                .setParameter("nome", "%" + email.toLowerCase() + "%")
                .getResultList();
    }
}
