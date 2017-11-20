package com.mauriciotogneri.momowars.endpoint.account;

import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.momowars.Api;
import com.mauriciotogneri.momowars.database.SQL.AccountQueries;
import com.mauriciotogneri.momowars.database.rows.AccountRow;
import com.mauriciotogneri.momowars.database.sql.QueryResult;
import com.mauriciotogneri.momowars.database.sql.SelectQuery;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/api")
public class GetAccount extends EndPoint
{
    @GET
    @Path("/v1/account")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@HeaderParam(Api.HEADER_SESSION_TOKEN) String sessionToken) throws Exception
    {
        SelectQuery<AccountRow> query = new SelectQuery<>(AccountQueries.SELECT_BY_SESSION_TOKEN, AccountRow.class);

        QueryResult<AccountRow> result = query.execute(sessionToken);

        if (result.hasRows())
        {
            return response(OK, result.row().account());
        }
        else
        {
            return response(UNAUTHORIZED);
        }
    }
}