package com.mauriciotogneri.momowars.repository.error;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.ErrorQueries;
import com.mauriciotogneri.momowars.util.Now;

public class ErrorDao
{
    private final DatabaseConnection connection;

    public ErrorDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public void create(String stacktrace) throws DatabaseException
    {
        InsertQuery query = connection.insertQuery(ErrorQueries.CREATE);

        query.execute(
                Now.timestamp(),
                stacktrace
        );
    }
}