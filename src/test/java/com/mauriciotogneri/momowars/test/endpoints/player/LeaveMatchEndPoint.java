package com.mauriciotogneri.momowars.test.endpoints.player;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.endpoints.players.LeaveMatch;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class LeaveMatchEndPoint extends BaseEndPoint implements LeaveMatch
{
    public LeaveMatchEndPoint()
    {
        super(new EndPointDefinition(LeaveMatch.class));
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