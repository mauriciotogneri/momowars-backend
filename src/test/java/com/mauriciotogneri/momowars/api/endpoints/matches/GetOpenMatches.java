package com.mauriciotogneri.momowars.api.endpoints.matches;

import com.mauriciotogneri.momowars.api.model.Match;
import com.mauriciotogneri.momowars.api.model.SessionCookie;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;
import com.mauriciotogneri.stewie.types.MimeType;

import static com.mauriciotogneri.stewie.types.Method.GET;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/matches",
        method = GET,
        description = "Returns a list of all the open matches"
)
@Parameters(
        header = SessionCookie.class
)
@Responses({
        @Response(
                code = OK,
                description = "Successful operation",
                produces = MimeType.JSON,
                type = Match[].class
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session not valid"
        )
})
public interface GetOpenMatches
{
}