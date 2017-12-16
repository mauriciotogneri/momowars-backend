package com.mauriciotogneri.momowars.api.endpoints.matches;

import com.mauriciotogneri.momowars.api.endpoints.matches.GetMatch.PathParameter;
import com.mauriciotogneri.momowars.api.model.headers.SessionToken;
import com.mauriciotogneri.momowars.api.model.matches.Match;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;
import com.mauriciotogneri.stewie.types.MimeType;

import static com.mauriciotogneri.stewie.types.Method.GET;
import static com.mauriciotogneri.stewie.types.StatusCode.FORBIDDEN;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/matches/{matchId}",
        method = GET,
        description = "Returns a match by its id"
)
@Parameters(
        header = SessionToken.class,
        path = PathParameter.class
)
@Responses({
        @Response(
                code = OK,
                description = "Successful operation",
                produces = MimeType.JSON,
                type = Match.class
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session token not valid"
        ),
        @Response(
                code = FORBIDDEN,
                description = "Not allowed to retrieve the match"
        )
})
public interface GetMatch
{
    class PathParameter
    {
        public Long matchId;
    }
}