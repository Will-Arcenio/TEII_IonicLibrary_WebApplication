
package com.mycompany.library.author;

import java.util.List;
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

@Path("authors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthorController {
    
    @Inject
    private AuthorService authorService;
    
    @GET
    public List<Author> findAll() {
        return this.authorService.findAll();
    }
    
    @GET
    @Path("{id}")
    public Author findById(@PathParam("id") Long id) {
        Author author = this.authorService.findById(id);
        if (author == null) {
            throw new WebApplicationException("Autor não encontrado", Response.Status.NOT_FOUND);
        }
        
        return author;
    }
    
    @POST
    public Author add(Author author) {
        return this.authorService.add(author);
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        Author author = this.authorService.findById(id);
        if (author == null) {
            throw new NotFoundException("Autor(a) não encontrado(a)");
        }
        
        this.authorService.remove(author);
    }
    
    @PUT
    @Path("{id}")
    public Author update(@PathParam("id") Long id, Author authorUpdated) {
        Author authorFinded = this.authorService.findById(id);
        if (authorFinded == null) {
            throw new NotFoundException("Autor(a) não encontrado(a)");
        }
        
        authorUpdated.setId(id);
        return this.authorService.update(authorUpdated);
    }
    
    @GET
    @Path("search")
    public List<Author> search(@QueryParam("nome") String nome) {
        return this.authorService.search(nome);
    }
    
}
