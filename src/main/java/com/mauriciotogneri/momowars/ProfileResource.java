package com.mauriciotogneri.momowars;

import com.mauriciotogneri.jerry.EndPoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.OK;

@Path("/")
public class ProfileResource extends EndPoint
{
    @GET
    @Path("v1/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile()
    {
        Profile profile = new Profile(456L, "email@email.com", "Name");

        return response(OK, profile);
    }

    @PUT
    @Path("v1/profile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProfile(Profile newProfile)
    {
        if (newProfile.isValid())
        {
            Profile profile = new Profile(123L, "email@email.com", "Name");

            return response(OK, newProfile);
        }
        else
        {
            return response(BAD_REQUEST);
        }
    }
}