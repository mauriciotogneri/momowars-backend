package com.mauriciotogneri.momowars.model.exceptions;

public class AccountAlreadyExistsException extends Exception
{
    public AccountAlreadyExistsException(Exception e)
    {
        super(e);
    }
}