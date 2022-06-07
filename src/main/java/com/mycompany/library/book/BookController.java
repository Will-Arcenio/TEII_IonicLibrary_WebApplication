package com.mycompany.library.book;

import java.util.List;
import com.mycompany.library.regrasnegocio.RegraNegocioMensagem;
import com.mycompany.library.regrasnegocio.RegraNegocioException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookController {
    
    @Inject
    private BookService bookService;
    
    @GET
    public List<Book> findAll() {
        return this.bookService.findAll();
    }
    
    @GET
    @Path("{id}")
    public Book findById(@PathParam("id") Long id) {
        Book book = this.bookService.findById(id);
        if (book == null) {
            throw new WebApplicationException("Livro não encontrado", Response.Status.NOT_FOUND);
        }
        
        return book;
    }
    
    @POST
    public Response add(Book book) {
        try{
            Book bookAdicionado = this.bookService.add(book);
            return Response.status(Response.Status.CREATED).entity(bookAdicionado).build();
            
        }catch (RegraNegocioException e ){
            return Response.status(Response.Status.BAD_REQUEST).entity(
                new RegraNegocioMensagem(e.getMessage())
            ).build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        Book book = this.bookService.findById(id);
        if (book == null) {
            throw new NotFoundException("Livro não encontrado(a)");
        }
        
        this.bookService.remove(book);
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Book bookUpdated) {
        Book bookFinded = this.bookService.findById(id);
        if (bookFinded == null) {
            throw new NotFoundException("Livro não encontrado(a)");
        }
        
        bookUpdated.setId(id);
        try {
            return Response.ok(this.bookService.update(bookUpdated)).build();
        } catch (RegraNegocioException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(
                    new RegraNegocioMensagem(e.getMessage())
            ).build();
        }
    }
    
    @GET
    @Path("search")
    public List<Book> search(@QueryParam("nome") String nome) {
        return this.bookService.search(nome);
    }
    
}