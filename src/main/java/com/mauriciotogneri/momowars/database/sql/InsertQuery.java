package com.mauriciotogneri.momowars.database.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertQuery extends Query
{
    public InsertQuery(String queryFile)
    {
        super(queryFile, true);
    }

    public long execute(Object... parameters) throws Exception
    {
        PreparedStatement statement = preparedStatement(parameters);
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();

        if (resultSet.next())
        {
            return resultSet.getLong(1);
        }
        else
        {
            throw new RuntimeException();
        }
    }
}