package com.java8new.lambda;

public class ThreadEx1 {
    public static void main(String[] args) {
        Runnable r= new Runnable() {
            @Override
            public void run() {
               for (int i=0; i<10;++i){
                   System.out.println("child="+i);
               }
            }
        };

        Runnable rLambda=()->{
            for (int i=0; i<10;++i){
                System.out.println("lambda="+i);
            }
        };
        Thread t1=new Thread(r);
        Thread t2=new Thread(rLambda);
        new Thread(()->{
            for (int i=0;i<10;++i)
                System.out.println("inner lambda="+i);
        }).start();

        t1.start();
        t2.start();
        for (int i=0; i<10;++i){
            System.out.println("Parent="+i);
        }
    }
}
