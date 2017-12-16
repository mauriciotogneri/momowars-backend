package com.mauriciotogneri.momowars.api.endpoints.units;

import com.mauriciotogneri.jsonschema.annotations.Minimum;
import com.mauriciotogneri.momowars.api.endpoints.units.RecruitUnits.DataParameter;
import com.mauriciotogneri.momowars.api.endpoints.units.RecruitUnits.PathParameter;
import com.mauriciotogneri.momowars.api.model.headers.SessionToken;
import com.mauriciotogneri.momowars.types.UnitType;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.PUT;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.NO_CONTENT;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNPROCESSABLE_ENTITY;

@EndPoint(
        path = "/v1/games/{gameId}/players/{playerId}/cells/{cellId}/queue",
        method = PUT,
        description = "Enqueues the command to recruit of the units"
)
@Parameters(
        header = SessionToken.class,
        path = PathParameter.class,
        data = DataParameter.class
)
@Responses({
        @Response(
                code = NO_CONTENT,
                description = "Successful operation"
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session token not valid"
        ),
        @Response(
                code = BAD_REQUEST,
                description = "Parameters missing or invalid"
        ),
        @Response(
                code = UNPROCESSABLE_ENTITY,
                description = "Invalid gameId or cellId"
        )
})
public interface RecruitUnits
{
    class PathParameter
    {
        public Long gameId;

        public Long playerId;

        public Long cellId;
    }

    class DataParameter
    {
        public UnitType type;

        @Minimum(1)
        public Integer quantity;
    }
}