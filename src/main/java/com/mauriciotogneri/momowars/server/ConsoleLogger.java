package com.mauriciotogneri.momowars.server;

import com.mauriciotogneri.jerry.http.HttpRequest;
import com.mauriciotogneri.jerry.http.HttpResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ConsoleLogger
{
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    static
    {
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static void logRequest(HttpRequest httpRequest)
    {
        try
        {
            StringBuilder builder = new StringBuilder();

            builder.append(String.format(
                    "<<< %s%n",
                    dateFormat.format(new Date())));

            builder.append(httpRequest.toString());
            builder.append(String.format("%n%n"));

            log(builder.toString());
        }
        catch (Exception e)
        {
            ErrorLogger.log(e);
        }
    }

    public static void logResponse(HttpResponse response)
    {
        try
        {
            StringBuilder builder = new StringBuilder();

            builder.append(String.format(
                    ">>> %s%n",
                    dateFormat.format(new Date())));

            builder.append(response.toString());
            builder.append(String.format("%n%n"));

            log(builder.toString());
        }
        catch (Exception e)
        {
            ErrorLogger.log(e);
        }
    }

    private static void log(String text)
    {
        System.out.print(text);
        System.out.flush();
    }
}