package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.javautils.Resource;

import java.sql.Connection;
import java.sql.Statement;

public class CreateDatabase
{
    public static final String CREATE_SCHEMA = "sql/schema/create_schema.sql";
    public static final String DROP_SCHEMA = "sql/schema/drop_schema.sql";

    public static final String CELL_TYPE = "sql/types/cell_type.sql";
    public static final String GAME_STATUS = "sql/types/game_status.sql";
    public static final String MOVEMENT_TYPE = "sql/types/movement_type.sql";
    public static final String PLAYER_STATUS = "sql/types/player_status.sql";
    public static final String UNIT_TYPE = "sql/types/unit_type.sql";

    public static final String TABLE_ACCOUNT = "sql/tables/account.sql";
    public static final String TABLE_CELL = "sql/tables/cell.sql";
    public static final String TABLE_GAME = "sql/tables/game.sql";
    public static final String TABLE_MAP = "sql/tables/map.sql";
    public static final String TABLE_PLAYER = "sql/tables/player.sql";
    public static final String TABLE_QUEUE = "sql/tables/queue.sql";
    public static final String TABLE_UNIT = "sql/tables/unit.sql";

    public static final String INDEX_ACCOUNT_SESSION = "sql/indices/account_session.sql";

    public static final String ACCOUNT_GAMES = "sql/relationships/account_games.sql";
    public static final String GAME_PLAYERS = "sql/relationships/game_players.sql";
    public static final String MAP_CELLS = "sql/relationships/map_cells.sql";
    public static final String PLAYER_QUEUES = "sql/relationships/player_queues.sql";
    public static final String PLAYER_UNITS = "sql/relationships/player_units.sql";

    public static void create(Connection connection) throws Exception
    {
        execute(connection, DROP_SCHEMA);
        execute(connection, CREATE_SCHEMA);

        execute(connection, CELL_TYPE);
        execute(connection, GAME_STATUS);
        execute(connection, MOVEMENT_TYPE);
        execute(connection, PLAYER_STATUS);
        execute(connection, UNIT_TYPE);

        execute(connection, TABLE_MAP);
        execute(connection, TABLE_CELL);
        execute(connection, TABLE_ACCOUNT);
        execute(connection, TABLE_GAME);
        execute(connection, TABLE_PLAYER);
        execute(connection, TABLE_UNIT);
        execute(connection, TABLE_QUEUE);

        execute(connection, INDEX_ACCOUNT_SESSION);

        execute(connection, ACCOUNT_GAMES);
        execute(connection, GAME_PLAYERS);
        execute(connection, MAP_CELLS);
        execute(connection, PLAYER_QUEUES);
        execute(connection, PLAYER_UNITS);

        connection.commit();
    }

    private static void execute(Connection connection, String sqlFile) throws Exception
    {
        try (Statement statement = connection.createStatement())
        {
            statement.execute(Resource.string(sqlFile));
        }
    }
}