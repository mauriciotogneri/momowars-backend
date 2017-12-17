package com.mauriciotogneri.momowars.repository;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.DeleteQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.QueueQueries;
import com.mauriciotogneri.momowars.model.Queue;

import java.util.Arrays;
import java.util.List;

public class QueueDao
{
    private final DatabaseConnection connection;

    public QueueDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public List<Queue> byCell(Long cellId) throws DatabaseException
    {
        return Arrays.asList();
    }

    public void delete(Long playerId) throws DatabaseException
    {
        DeleteQuery query = connection.deleteQuery(QueueQueries.DELETE);

        int rowsAffected = query.execute(playerId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }
}