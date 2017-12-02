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

    public class GameQueries
    {
        public static final String INSERT = "queries/game/insert.sql";
        public static final String SELECT_OPEN = "queries/game/select_open.sql";
    }

    public class MapQueries
    {
        public static final String SELECT_BY_ID = "queries/map/select_by_id.sql";
        public static final String SELECT_ALL = "queries/map/select_all.sql";
    }

    public class CellQueries
    {
        public static final String SELECT_BY_MAP = "queries/cell/select_by_map.sql";
    }

    public class LogQueries
    {
        public static final String INSERT = "queries/log/insert.sql";
        public static final String UPDATE = "queries/log/update.sql";
    }

    public class ErrorQueries
    {
        public static final String INSERT = "queries/error/insert.sql";
    }
}