package com.mauriciotogneri.momowars.tasks;

import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.logger.ErrorLogger;
import com.mauriciotogneri.momowars.util.TaskPool;

public abstract class Task implements Runnable
{
    public void submit()
    {
        TaskPool.submit(this);
    }

    @Override
    public void run()
    {
        try
        {
            DatabaseConnection connection = new DatabaseConnection();

            try
            {
                process(connection);

                connection.commit();
            }
            catch (Exception e)
            {
                connection.rollback();

                throw e;
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

    public abstract void process(DatabaseConnection connection) throws Exception;
}