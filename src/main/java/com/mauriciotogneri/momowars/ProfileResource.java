package com.mauriciotogneri.momowars;

import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.momowars.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

@Path("/")
public class ProfileResource extends EndPoint
{
    @GET
    @Path("v1/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile() throws Exception
    {
        Profile profile = null;

        Connection connection = Database.connection();

        PreparedStatement statement = connection.prepareStatement("SELECT * from example");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            profile = new Profile(456L, "email@email.com", resultSet.getString(2));
        }

        return response(OK, profile);
    }

    @POST
    @Path("v1/profile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(Profile profile) throws Exception
    {
        Connection connection = Database.connection();

        PreparedStatement statement = connection.prepareStatement("SELECT * from example");
        ResultSet resultSet = statement.executeQuery();

        return response(CREATED, profile);
    }
}