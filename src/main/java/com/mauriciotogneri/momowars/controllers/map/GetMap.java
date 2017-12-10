package com.mauriciotogneri.momowars.controllers.map;

import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.server.BaseController;

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
                               @PathParam(PARAM_MAP_ID) Long mapId) throws Exception
    {
        return process(() -> response(sessionToken, mapId));
    }

    private Response response(String sessionToken, Long mapId) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(mapId);
        validateSessionToken(sessionToken);

        Map map = mapService.getMap(mapId);

        return response(OK, map);
    }
}