package com.mauriciotogneri.momowars.database.sql;

import com.mauriciotogneri.momowars.database.DatabaseException;
import com.mauriciotogneri.momowars.utils.Resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            ResultSet rows = statement.executeQuery();

            TypedResultSet<T> result = new TypedResultSet<>(rows, clazz);

            Resources.close(rows);
            Resources.close(statement);

            return result.rows();
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }
}