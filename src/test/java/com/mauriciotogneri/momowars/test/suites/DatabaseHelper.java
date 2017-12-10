package com.mauriciotogneri.momowars.test.suites;

import com.mauriciotogneri.momowars.database.Database;
import com.mauriciotogneri.momowars.database.DatabaseConnection;

public class DatabaseHelper
{
    public static void main(String[] args) throws Exception
    {
        String databaseUrl = System.getenv("JDBC_DATABASE_URL");
        int connectionPoolSize = Integer.valueOf(System.getenv("CONNECTION_POOL_SIZE"));

        Database database = new Database(databaseUrl, connectionPoolSize);
        initialize(database);
    }

    public static void initialize(Database database) throws Exception
    {
        DatabaseConnection connection = new DatabaseConnection(database.newConnection());

        cleanDatabase(connection);
        database.migrate();
        populateDatabase(connection);

        connection.close();
    }

    private static void cleanDatabase(DatabaseConnection connection) throws Exception
    {
        connection.executeQuery("sql/schema/drop_schema.sql");
        connection.executeQuery("sql/schema/create_schema.sql");
        connection.commit();
    }

    private static void populateDatabase(DatabaseConnection connection) throws Exception
    {
        connection.executeQuery("sql/data/account.sql");
        connection.executeQuery("sql/data/map.sql");
        connection.executeQuery("sql/data/cell.sql");
        connection.executeQuery("sql/data/game.sql");
        connection.executeQuery("sql/data/player.sql");
        connection.executeQuery("sql/data/account_games.sql");
        connection.executeQuery("sql/data/game_players.sql");
        connection.commit();
    }
}