package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.momowars.utils.Resources;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Query
{
    protected final String queryFile;

    public Query(String queryFile)
    {
        this.queryFile = queryFile;
    }

    protected String query() throws Exception
    {
        return Resources.content(queryFile);
    }

    protected PreparedStatement preparedStatement() throws Exception
    {
        Connection connection = Database.connection();

        return connection.prepareStatement(query());
    }
}