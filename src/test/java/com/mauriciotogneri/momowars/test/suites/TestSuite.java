package com.mauriciotogneri.momowars.test.suites;

import com.mauriciotogneri.momowars.app.Main;
import com.mauriciotogneri.momowars.test.suites.account.AccountSuite;
import com.mauriciotogneri.momowars.test.suites.game.GameSuite;
import com.mauriciotogneri.momowars.test.suites.map.MapSuite;
import com.mauriciotogneri.momowars.test.suites.player.PlayerSuite;
import com.mauriciotogneri.momowars.test.suites.session.SessionSuite;

import org.eclipse.jetty.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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

        DatabaseHelper.initialize(Main.database);

        server = main.server();
        server.start();
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