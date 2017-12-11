package com.mauriciotogneri.momowars.server;

import com.mauriciotogneri.javautils.Strings;
import com.mauriciotogneri.jerry.http.HttpRequest;
import com.mauriciotogneri.momowars.app.AppParameters;
import com.mauriciotogneri.momowars.logger.ConsoleLogger;
import com.mauriciotogneri.momowars.logger.DatabaseLogger;
import com.mauriciotogneri.momowars.logger.ErrorLogger;
import com.mauriciotogneri.momowars.security.IpLimitation;

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

    private final IpLimitation ipLimitation = new IpLimitation(AppParameters.REQUESTS_PER_MINUTE);

    @Override
    public void filter(ContainerRequestContext request)
    {
        try
        {
            String ip = ip(request);
            ipLimitation.check(request, ip);

            request.setProperty(PROPERTY_TIME_START, System.currentTimeMillis());

            HttpRequest httpRequest = new HttpRequest(request);
            ConsoleLogger.logRequest(httpRequest);
            long logId = DatabaseLogger.logRequest(ip, httpRequest);
            request.setProperty(PROPERTY_LOG_ID, logId);
        }
        catch (Exception e)
        {
            ErrorLogger.log(e);
        }
    }

    private String ip(ContainerRequestContext request)
    {
        String ip = request.getHeaderString("X-Forwarded-For");

        if (Strings.isEmpty(ip))
        {
            ip = servletRequest.getRemoteAddr();
        }

        return ip;
    }
}