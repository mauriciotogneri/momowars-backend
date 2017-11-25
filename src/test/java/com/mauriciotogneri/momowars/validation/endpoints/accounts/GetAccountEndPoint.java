package com.mauriciotogneri.momowars.validation.endpoints.accounts;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.api.accounts.GetAccount;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;
import com.mauriciotogneri.momowars.validation.EndPointDefinition;

public class GetAccountEndPoint extends BaseEndPoint implements GetAccount
{
    public GetAccountEndPoint()
    {
        super(new EndPointDefinition(GetAccount.class));
    }

    public ApiResult execute(String sessionToken) throws Exception
    {
        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.response(jsonResponse());

        return process(builder);
    }
}