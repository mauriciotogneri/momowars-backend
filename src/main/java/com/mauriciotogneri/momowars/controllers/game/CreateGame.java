package com.mauriciotogneri.momowars.controllers.game;

import com.mauriciotogneri.jerry.controller.EntityProvider;
import com.mauriciotogneri.jerry.controller.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.server.BaseController;

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
public class CreateGame extends BaseController
{
    @POST
    @Path("v1/games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken,
                               Entity entity) throws Exception
    {
        return process(() -> response(sessionToken, entity));
    }

    private Response response(String sessionToken, Entity entity) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(entity);

        Account account = validateSessionToken(sessionToken);

        Game newGame = gameService.createGame(entity.maxPlayers, entity.mapId, account.id());

        playerService.create(account.id(), newGame.id());

        Game game = gameService.getGame(newGame.id(), account.id());

        return response(CREATED, game);
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class Entity extends EntityProvider<Entity> implements EntityObject
    {
        private Long mapId;
        private Integer maxPlayers;

        public Entity()
        {
            super(Entity.class);
        }

        @Override
        public boolean isValid()
        {
            return Objects.nonNull(mapId) && Objects.nonNull(maxPlayers) && (maxPlayers >= 2);
        }
    }
}