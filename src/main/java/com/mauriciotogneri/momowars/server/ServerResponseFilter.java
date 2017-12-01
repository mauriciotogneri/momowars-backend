package com.mauriciotogneri.momowars.server;

import com.mauriciotogneri.jerry.logging.RequestLogger;
import com.mauriciotogneri.jerry.logging.ResponseLogger;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.repository.log.LogDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class ServerResponseFilter implements ContainerResponseFilter
{
    @Context
    private HttpServletRequest servletRequest;

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response)
    {
        logResponseInConsole(response);
        logRequestResponseInDatabase(request, response);
    }

    private void logRequestResponseInDatabase(ContainerRequestContext request, ContainerResponseContext response)
    {
        try
        {
            DatabaseConnection connection = new DatabaseConnection();

            try
            {
                LogDao logDao = new LogDao(connection);
                logDao.create(
                        servletRequest.getRemoteAddr(),
                        RequestLogger.log(request).trim(),
                        ResponseLogger.log(response).trim());

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
            // TODO: create exception log entry
        }
    }

    private void logResponseInConsole(ContainerResponseContext response)
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