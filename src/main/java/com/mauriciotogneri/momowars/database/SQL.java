package com.mauriciotogneri.momowars.database;

public class SQL
{
    public class AccountQueries
    {
        public static final String CREATE = "queries/account/create.sql";
        public static final String SELECT_BY_ID = "queries/account/select_by_id.sql";
        public static final String SELECT_BY_EMAIL = "queries/account/select_by_email.sql";
        public static final String SELECT_BY_SESSION = "queries/account/select_by_session.sql";
        public static final String SELECT_BY_MATCH = "queries/account/select_by_match.sql";
        public static final String SELECT_MATCHES = "queries/account/select_matches.sql";
        public static final String UPDATE_SESSION = "queries/account/update_session.sql";
        public static final String UPDATE_NICKNAME = "queries/account/update_nickname.sql";
        public static final String UPDATE_PASSWORD = "queries/account/update_password.sql";
        public static final String UPDATE_PICTURE = "queries/account/update_picture.sql";
    }

    public class MatchQueries
    {
        public static final String CREATE = "queries/match/create.sql";
        public static final String SELECT_OPEN = "queries/match/select_open.sql";
        public static final String SELECT_BY_ID = "queries/match/select_by_id.sql";
        public static final String UPDATE_STATUS = "queries/match/update_status.sql";
        public static final String DELETE = "queries/match/delete.sql";
    }

    public class PlayerQueries
    {
        public static final String CREATE = "queries/player/create.sql";
        public static final String SELECT_BY_ID = "queries/player/select_by_id.sql";
        public static final String SELECT_BY_MATCH = "queries/player/select_by_match.sql";
        public static final String END_TURN = "queries/player/update_status.sql";
        public static final String DELETE = "queries/player/delete.sql";
    }

    public class UnitQueries
    {
        public static final String DELETE = "queries/unit/delete.sql";
    }

    public class QueueQueries
    {
        public static final String DELETE = "queries/queue/delete.sql";
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