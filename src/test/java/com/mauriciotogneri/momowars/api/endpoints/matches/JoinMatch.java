package com.mauriciotogneri.momowars.api.endpoints.matches;

import com.mauriciotogneri.momowars.api.endpoints.matches.JoinMatch.PathParameter;
import com.mauriciotogneri.momowars.api.model.headers.SessionToken;
import com.mauriciotogneri.momowars.api.model.matches.Match;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;
import com.mauriciotogneri.stewie.types.MimeType;

import static com.mauriciotogneri.stewie.types.Method.POST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNPROCESSABLE_ENTITY;

@EndPoint(
        path = "/v1/matches/{matchId}/players",
        method = POST,
        description = "Joins the given match"
)
@Parameters(
        header = SessionToken.class,
        path = PathParameter.class
)
@Responses({
        @Response(
                code = CREATED,
                description = "Successful operation",
                produces = MimeType.JSON,
                type = Match.class
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session token not valid"
        ),
        @Response(
                code = NOT_FOUND,
                description = "Invalid match id"
        ),
        @Response(
                code = UNPROCESSABLE_ENTITY,
                description = "Either: the player is already in the match" +
                        " or the match is playing" +
                        " or the match is finished."
        )
})
public interface JoinMatch
{
    class PathParameter
    {
        public Long matchId;
    }
}