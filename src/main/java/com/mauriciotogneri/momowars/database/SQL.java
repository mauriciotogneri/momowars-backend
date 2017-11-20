package com.mauriciotogneri.momowars.database;

public class SQL
{
    public class Table
    {
        public static final String ACCOUNT = "sql/tables/account.sql";
    }

    public class Account
    {
        public static final String INSERT = "sql/queries/account/insert.sql";
        public static final String SELECT_BY_CREDENTIALS = "sql/queries/account/select-by-credentials.sql";
        public static final String SELECT_BY_SESSION_TOKEN = "sql/queries/account/select-by-session-token.sql";
    }
}