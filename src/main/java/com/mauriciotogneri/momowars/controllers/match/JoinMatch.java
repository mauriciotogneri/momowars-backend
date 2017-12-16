package com.mauriciotogneri.momowars.controllers.match;

import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Match;
import com.mauriciotogneri.momowars.server.BaseController;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("api")
public class JoinMatch extends BaseController
{
    @POST
    @Path("v1/matches/{matchId}/players")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@CookieParam(COOKIE_SESSION) String session,
                               @PathParam(PARAM_MATCH_ID) Long matchId) throws Exception
    {
        return process(() -> response(session, matchId));
    }

    private Response response(String session, Long matchId) throws Exception
    {
        checkIfNotEmpty(session);

        Account account = validateSession(session);
        Match match = matchService.joinMatch(account, matchId);

        return response(CREATED, match);
    }
}