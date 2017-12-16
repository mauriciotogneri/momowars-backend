package com.mauriciotogneri.momowars.api.endpoints.matches;

import com.mauriciotogneri.jsonschema.annotations.Minimum;
import com.mauriciotogneri.momowars.api.endpoints.matches.CreateMatch.DataParameter;
import com.mauriciotogneri.momowars.api.model.headers.SessionToken;
import com.mauriciotogneri.momowars.api.model.matches.Match;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;
import com.mauriciotogneri.stewie.types.MimeType;

import static com.mauriciotogneri.stewie.types.Method.POST;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNPROCESSABLE_ENTITY;

@EndPoint(
        path = "/v1/matches",
        method = POST,
        description = "Creates a new match"
)
@Parameters(
        header = SessionToken.class,
        data = DataParameter.class
)
@Responses({
        @Response(
                code = CREATED,
                description = "Successful operation",
                produces = MimeType.JSON,
                type = Match.class
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
                code = UNPROCESSABLE_ENTITY,
                description = "The map id doesn't correspond to a valid map"
        )
})
public interface CreateMatch
{
    class DataParameter
    {
        public Long mapId;

        @Minimum(2)
        public Integer maxPlayers;
    }
}