package com.mauriciotogneri.momowars.api.sessions;

import com.mauriciotogneri.jsonschema.annotations.Format;
import com.mauriciotogneri.momowars.api.sessions.CreateSession.DataParameter;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;

import static com.mauriciotogneri.stewie.types.Method.POST;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.CREATED;
import static com.mauriciotogneri.stewie.types.StatusCode.NOT_FOUND;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/session",
        method = POST,
        description = "Creates a new session"
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
                description = "Invalid password"
        ),
        @Response(
                code = NOT_FOUND,
                description = "Invalid email"
        )
})
public interface CreateSession
{
    class DataParameter
    {
        @Format("email")
        public String email;

        public String password;
    }
}