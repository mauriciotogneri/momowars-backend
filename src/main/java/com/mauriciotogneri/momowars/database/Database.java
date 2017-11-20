package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.momowars.utils.Resources;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database
{
    private static Connection connection;

    public static synchronized Connection connection() throws Exception
    {
        if (connection == null)
        {
            connection = createConnection();
        }

        return connection;
    }

    private static Connection createConnection() throws Exception
    {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        System.out.println(System.getenv("JDBC_DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        initialize(connection);

        return connection;
    }

    private static void initialize(Connection connection) throws Exception
    {
        execute(connection, "account.sql");
    }

    private static void execute(Connection connection, String sqlFile) throws Exception
    {
        String query = Resources.content(String.format("sql/tables/%s", sqlFile));
        connection.createStatement().execute(query);
    }
}