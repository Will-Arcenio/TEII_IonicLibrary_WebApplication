
package com.mycompany.library.author;

import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Stateless
public class AuthorService {
    
    @PersistenceContext(unitName = "LibraryPU")
    private EntityManager entityManager;
    
    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }
    
    public Author add(Author author) {
        this.checkAuthorExist(author);
        this.checkAuthorAge(author);
        entityManager.persist(author);
        return author;
    }
    
    public void remove(Author author) {
        this.entityManager.remove(findById(author.getId()));
    }
    
    public Author update(Author authorUpdated) {
        this.checkAuthorAge(authorUpdated);
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
    
//  Regras de Negócio / Validações
    /* VALIDA SE AUTOR JÁ ESTÁ CADASTRADO NA BASE */
    public void checkAuthorExist(Author author) {
        List<Author> resultList = entityManager
                .createQuery("SELECT a FROM Author a WHERE LOWER(a.nome) LIKE :nome AND LOWER(a.sobrenome) LIKE :sobrenome ")
                .setParameter("nome", "%" + author.getNome().toLowerCase() + "")
                .setParameter("sobrenome", "%" + author.getSobrenome().toLowerCase() + "")
                .getResultList();
        
        if (resultList != null && !resultList.isEmpty()) {
            throw new WebApplicationException("O Autor '<strong>" + author.getNome() + " " + author.getSobrenome() + "</strong>' já está cadastrado na base.",Response.Status.BAD_REQUEST);
                    
        }
    }
    
    /* VALIDA SE O AUTOR POSSUI A IDADE MAIOR QUE 5 ANOS*/
    public void checkAuthorAge(Author author) {
        LocalDate authorBorn = author.getNascimento();
        LocalDate today = LocalDate.now();
        
        //
//        if ((today.getYear() - authorBorn.getYear()) <= 5) {
//            throw new WebApplicationException("O Autor precisa ter 6 anos ou mais.", Response.Status.BAD_REQUEST);
//        }

        //
        if (today.compareTo(authorBorn) < 6) {
            throw new WebApplicationException("O Autor precisa ter 6 anos ou mais.", Response.Status.BAD_REQUEST);
        }
    }
    
}
