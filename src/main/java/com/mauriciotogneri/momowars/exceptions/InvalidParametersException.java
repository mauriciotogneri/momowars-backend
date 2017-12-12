package com.mauriciotogneri.momowars.exceptions;

public class InvalidParametersException extends ApiException
{
    public InvalidParametersException(Exception e)
    {
        super(e);
    }
}