package com.mauriciotogneri.momowars.repository.map;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.MapQueries;
import com.mauriciotogneri.momowars.exceptions.InvalidSessionTokenException;
import com.mauriciotogneri.momowars.model.Map;

import java.util.List;
import java.util.stream.Collectors;

public class MapDao
{
    private final DatabaseConnection connection;

    public MapDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public List<Map> getMaps() throws InvalidSessionTokenException, DatabaseException
    {
        SelectQuery<MapRow> query = connection.selectQuery(MapQueries.SELECT_MAPS, MapRow.class);
        QueryResult<MapRow> result = query.execute();

        return result.stream().map(MapRow::map).collect(Collectors.toList());
    }
}