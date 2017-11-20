package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.momowars.utils.Resources;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;

import javax.sql.DataSource;

public class Database
{
    private static DataSource connectionPool;

    private static final int CONNECTION_POOL_SIZE = 3;

    public static synchronized Connection connection() throws Exception
    {
        if (connectionPool == null)
        {
            connectionPool = dataSource();
        }

        return connectionPool.getConnection();
    }

    public static DataSource dataSource() throws Exception
    {
        BasicDataSource connectionPool = new BasicDataSource();
        connectionPool.setDriverClassName("org.postgresql.Driver");
        connectionPool.setUrl(System.getenv("JDBC_DATABASE_URL"));
        connectionPool.setInitialSize(CONNECTION_POOL_SIZE);

        initialize(connectionPool.getConnection());

        return connectionPool;
    }

    private static void initialize(Connection connection) throws Exception
    {
        execute(connection, SQL.Table.ACCOUNT);
    }

    private static void execute(Connection connection, String sqlFile) throws Exception
    {
        String query = Resources.content(sqlFile);
        connection.createStatement().execute(query);
    }
}