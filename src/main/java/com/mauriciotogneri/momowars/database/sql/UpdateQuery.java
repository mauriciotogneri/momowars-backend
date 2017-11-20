package com.mauriciotogneri.momowars.database.sql;

import java.sql.PreparedStatement;

public class UpdateQuery extends Query
{
    public UpdateQuery(String queryFile)
    {
        super(queryFile, true);
    }

    public int execute(Object... parameters) throws Exception
    {
        PreparedStatement statement = preparedStatement(parameters);

        return statement.executeUpdate();
    }
}