package com.mauriciotogneri.momowars.api.games;

import com.mauriciotogneri.jsonschema.annotations.Minimum;
import com.mauriciotogneri.momowars.api.games.CreateGame.DataParameter;
import com.mauriciotogneri.momowars.model.headers.SessionToken;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.POST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/games",
        method = POST,
        description = "Creates a new game"
)
@Parameters(
        header = SessionToken.class,
        data = DataParameter.class
)
@Responses({
        @Response(
                code = CREATED,
                description = "Successful operation"
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session token not valid"
        )
})
public interface CreateGame
{
    class DataParameter
    {
        public String mapId;

        @Minimum(2)
        public Integer maxPlayers;
    }
}