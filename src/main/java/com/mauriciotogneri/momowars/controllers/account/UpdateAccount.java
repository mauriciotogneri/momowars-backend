package com.mauriciotogneri.momowars.controllers.account;

import com.mauriciotogneri.javautils.Strings;
import com.mauriciotogneri.jerry.controller.EntityProvider;
import com.mauriciotogneri.jerry.controller.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.services.AccountService;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
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
    public Response updateAccount(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken, AccountEntity accountRequest) throws Exception
    {
        return process(connection -> updateAccount(connection, sessionToken, accountRequest));
    }

    private Response updateAccount(DatabaseConnection connection, String sessionToken, AccountEntity entity) throws Exception
    {
        checkIfNotEmpty(entity);

        Account account = AccountService.updateAccount(connection, sessionToken, entity.password, entity.nickname);

        return response(OK, account);
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class AccountEntityProvider extends EntityProvider<AccountEntity>
    {
        public AccountEntityProvider()
        {
            super(AccountEntity.class);
        }
    }

    private static class AccountEntity implements EntityObject
    {
        private String nickname;
        private String password;

        @Override
        public boolean isValid()
        {
            return Strings.isNotEmpty(nickname) && Strings.isNotEmpty(password);
        }
    }
}