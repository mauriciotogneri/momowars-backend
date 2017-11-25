package com.mauriciotogneri.momowars.validation.endpoints.units;

import com.mauriciotogneri.apivalidator.api.ApiRequest;
import com.mauriciotogneri.apivalidator.api.ApiResult;
import com.mauriciotogneri.apivalidator.parameters.body.JsonBodyParameter;
import com.mauriciotogneri.momowars.api.units.MoveUnits;
import com.mauriciotogneri.momowars.model.constants.MovementType;
import com.mauriciotogneri.momowars.validation.BaseEndPoint;
import com.mauriciotogneri.momowars.validation.EndPointDefinition;

public class MoveUnitsEndPoint extends BaseEndPoint implements MoveUnits
{
    public MoveUnitsEndPoint()
    {
        super(new EndPointDefinition(MoveUnits.class));
    }

    public ApiResult execute(String sessionToken, MovementType movement) throws Exception
    {
        DataParameter data = new DataParameter();
        data.movement = movement;

        ApiRequest.Builder builder = request();
        builder.header(HEADER_SESSION_TOKEN, sessionToken);
        builder.body(new JsonBodyParameter(data));
        builder.response(jsonResponse());

        return process(builder);
    }
}