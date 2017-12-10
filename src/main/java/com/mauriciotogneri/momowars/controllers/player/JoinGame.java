package com.mauriciotogneri.momowars.controllers.player;

import com.mauriciotogneri.jerry.controller.EntityProvider;
import com.mauriciotogneri.jerry.controller.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.GameFinishedException;
import com.mauriciotogneri.momowars.exceptions.GameFullException;
import com.mauriciotogneri.momowars.exceptions.GamePlayingException;
import com.mauriciotogneri.momowars.exceptions.PlayerAlreadyJoinedException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.notifications.GameStarted;
import com.mauriciotogneri.momowars.notifications.Notification;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.services.AccountService;
import com.mauriciotogneri.momowars.services.GameService;
import com.mauriciotogneri.momowars.services.PlayerService;
import com.mauriciotogneri.momowars.types.GameStatus;

import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("api")
public class JoinGame extends BaseController
{
    @POST
    @Path("v1/players")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken,
                               Entity entity) throws Exception
    {
        return process(connection -> controller(connection, sessionToken, entity));
    }

    private Response controller(DatabaseConnection connection, String sessionToken, Entity entity) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(entity);

        Account account = validateSessionToken(connection, sessionToken);

        if (account.hasGame(entity.gameId))
        {
            throw new PlayerAlreadyJoinedException();
        }

        Game loadedGame = GameService.getGame(connection, entity.gameId, account.id());

        if (loadedGame.isPlaying())
        {
            throw new GamePlayingException();
        }
        else if (loadedGame.isFinished())
        {
            throw new GameFinishedException();
        }
        else if (loadedGame.isFull())
        {
            throw new GameFullException();
        }

        AccountService.joinGame(connection, account.id(), loadedGame.id());
        PlayerService.create(connection, account.id(), loadedGame.id());

        if (loadedGame.playersMissing() == 1)
        {
            GameService.updateStatus(connection, loadedGame.id(), GameStatus.PLAYING);

            Notification notification = new GameStarted(loadedGame.id());
            notification.send();
        }

        Game game = GameService.getGame(connection, loadedGame.id(), account.id());

        return response(CREATED, game);
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class Entity extends EntityProvider<Entity> implements EntityObject
    {
        private Long gameId;

        public Entity()
        {
            super(Entity.class);
        }

        @Override
        public boolean isValid()
        {
            return Objects.nonNull(gameId);
        }
    }
}