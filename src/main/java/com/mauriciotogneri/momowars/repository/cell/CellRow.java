package com.mauriciotogneri.momowars.repository.cell;

import com.mauriciotogneri.momowars.model.Cell;
import com.mauriciotogneri.momowars.types.CellType;

public class CellRow
{
    public Long id;
    public CellType type;
    public Integer x;
    public Integer y;

    public Cell cell()
    {
        return new Cell(id, type, x, y);
    }
}