package com.mauriciotogneri.momowars;

import com.mauriciotogneri.momowars.model.exceptions.AccountNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<AccountNotFoundException>
{
    public Response toResponse(AccountNotFoundException exception)
    {
        return Response.status(404).
                entity(exception.getMessage()).
                type("text/plain").
                build();
    }
}