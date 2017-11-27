package com.mauriciotogneri.momowars.repository.cell;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.CellQueries;
import com.mauriciotogneri.momowars.exceptions.InvalidSessionTokenException;
import com.mauriciotogneri.momowars.model.Cell;

import java.util.List;
import java.util.stream.Collectors;

public class CellDao
{
    private final DatabaseConnection connection;

    public CellDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public List<Cell> byMap(Long mapId) throws InvalidSessionTokenException, DatabaseException
    {
        SelectQuery<CellRow> query = connection.selectQuery(CellQueries.SELECT_BY_MAP, CellRow.class);
        QueryResult<CellRow> result = query.execute(mapId);

        return result.stream().map(CellRow::cell).collect(Collectors.toList());
    }
}