package com.mauriciotogneri.momowars.controllers.game;

import com.mauriciotogneri.jerry.controller.EntityProvider;
import com.mauriciotogneri.jerry.controller.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.model.Game;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.services.GameService;

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
        return process(connection -> controller(connection, sessionToken, entity));
    }

    private Response controller(DatabaseConnection connection, String sessionToken, Entity entity) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(entity);
        validateSessionToken(connection, sessionToken);

        Game game = GameService.createGame(connection, entity.maxPlayers, entity.mapId);
        // TODO: create player and add it to the game

        return response(CREATED, game);
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class ControllerEntityProvider extends EntityProvider<Entity>
    {
        public ControllerEntityProvider()
        {
            super(Entity.class);
        }
    }

    private static class Entity implements EntityObject
    {
        private Long mapId;
        private Integer maxPlayers;

        @Override
        public boolean isValid()
        {
            return Objects.nonNull(mapId) && Objects.nonNull(maxPlayers);
        }
    }
}