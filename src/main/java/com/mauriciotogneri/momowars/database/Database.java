package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;
import com.mauriciotogneri.momowars.database.SQL.TableQueries;
import com.mauriciotogneri.momowars.utils.Resources;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class Database
{
    private static HikariDataSource connectionPool;

    private static final int CONNECTION_POOL_SIZE = 3;

    public static synchronized Connection newConnection()
    {
        try
        {
            if (connectionPool == null)
            {
                connectionPool = dataSource();
            }

            return connectionPool.getConnection();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e);
        }
    }

    private static HikariDataSource dataSource() throws Exception
    {
        connectionPool = new HikariDataSource();
        connectionPool.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
        connectionPool.setMaximumPoolSize(CONNECTION_POOL_SIZE);
        connectionPool.setAutoCommit(false);

        initialize(connectionPool.getConnection());

        return connectionPool;
    }

    private static void initialize(Connection connection) throws Exception
    {
        execute(connection, TableQueries.ACCOUNT);
    }

    private static void execute(Connection connection, String sqlFile) throws Exception
    {
        String query = Resources.content(sqlFile);
        connection.createStatement().execute(query);
    }
}