package com.example.domaci7.resources;

import com.example.domaci7.entities.Komentar;
import com.example.domaci7.entities.Post;
import com.example.domaci7.services.KomentarService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comments")
public class CommentResource {

    @Inject
    private KomentarService komentarService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allKoments(@PathParam("id") Integer id) {
        return Response.ok(this.komentarService.allKoments(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar create(@Valid Komentar komentar) {
        return this.komentarService.addKomentar(komentar);
    }
}
