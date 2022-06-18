package com.mycompany.library.book;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.mycompany.library.regrasnegocio.RegraNegocioException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Stateless
public class BookService {
    
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @PersistenceContext(unitName = "LibraryPU")
    private EntityManager entityManager;
    
    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }
    
    public Book add(Book book) throws RegraNegocioException {
        valida(book);
        this.checkPrice(book);
        this.checkPublicationDate(book);
        entityManager.persist(book);
        return book;
    }
    
    public void remove(Book book) {
        this.entityManager.remove(findById(book.getId()));
    }
    
    public Book update(Book bookUpdated) throws RegraNegocioException {
        this.checkPrice(bookUpdated);
        this.checkPublicationDate(bookUpdated);
        entityManager.merge(bookUpdated);
        return bookUpdated;
    }
    
    private void valida(Book book) throws RegraNegocioException {
        this.validaExistenciaReferencia(book);
        this.validaExistenciaBook(book);
    }
    
    private void validaExistenciaReferencia(Book book) throws RegraNegocioException {
        List<Book> resultList = entityManager
                .createQuery("SELECT b FROM Book b WHERE LOWER(b.referencia) = :referencia", Book.class)
                .setParameter("referencia", book.getReferencia().toLowerCase())
                .getResultList();
        if(resultList != null && !resultList.isEmpty()) {
            throw new RegraNegocioException("A referencia '<strong>" + book.getReferencia() + "</strong>' já está cadastrada em nossa base");
        }
    }

    private void validaExistenciaBook(Book book) throws RegraNegocioException {
        List<Book> resultList = entityManager
                .createQuery("SELECT b FROM Book b WHERE LOWER(b.nome) = :nome", Book.class)
                .setParameter("nome", book.getNome().toLowerCase())
                .getResultList();
        if(resultList != null && !resultList.isEmpty()) {
            throw new RegraNegocioException("O livro '<strong>" + book.getNome() + "</strong>' já está cadastrado em nossa base");
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
    
    
//  Regras de Negócio / Validações
    /* VALIDA PREÇO DO LIVRO */
    public void checkPrice(Book book) {
        Float checkBookPrice = book.getPreco();
        if (checkBookPrice < 0) {
            throw new WebApplicationException("O preço do livro precisa ser maior ou igual a 0 (zero).", Response.Status.BAD_REQUEST);
        }
    }
    
    /* VALIDA SE A DATA DE PUBLICAÇÃO É MAIOR QUE A DATA ATUAL */
    public void checkPublicationDate(Book book) {
        if (!LocalDate.now().isAfter(book.getPublicacao()) && !book.getPublicacao().isEqual(LocalDate.now()) ) {
            throw new WebApplicationException("A data de publicação do livro não pode ser maior que a data de hoje <strong>(" + LocalDate.now().format(dateFormat) + ")</strong>", Response.Status.BAD_REQUEST);
        }
    }
    
}