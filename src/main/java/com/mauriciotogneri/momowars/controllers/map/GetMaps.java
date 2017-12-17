package com.mauriciotogneri.momowars.controllers.map;

import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.server.BaseController;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
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
    public Response controller(@CookieParam(COOKIE_SESSION) String session) throws Exception
    {
        return process(() -> response(session));
    }

    private Response response(String session) throws Exception
    {
        checkIfNotEmpty(session);
        validateSession(session);

        List<Map> maps = mapService.maps();

        return response(OK, maps.stream().map(Map::json).collect(Collectors.toList()));
    }
}