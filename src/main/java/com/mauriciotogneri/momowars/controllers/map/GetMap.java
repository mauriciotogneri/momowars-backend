package com.mauriciotogneri.momowars.controllers.map;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.services.MapService;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;

@Path("api")
public class GetMap extends BaseController
{
    @GET
    @Path("v1/maps/{mapId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken,
                               @PathParam("mapId") Long mapId) throws Exception
    {
        return process(connection -> controller(connection, sessionToken, mapId));
    }

    private Response controller(DatabaseConnection connection, String sessionToken, Long mapId) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(mapId);
        validateSessionToken(connection, sessionToken);

        Map map = MapService.getMap(connection, mapId);

        return response(OK, map);
    }
}