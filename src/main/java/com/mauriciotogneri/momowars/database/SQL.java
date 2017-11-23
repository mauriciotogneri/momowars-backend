package com.mauriciotogneri.momowars.database;

public class SQL
{
    public class TypeQueries
    {
        public static final String TYPES = "sql/types/types.sql";
    }

    public class TableQueries
    {
        public static final String ACCOUNT = "sql/tables/account.sql";
        public static final String CELL = "sql/tables/cell.sql";
        public static final String GAME = "sql/tables/game.sql";
        public static final String MAP = "sql/tables/map.sql";
        public static final String PLAYER = "sql/tables/player.sql";
        public static final String QUEUE = "sql/tables/queue.sql";
        public static final String UNIT = "sql/tables/unit.sql";
    }

    public class IndexQueries
    {
        public static final String ACCOUNT_SESSION = "sql/indices/account_session.sql";
    }

    public class RelationshipQueries
    {
        public static final String ACCOUNT_GAMES = "sql/relationships/account_games.sql";
        public static final String GAME_PLAYERS = "sql/relationships/game_players.sql";
        public static final String MAP_CELLS = "sql/relationships/map_cells.sql";
        public static final String PLAYER_QUEUES = "sql/relationships/player_queues.sql";
        public static final String PLAYER_UNITS = "sql/relationships/player_units.sql";
    }

    public class AccountQueries
    {
        public static final String INSERT = "sql/queries/account/insert.sql";
        public static final String SELECT_BY_CREDENTIALS = "sql/queries/account/select-by-credentials.sql";
        public static final String SELECT_BY_SESSION_TOKEN = "sql/queries/account/select-by-session-token.sql";
        public static final String UPDATE_SESSION_TOKEN = "sql/queries/account/update-session-token.sql";
    }
}