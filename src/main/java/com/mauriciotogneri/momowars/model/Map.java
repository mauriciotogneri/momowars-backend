package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.json.CellJson;
import com.mauriciotogneri.momowars.json.MapJson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Map
{
    public Long id;
    public String name;
    public Integer width;
    public Integer height;
    public List<Cell> cells = new ArrayList<>();

    public MapJson json()
    {
        List<CellJson> cellJson = cells.stream().map(Cell::json).collect(Collectors.toList());

        return new MapJson(id,
                           name,
                           width,
                           height,
                           cellJson);
    }
}