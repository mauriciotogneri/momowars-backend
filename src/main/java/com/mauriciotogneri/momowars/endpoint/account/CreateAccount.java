package com.mauriciotogneri.momowars.endpoint.account;

import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.jerry.EntityProvider;
import com.mauriciotogneri.momowars.database.SQL;
import com.mauriciotogneri.momowars.database.queries.InsertQuery;
import com.mauriciotogneri.momowars.endpoint.session.CreateSession;
import com.mauriciotogneri.momowars.utils.SHA;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/api")
public class CreateAccount extends EndPoint
{
    @POST
    @Path("/v1/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(CreateAccountRequest account) throws Exception
    {
        InsertQuery query = new InsertQuery(SQL.Account.INSERT);

        try
        {
            query.execute(
                    account.email,
                    account.nickname,
                    SHA.sha512(account.password),
                    CreateSession.newSessionId()
            );

            // TODO: read account

            return response(CREATED, account);
        }
        catch (Exception e)
        {
            return response(CONFLICT);
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

    private static class CreateAccountRequest
    {
        private String email;
        private String nickname;
        private String password;
    }
}