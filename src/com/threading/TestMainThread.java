package com.threading;

public class TestMainThread {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        System.out.println("Main name==" + mainThread.getName() + "==Priority=" + mainThread.getPriority());

        MyThread1 t1=new MyThread1();
        Thread th1=new Thread(t1);
        th1.setPriority(Thread.MAX_PRIORITY);
        th1.start();
        System.out.println("Custom thread name==" + th1.getName() + "==Priority=" + th1.getPriority());

    }
}

class MyThread1 implements Runnable {

    public void run(){
        System.out.println("My custom thread executing");
    }
}
