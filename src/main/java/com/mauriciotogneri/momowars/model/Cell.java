package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.types.CellType;

public class Cell
{
    private final Long id;
    private final CellType type;
    private final Integer x;
    private final Integer y;

    public Cell(Long id, CellType type, Integer x, Integer y)
    {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
    }
}