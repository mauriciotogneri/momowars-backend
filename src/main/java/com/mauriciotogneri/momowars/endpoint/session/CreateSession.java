package com.mauriciotogneri.momowars.endpoint.session;

import com.mauriciotogneri.javautils.Strings;
import com.mauriciotogneri.jerry.EntityProvider;
import com.mauriciotogneri.jerry.EntityProvider.EntityObject;
import com.mauriciotogneri.momowars.api.Api;
import com.mauriciotogneri.momowars.api.BaseEndPoint;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.services.SessionService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.OK;

@Path("api")
public class CreateSession extends BaseEndPoint
{
    @POST
    @Path("v1/session")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSession(SessionEntity sessionRequest) throws Exception
    {
        return process(connection -> createSession(connection, sessionRequest));
    }

    private Response createSession(DatabaseConnection connection, SessionEntity entity) throws Exception
    {
        String sessionToken = SessionService.createSession(connection, entity.email, entity.password);

        return Response
                .status(OK)
                .header(Api.HEADER_SESSION_TOKEN, sessionToken)
                .build();
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class SessionEntityProvider extends EntityProvider<SessionEntity>
    {
        public SessionEntityProvider()
        {
            super(SessionEntity.class);
        }
    }

    private static class SessionEntity implements EntityObject
    {
        private String email;
        private String password;

        @Override
        public boolean isValid()
        {
            return Strings.isNotEmpty(email) && Strings.isNotEmpty(password);
        }
    }
}