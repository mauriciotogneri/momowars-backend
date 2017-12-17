package com.mauriciotogneri.momowars.logger;

import com.mauriciotogneri.jerry.http.HttpRequest;
import com.mauriciotogneri.jerry.http.HttpResponse;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.repository.LogDao;

public class DatabaseLogger
{
    public static long logRequest(String ip, HttpRequest httpRequest)
    {
        try
        {
            DatabaseConnection connection = new DatabaseConnection();

            try
            {
                LogDao logDao = new LogDao(connection);
                long logId = logDao.create(
                        ip,
                        httpRequest);

                connection.commit();

                return logId;
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
            ErrorLogger.log(e);
        }

        return 0;
    }

    public static void logResponse(long logId, HttpResponse httpResponse, int duration)
    {
        try
        {
            DatabaseConnection connection = new DatabaseConnection();

            try
            {
                LogDao logDao = new LogDao(connection);
                logDao.addResponse(
                        logId,
                        httpResponse,
                        duration);

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
            ErrorLogger.log(e);
        }
    }
}