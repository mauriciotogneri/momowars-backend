package com.mauriciotogneri.momowars.controllers.player;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.server.BaseController;
import com.mauriciotogneri.momowars.services.PlayerService;

import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;

@Path("api")
public class EndTurn extends BaseController
{
    @DELETE
    @Path("v1/players/{playerId}/turn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response controller(@HeaderParam(HEADER_SESSION_TOKEN) String sessionToken,
                               @PathParam(PARAM_PLAYER_ID) Long playerId) throws Exception
    {
        return process(connection -> controller(connection, sessionToken, playerId));
    }

    private Response controller(DatabaseConnection connection, String sessionToken, Long playerId) throws Exception
    {
        checkIfNotEmpty(sessionToken);
        checkIfNotEmpty(playerId);

        Account account = validateSessionToken(connection, sessionToken);

        PlayerService.endTurn(connection, playerId, account.id());

        return response(NO_CONTENT);
    }
}