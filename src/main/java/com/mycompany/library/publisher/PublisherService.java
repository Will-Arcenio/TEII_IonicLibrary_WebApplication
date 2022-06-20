
package com.mycompany.library.publisher;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Stateless
public class PublisherService {
    
    @PersistenceContext(unitName = "LibraryPU")
    private EntityManager entityManager;

    public Publisher findById(Long id) {
        return entityManager.find(Publisher.class, id);
    }
    
    public Publisher add(Publisher publisher) {
        this.checkPublisherExists(publisher);
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
    
//  Regras de Negócio / Validações
    /* VALIDA SE EDITORA JÁ ESTÁ CADASTRADA */
    public void checkPublisherExists(Publisher publisher) {
        List<Publisher> resultList = entityManager
                .createQuery("SELECT p FROM Publisher p WHERE LOWER(p.nome) = :nome")
                .setParameter("nome", publisher.getNome().trim().toLowerCase())
                .getResultList();
        
        if (resultList != null && !resultList.isEmpty()) {
            throw new WebApplicationException("A editora '" + publisher.getNome() + "' já está cadastrada na nossa base.", Response.Status.BAD_REQUEST);
        }
    }
}
