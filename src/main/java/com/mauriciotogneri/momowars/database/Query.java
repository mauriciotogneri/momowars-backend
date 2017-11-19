package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.momowars.utils.Resources;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Query
{
    private final String queryFile;

    public Query(String queryFile)
    {
        this.queryFile = queryFile;
    }

    protected String query() throws Exception
    {
        return Resources.content(queryFile);
    }

    protected PreparedStatement preparedStatement() throws Exception
    {
        Connection connection = Database.connection();

        return connection.prepareStatement(query());
    }

    protected PreparedStatement preparedStatement(QueryParameter<?>... parameters) throws Exception
    {
        PreparedStatement statement = preparedStatement();

        for (int i = 0; i < parameters.length; i++)
        {
            QueryParameter<?> parameter = parameters[i];
            int index = i + 1;

            if (parameter.isString())
            {
                statement.setString(index, parameter.value());
            }
            else if (parameter.isBoolean())
            {
                statement.setBoolean(index, parameter.value());
            }
            else if (parameter.isInteger())
            {
                statement.setInt(index, parameter.value());
            }
            else if (parameter.isLong())
            {
                statement.setLong(index, parameter.value());
            }
            else if (parameter.isFloat())
            {
                statement.setFloat(index, parameter.value());
            }
            else if (parameter.isDouble())
            {
                statement.setDouble(index, parameter.value());
            }
        }

        return statement;
    }
}