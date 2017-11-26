package com.mauriciotogneri.momowars.database;

import com.zaxxer.hikari.HikariDataSource;

import org.flywaydb.core.Flyway;

import java.sql.Connection;

import javax.sql.DataSource;

public class Database
{
    private final HikariDataSource connectionPool;

    public Database(String url, int poolSize) throws Exception
    {
        this.connectionPool = connectionPool(url, poolSize);
        migrate(connectionPool);
    }

    private void migrate(DataSource dataSource)
    {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setDataSource(dataSource);
        flyway.setLocations(String.format("%s/migrations", getClass().getPackage().getName().replace(".", "/")));
        flyway.migrate();
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