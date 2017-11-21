package com.mauriciotogneri.momowars.database.sql;

import com.mauriciotogneri.momowars.database.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SelectQuery<T> extends Query
{
    private final Class<T> clazz;

    public SelectQuery(Connection connection, String queryFile, Class<T> clazz)
    {
        super(connection, queryFile);

        this.clazz = clazz;
    }

    public QueryResult<T> execute(Object... parameters) throws DatabaseException
    {
        try
        {
            PreparedStatement statement = preparedStatement(parameters);

            return new QueryResult<>(statement.executeQuery(), clazz);
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }
}