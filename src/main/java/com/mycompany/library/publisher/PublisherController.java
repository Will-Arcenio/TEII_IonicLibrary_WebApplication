package com.mycompany.library.publisher;

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

@Path("publishers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class PublisherController {
    
    @Inject
    private PublisherService publisherService;

    @GET
    public List<Publisher> findAll() {
        return this.publisherService.findAll();
    }
    
    @GET
    @Path("{id}")
    public Publisher findById(@PathParam("id") Long id) {
        Publisher publisher = this.publisherService.findById(id);
        if (publisher == null) {
            throw new WebApplicationException("Editora não encontrada", Response.Status.NOT_FOUND);
        }
        return publisher;
    }
    
    @POST
    public Publisher add(Publisher publisher) {
        return this.publisherService.add(publisher);
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        Publisher publisher = this.findById(id);
        if (publisher == null) {
            throw new NotFoundException("Editora não encontrada");
        }
        this.publisherService.remove(publisher);
    }
    
    @PUT
    @Path("{id}")
    public Publisher update(@PathParam("id") Long id, Publisher publisherAtualizada) {
        Publisher publisherEncontrada = this.findById(id);
        if (publisherEncontrada == null) {
            throw new NotFoundException("Editora não encontrada");
        }
        publisherAtualizada.setId(id);
        return this.publisherService.update(publisherAtualizada);
    }
    
    @GET
    @Path("search")
    public List<Publisher> search(@QueryParam("email") String email) {
        return this.publisherService.search(email);
    }
}
