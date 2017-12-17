package com.mauriciotogneri.momowars.repository;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.jerry.http.HttpRequest;
import com.mauriciotogneri.jerry.http.HttpResponse;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.LogQueries;
import com.mauriciotogneri.momowars.util.Now;

public class LogDao extends BaseDao
{
    public LogDao(DatabaseConnection connection)
    {
        super(connection);
    }

    public long create(String ip, HttpRequest request) throws DatabaseException
    {
        InsertQuery query = insert(LogQueries.CREATE);

        return query.execute(
                Now.timestamp(),
                ip,
                request.method(),
                request.path(),
                request.headers().toString()
        );
    }

    public void addResponse(Long logId, HttpResponse response, int duration) throws DatabaseException
    {
        UpdateQuery query = update(LogQueries.UPDATE);

        query.execute(
                response.status(),
                response.headers().toString(),
                duration,
                logId
        );
    }
}