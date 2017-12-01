package com.mauriciotogneri.momowars.tests;

import com.mauriciotogneri.momowars.app.Main;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.tests.account.AccountSuite;
import com.mauriciotogneri.momowars.tests.game.GameSuite;
import com.mauriciotogneri.momowars.tests.map.MapSuite;
import com.mauriciotogneri.momowars.tests.player.PlayerSuite;
import com.mauriciotogneri.momowars.tests.session.SessionSuite;

import org.eclipse.jetty.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.sql.Connection;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountSuite.class,
        SessionSuite.class,
        GameSuite.class,
        MapSuite.class,
        PlayerSuite.class
})
public class TestSuite
{
    private static Server server;

    @BeforeClass
    public static void startServer() throws Exception
    {
        int port = Integer.valueOf(System.getenv("PORT"));
        String databaseUrl = System.getenv("JDBC_DATABASE_URL");
        int connectionPoolSize = Integer.valueOf(System.getenv("CONNECTION_POOL_SIZE"));

        Main main = new Main(port, databaseUrl, connectionPoolSize);

        cleanDatabase(Main.database.newConnection());
        main.migrate();

        server = main.server();
        server.start();
    }

    private static void cleanDatabase(Connection connection) throws Exception
    {
        DatabaseConnection databaseConnection = new DatabaseConnection(connection);
        databaseConnection.executeQuery("sql/schema/drop_schema.sql");
        databaseConnection.executeQuery("sql/schema/create_schema.sql");

        connection.commit();
    }

    @AfterClass
    public static void stopServer()
    {
        try
        {
            server.stop();
        }
        catch (Exception e)
        {
            // ignore
        }
    }
}