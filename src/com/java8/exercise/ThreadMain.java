package com.java8.exercise;

class Thread1 implements Runnable{

    public void run(){
        for(int i=0;i<10;++i){
            if(i%2==0){
                System.out.println(i);
                try {
//                    Thread.sleep(100);
                    wait();
//                    notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class Thread2 implements Runnable{
    public void run(){
        for(int i=0;i<10;++i){
            if(i%2!=0){
                System.out.println(i);
                try {
//                    Thread.sleep(100);
                    wait();
//                    notify();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


public class ThreadMain{
    public static void main(String aa[]){
        Thread t1=new Thread(new Thread1());
        Thread t2=new Thread(new Thread2());
        t1.start();
        t2.start();
    }
}
