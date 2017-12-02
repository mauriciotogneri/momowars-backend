package com.mauriciotogneri.momowars.server;

import com.mauriciotogneri.jerry.http.HttpResponse;
import com.mauriciotogneri.momowars.logger.ConsoleLogger;
import com.mauriciotogneri.momowars.logger.DatabaseLogger;
import com.mauriciotogneri.momowars.logger.ErrorLogger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class ServerResponseFilter implements ContainerResponseFilter
{
    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response)
    {
        try
        {
            Long logId = (Long) request.getProperty(ServerRequestFilter.PROPERTY_LOG_ID);

            if (logId != 0)
            {
                Long timeStart = (Long) request.getProperty(ServerRequestFilter.PROPERTY_TIME_START);
                int duration = (int) (System.currentTimeMillis() - timeStart);

                HttpResponse httpResponse = new HttpResponse(response);
                ConsoleLogger.logResponse(httpResponse);
                DatabaseLogger.logResponse(logId, httpResponse, duration);
            }
        }
        catch (Exception e)
        {
            ErrorLogger.log(e);
        }
    }
}