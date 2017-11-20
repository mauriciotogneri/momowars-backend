package com.mauriciotogneri.momowars.database;

public class SQL
{
    public class TableQueries
    {
        public static final String ACCOUNT = "sql/tables/account.sql";
    }

    public class AccountQueries
    {
        public static final String INSERT = "sql/queries/account/insert.sql";
        public static final String SELECT_BY_CREDENTIALS = "sql/queries/account/select-by-credentials.sql";
        public static final String SELECT_BY_SESSION_TOKEN = "sql/queries/account/select-by-session-token.sql";
        public static final String UPDATE_SESSION_TOKEN = "sql/queries/account/update-session-token.sql";
    }
}