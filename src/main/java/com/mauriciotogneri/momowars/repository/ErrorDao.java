package com.mauriciotogneri.momowars.repository;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.ErrorQueries;
import com.mauriciotogneri.momowars.util.Now;

public class ErrorDao extends BaseDao
{
    public ErrorDao(DatabaseConnection connection)
    {
        super(connection);
    }

    public void create(String stacktrace) throws DatabaseException
    {
        InsertQuery query = insert(ErrorQueries.CREATE);

        query.execute(Now.timestamp(),
                      stacktrace);
    }
}