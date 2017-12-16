package com.mauriciotogneri.momowars.controllers.map;

import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.server.BaseController;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
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
    public Response controller(@CookieParam(COOKIE_SESSION) String session,
                               @PathParam(PARAM_MAP_ID) Long mapId) throws Exception
    {
        return process(() -> response(session, mapId));
    }

    private Response response(String session, Long mapId) throws Exception
    {
        checkIfNotEmpty(session);
        checkIfNotEmpty(mapId);
        validateSession(session);

        Map map = mapService.getMap(mapId);

        return response(OK, map);
    }
}