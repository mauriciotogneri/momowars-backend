package com.mauriciotogneri.momowars.database.migrations;

import com.mauriciotogneri.momowars.database.DatabaseConnection;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;

public class V1_2__CreateDatabase implements JdbcMigration
{
    public void migrate(Connection connection) throws Exception
    {
        DatabaseConnection databaseConnection = new DatabaseConnection(connection);

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
        databaseConnection.executeQuery("sql/tables/log.sql");

        databaseConnection.executeQuery("sql/indices/account_session.sql");

        databaseConnection.executeQuery("sql/relationships/account_games.sql");
        databaseConnection.executeQuery("sql/relationships/game_players.sql");
        databaseConnection.executeQuery("sql/relationships/map_cells.sql");
        databaseConnection.executeQuery("sql/relationships/player_queues.sql");
        databaseConnection.executeQuery("sql/relationships/player_units.sql");
    }
}