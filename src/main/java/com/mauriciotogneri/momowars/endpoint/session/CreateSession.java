package com.mauriciotogneri.momowars.endpoint.session;

import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.jerry.EntityProvider;
import com.mauriciotogneri.momowars.Api;
import com.mauriciotogneri.momowars.database.SQL.AccountQueries;
import com.mauriciotogneri.momowars.database.rows.AccountRow;
import com.mauriciotogneri.momowars.database.sql.QueryResult;
import com.mauriciotogneri.momowars.database.sql.SelectQuery;
import com.mauriciotogneri.momowars.utils.SHA;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/api")
public class CreateSession extends EndPoint
{
    @POST
    @Path("/v1/session")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSession(CreateSessionRequest account) throws Exception
    {
        SelectQuery<AccountRow> query = new SelectQuery<>(AccountQueries.SELECT_BY_CREDENTIALS, AccountRow.class);

        QueryResult<AccountRow> result = query.execute(account.email, SHA.sha512(account.password));

        if (result.hasRows())
        {
            ResponseBuilder builder = Response.status(OK);
            builder.header(Api.HEADER_SESSION_TOKEN, newSessionToken());

            return builder.build();
        }
        else
        {
            return response(UNAUTHORIZED);
        }
    }

    public static String newSessionToken() throws Exception
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