package com.mauriciotogneri.momowars.exceptions;

public class AccountAlreadyExistsException extends Exception
{
    public AccountAlreadyExistsException(Exception e)
    {
        super(e);
    }
}