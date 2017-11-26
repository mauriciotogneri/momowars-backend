package com.mauriciotogneri.momowars.tests;

import com.mauriciotogneri.jerry.Jerry.Mode;
import com.mauriciotogneri.momowars.Main;
import com.mauriciotogneri.momowars.database.CreateDatabase;
import com.mauriciotogneri.momowars.tests.account.AccountSuite;
import com.mauriciotogneri.momowars.tests.game.GameSuite;
import com.mauriciotogneri.momowars.tests.player.PlayerSuite;
import com.mauriciotogneri.momowars.tests.session.SessionSuite;

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
        PlayerSuite.class
})
public class JunitTestSuite
{
    private static Server server;

    @BeforeClass
    public static void startServer() throws Exception
    {
        int port = Integer.valueOf(System.getenv("PORT"));
        String databaseUrl = System.getenv("JDBC_DATABASE_URL");
        int connectionPoolSize = Integer.valueOf(System.getenv("CONNECTION_POOL_SIZE"));

        Main main = new Main(port, Mode.LOCAL, databaseUrl, connectionPoolSize);

        CreateDatabase.create(Main.database.newConnection());

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