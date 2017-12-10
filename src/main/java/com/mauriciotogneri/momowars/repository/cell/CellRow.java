package com.mauriciotogneri.momowars.repository.cell;

import com.mauriciotogneri.momowars.model.Cell;
import com.mauriciotogneri.momowars.model.Queue;
import com.mauriciotogneri.momowars.model.Unit;
import com.mauriciotogneri.momowars.types.CellType;

import java.util.List;

public class CellRow
{
    public Long id;
    public CellType type;
    public Long map;
    public Integer x;
    public Integer y;

    public Cell cell(List<Unit> units, List<Queue> queues)
    {
        return new Cell(id, type, x, y, units, queues);
    }
}