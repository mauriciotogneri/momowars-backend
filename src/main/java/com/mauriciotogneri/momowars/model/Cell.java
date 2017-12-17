package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.json.CellJson;
import com.mauriciotogneri.momowars.json.QueueJson;
import com.mauriciotogneri.momowars.json.UnitJson;
import com.mauriciotogneri.momowars.types.CellType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cell
{
    public Long id;
    public CellType type;
    public Long map;
    public Integer x;
    public Integer y;
    public List<Unit> units = new ArrayList<>();
    public List<Queue> queues = new ArrayList<>();

    public CellJson json()
    {
        List<UnitJson> unitJson = units.stream().map(Unit::json).collect(Collectors.toList());
        List<QueueJson> queueJson = queues.stream().map(Queue::json).collect(Collectors.toList());

        return new CellJson(id, type, x, y, unitJson, queueJson);
    }
}