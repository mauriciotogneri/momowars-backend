package com.mauriciotogneri.momowars.test.endpoints.match;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.endpoints.matches.LeaveMatch;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class LeaveMatchEndPoint extends BaseEndPoint implements LeaveMatch
{
    public LeaveMatchEndPoint()
    {
        super(new EndPointDefinition(LeaveMatch.class));
    }

    public ApiResult execute(String session, Long matchId, Long playerId) throws Exception
    {
        PathParameter path = new PathParameter();
        path.matchId = matchId;
        path.playerId = playerId;

        ApiRequest.Builder builder = request();
        builder.header(sessionCookie(session));
        builder.path(new PathParameters(path), PATH_FORMAT);
        builder.response(jsonResponse());

        return process(builder);
    }
}