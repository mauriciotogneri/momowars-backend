package com.mauriciotogneri.momowars.test.endpoints.player;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.endpoints.players.EndTurn;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class EndTurnEndPoint extends BaseEndPoint implements EndTurn
{
    public EndTurnEndPoint()
    {
        super(new EndPointDefinition(EndTurn.class));
    }

    public ApiResult execute(String session, Long playerId) throws Exception
    {
        PathParameter path = new PathParameter();
        path.playerId = playerId;

        ApiRequest.Builder builder = request();
        builder.header(sessionCookie(session));
        builder.path(new PathParameters(path), PATH_FORMAT);
        builder.response(jsonResponse());

        return process(builder);
    }
}