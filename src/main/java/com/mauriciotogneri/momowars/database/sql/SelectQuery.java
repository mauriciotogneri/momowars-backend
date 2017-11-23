package com.mauriciotogneri.momowars.database.sql;

import com.mauriciotogneri.javautils.Resource;
import com.mauriciotogneri.momowars.database.DatabaseException;

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

            TypedResultSet<T> typedResultSet = new TypedResultSet<>(rows, clazz);

            QueryResult<T> result = typedResultSet.rows();

            Resource.close(rows);
            Resource.close(statement);

            return result;
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }
}