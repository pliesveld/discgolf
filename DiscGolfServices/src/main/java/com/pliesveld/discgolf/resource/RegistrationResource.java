package com.pliesveld.discgolf.resource;

import com.pliesveld.discgolf.domain.Player;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
public class RegistrationResource {

    @Path("/player")
    public Player registerPlayer() {
        return new Player();
    }
}
