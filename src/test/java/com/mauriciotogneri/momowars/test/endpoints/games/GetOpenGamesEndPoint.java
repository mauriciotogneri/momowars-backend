package com.mauriciotogneri.momowars.test.endpoints.games;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.api.endpoints.games.GetOpenGames;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class GetOpenGamesEndPoint extends BaseEndPoint implements GetOpenGames
{
    public GetOpenGamesEndPoint()
    {
        super(new EndPointDefinition(GetOpenGames.class));
    }

    public ApiResult execute(String sessionToken) throws Exception
    {
        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.response(jsonResponse());

        return process(builder);
    }
}