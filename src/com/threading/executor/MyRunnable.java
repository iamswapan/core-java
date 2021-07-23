package com.threading.executor;

import java.util.Date;

public class MyRunnable implements Runnable {

    private String threadName;

    MyRunnable(String name) {
        threadName = name;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < 5; ++i) {
                if (i == 0) {
                    System.out.println("Initilize the thread: " + threadName + " Time: " + new Date());
                } else {
                    System.out.println("Executing the thread: " + threadName + " Time: " + new Date());

                }
                Thread.sleep(100);
            }
            System.out.println("Completed the thread: " + threadName + " Time: " + new Date());
        } catch (InterruptedException ie) {
            System.out.println("Thread::Exception occurred:" + ie.getMessage());
        }
    }
}
