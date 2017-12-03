package com.mauriciotogneri.momowars.test.endpoints.games;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.path.PathParameters;
import com.mauriciotogneri.momowars.api.endpoints.games.GetGame;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class GetGameEndPoint extends BaseEndPoint implements GetGame
{
    public GetGameEndPoint()
    {
        super(new EndPointDefinition(GetGame.class));
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