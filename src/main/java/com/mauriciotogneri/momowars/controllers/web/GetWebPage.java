package com.mauriciotogneri.momowars.controllers.web;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.templates.ExampleTemplate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class GetWebPage extends BaseController
{
    @GET
    @Path("web")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller() throws Exception
    {
        return process(this::controller);
    }

    private Response controller(DatabaseConnection connection) throws Exception
    {
        ExampleTemplate template = new ExampleTemplate("It works!");

        return htmlResponse(template);
    }
}