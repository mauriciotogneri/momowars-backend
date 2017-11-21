package com.mauriciotogneri.momowars.database.sql;

import com.mauriciotogneri.momowars.database.DatabaseException;

import java.sql.PreparedStatement;

public class UpdateQuery extends Query
{
    public UpdateQuery(String queryFile)
    {
        super(queryFile, true);
    }

    public int execute(Object... parameters) throws DatabaseException
    {
        try
        {
            PreparedStatement statement = preparedStatement(parameters);

            return statement.executeUpdate();
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }
}