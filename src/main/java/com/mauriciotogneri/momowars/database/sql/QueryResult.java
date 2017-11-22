package com.mauriciotogneri.momowars.database.sql;

import com.mauriciotogneri.momowars.database.DatabaseException;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryResult<T>
{
    private final ResultSet rows;
    private final Class<T> clazz;

    public QueryResult(ResultSet rows, Class<T> clazz)
    {
        this.rows = rows;
        this.clazz = clazz;
    }

    public boolean hasRows()
    {
        try
        {
            return rows.next();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public List<T> rows() throws DatabaseException
    {
        List<T> result = new ArrayList<>();

        try
        {
            rows.beforeFirst();

            while (rows.next())
            {
                result.add(row());
            }
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }

        return result;
    }

    public T row() throws DatabaseException
    {
        return row(rows, clazz);
    }

    private T row(ResultSet rows, Class<T> clazz) throws DatabaseException
    {
        try
        {
            T object = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (int i = 0; i < fields.length; i++)
            {
                int index = i + 1;
                Field field = fields[i];

                if (field.getType().equals(String.class))
                {
                    field.set(object, rows.getString(index));
                }
                else if (field.getType().equals(Boolean.class))
                {
                    field.set(object, rows.getBoolean(index));
                }
                else if (field.getType().equals(Integer.class))
                {
                    field.set(object, rows.getInt(index));
                }
                else if (field.getType().equals(Long.class))
                {
                    field.set(object, rows.getLong(index));
                }
                else if (field.getType().equals(Float.class))
                {
                    field.set(object, rows.getFloat(index));
                }
                else if (field.getType().equals(Double.class))
                {
                    field.set(object, rows.getDouble(index));
                }
            }

            return object;
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }
}