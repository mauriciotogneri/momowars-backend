package com.mauriciotogneri.momowars.database.sql;

import java.sql.PreparedStatement;

public class SelectQuery<T> extends Query
{
    private final Class<T> clazz;

    public SelectQuery(String queryFile, Class<T> clazz)
    {
        super(queryFile);

        this.clazz = clazz;
    }

    public QueryResult<T> execute(Object... parameters) throws Exception
    {
        PreparedStatement statement = preparedStatement(parameters);

        return new QueryResult<>(statement.executeQuery(), clazz);
    }
}