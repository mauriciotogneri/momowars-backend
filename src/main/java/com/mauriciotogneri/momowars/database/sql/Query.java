package com.mauriciotogneri.momowars.database.sql;

import com.mauriciotogneri.momowars.database.DatabaseException;
import com.mauriciotogneri.momowars.utils.Resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Query
{
    private final Connection connection;
    private final String queryFile;
    private final boolean returnGeneratedKeys;

    public Query(Connection connection, String queryFile, boolean returnGeneratedKeys)
    {
        this.connection = connection;
        this.queryFile = queryFile;
        this.returnGeneratedKeys = returnGeneratedKeys;
    }

    public Query(Connection connection, String queryFile)
    {
        this(connection, queryFile, false);
    }

    private String query()
    {
        return Resources.content(queryFile);
    }

    private PreparedStatement preparedStatement() throws DatabaseException, SQLException
    {
        if (returnGeneratedKeys)
        {
            return connection.prepareStatement(query(), Statement.RETURN_GENERATED_KEYS);
        }
        else
        {
            return connection.prepareStatement(query());
        }
    }

    protected PreparedStatement preparedStatement(Object... parameters) throws DatabaseException
    {
        try
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
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }
}