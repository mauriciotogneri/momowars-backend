package com.mauriciotogneri.momowars.repository.log;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
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

    public void create(String ip, String request, String response) throws DatabaseException
    {
        InsertQuery query = connection.insertQuery(LogQueries.INSERT);

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp timestamp = new Timestamp(now.getTime());

        query.execute(
                timestamp,
                ip,
                request,
                response
        );
    }
}