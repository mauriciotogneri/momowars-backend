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

        createDatabase(Main.database.newConnection());

        server = main.server();
        server.start();
    }

    private static void createDatabase(Connection connection) throws Exception
    {
        DatabaseConnection databaseConnection = new DatabaseConnection(connection);

        databaseConnection.executeQuery("sql/schema/drop_schema.sql");
        databaseConnection.executeQuery("sql/schema/create_schema.sql");

        databaseConnection.executeQuery("sql/types/cell_type.sql");
        databaseConnection.executeQuery("sql/types/game_status.sql");
        databaseConnection.executeQuery("sql/types/movement_type.sql");
        databaseConnection.executeQuery("sql/types/player_status.sql");
        databaseConnection.executeQuery("sql/types/unit_type.sql");

        databaseConnection.executeQuery("sql/tables/map.sql");
        databaseConnection.executeQuery("sql/tables/cell.sql");
        databaseConnection.executeQuery("sql/tables/account.sql");
        databaseConnection.executeQuery("sql/tables/game.sql");
        databaseConnection.executeQuery("sql/tables/player.sql");
        databaseConnection.executeQuery("sql/tables/unit.sql");
        databaseConnection.executeQuery("sql/tables/queue.sql");

        databaseConnection.executeQuery("sql/indices/account_session.sql");

        databaseConnection.executeQuery("sql/relationships/account_games.sql");
        databaseConnection.executeQuery("sql/relationships/game_players.sql");
        databaseConnection.executeQuery("sql/relationships/map_cells.sql");
        databaseConnection.executeQuery("sql/relationships/player_queues.sql");
        databaseConnection.executeQuery("sql/relationships/player_units.sql");

        databaseConnection.executeQuery("sql/data/cell.sql");
        databaseConnection.executeQuery("sql/data/map.sql");
        databaseConnection.executeQuery("sql/data/map_cells.sql");

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