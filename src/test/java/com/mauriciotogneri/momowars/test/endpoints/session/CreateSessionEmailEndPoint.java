package com.mauriciotogneri.momowars.test.endpoints.session;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.momowars.api.endpoints.sessions.CreateSessionEmail;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class CreateSessionEmailEndPoint extends BaseEndPoint implements CreateSessionEmail
{
    public CreateSessionEmailEndPoint()
    {
        super(new EndPointDefinition(CreateSessionEmail.class));
    }

    public ApiResult execute(String email, String password) throws Exception
    {
        DataParameter data = new DataParameter();
        data.email = email;
        data.password = password;

        ApiRequest.Builder builder = request();
        builder.header(CONTENT_TYPE_JSON);
        builder.body(new JsonBodyParameter(data));
        builder.response(emptyResponse());

        return process(builder);
    }
}