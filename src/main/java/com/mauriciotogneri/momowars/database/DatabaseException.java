package com.mauriciotogneri.momowars.database;

public class DatabaseException extends Exception
{
    public DatabaseException()
    {
        super();
    }

    public DatabaseException(Exception e)
    {
        super(e);
    }
}