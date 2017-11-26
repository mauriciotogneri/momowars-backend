package com.mauriciotogneri.momowars.database;

public class SQL
{
    public class AccountQueries
    {
        public static final String INSERT = "queries/account/insert.sql";
        public static final String SELECT_BY_ID = "queries/account/select_by_id.sql";
        public static final String SELECT_BY_CREDENTIALS = "queries/account/select_by_credentials.sql";
        public static final String SELECT_BY_SESSION_TOKEN = "queries/account/select_by_session_token.sql";
        public static final String UPDATE_SESSION_TOKEN = "queries/account/update_session_token.sql";
        public static final String UPDATE_PASSWORD = "queries/account/update_password.sql";
        public static final String UPDATE_NICKNAME = "queries/account/update_nickname.sql";
    }
}