package com.mauriciotogneri.momowars.api.maps;

import com.mauriciotogneri.momowars.model.games.Map;
import com.mauriciotogneri.momowars.model.headers.SessionToken;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;
import com.mauriciotogneri.stewie.types.MimeType;

import static com.mauriciotogneri.stewie.types.Method.GET;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/maps",
        method = GET,
        description = "Returns the list of available maps"
)
@Parameters(
        header = SessionToken.class
)
@Responses({
        @Response(
                code = OK,
                description = "Successful operation",
                produces = MimeType.JSON,
                type = Map[].class
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session token not valid"
        )
})
public interface GetMaps
{
}