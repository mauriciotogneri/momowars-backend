package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.repository.map.MapDao;

import java.util.List;

public class MapService
{
    public static List<Map> getMaps(DatabaseConnection connection) throws DatabaseException
    {
        MapDao mapDao = new MapDao(connection);

        return mapDao.getMaps();
    }

    public static Map getMap(DatabaseConnection connection,
                             Long mapId) throws DatabaseException, ApiException
    {
        MapDao mapDao = new MapDao(connection);

        return mapDao.getMap(mapId);
    }
}