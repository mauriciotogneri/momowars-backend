package com.mauriciotogneri.momowars.database.sql;

import com.mauriciotogneri.momowars.database.Database;
import com.mauriciotogneri.momowars.utils.Resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Query
{
    private final String queryFile;
    private final boolean returnGeneratedKeys;

    public Query(String queryFile, boolean returnGeneratedKeys)
    {
        this.queryFile = queryFile;
        this.returnGeneratedKeys = returnGeneratedKeys;
    }

    public Query(String queryFile)
    {
        this(queryFile, false);
    }

    protected String query() throws Exception
    {
        return Resources.content(queryFile);
    }

    protected PreparedStatement preparedStatement() throws Exception
    {
        Connection connection = Database.connection();

        if (returnGeneratedKeys)
        {
            return connection.prepareStatement(query(), Statement.RETURN_GENERATED_KEYS);
        }
        else
        {
            return connection.prepareStatement(query());
        }
    }

    protected PreparedStatement preparedStatement(Object... parameters) throws Exception
    {
        PreparedStatement statement = preparedStatement();

        for (int i = 0; i < parameters.length; i++)
        {
            Object parameter = parameters[i];
            int index = i + 1;

            if (parameter.getClass().equals(String.class))
            {
                statement.setString(index, (String) parameter);
            }
            else if (parameter.getClass().equals(Boolean.class))
            {
                statement.setBoolean(index, (Boolean) parameter);
            }
            else if (parameter.getClass().equals(Integer.class))
            {
                statement.setInt(index, (Integer) parameter);
            }
            else if (parameter.getClass().equals(Long.class))
            {
                statement.setLong(index, (Long) parameter);
            }
            else if (parameter.getClass().equals(Float.class))
            {
                statement.setFloat(index, (Float) parameter);
            }
            else if (parameter.getClass().equals(Double.class))
            {
                statement.setDouble(index, (Double) parameter);
            }
        }

        return statement;
    }
}