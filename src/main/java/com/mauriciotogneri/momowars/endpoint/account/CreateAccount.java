package com.mauriciotogneri.momowars.endpoint.account;

import com.mauriciotogneri.javautils.Strings;
import com.mauriciotogneri.jerry.EntityProvider;
import com.mauriciotogneri.jerry.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.api.BaseEndPoint;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.model.Account;
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
public class CreateAccount extends BaseEndPoint
{
    @POST
    @Path("v1/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountEntity accountRequest) throws Exception
    {
        return process(connection -> createAccount(connection, accountRequest));
    }

    private Response createAccount(DatabaseConnection connection, AccountEntity entity) throws Exception
    {
        Account account = AccountService.createAccount(connection, entity.email, entity.nickname, entity.password);

        return response(CREATED, account);
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