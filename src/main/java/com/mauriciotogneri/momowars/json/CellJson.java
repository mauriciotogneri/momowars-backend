package com.mauriciotogneri.momowars.json;

import com.mauriciotogneri.momowars.types.CellType;

import java.util.List;

public class CellJson
{
    private final Long id;
    private final CellType type;
    private final Integer x;
    private final Integer y;
    private final List<UnitJson> units;
    private final List<QueueJson> queues;

    public CellJson(Long id, CellType type, Integer x, Integer y, List<UnitJson> units, List<QueueJson> queues)
    {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
        this.units = units;
        this.queues = queues;
    }
}