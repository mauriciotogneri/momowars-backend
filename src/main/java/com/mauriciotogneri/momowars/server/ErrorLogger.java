package com.mauriciotogneri.momowars.server;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.repository.error.ErrorDao;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorLogger
{
    public static void log(Exception exception)
    {
        logInConsole(exception);
        logInDatabase(exception);
    }

    private static void logInConsole(Exception exception)
    {
        ConsoleLogger.logException(exception);
    }

    private static void logInDatabase(Exception exception)
    {
        try
        {
            DatabaseConnection connection = new DatabaseConnection();

            try
            {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                exception.printStackTrace(printWriter);

                ErrorDao errorDao = new ErrorDao(connection);
                errorDao.create(stringWriter.toString());

                connection.commit();
            }
            catch (Exception e)
            {
                connection.rollback();
            }
            finally
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            // ignore
        }
    }
}