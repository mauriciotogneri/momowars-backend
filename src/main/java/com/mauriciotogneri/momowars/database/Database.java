package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.javautils.Resource;
import com.mauriciotogneri.momowars.database.SQL.IndexQueries;
import com.mauriciotogneri.momowars.database.SQL.RelationshipQueries;
import com.mauriciotogneri.momowars.database.SQL.TableQueries;
import com.mauriciotogneri.momowars.database.SQL.TypeQueries;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.Statement;

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

        initialize(connectionPool.getConnection());

        return connectionPool;
    }

    private void initialize(Connection connection) throws Exception
    {
        execute(connection, TypeQueries.CELL_TYPE);
        execute(connection, TypeQueries.GAME_STATUS);
        execute(connection, TypeQueries.MOVEMENT_TYPE);
        execute(connection, TypeQueries.PLAYER_STATUS);
        execute(connection, TypeQueries.UNIT_TYPE);

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

    private void execute(Connection connection, String sqlFile) throws Exception
    {
        try (Statement statement = connection.createStatement())
        {
            statement.execute(Resource.string(sqlFile));
        }
    }
}