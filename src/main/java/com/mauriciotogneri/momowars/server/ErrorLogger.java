package com.mauriciotogneri.momowars.server;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.email.Email;
import com.mauriciotogneri.momowars.repository.error.ErrorDao;
import com.mauriciotogneri.momowars.util.Now;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorLogger
{
    public static void log(Exception exception)
    {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        String stacktrace = stringWriter.toString();

        logInDatabase(stacktrace);
        sendAlert(stacktrace);
    }

    private static void logInDatabase(String stacktrace)
    {
        try
        {
            DatabaseConnection connection = new DatabaseConnection();

            try
            {
                ErrorDao errorDao = new ErrorDao(connection);
                errorDao.create(stacktrace);

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

    private static void sendAlert(String stacktrace)
    {
        try
        {
            Email email = new Email(
                    "error@momowars.com",
                    "mauricio.togneri@gmail.com",
                    String.format("Momowars error: %s", Now.timestamp()),
                    String.format("<pre>%s</pre>", stacktrace));
            email.send();
        }
        catch (Exception e)
        {
            // ignore
        }
    }
}