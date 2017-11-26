package com.mauriciotogneri.momowars.unit;

import com.mauriciotogneri.javautils.Resource;
import com.mauriciotogneri.jerry.Jerry.Mode;
import com.mauriciotogneri.momowars.Main;
import com.mauriciotogneri.momowars.database.SQL.IndexQueries;
import com.mauriciotogneri.momowars.database.SQL.RelationshipQueries;
import com.mauriciotogneri.momowars.database.SQL.SchemaQueries;
import com.mauriciotogneri.momowars.database.SQL.TableQueries;
import com.mauriciotogneri.momowars.database.SQL.TypeQueries;

import org.eclipse.jetty.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.Statement;

public class BaseTest
{
    private static Server server;
    private static Boolean databaseInitialized = false;

    @BeforeClass
    public static void startServer() throws Exception
    {
        //int port = Integer.valueOf(System.getenv("PORT"));
        //String databaseUrl = System.getenv("JDBC_DATABASE_URL");
        //int connectionPoolSize = Integer.valueOf(System.getenv("CONNECTION_POOL_SIZE"));

        Main main = new Main(5000, Mode.LOCAL, "jdbc:postgresql://localhost:5432/momowarstest?user=postgres&password=password", 3);

        initialize(Main.database.newConnection());

        server = main.server();
        server.start();
    }

    private static void initialize(Connection connection) throws Exception
    {
        if (!databaseInitialized)
        {
            databaseInitialized = true;

            execute(connection, SchemaQueries.DROP_SCHEMA);
            execute(connection, SchemaQueries.CREATE_SCHEMA);

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
    }

    private static void execute(Connection connection, String sqlFile) throws Exception
    {
        try (Statement statement = connection.createStatement())
        {
            statement.execute(Resource.string(sqlFile));
        }
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