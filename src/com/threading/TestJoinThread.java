package com.threading;

public class TestJoinThread {
    public static void main(String[] args) throws InterruptedException {
        MyThreadJoin t1=new MyThreadJoin();
        MyThreadJoin t2=new MyThreadJoin();
        MyThreadJoin t3=new MyThreadJoin();

        t1.start();
        System.out.println("Executing :: My Thread=="+Thread.currentThread().getName());
        //t1.join();

        t2.start();
        t2.join();
        System.out.println("Executing :: My Thread=="+Thread.currentThread().getName());
        //t1.join();

        t3.start();
        System.out.println("Executing :: My Thread=="+Thread.currentThread().getName());
        //t3.join();
    }
}

class MyThreadJoin extends Thread{
    public void run(){
        for (int i=0;i<5;++i){
            System.out.println("Executing :: custom Thread=="+Thread.currentThread().getName());
        }
        System.out.println("Finish :: custom Thread=="+Thread.currentThread().getName());
    }
}
