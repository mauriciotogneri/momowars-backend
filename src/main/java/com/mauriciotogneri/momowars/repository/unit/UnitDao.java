package com.mauriciotogneri.momowars.repository.unit;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.model.Unit;

import java.util.Arrays;
import java.util.List;

public class UnitDao
{
    private final DatabaseConnection connection;

    public UnitDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public List<Unit> byCell(Long cellId) throws DatabaseException
    {
        return Arrays.asList();
    }
}