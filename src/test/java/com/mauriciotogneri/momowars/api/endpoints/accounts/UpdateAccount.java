package com.mauriciotogneri.momowars.api.endpoints.accounts;

import com.mauriciotogneri.jsonschema.annotations.MaxLength;
import com.mauriciotogneri.momowars.api.endpoints.accounts.UpdateAccount.DataParameter;
import com.mauriciotogneri.momowars.api.model.accounts.Account;
import com.mauriciotogneri.momowars.api.model.headers.ApplicationJson;
import com.mauriciotogneri.stewie.annotations.EndPoint;
import com.mauriciotogneri.stewie.annotations.Parameters;
import com.mauriciotogneri.stewie.annotations.Response;
import com.mauriciotogneri.stewie.annotations.Responses;
import com.mauriciotogneri.stewie.types.MimeType;

import static com.mauriciotogneri.stewie.types.Method.PATCH;
import static com.mauriciotogneri.stewie.types.StatusCode.BAD_REQUEST;
import static com.mauriciotogneri.stewie.types.StatusCode.OK;
import static com.mauriciotogneri.stewie.types.StatusCode.UNAUTHORIZED;

@EndPoint(
        path = "/v1/account",
        method = PATCH,
        description = "Updates a user's account"
)
@Parameters(
        header = ApplicationJson.class,
        data = DataParameter.class
)
@Responses({
        @Response(
                code = OK,
                description = "Successful operation",
                produces = MimeType.JSON,
                type = Account.class
        ),
        @Response(
                code = BAD_REQUEST,
                description = "Parameters missing or invalid"
        ),
        @Response(
                code = UNAUTHORIZED,
                description = "Session token not valid"
        )
})
public interface UpdateAccount
{
    class DataParameter
    {
        public String password;

        @MaxLength(30)
        public String nickname;
    }
}