package com.mauriciotogneri.momowars.controllers.match;

import com.mauriciotogneri.momowars.model.Match;
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
public class GetOpenMatches extends BaseController
{
    @GET
    @Path("v1/matches")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@CookieParam(COOKIE_SESSION) String session) throws Exception
    {
        return process(() -> response(session));
    }

    private Response response(String session) throws Exception
    {
        checkIfNotEmpty(session);
        validateSession(session);

        List<Match> matches = matchService.getOpenMatches();

        return response(OK, matches.stream().map(Match::json).collect(Collectors.toList()));
    }
}