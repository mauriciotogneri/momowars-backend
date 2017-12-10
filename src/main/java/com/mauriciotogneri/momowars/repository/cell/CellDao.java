package com.mauriciotogneri.momowars.repository.cell;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.QueryResult;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.CellQueries;
import com.mauriciotogneri.momowars.model.Cell;
import com.mauriciotogneri.momowars.repository.queue.QueueDao;
import com.mauriciotogneri.momowars.repository.unit.UnitDao;

import java.util.ArrayList;
import java.util.List;

public class CellDao
{
    private final DatabaseConnection connection;

    public CellDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public List<Cell> byMap(Long mapId) throws DatabaseException
    {
        UnitDao unitDao = new UnitDao(connection);
        QueueDao queueDao = new QueueDao(connection);

        SelectQuery<CellRow> query = connection.selectQuery(CellQueries.SELECT_BY_MAP, CellRow.class);
        QueryResult<CellRow> result = query.execute(mapId);

        List<Cell> cells = new ArrayList<>();

        for (CellRow row : result)
        {
            cells.add(row.cell(
                    unitDao.byCell(row.id),
                    queueDao.byCell(row.id)
            ));
        }

        return cells;
    }
}