package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.types.CellType;

import java.util.List;

public class Cell
{
    private final Long id;
    private final CellType type;
    private final Integer x;
    private final Integer y;
    private final List<Unit> units;
    private final List<Queue> queue;

    public Cell(Long id, CellType type, Integer x, Integer y)
    {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
        this.units = null;
        this.queue = null;
    }
}