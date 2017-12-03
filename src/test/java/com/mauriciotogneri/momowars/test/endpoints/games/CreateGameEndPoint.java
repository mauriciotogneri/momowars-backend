package com.mauriciotogneri.momowars.test.endpoints.games;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.momowars.api.endpoints.games.CreateGame;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class CreateGameEndPoint extends BaseEndPoint implements CreateGame
{
    public CreateGameEndPoint()
    {
        super(new EndPointDefinition(CreateGame.class));
    }

    public ApiResult execute(String sessionToken, Long mapId, Integer maxPlayers) throws Exception
    {
        DataParameter data = new DataParameter();
        data.mapId = mapId;
        data.maxPlayers = maxPlayers;

        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.body(new JsonBodyParameter(data));
        builder.response(jsonResponse());

        return process(builder);
    }
}