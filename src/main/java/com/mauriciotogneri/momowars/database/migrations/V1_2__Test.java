package com.mauriciotogneri.momowars.database.migrations;

import com.mauriciotogneri.momowars.database.DatabaseConnection;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;

public class V1_2__Test implements JdbcMigration
{
    public void migrate(Connection connection) throws Exception
    {
        DatabaseConnection databaseConnection = new DatabaseConnection(connection);
        databaseConnection.executeQuery(String.format("migrations/%s.sql", getClass().getSimpleName()));
    }
}