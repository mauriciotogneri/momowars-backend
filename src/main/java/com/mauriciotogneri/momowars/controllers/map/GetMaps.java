package com.mauriciotogneri.momowars.controllers.map;

import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.server.BaseController;

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
        return process(() -> response(sessionToken));
    }

    private Response response(String sessionToken) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        validateSessionToken(sessionToken);

        List<Map> maps = mapService.getMaps();

        return response(OK, maps);
    }
}