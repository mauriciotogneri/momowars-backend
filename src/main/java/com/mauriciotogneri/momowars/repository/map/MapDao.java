package com.mauriciotogneri.momowars.repository.map;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.MapQueries;
import com.mauriciotogneri.momowars.exceptions.InvalidSessionTokenException;
import com.mauriciotogneri.momowars.exceptions.MapNotFoundException;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.repository.cell.CellDao;

import java.util.ArrayList;
import java.util.List;

public class MapDao
{
    private final DatabaseConnection connection;

    public MapDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public List<Map> getMaps() throws InvalidSessionTokenException, DatabaseException
    {
        CellDao cellDao = new CellDao(connection);

        SelectQuery<MapRow> query = connection.selectQuery(MapQueries.SELECT_ALL, MapRow.class);
        QueryResult<MapRow> result = query.execute();

        List<Map> maps = new ArrayList<>();

        for (MapRow map : result)
        {
            maps.add(map.map(cellDao.byMap(map.id)));
        }

        return maps;
    }

    public Map getMap(Long mapId) throws MapNotFoundException, DatabaseException
    {
        CellDao cellDao = new CellDao(connection);

        SelectQuery<MapRow> query = connection.selectQuery(MapQueries.SELECT_BY_ID, MapRow.class);
        QueryResult<MapRow> result = query.execute(mapId);

        if (result.hasElements())
        {
            return result.first().map(cellDao.byMap(mapId));
        }
        else
        {
            throw new MapNotFoundException();
        }
    }
}