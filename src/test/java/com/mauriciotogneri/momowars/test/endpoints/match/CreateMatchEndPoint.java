package com.mauriciotogneri.momowars.test.endpoints.match;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.momowars.api.endpoints.matches.CreateMatch;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class CreateMatchEndPoint extends BaseEndPoint implements CreateMatch
{
    public CreateMatchEndPoint()
    {
        super(new EndPointDefinition(CreateMatch.class));
    }

    public ApiResult execute(String session, Long mapId, Integer maxPlayers) throws Exception
    {
        DataParameter data = new DataParameter();
        data.mapId = mapId;
        data.maxPlayers = maxPlayers;

        ApiRequest.Builder builder = request();
        builder.header(sessionCookie(session));
        builder.body(new JsonBodyParameter(data));
        builder.response(jsonResponse());

        return process(builder);
    }
}