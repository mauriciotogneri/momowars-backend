package com.mauriciotogneri.momowars.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class MemoryCache<K, V>
{
    private final Map<K, MemoryCacheEntry> map;
    private final int expirationLimit;
    private final TimeUnit expirationTimeUnit;

    public MemoryCache(int cleanUpInterval, TimeUnit cleanUpTimeUnit, int expirationLimit, TimeUnit expirationTimeUnit)
    {
        this.map = new HashMap<>();
        this.expirationLimit = expirationLimit;
        this.expirationTimeUnit = expirationTimeUnit;

        launchCleanupWorker(cleanUpInterval, cleanUpTimeUnit);
    }

    private void launchCleanupWorker(int cleanUpInterval, TimeUnit cleanUpTimeUnit)
    {
        Thread thread = new Thread(() -> {
            while (true)
            {
                try
                {
                    Thread.sleep(cleanUpTimeUnit.toMillis(cleanUpInterval));
                }
                catch (Exception e)
                {
                    // ignore
                }

                cleanup();
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    public synchronized void put(K key, V value)
    {
        map.put(key, new MemoryCacheEntry(value, expirationLimit, expirationTimeUnit));
    }

    public synchronized Optional<V> get(K key)
    {
        MemoryCacheEntry entry = map.get(key);

        if (entry == null)
        {
            return Optional.empty();
        }
        else
        {
            entry.extend(expirationLimit, expirationTimeUnit);

            return Optional.of(entry.value);
        }
    }

    public synchronized void remove(K key)
    {
        map.remove(key);
    }

    public synchronized int size()
    {
        return map.size();
    }

    private synchronized void cleanup()
    {
        long now = System.currentTimeMillis();

        map.entrySet().removeIf(e -> e.getValue().expired(now));
    }

    public class MemoryCacheEntry
    {
        private long expirationTime = System.currentTimeMillis();
        private final V value;

        public MemoryCacheEntry(V value, int expirationLimit, TimeUnit expirationTimeUnit)
        {
            this.value = value;
            extend(expirationLimit, expirationTimeUnit);
        }

        public boolean expired(long now)
        {
            return expirationTime < now;
        }

        public void extend(int expirationLimit, TimeUnit expirationTimeUnit)
        {
            expirationTime = System.currentTimeMillis() + expirationTimeUnit.toMillis(expirationLimit);
        }
    }
}