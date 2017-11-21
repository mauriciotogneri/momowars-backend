package com.mauriciotogneri.momowars.endpoint.session;

import com.mauriciotogneri.jerry.EntityProvider;
import com.mauriciotogneri.momowars.api.Api;
import com.mauriciotogneri.momowars.api.BaseEndPoint;
import com.mauriciotogneri.momowars.dao.AccountDao;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.DatabaseException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.exceptions.AccountNotFoundException;
import com.mauriciotogneri.momowars.utils.SHA;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/api")
public class CreateSession extends BaseEndPoint
{
    @POST
    @Path("/v1/session")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSession(CreateSessionRequest sessionRequest)
    {
        return process(connection -> createSession(connection, sessionRequest));
    }

    private Response createSession(DatabaseConnection connection, CreateSessionRequest sessionRequest) throws DatabaseException
    {
        try
        {
            AccountDao accountDao = new AccountDao(connection);
            Account account = accountDao.byCredentials(sessionRequest.email, sessionRequest.password);

            String sessionToken = newSessionToken();

            accountDao.updateSessionToken(account.id(), sessionToken);

            return Response
                    .status(OK)
                    .header(Api.HEADER_SESSION_TOKEN, sessionToken)
                    .build();
        }
        catch (AccountNotFoundException e)
        {
            return response(UNAUTHORIZED);
        }
    }

    public static String newSessionToken()
    {
        return SHA.sha512(UUID.randomUUID().toString());
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class CreateSessionProvider extends EntityProvider<CreateSessionRequest>
    {
        public CreateSessionProvider()
        {
            super(CreateSessionRequest.class);
        }
    }

    private static class CreateSessionRequest
    {
        private String email;
        private String password;
    }
}