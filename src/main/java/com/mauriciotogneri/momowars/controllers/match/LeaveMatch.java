package com.mauriciotogneri.momowars.controllers.match;

import com.mauriciotogneri.momowars.server.BaseController;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Path("api")
public class LeaveMatch extends BaseController
{
    @DELETE
    @Path("v1/matches/{matchId}/players/{playerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@CookieParam(COOKIE_SESSION) String session,
                               @PathParam(PARAM_MATCH_ID) Long matchId,
                               @PathParam(PARAM_PLAYER_ID) Long playerId) throws Exception
    {
        return process(() -> response(session, matchId, playerId));
    }

    private Response response(String session, Long matchId, Long playerId) throws Exception
    {
        checkIfNotEmpty(session);
        checkIfNotEmpty(playerId);
        checkIfNotEmpty(matchId);
        validateSession(session);

        playerService.leaveMatch(matchId, playerId);

        return response(NO_CONTENT);
    }
}