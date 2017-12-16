package com.mauriciotogneri.momowars.test.endpoints.account;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.api.endpoints.accounts.GetAccount;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class GetAccountEndPoint extends BaseEndPoint implements GetAccount
{
    public GetAccountEndPoint()
    {
        super(new EndPointDefinition(GetAccount.class));
    }

    public ApiResult execute(String session) throws Exception
    {
        ApiRequest.Builder builder = request();
        builder.header(sessionCookie(session));
        builder.response(jsonResponse());

        return process(builder);
    }
}