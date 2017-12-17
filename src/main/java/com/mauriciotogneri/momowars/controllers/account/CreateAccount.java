package com.mauriciotogneri.momowars.controllers.account;

import com.mauriciotogneri.javautils.Strings;
import com.mauriciotogneri.jerry.controller.EntityProvider;
import com.mauriciotogneri.jerry.controller.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.AccountMatches;
import com.mauriciotogneri.momowars.server.BaseController;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("api")
public class CreateAccount extends BaseController
{
    @POST
    @Path("v1/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(Entity accountRequest) throws Exception
    {
        return process(() -> response(accountRequest));
    }

    private Response response(Entity entity) throws Exception
    {
        checkIfNotEmpty(entity);

        Account account = accountService.createAccount(entity.email, entity.nickname, entity.password);
        AccountMatches accountMatches = accountService.matches(account.id);

        return response(CREATED, account.json(accountMatches));
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class Entity extends EntityProvider<Entity> implements EntityObject
    {
        private String email;
        private String nickname;
        private String password;

        public Entity()
        {
            super(Entity.class);
        }

        @Override
        public boolean isValid()
        {
            return Strings.isNotEmpty(email) && Strings.isNotEmpty(nickname) && Strings.isNotEmpty(password);
        }
    }
}