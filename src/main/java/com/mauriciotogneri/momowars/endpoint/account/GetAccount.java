package com.mauriciotogneri.momowars.endpoint.account;

import com.mauriciotogneri.momowars.api.Api;
import com.mauriciotogneri.momowars.api.BaseEndPoint;
import com.mauriciotogneri.momowars.dao.AccountDao;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.DatabaseException;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.exceptions.AccountNotFoundException;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/api")
public class GetAccount extends BaseEndPoint
{
    @GET
    @Path("/v1/account")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@HeaderParam(Api.HEADER_SESSION_TOKEN) String sessionToken) throws Exception
    {
        return process(connection -> getAccount(connection, sessionToken));
    }

    private Response getAccount(DatabaseConnection connection, String sessionToken) throws DatabaseException
    {
        try
        {
            AccountDao accountDao = new AccountDao(connection);
            Account account = accountDao.bySessionToken(sessionToken);

            return response(OK, account);
        }
        catch (AccountNotFoundException e)
        {
            return response(UNAUTHORIZED);
        }
    }
}