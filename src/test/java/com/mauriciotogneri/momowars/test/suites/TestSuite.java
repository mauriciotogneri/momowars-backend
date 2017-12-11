package com.mauriciotogneri.momowars.test.suites;

import com.mauriciotogneri.momowars.app.AppParameters;
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
        Main main = new Main(AppParameters.PORT,
                             AppParameters.DATABASE_URL,
                             AppParameters.CONNECTION_POOL_SIZE);

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