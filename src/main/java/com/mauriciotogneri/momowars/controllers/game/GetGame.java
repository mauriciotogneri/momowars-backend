package com.mauriciotogneri.momowars.controllers.game;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.InvalidGameException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.services.GameService;

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
        return process(connection -> controller(connection, sessionToken, gameId));
    }

    private Response controller(DatabaseConnection connection, String sessionToken, Long gameId) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(gameId);

        Account account = validateSessionToken(connection, sessionToken);

        if (!account.hasGame(gameId))
        {
            throw new InvalidGameException();
        }

        Game game = GameService.getGame(connection, gameId, account.id());

        return response(OK, game);
    }
}