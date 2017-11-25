package com.mauriciotogneri.momowars.validation.endpoints.maps;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.momowars.api.maps.GetMaps;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;
import com.mauriciotogneri.momowars.validation.EndPointDefinition;

public class GetMapsEndPoint extends BaseEndPoint implements GetMaps
{
    public GetMapsEndPoint()
    {
        super(new EndPointDefinition(GetMaps.class));
    }

    public ApiResult execute(String sessionToken) throws Exception
    {
        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.response(jsonResponse());

        return process(builder);
    }
}