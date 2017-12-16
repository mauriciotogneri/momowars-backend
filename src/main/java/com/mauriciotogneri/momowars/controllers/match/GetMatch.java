package com.mauriciotogneri.momowars.controllers.match;

import com.mauriciotogneri.momowars.exceptions.InvalidMatchException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Match;
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
public class GetMatch extends BaseController
{
    @GET
    @Path("v1/matches/{matchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken,
                               @PathParam(PARAM_MATCH_ID) Long matchId) throws Exception
    {
        return process(() -> response(sessionToken, matchId));
    }

    private Response response(String sessionToken, Long matchId) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(matchId);

        Account account = validateSessionToken(sessionToken);

        if (!account.hasMatch(matchId))
        {
            throw new InvalidMatchException();
        }

        Match match = matchService.getMatch(matchId, account.id());

        return response(OK, match);
    }
}