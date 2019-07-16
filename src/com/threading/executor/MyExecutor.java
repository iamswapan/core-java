package com.threading.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyExecutor {
    private static ThreadPoolExecutor executor;

    private static int corePoolSize=3;
    private static int maximumPoolSize=5;
    private static long keepAliveTime=100;
    private static TimeUnit thereadTime=TimeUnit.SECONDS;
    private static BlockingQueue<Runnable> runnableQueue=new LinkedBlockingDeque<>();

    public static ThreadPoolExecutor getRunnableExecutor() {
        if(executor==null){
            executor=new ThreadPoolExecutor(corePoolSize, maximumPoolSize,keepAliveTime,thereadTime, runnableQueue);
        }
        return executor;
    }
}
