package com.mauriciotogneri.momowars.test.endpoints.players;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.endpoints.players.LeaveGame;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class LeaveGameEndPoint extends BaseEndPoint implements LeaveGame
{
    public LeaveGameEndPoint()
    {
        super(new EndPointDefinition(LeaveGame.class));
    }

    public ApiResult execute(String sessionToken, Long playerId) throws Exception
    {
        PathParameter path = new PathParameter();
        path.playerId = playerId;

        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.path(new PathParameters(path), PATH_FORMAT);
        builder.response(jsonResponse());

        return process(builder);
    }
}