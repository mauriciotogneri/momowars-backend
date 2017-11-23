package com.mauriciotogneri.momowars.database.sql;

import com.mauriciotogneri.javautils.Resource;
import com.mauriciotogneri.momowars.database.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateQuery extends Query
{
    public UpdateQuery(Connection connection, String queryFile)
    {
        super(connection, queryFile);
    }

    public int execute(Object... parameters) throws DatabaseException
    {
        try
        {
            PreparedStatement statement = preparedStatement(parameters);

            int rowsAffected = statement.executeUpdate();

            Resource.close(statement);

            return rowsAffected;
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }
}