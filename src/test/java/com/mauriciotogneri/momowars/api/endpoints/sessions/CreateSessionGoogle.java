package com.mauriciotogneri.momowars.api.endpoints.sessions;

import com.mauriciotogneri.momowars.api.endpoints.sessions.CreateSessionEmail.DataParameter;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.POST;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/session/google",
        method = POST,
        description = "Creates a new session with a Google token"
)
@Parameters(
        data = DataParameter.class
)
@Responses({
        @Response(
                code = CREATED,
                description = "Successful operation"
        ),
        @Response(
                code = BAD_REQUEST,
                description = "Parameters missing or invalid"
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Invalid token"
        )
})
public interface CreateSessionGoogle
{
    class DataParameter
    {
        public String token;
    }
}