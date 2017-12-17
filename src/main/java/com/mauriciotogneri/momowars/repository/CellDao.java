package com.mauriciotogneri.momowars.repository;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.CellQueries;
import com.mauriciotogneri.momowars.model.Cell;

import java.util.List;

public class CellDao extends BaseDao
{
    public CellDao(DatabaseConnection connection)
    {
        super(connection);
    }

    public List<Cell> byMap(Long mapId) throws DatabaseException
    {
        SelectQuery<Cell> query = select(CellQueries.SELECT_BY_MAP, Cell.class);

        List<Cell> cells = query.execute(mapId);

        UnitDao unitDao = new UnitDao(connection);
        QueueDao queueDao = new QueueDao(connection);

        for (Cell cell : cells)
        {
            cell.units = unitDao.byCell(cell.id);
            cell.queues = queueDao.byCell(cell.id);
        }

        return cells;
    }
}