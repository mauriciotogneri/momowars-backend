package com.mauriciotogneri.momowars.server;

import com.mauriciotogneri.jerry.logging.ResponseLogger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
        logResponse(response);
    }

    private void logResponse(ContainerResponseContext response)
    {
        try
        {
            StringBuilder builder = new StringBuilder();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            builder.append(String.format(
                    ">>> %s%n%n",
                    dateFormat.format(new Date())));

            builder.append(ResponseLogger.log(response));

            System.out.print(builder.toString());
            System.out.flush();
        }
        catch (Exception e)
        {
            // ignore
        }
    }
}