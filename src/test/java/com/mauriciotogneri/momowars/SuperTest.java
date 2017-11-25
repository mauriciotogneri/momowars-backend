package com.mauriciotogneri.momowars;

import org.junit.Assert;
import org.junit.Test;

public class SuperTest
{
    @Test
    public void superTest()
    {
        Assert.assertEquals("xxx", System.getenv("VERSION"));
    }
}