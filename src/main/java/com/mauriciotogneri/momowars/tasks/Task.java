package com.mauriciotogneri.momowars.tasks;

import com.mauriciotogneri.momowars.util.TaskPool;

public abstract class Task implements Runnable
{
    public void submit()
    {
        TaskPool.submit(this);
    }
}