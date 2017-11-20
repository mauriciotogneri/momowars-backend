package com.mauriciotogneri.momowars.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectQuery extends Query
{
    public SelectQuery(String queryFile)
    {
        super(queryFile);
    }

    public ResultSet execute(Object... parameters) throws Exception
    {
        PreparedStatement statement = preparedStatement(parameters);

        return statement.executeQuery();
    }
}