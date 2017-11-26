package com.mauriciotogneri.momowars.controllers.game;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.server.BaseController;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_IMPLEMENTED;

@Path("api")
public class GetOpenGames extends BaseController
{
    @GET
    @Path("v1/games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOpenGames(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken) throws Exception
    {
        return process(connection -> getOpenGames(connection, sessionToken));
    }

    private Response getOpenGames(DatabaseConnection connection, String sessionToken) throws Exception
    {
        checkIfNotEmpty(sessionToken);

        return response(NOT_IMPLEMENTED);
    }
}