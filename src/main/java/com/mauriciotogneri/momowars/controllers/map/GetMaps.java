package com.mauriciotogneri.momowars.controllers.map;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.services.MapService;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;

@Path("api")
public class GetMaps extends BaseController
{
    @GET
    @Path("v1/maps")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken) throws Exception
    {
        return process(connection -> controller(connection, sessionToken));
    }

    private Response controller(DatabaseConnection connection, String sessionToken) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        validateSessionToken(connection, sessionToken);

        List<Map> maps = MapService.getMaps(connection);

        return response(OK, maps);
    }
}