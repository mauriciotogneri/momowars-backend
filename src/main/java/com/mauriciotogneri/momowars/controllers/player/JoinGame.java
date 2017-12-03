package com.mauriciotogneri.momowars.controllers.player;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.GameFullException;
import com.mauriciotogneri.momowars.exceptions.GameNotOpenException;
import com.mauriciotogneri.momowars.exceptions.PlayerAlreadyJoinedException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.services.AccountService;
import com.mauriciotogneri.momowars.services.GameService;
import com.mauriciotogneri.momowars.services.PlayerService;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("api")
public class JoinGame extends BaseController
{
    @POST
    @Path("v1/games/{gameId}/players")
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

        if (account.hasGame(gameId))
        {
            throw new PlayerAlreadyJoinedException();
        }

        Game game = GameService.getGame(connection, gameId);

        if (!game.isOpen())
        {
            throw new GameNotOpenException();
        }
        else if (game.isFull())
        {
            throw new GameFullException();
        }

        AccountService.joinGame(connection, account.id(), game.id());
        PlayerService.create(connection, account.id(), game.id());

        return response(CREATED, GameService.getGame(connection, game.id()));
    }
}