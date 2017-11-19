package com.mauriciotogneri.momowars.database;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.PreparedStatement;

public class InsertQuery extends Query
{
    public InsertQuery(String queryFile)
    {
        super(queryFile);
    }

    public int execute(InsertParameter<?>... parameters) throws Exception
    {
        PreparedStatement statement = preparedStatement();

        for (int i = 0; i < parameters.length; i++)
        {
            InsertParameter<?> parameter = parameters[i];
            Class<?> clazz = parameter.clazz;
            int index = i + 1;

            if (clazz.equals(String.class))
            {
                statement.setString(index, parameter.value());
            }
            else if (clazz.equals(Bool.class))
            {
                statement.setBoolean(index, parameter.value());
            }
            else if (clazz.equals(Integer.class))
            {
                statement.setInt(index, parameter.value());
            }
            else if (clazz.equals(Long.class))
            {
                statement.setLong(index, parameter.value());
            }
            else if (clazz.equals(Float.class))
            {
                statement.setFloat(index, parameter.value());
            }
            else if (clazz.equals(Double.class))
            {
                statement.setDouble(index, parameter.value());
            }
        }

        return statement.executeUpdate();
    }

    public static class InsertParameter<T>
    {
        private final Class<T> clazz;
        private final T value;

        public InsertParameter(Class<T> clazz, T value)
        {
            this.clazz = clazz;
            this.value = value;
        }

        @SuppressWarnings("unchecked")
        private <V> V value()
        {
            return (V) value;
        }

        public static InsertParameter<String> string(String value)
        {
            return new InsertParameter<>(String.class, value);
        }
    }
}