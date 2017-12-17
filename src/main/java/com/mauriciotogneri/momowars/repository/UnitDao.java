package com.mauriciotogneri.momowars.repository;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.DeleteQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.UnitQueries;
import com.mauriciotogneri.momowars.model.Unit;

import java.util.Arrays;
import java.util.List;

public class UnitDao extends BaseDao
{
    public UnitDao(DatabaseConnection connection)
    {
        super(connection);
    }

    public List<Unit> byCell(Long cellId) throws DatabaseException
    {
        return Arrays.asList();
    }

    public void delete(Long playerId) throws DatabaseException
    {
        DeleteQuery query = delete(UnitQueries.DELETE);

        int rowsAffected = query.execute(playerId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }
}