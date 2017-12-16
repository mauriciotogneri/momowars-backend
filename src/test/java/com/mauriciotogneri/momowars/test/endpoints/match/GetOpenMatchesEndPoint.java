package com.mauriciotogneri.momowars.test.endpoints.match;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.api.endpoints.matches.GetOpenMatches;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class GetOpenMatchesEndPoint extends BaseEndPoint implements GetOpenMatches
{
    public GetOpenMatchesEndPoint()
    {
        super(new EndPointDefinition(GetOpenMatches.class));
    }

    public ApiResult execute(String session) throws Exception
    {
        ApiRequest.Builder builder = request();
        builder.header(sessionCookie(session));
        builder.response(jsonResponse());

        return process(builder);
    }
}