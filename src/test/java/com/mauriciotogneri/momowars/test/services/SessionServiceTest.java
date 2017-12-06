package com.mauriciotogneri.momowars.test.services;

import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.session.CreateSessionEmailEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.session.CreateSessionGoogleEndPoint;

public class SessionServiceTest extends BaseService
{
    private static final CreateSessionEmailEndPoint createSessionEndPoint = new CreateSessionEmailEndPoint();
    private static final CreateSessionGoogleEndPoint createGoogleEndPoint = new CreateSessionGoogleEndPoint();

    public String createSessionEmail(int statusCode, String email, String password) throws Exception
    {
        ApiResult result = createSessionEndPoint.execute(email, password);
        checkHttpStatus(statusCode, result);

        return result.response().header(BaseEndPoint.HEADER_SESSION_TOKEN);
    }

    public String createSessionGoogle(int statusCode, String token) throws Exception
    {
        ApiResult result = createGoogleEndPoint.execute(token);
        checkHttpStatus(statusCode, result);

        return result.response().header(BaseEndPoint.HEADER_SESSION_TOKEN);
    }
}