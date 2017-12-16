package com.mauriciotogneri.momowars.controllers.match;

import com.mauriciotogneri.momowars.model.Match;
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
public class GetOpenMatches extends BaseController
{
    @GET
    @Path("v1/matches")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken) throws Exception
    {
        return process(() -> response(sessionToken));
    }

    private Response response(String sessionToken) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        validateSessionToken(sessionToken);

        List<Match> matches = matchService.getOpenMatches();

        return response(OK, matches);
    }
}