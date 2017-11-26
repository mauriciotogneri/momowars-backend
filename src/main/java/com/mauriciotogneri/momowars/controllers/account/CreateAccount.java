package com.mauriciotogneri.momowars.controllers.account;

import com.mauriciotogneri.javautils.Strings;
import com.mauriciotogneri.jerry.controller.EntityProvider;
import com.mauriciotogneri.jerry.controller.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.services.AccountService;

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
    public Response createAccount(Entity accountRequest) throws Exception
    {
        return process(connection -> createAccount(connection, accountRequest));
    }

    private Response createAccount(DatabaseConnection connection, Entity entity) throws Exception
    {
        checkIfNotEmpty(entity);

        Account account = AccountService.createAccount(connection, entity.email, entity.nickname, entity.password);

        return response(CREATED, account);
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
        private String email;
        private String nickname;
        private String password;

        @Override
        public boolean isValid()
        {
            return Strings.isNotEmpty(email) && Strings.isNotEmpty(nickname) && Strings.isNotEmpty(password);
        }
    }
}