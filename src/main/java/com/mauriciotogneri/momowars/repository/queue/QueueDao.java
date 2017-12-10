package com.mauriciotogneri.momowars.repository.queue;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
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
}