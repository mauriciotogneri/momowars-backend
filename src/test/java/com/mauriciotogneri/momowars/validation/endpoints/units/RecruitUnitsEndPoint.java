package com.mauriciotogneri.momowars.validation.endpoints.units;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.momowars.api.units.RecruitUnits;
import com.mauriciotogneri.momowars.model.constants.UnitType;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;
import com.mauriciotogneri.momowars.validation.EndPointDefinition;

public class RecruitUnitsEndPoint extends BaseEndPoint implements RecruitUnits
{
    public RecruitUnitsEndPoint()
    {
        super(new EndPointDefinition(RecruitUnits.class));
    }

    public ApiResult execute(String sessionToken, UnitType type, Integer quantity) throws Exception
    {
        DataParameter data = new DataParameter();
        data.type = type;
        data.quantity = quantity;

        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.body(new JsonBodyParameter(data));
        builder.response(jsonResponse());

        return process(builder);
    }
}