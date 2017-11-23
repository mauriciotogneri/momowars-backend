package com.mauriciotogneri.momowars.database.sql;

import com.mauriciotogneri.javautils.Resource;
import com.mauriciotogneri.momowars.database.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsertQuery extends Query
{
    public InsertQuery(Connection connection, String queryFile)
    {
        super(connection, queryFile, Statement.RETURN_GENERATED_KEYS);
    }

    public long execute(Object... parameters) throws DatabaseException
    {
        try
        {
            PreparedStatement statement = preparedStatement(parameters);
            statement.executeUpdate();

            ResultSet rows = statement.getGeneratedKeys();

            if (rows.next())
            {
                long id = rows.getLong(1);

                Resource.close(rows);
                Resource.close(statement);

                return id;
            }
            else
            {
                throw new DatabaseException();
            }
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }
}