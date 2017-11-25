package com.mauriciotogneri.momowars.database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class Database
{
    private final HikariDataSource connectionPool;

    public Database(String url, int poolSize) throws Exception
    {
        this.connectionPool = connectionPool(url, poolSize);
    }

    public synchronized Connection newConnection() throws Exception
    {
        return connectionPool.getConnection();
    }

    private HikariDataSource connectionPool(String url, int poolSize) throws Exception
    {
        HikariDataSource connectionPool = new HikariDataSource();
        connectionPool.setJdbcUrl(url);
        connectionPool.setMaximumPoolSize(poolSize);
        connectionPool.setAutoCommit(false);

        return connectionPool;
    }
}