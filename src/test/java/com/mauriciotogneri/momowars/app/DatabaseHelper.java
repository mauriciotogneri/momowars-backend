package com.mauriciotogneri.momowars.app;

import com.mauriciotogneri.momowars.database.Database;
import com.mauriciotogneri.momowars.database.DatabaseConnection;

import java.sql.Connection;

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
        Connection connection = database.newConnection();
        DatabaseConnection databaseConnection = new DatabaseConnection(connection);

        cleanDatabase(databaseConnection);
        connection.commit();

        database.migrate();

        populateDatabase(databaseConnection);
        connection.commit();

        connection.close();
    }

    private static void cleanDatabase(DatabaseConnection databaseConnection) throws Exception
    {
        databaseConnection.executeQuery("sql/schema/drop_schema.sql");
        databaseConnection.executeQuery("sql/schema/create_schema.sql");
    }

    private static void populateDatabase(DatabaseConnection databaseConnection) throws Exception
    {
        databaseConnection.executeQuery("sql/data/account.sql");
        databaseConnection.executeQuery("sql/data/cell.sql");
        databaseConnection.executeQuery("sql/data/map.sql");
        databaseConnection.executeQuery("sql/data/map_cells.sql");
    }
}