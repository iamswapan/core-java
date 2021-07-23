package com.threading;

public class TestYeild {
    public static void main(String[] args) {
        YeildThread yt=new YeildThread();
        Thread th=new Thread(yt);
        th.start();
        th.setPriority(Thread.MAX_PRIORITY);
        for(int i=0;i<5;i++){
            th.yield();
            System.out.println("Main thread===="+Thread.currentThread().getName());
        }
    }
}

class YeildThread implements Runnable{

    @Override
    public void run() {
        for (int i=0;i<5;i++){
            System.out.println("Yeild Thread=="+Thread.currentThread().getName());
        }

    }
}
