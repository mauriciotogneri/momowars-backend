package com.mauriciotogneri.momowars.database;

import java.sql.PreparedStatement;

public class InsertQuery extends Query
{
    public InsertQuery(String queryFile)
    {
        super(queryFile);
    }

    public int execute(Object... parameters) throws Exception
    {
        PreparedStatement statement = preparedStatement(parameters);

        return statement.executeUpdate();
    }
}