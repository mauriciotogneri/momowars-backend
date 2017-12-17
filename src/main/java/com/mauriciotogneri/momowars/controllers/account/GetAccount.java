package com.mauriciotogneri.momowars.controllers.account;

import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.AccountMatches;
import com.mauriciotogneri.momowars.server.BaseController;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;

@Path("api")
public class GetAccount extends BaseController
{
    @GET
    @Path("v1/account")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@CookieParam(COOKIE_SESSION) String session) throws Exception
    {
        return process(() -> response(session));
    }

    private Response response(String session) throws Exception
    {
        checkIfNotEmpty(session);

        Account account = accountService.getAccount(session);
        AccountMatches accountMatches = accountService.matches(account.id);

        return response(OK, account.json(accountMatches));
    }
}