package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.javautils.Resource;
import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;

import java.sql.Connection;

public class DatabaseConnection
{
    private final Connection connection;

    public DatabaseConnection()
    {
        this.connection = Database.newConnection();
    }

    public Connection connection()
    {
        return connection;
    }

    public void commit() throws DatabaseException
    {
        try
        {
            connection.commit();
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }

    public void close()
    {
        Resource.close(connection);
    }

    public void rollback()
    {
        try
        {
            connection.rollback();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e);
        }
    }
}