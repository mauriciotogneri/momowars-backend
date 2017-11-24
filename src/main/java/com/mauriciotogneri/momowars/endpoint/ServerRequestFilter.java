package com.mauriciotogneri.momowars.endpoint;

import com.mauriciotogneri.jerry.logging.RequestLogger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class ServerRequestFilter implements ContainerRequestFilter
{
    @Override
    public void filter(ContainerRequestContext request)
    {
        logRequest(request);
    }

    private void logRequest(ContainerRequestContext request)
    {
        try
        {
            StringBuilder builder = new StringBuilder();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            builder.append(String.format(
                    "<<< %s%n%n",
                    dateFormat.format(new Date())));

            builder.append(RequestLogger.log(request));

            System.out.print(builder.toString());
            System.out.flush();
        }
        catch (Exception e)
        {
            // ignore
        }
    }
}