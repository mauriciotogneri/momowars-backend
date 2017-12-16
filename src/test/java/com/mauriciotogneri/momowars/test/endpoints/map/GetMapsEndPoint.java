package com.mauriciotogneri.momowars.test.endpoints.map;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.api.endpoints.maps.GetMaps;
import com.mauriciotogneri.momowars.test.endpoints.BaseEndPoint;
import com.mauriciotogneri.momowars.test.endpoints.EndPointDefinition;

public class GetMapsEndPoint extends BaseEndPoint implements GetMaps
{
    public GetMapsEndPoint()
    {
        super(new EndPointDefinition(GetMaps.class));
    }

    public ApiResult execute(String session) throws Exception
    {
        ApiRequest.Builder builder = request();
        builder.header(sessionCookie(session));
        builder.response(jsonResponse());

        return process(builder);
    }
}