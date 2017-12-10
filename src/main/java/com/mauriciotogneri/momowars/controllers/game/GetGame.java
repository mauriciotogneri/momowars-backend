package com.mauriciotogneri.momowars.controllers.game;

import com.mauriciotogneri.momowars.exceptions.InvalidGameException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Game;
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
public class GetGame extends BaseController
{
    @GET
    @Path("v1/games/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken,
                               @PathParam(PARAM_GAME_ID) Long gameId) throws Exception
    {
        return process(() -> response(sessionToken, gameId));
    }

    private Response response(String sessionToken, Long gameId) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(gameId);

        Account account = validateSessionToken(sessionToken);

        if (!account.hasGame(gameId))
        {
            throw new InvalidGameException();
        }

        Game game = gameService.getGame(gameId, account.id());

        return response(OK, game);
    }
}