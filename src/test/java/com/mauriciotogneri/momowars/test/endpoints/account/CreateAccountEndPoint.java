package com.mauriciotogneri.momowars.test.endpoints.account;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.momowars.api.endpoints.accounts.CreateAccount;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class CreateAccountEndPoint extends BaseEndPoint implements CreateAccount
{
    public CreateAccountEndPoint()
    {
        super(new EndPointDefinition(CreateAccount.class));
    }

    public ApiResult execute(String email, String password, String nickname) throws Exception
    {
        DataParameter data = new DataParameter();
        data.email = email;
        data.password = password;
        data.nickname = nickname;

        ApiRequest.Builder builder = request();
        builder.body(new JsonBodyParameter(data));
        builder.response(jsonResponse());

        return process(builder);
    }
}