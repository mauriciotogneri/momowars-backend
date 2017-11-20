package com.mauriciotogneri.momowars.endpoint.account;

import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.momowars.Api;
import com.mauriciotogneri.momowars.database.SQL;
import com.mauriciotogneri.momowars.database.queries.SelectQuery;
import com.mauriciotogneri.momowars.model.Account;

import java.sql.ResultSet;

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
        SelectQuery query = new SelectQuery(SQL.Account.SELECT_BY_SESSION_TOKEN);

        ResultSet resultSet = query.execute(sessionToken);

        if (resultSet.next())
        {
            Account account = new Account(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );

            return response(OK, account);
        }
        else
        {
            return response(UNAUTHORIZED);
        }
    }
}