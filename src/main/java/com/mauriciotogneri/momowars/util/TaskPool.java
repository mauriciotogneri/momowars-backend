package com.mauriciotogneri.momowars.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskPool
{
    private static final int corePoolSize = 10;
    private static final int maxPoolSize = 100;
    private static final long keepAliveTime = 30;

    private static final ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
            corePoolSize,
            maxPoolSize,
            keepAliveTime,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>()
    );

    public static void submit(Runnable runnable)
    {
        threadPoolExecutor.submit(runnable);
    }
}