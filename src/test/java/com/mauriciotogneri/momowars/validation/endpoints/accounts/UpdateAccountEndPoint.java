package com.mauriciotogneri.momowars.validation.endpoints.accounts;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.momowars.api.accounts.UpdateAccount;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;
import com.mauriciotogneri.momowars.validation.EndPointDefinition;

public class UpdateAccountEndPoint extends BaseEndPoint implements UpdateAccount
{
    public UpdateAccountEndPoint()
    {
        super(new EndPointDefinition(UpdateAccount.class));
    }

    public ApiResult execute(String sessionToken, String password, String nickname) throws Exception
    {
        DataParameter data = new DataParameter();
        data.password = password;
        data.nickname = nickname;

        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.body(new JsonBodyParameter(data));
        builder.response(jsonResponse());

        return process(builder);
    }
}