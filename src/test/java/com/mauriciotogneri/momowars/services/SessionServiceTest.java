package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;
import com.mauriciotogneri.momowars.validation.endpoints.session.CreateSessionEndPoint;

public class SessionServiceTest extends BaseService
{
    private static final CreateSessionEndPoint createSessionEndPoint = new CreateSessionEndPoint();

    public String createSession(int statusCode, String email, String password) throws Exception
    {
        ApiResult result = createSessionEndPoint.execute(email, password);
        checkHttpStatus(statusCode, result);

        return result.response().header(BaseEndPoint.HEADER_SESSION_TOKEN);
    }
}