package com.mauriciotogneri.momowars.controllers.game;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.server.BaseController;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_IMPLEMENTED;

@Path("api")
public class GetGame extends BaseController
{
    @GET
    @Path("v1/games/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGame(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken,
                            @PathParam("gameId") Long gameId) throws Exception
    {
        return process(connection -> getGame(connection, sessionToken, gameId));
    }

    private Response getGame(DatabaseConnection connection, String sessionToken, Long gameId) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(gameId);

        return response(NOT_IMPLEMENTED);
    }
}