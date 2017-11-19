package com.mauriciotogneri.momowars.endpoint.account;

import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.momowars.database.Database;
import com.mauriciotogneri.momowars.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;

@Path("/api/")
public class GetAccount extends EndPoint
{
    @GET
    @Path("v1/account")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile() throws Exception
    {
        Account account = null;

        Connection connection = Database.connection();

        PreparedStatement statement = connection.prepareStatement("SELECT * from example");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            account = new Account(456L, "email@email.com", resultSet.getString(2));
        }

        return response(OK, account);
    }
}