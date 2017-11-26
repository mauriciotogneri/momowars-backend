package com.mauriciotogneri.momowars.controllers.map;

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
public class GetMap extends BaseController
{
    @GET
    @Path("v1/maps/{mapId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMap(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken,
                           @PathParam("mapId") Long mapId) throws Exception
    {
        return process(connection -> getMap(connection, sessionToken, mapId));
    }

    private Response getMap(DatabaseConnection connection, String sessionToken, Long mapId) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(mapId);

        return response(NOT_IMPLEMENTED);
    }
}