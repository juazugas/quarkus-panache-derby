package org.acme;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * FruitsResource
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/fruit")
public class FruitsResource {

    @GET
    public List<Fruit> listAll() {
        return Fruit.findAll().list();
    }

    @Transactional
    @POST
    public Fruit create (Fruit fruit) {
        fruit.persist();
        return fruit;
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void delete (@PathParam("id") long id) {

        Fruit fruit = Fruit.<Fruit>findByIdOptional(id)
                        .orElseThrow(NotFoundException::new);
        fruit.delete();
    }

}