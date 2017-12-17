package com.mauriciotogneri.momowars.repository;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.MapQueries;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.exceptions.MapNotFoundException;
import com.mauriciotogneri.momowars.model.Map;

import java.util.List;

public class MapDao extends BaseDao
{
    public MapDao(DatabaseConnection connection)
    {
        super(connection);
    }

    public List<Map> maps() throws DatabaseException
    {
        SelectQuery<Map> query = select(MapQueries.SELECT_ALL, Map.class);

        List<Map> maps = query.execute();

        for (Map map : maps)
        {
            CellDao cellDao = new CellDao(connection);
            map.cells = cellDao.byMap(map.id);
        }

        return maps;
    }

    public Map byId(Long mapId) throws DatabaseException, ApiException
    {
        SelectQuery<Map> query = select(MapQueries.SELECT_BY_ID, Map.class);
        QueryResult<Map> result = query.execute(mapId);

        if (result.hasElements())
        {
            Map map = result.first();

            CellDao cellDao = new CellDao(connection);
            map.cells = cellDao.byMap(map.id);

            return map;
        }
        else
        {
            throw new MapNotFoundException();
        }
    }
}