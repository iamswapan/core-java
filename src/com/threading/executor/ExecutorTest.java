package com.threading.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorTest {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ThreadPoolExecutor executorService = MyExecutor.getRunnableExecutor();
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);

        MyRunnable r1 = new MyRunnable("T1");
        MyRunnable r2 = new MyRunnable("T2");
        MyRunnable r3 = new MyRunnable("T3");
        MyRunnable r4 = new MyRunnable("T4");
        MyRunnable r5 = new MyRunnable("T5");
        executorService1.execute(r1);
//        executorService.submit(r2);
        executorService1.execute(r2);
        executorService1.execute(r3);
        executorService1.execute(r4);
        executorService1.execute(r5);
        System.out.println("=======================Execution Done");
        executorService.shutdown();
    }

}
