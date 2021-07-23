package com.java8new.lambda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class StreamFailFirstExample {
    public static void main(String[] args) {
        CopyOnWriteArrayList l=new CopyOnWriteArrayList();
        for(int i=1; i<=10000; i++)
        {
            l.add(i);
        }

        Runnable r1= new Runnable() {
            @Override
            public void run() {
                System.out.println("T1 start====================");
                Iterator it1=l.iterator();
                /*while(it1.hasNext()){
                    Integer el=(Integer) it1.next();
                    if(el==99)
                        System.out.println("=================="+el);
                }*/

                Object al=l.stream().filter(data->(Integer)data%1==0)
                        .filter(data->{
                            Iterator it=l.iterator();
                            while(it.hasNext()){
                                Integer e=(Integer) it.next();
                                if(e==99){
                                    System.out.println("Stream=================="+e);
                                }
                            }
                            return true;
                        })
                        .collect(Collectors.toCollection(ArrayList::new));
                System.out.println("T1 end====================");
            }

        };
        Runnable r2=new Runnable() {
            @Override
            public void run() {
                Integer a=null;
                float b=Float.valueOf(a);

                System.out.println("T2 start====================");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Iterator it=l.iterator();
                while(it.hasNext()){
                    Integer e=(Integer) it.next();
                    if(e==50)
                    it.remove();
                }
                System.out.println("T2 end====================");

            }
        };

        new Thread(r1).start();
        new Thread(r2).start();

    }
}
