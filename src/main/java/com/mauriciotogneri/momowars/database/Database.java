package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.javautils.Resource;
import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;
import com.mauriciotogneri.momowars.database.SQL.IndexQueries;
import com.mauriciotogneri.momowars.database.SQL.RelationshipQueries;
import com.mauriciotogneri.momowars.database.SQL.TableQueries;
import com.mauriciotogneri.momowars.database.SQL.TypeQueries;
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
        execute(connection, TypeQueries.TYPES);

        execute(connection, TableQueries.MAP);
        execute(connection, TableQueries.CELL);
        execute(connection, TableQueries.ACCOUNT);
        execute(connection, TableQueries.GAME);
        execute(connection, TableQueries.PLAYER);
        execute(connection, TableQueries.UNIT);
        execute(connection, TableQueries.QUEUE);

        execute(connection, IndexQueries.ACCOUNT_SESSION);

        execute(connection, RelationshipQueries.ACCOUNT_GAMES);
        execute(connection, RelationshipQueries.GAME_PLAYERS);
        execute(connection, RelationshipQueries.MAP_CELLS);
        execute(connection, RelationshipQueries.PLAYER_QUEUES);
        execute(connection, RelationshipQueries.PLAYER_UNITS);

        connection.commit();
    }

    private static void execute(Connection connection, String sqlFile) throws Exception
    {
        String query = Resource.string(sqlFile);
        connection.createStatement().execute(query);
    }
}