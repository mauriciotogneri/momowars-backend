package com.mauriciotogneri.momowars.controllers.session;

import com.mauriciotogneri.javautils.Strings;
import com.mauriciotogneri.jerry.controller.EntityProvider;
import com.mauriciotogneri.jerry.controller.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.services.SessionService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("api")
public class CreateSessionGoogle extends BaseController
{
    @POST
    @Path("v1/session")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response controller(Entity sessionRequest) throws Exception
    {
        return process(connection -> controller(connection, sessionRequest));
    }

    private Response controller(DatabaseConnection connection, Entity entity) throws Exception
    {
        checkIfNotEmpty(entity);

        String sessionToken = SessionService.createSessionGoogle(connection, entity.token);

        return Response
                .status(CREATED)
                .header(HEADER_SESSION_TOKEN, sessionToken)
                .build();
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class Entity extends EntityProvider<Entity> implements EntityObject
    {
        private String token;

        public Entity()
        {
            super(Entity.class);
        }

        @Override
        public boolean isValid()
        {
            return Strings.isNotEmpty(token);
        }
    }
}