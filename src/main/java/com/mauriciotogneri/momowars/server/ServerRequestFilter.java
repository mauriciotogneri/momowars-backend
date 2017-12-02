package com.mauriciotogneri.momowars.server;

import com.mauriciotogneri.jerry.http.HttpRequest;
import com.mauriciotogneri.momowars.logger.ConsoleLogger;
import com.mauriciotogneri.momowars.logger.DatabaseLogger;
import com.mauriciotogneri.momowars.logger.ErrorLogger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class ServerRequestFilter implements ContainerRequestFilter
{
    @Context
    private HttpServletRequest servletRequest;

    public static final String PROPERTY_TIME_START = "time.start";
    public static final String PROPERTY_LOG_ID = "log.id";

    @Override
    public void filter(ContainerRequestContext request)
    {
        try
        {
            request.setProperty(PROPERTY_TIME_START, System.currentTimeMillis());

            HttpRequest httpRequest = new HttpRequest(request);
            ConsoleLogger.logRequest(httpRequest);
            long id = DatabaseLogger.logRequest(servletRequest.getRemoteAddr(), httpRequest);
            request.setProperty(PROPERTY_LOG_ID, id);
        }
        catch (Exception e)
        {
            ErrorLogger.log(e);
        }
    }
}