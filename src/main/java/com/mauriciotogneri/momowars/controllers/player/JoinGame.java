package com.mauriciotogneri.momowars.controllers.player;

import com.mauriciotogneri.momowars.exceptions.GameFinishedException;
import com.mauriciotogneri.momowars.exceptions.GamePlayingException;
import com.mauriciotogneri.momowars.exceptions.PlayerAlreadyJoinedException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.server.BaseController;

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
        return process(() -> response(sessionToken, gameId));
    }

    private Response response(String sessionToken, Long gameId) throws Exception
    {
        checkIfNotEmpty(sessionToken);

        Account account = validateSessionToken(sessionToken);

        if (account.hasGame(gameId))
        {
            throw new PlayerAlreadyJoinedException();
        }

        Game loadedGame = gameService.getGame(gameId, account.id());

        if (loadedGame.isPlaying())
        {
            throw new GamePlayingException();
        }
        else if (loadedGame.isFinished())
        {
            throw new GameFinishedException();
        }

        playerService.create(account.id(), loadedGame.id());

        if (loadedGame.playersMissing() == 1)
        {
            gameService.startGame(loadedGame.id());
        }

        Game game = gameService.getGame(loadedGame.id(), account.id());

        return response(CREATED, game);
    }
}