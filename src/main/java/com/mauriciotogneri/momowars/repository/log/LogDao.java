package com.mauriciotogneri.momowars.repository.log;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.jerry.http.HttpRequest;
import com.mauriciotogneri.jerry.http.HttpResponse;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.LogQueries;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class LogDao
{
    private final DatabaseConnection connection;

    public LogDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public long create(String ip, HttpRequest request) throws DatabaseException
    {
        InsertQuery query = connection.insertQuery(LogQueries.INSERT);

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp timestamp = new Timestamp(now.getTime());

        return query.execute(
                timestamp,
                ip,
                request.method(),
                request.path(),
                request.headers().toString(),
                request.entity()
        );
    }

    public void addResponse(Long id, HttpResponse response, int duration) throws DatabaseException
    {
        UpdateQuery query = connection.updateQuery(LogQueries.UPDATE);

        query.execute(
                response.status(),
                response.headers().toString(),
                response.entity(),
                duration,
                id
        );
    }
}