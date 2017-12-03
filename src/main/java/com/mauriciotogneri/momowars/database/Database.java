package com.mauriciotogneri.momowars.database;

import com.zaxxer.hikari.HikariDataSource;

import org.flywaydb.core.Flyway;

import java.sql.Connection;

public class Database
{
    private final HikariDataSource connectionPool;

    public Database(String url, int poolSize) throws Exception
    {
        this.connectionPool = connectionPool(url, poolSize);
    }

    public void migrate()
    {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setDataSource(connectionPool);
        flyway.setLocations(String.format("%s/migrations", getClass().getPackage().getName().replace(".", "/")));
        flyway.migrate();
    }

    public synchronized Connection newConnection() throws Exception
    {
        return connectionPool.getConnection();
    }

    private HikariDataSource connectionPool(String url, int poolSize)
    {
        HikariDataSource connectionPool = new HikariDataSource();
        connectionPool.setJdbcUrl(url);
        connectionPool.setMaximumPoolSize(poolSize);
        connectionPool.setAutoCommit(false);

        return connectionPool;
    }
}