package com.mauriciotogneri.momowars.database.sql;

import java.util.ArrayList;

public class QueryResult<T> extends ArrayList<T>
{
    public QueryResult(int initialCapacity)
    {
        super(initialCapacity);
    }

    public boolean hasElements()
    {
        return !isEmpty();
    }

    public T first()
    {
        return get(0);
    }
}