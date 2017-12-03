package com.mauriciotogneri.momowars.test.endpoints.players;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.endpoints.players.JoinGame;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class JoinGameEndPoint extends BaseEndPoint implements JoinGame
{
    public JoinGameEndPoint()
    {
        super(new EndPointDefinition(JoinGame.class));
    }

    public ApiResult execute(String sessionToken, Long gameId) throws Exception
    {
        PathParameter path = new PathParameter();
        path.gameId = gameId;

        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.path(new PathParameters(path), PATH_FORMAT);
        builder.response(jsonResponse());

        return process(builder);
    }
}