package org.programacionparaaprender;

import org.programacionparaaprender.entities.Product;
import org.programacionparaaprender.repositories.ProductRepository;

import java.util.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/product")
public class ExampleResource {

    @ConfigProperty(name="greeting")
	private String greeting;

	@Inject
    ProductRepository pr;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("custom/{name}")
    public String customHello(@PathParam("name") String name) {
    	return greeting + " " + name + " how are you doing?";
    }

    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("obtener/{id}")
    public Product obtener(@PathParam("id") final String id){
        return pr.getProduct(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> list() {
        return pr.listProduct();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Product p) {
        pr.createdProduct(p);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Product p) {
       pr.deleteProduct(p);
        return Response.ok().build();
    }
    
}
