package com.mauriciotogneri.momowars.database;

public class QueryParameter<T>
{
    private final Class<T> clazz;
    private final T value;

    private QueryParameter(Class<T> clazz, T value)
    {
        this.clazz = clazz;
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    public <V> V value()
    {
        return (V) value;
    }

    public boolean isString()
    {
        return clazz.equals(String.class);
    }

    public boolean isBoolean()
    {
        return clazz.equals(Boolean.class);
    }

    public boolean isInteger()
    {
        return clazz.equals(Integer.class);
    }

    public boolean isLong()
    {
        return clazz.equals(Long.class);
    }

    public boolean isFloat()
    {
        return clazz.equals(Float.class);
    }

    public boolean isDouble()
    {
        return clazz.equals(Double.class);
    }

    public static QueryParameter<String> asString(String value)
    {
        return new QueryParameter<>(String.class, value);
    }

    public static QueryParameter<Boolean> asBoolean(boolean value)
    {
        return new QueryParameter<>(Boolean.class, value);
    }

    public static QueryParameter<Integer> asInteger(int value)
    {
        return new QueryParameter<>(Integer.class, value);
    }

    public static QueryParameter<Long> asLong(long value)
    {
        return new QueryParameter<>(Long.class, value);
    }

    public static QueryParameter<Float> asFloat(float value)
    {
        return new QueryParameter<>(Float.class, value);
    }

    public static QueryParameter<Double> asDouble(double value)
    {
        return new QueryParameter<>(Double.class, value);
    }
}