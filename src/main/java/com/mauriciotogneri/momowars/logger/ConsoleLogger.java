package com.mauriciotogneri.momowars.logger;

import com.mauriciotogneri.jerry.http.HttpRequest;
import com.mauriciotogneri.jerry.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleLogger
{
    private static final Logger logger = LoggerFactory.getLogger("");

    public static void logRequest(HttpRequest httpRequest)
    {
        try
        {
            logger.info(String.format("%n%s%n", httpRequest.toString()));
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
            logger.info(String.format("%n%s%n", response.toString()));
        }
        catch (Exception e)
        {
            ErrorLogger.log(e);
        }
    }
}