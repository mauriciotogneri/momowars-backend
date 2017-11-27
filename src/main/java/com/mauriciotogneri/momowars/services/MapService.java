package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.InvalidSessionTokenException;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.repository.map.MapDao;

import java.util.List;

public class MapService
{
    public static List<Map> getMaps(DatabaseConnection connection)
            throws InvalidSessionTokenException, DatabaseException
    {
        MapDao mapDao = new MapDao(connection);

        return mapDao.getMaps();
    }
}