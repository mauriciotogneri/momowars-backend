package com.mauriciotogneri.momowars.repository.log;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.jerry.http.HttpRequest;
import com.mauriciotogneri.jerry.http.HttpResponse;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.LogQueries;
import com.mauriciotogneri.momowars.util.Now;

public class LogDao
{
    private final DatabaseConnection connection;

    public LogDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public long create(String ip, HttpRequest request) throws DatabaseException
    {
        InsertQuery query = connection.insertQuery(LogQueries.CREATE);

        return query.execute(
                Now.timestamp(),
                ip,
                request.method(),
                request.path(),
                request.headers().toString()
        );
    }

    public void addResponse(Long id, HttpResponse response, int duration) throws DatabaseException
    {
        UpdateQuery query = connection.updateQuery(LogQueries.UPDATE);

        query.execute(
                response.status(),
                response.headers().toString(),
                duration,
                id
        );
    }
}