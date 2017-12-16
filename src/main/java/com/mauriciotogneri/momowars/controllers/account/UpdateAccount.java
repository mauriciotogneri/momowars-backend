package com.mauriciotogneri.momowars.controllers.account;

import com.mauriciotogneri.javautils.Strings;
import com.mauriciotogneri.jerry.controller.EntityProvider;
import com.mauriciotogneri.jerry.controller.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.server.BaseController;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.OK;

@Path("api")
public class UpdateAccount extends BaseController
{
    @PATCH
    @Path("v1/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@CookieParam(COOKIE_SESSION) String session,
                               Entity accountRequest) throws Exception
    {
        return process(() -> response(session, accountRequest));
    }

    private Response response(String session, Entity entity) throws Exception
    {
        checkIfNotEmpty(entity);

        Account account = accountService.updateAccount(session, entity.password, entity.nickname);

        return response(OK, account);
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class Entity extends EntityProvider<Entity> implements EntityObject
    {
        private String nickname;
        private String password;

        public Entity()
        {
            super(Entity.class);
        }

        @Override
        public boolean isValid()
        {
            return Strings.isNotEmpty(nickname) && Strings.isNotEmpty(password);
        }
    }
}