package com.mauriciotogneri.momowars.test.endpoints.session;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.momowars.api.endpoints.sessions.CreateSessionGoogle;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class CreateSessionGoogleEndPoint extends BaseEndPoint implements CreateSessionGoogle
{
    public CreateSessionGoogleEndPoint()
    {
        super(new EndPointDefinition(CreateSessionGoogle.class));
    }

    public ApiResult execute(String token) throws Exception
    {
        DataParameter data = new DataParameter();
        data.token = token;

        ApiRequest.Builder builder = request();
        builder.header(CONTENT_TYPE_JSON);
        builder.body(new JsonBodyParameter(data));
        builder.response(emptyResponse());

        return process(builder);
    }
}