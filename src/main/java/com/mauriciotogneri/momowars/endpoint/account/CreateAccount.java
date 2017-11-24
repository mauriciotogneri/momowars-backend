package com.mauriciotogneri.momowars.endpoint.account;

import com.mauriciotogneri.javautils.Strings;
import com.mauriciotogneri.jerry.EntityProvider;
import com.mauriciotogneri.jerry.EntityProvider.EntityObject;
import com.mauriciotogneri.jerry.exceptions.client.BadRequestException;
import com.mauriciotogneri.jerry.exceptions.client.ConflictException;
import com.mauriciotogneri.momowars.api.BaseEndPoint;
import com.mauriciotogneri.momowars.dao.AccountDao;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.endpoint.session.CreateSession;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.exceptions.AccountAlreadyExistsException;
import com.mauriciotogneri.momowars.validators.EmailValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/api")
public class CreateAccount extends BaseEndPoint
{
    @POST
    @Path("/v1/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(CreateAccountRequest accountRequest) throws Exception
    {
        return process(connection -> createAccount(connection, accountRequest));
    }

    private Response createAccount(DatabaseConnection connection, CreateAccountRequest accountRequest)
    {
        try
        {
            if (!EmailValidator.isValid(accountRequest.email))
            {
                throw new BadRequestException();
            }

            AccountDao accountDao = new AccountDao(connection);
            Account account = accountDao.create(accountRequest.email,
                                                accountRequest.nickname,
                                                accountRequest.password,
                                                CreateSession.newSessionToken());

            return response(CREATED, account);
        }
        catch (AccountAlreadyExistsException e)
        {
            throw new ConflictException(e);
        }
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class CreateAccountProvider extends EntityProvider<CreateAccountRequest>
    {
        public CreateAccountProvider()
        {
            super(CreateAccountRequest.class);
        }
    }

    private static class CreateAccountRequest implements EntityObject
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