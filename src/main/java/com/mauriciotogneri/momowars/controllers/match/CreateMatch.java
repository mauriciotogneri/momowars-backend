package com.mauriciotogneri.momowars.controllers.match;

import com.mauriciotogneri.jerry.controller.EntityProvider;
import com.mauriciotogneri.jerry.controller.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.exceptions.InvalidParametersException;
import com.mauriciotogneri.momowars.exceptions.MapNotFoundException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.Match;
import com.mauriciotogneri.momowars.server.BaseController;

import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("api")
public class CreateMatch extends BaseController
{
    @POST
    @Path("v1/matches")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@CookieParam(COOKIE_SESSION) String session,
                               Entity entity) throws Exception
    {
        return process(() -> response(session, entity));
    }

    private Response response(String session, Entity entity) throws Exception
    {
        checkIfNotEmpty(session);
        checkIfNotEmpty(entity);

        Account account = validateSession(session);

        try
        {
            Match newMatch = matchService.createMatch(entity.maxPlayers, entity.mapId, account.id);

            playerService.create(account.id, newMatch.id());

            Match match = matchService.getMatch(newMatch.id(), account.id);

            return response(CREATED, match);
        }
        catch (MapNotFoundException e)
        {
            throw new InvalidParametersException(e);
        }
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