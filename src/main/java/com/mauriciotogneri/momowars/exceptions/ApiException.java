package com.mauriciotogneri.momowars.exceptions;

public class ApiException extends Exception
{
    public ApiException(Exception e)
    {
        super(e);
    }

    public ApiException()
    {
    }
}
