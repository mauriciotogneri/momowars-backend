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
    private final List<Queue> queues;

    public Cell(Long id, CellType type, Integer x, Integer y, List<Unit> units, List<Queue> queues)
    {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
        this.units = units;
        this.queues = queues;
    }
}