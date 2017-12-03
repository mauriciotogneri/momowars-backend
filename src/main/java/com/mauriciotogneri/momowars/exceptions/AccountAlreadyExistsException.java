package com.mauriciotogneri.momowars.exceptions;

public class AccountAlreadyExistsException extends ApiException
{
    public AccountAlreadyExistsException(Exception e)
    {
        super(e);
    }
}