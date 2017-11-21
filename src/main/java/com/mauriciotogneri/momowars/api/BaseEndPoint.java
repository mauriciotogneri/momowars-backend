package com.mauriciotogneri.momowars.api;

import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.DatabaseException;

import javax.ws.rs.core.Response;

public class BaseEndPoint extends EndPoint
{
    protected Response process(EndPointImplementation endPoint)
    {
        DatabaseConnection connection = new DatabaseConnection();

        try
        {
            Response response = endPoint.response(connection);

            connection.commit();

            return response;
        }
        catch (DatabaseException e)
        {
            throw connection.rollback();
        }
        finally
        {
            connection.close();
        }
    }

    public interface EndPointImplementation
    {
        Response response(DatabaseConnection connection) throws DatabaseException;
    }
}