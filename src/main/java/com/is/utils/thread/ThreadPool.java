package com.is.utils.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool
{
    private static ThreadPool  threadPool         = null;

    private ThreadPoolExecutor threadPoolExecutor = null;

    private int                corePoolSize       = 128;

    private int                maximumPoolSize    = 512;

    private long               keepAliveTime      = 5;

    private ThreadPool()
    {
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public static ThreadPool getInstance()
    {
        if (threadPool == null)
        {
            synchronized (ThreadPool.class)
            {
                if (threadPool == null)
                {
                    threadPool = new ThreadPool();
                }
            }
        }
        return threadPool;
    }

    public void exec(Runnable task)
    {
        while (threadPoolExecutor.getPoolSize() > maximumPoolSize)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
            }
        }
        threadPoolExecutor.execute(task);
    }
}
