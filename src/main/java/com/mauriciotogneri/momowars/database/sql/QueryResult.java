package com.mauriciotogneri.momowars.database.sql;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public class QueryResult<T>
{
    private final ResultSet resultSet;
    private final Class<T> clazz;

    public QueryResult(ResultSet resultSet, Class<T> clazz)
    {
        this.resultSet = resultSet;
        this.clazz = clazz;
    }

    public boolean hasRows() throws Exception
    {
        return resultSet.next();
    }

    public T row() throws Exception
    {
        T object = clazz.newInstance();

        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++)
        {
            int index = i + 1;
            Field field = fields[i];

            if (field.getType().equals(String.class))
            {
                field.set(object, resultSet.getString(index));
            }
            else if (field.getType().equals(Boolean.class))
            {
                field.set(object, resultSet.getBoolean(index));
            }
            else if (field.getType().equals(Integer.class))
            {
                field.set(object, resultSet.getInt(index));
            }
            else if (field.getType().equals(Long.class))
            {
                field.set(object, resultSet.getLong(index));
            }
            else if (field.getType().equals(Float.class))
            {
                field.set(object, resultSet.getFloat(index));
            }
            else if (field.getType().equals(Double.class))
            {
                field.set(object, resultSet.getDouble(index));
            }
        }

        return object;
    }
}