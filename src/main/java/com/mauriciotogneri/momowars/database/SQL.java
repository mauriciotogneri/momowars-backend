package com.mauriciotogneri.momowars.database;

public class SQL
{
    public class AccountQueries
    {
        public static final String CREATE = "queries/account/create.sql";
        public static final String SELECT_BY_ID = "queries/account/select_by_id.sql";
        public static final String SELECT_BY_CREDENTIALS = "queries/account/select_by_credentials.sql";
        public static final String SELECT_BY_SESSION_TOKEN = "queries/account/select_by_session_token.sql";
        public static final String SELECT_GAMES = "queries/account/select_games.sql";
        public static final String UPDATE_SESSION_TOKEN = "queries/account/update_session_token.sql";
        public static final String UPDATE_PASSWORD = "queries/account/update_password.sql";
        public static final String UPDATE_NICKNAME = "queries/account/update_nickname.sql";
        public static final String JOIN_GAME = "queries/account/join_game.sql";
    }

    public class GameQueries
    {
        public static final String CREATE = "queries/game/create.sql";
        public static final String SELECT_OPEN = "queries/game/select_open.sql";
        public static final String SELECT_BY_ID = "queries/game/select_by_id.sql";
    }

    public class PlayerQueries
    {
        public static final String CREATE = "queries/player/create.sql";
        public static final String JOIN_GAME = "queries/player/join_game.sql";
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
        public static final String CREATE = "queries/log/create.sql";
        public static final String UPDATE = "queries/log/update.sql";
    }

    public class ErrorQueries
    {
        public static final String CREATE = "queries/error/create.sql";
    }
}