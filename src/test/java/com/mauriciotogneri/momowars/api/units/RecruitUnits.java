package com.mauriciotogneri.momowars.api.units;

import com.mauriciotogneri.jsonschema.annotations.Minimum;
import com.mauriciotogneri.momowars.api.units.RecruitUnits.DataParameter;
import com.mauriciotogneri.momowars.api.units.RecruitUnits.PathParameter;
import com.mauriciotogneri.momowars.model.constants.UnitType;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.PUT;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.NO_CONTENT;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/games/{gameId}/players/{playerId}/cells/{cellId}/queue",
        method = PUT,
        description = "Enqueues the command to recruit of the units"
)
@Parameters(
        path = PathParameter.class,
        data = DataParameter.class
)
@Responses({
        @Response(
                code = NO_CONTENT,
                description = "Successful operation"
        ),
        @Response(
                code = BAD_REQUEST,
                description = "Parameters missing or invalid"
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session token not valid"
        ),
        @Response(
                code = NOT_FOUND,
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