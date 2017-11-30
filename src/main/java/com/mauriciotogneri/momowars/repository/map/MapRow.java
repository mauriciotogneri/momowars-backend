package com.mauriciotogneri.momowars.repository.map;

import com.mauriciotogneri.momowars.model.Cell;
import com.mauriciotogneri.momowars.model.Map;

import java.util.List;

public class MapRow
{
    public Long id;
    public String name;
    public Integer width;
    public Integer height;

    public Map map(List<Cell> cells)
    {
        return new Map(id, name, width, height, cells);
    }
}