package com.mauriciotogneri.momowars.repository;

import com.mauriciotogneri.inquiry.queries.DeleteQuery;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;

public class BaseDao
{
    protected final DatabaseConnection connection;

    public BaseDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    protected <T> SelectQuery<T> select(String queryPath, Class<T> clazz)
    {
        return connection.selectQuery(queryPath, clazz);
    }

    protected InsertQuery insert(String queryPath)
    {
        return connection.insertQuery(queryPath);
    }

    protected UpdateQuery update(String queryPath)
    {
        return connection.updateQuery(queryPath);
    }

    protected DeleteQuery delete(String queryPath)
    {
        return connection.deleteQuery(queryPath);
    }
}