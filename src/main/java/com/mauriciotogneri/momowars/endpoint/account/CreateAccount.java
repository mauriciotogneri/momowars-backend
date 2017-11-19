package com.mauriciotogneri.momowars.endpoint.account;

import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.jerry.EntityProvider;
import com.mauriciotogneri.momowars.database.InsertQuery;
import com.mauriciotogneri.momowars.database.InsertQuery.InsertParameter;
import com.mauriciotogneri.momowars.database.Queries.Account;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/api/")
public class CreateAccount extends EndPoint
{
    @POST
    @Path("v1/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(CreateAccountRequest account) throws Exception
    {
        InsertParameter[] parameters = new InsertParameter[4];
        parameters[0] = InsertParameter.string(account.email);
        parameters[1] = InsertParameter.string(account.nickname);
        parameters[2] = InsertParameter.string(account.password);
        parameters[3] = InsertParameter.string("xxx");

        InsertQuery query = new InsertQuery(Account.CREATE_ACCOUNT);

        try
        {
            query.execute(parameters);

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

    public static class CreateAccountRequest
    {
        public String email;
        public String nickname;
        public String password;
    }
}