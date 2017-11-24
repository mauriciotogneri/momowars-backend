package com.mauriciotogneri.momowars.api;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class BaseEndPoint extends EndPoint
{
    protected Response process(EndPointImplementation endPoint) throws Exception
    {
        try
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
                connection.rollback();

                throw new InternalServerErrorException(e);
            }
            finally
            {
                connection.close();
            }
        }
        catch (InternalServerErrorException e)
        {
            e.printStackTrace();

            throw e;
        }
        catch (WebApplicationException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            throw e;
        }
    }

    public interface EndPointImplementation
    {
        Response response(DatabaseConnection connection) throws DatabaseException;
    }
}