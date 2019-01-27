package com.javaee8.rest;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("book")
@Produces(APPLICATION_JSON)
public class BookResource {

    @Inject
    private Repository repository;

    @GET
    public Response getBook() {
        List<String> books = repository.getBooks();
        return Response.ok(books).build();
    }
}