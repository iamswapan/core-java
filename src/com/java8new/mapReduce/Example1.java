package com.java8new.mapReduce;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttnd on 19/9/18.
 */
public class Example1 {
    public static void main(String[] args) {
        List<Integer> arrList=new ArrayList<>();
        for(int i=1; i<10000; i++){
            arrList.add(i);
        }

        double avg=arrList.parallelStream().mapToInt(e->{
            System.out.println(e+"===="+Thread.currentThread().getName());
            return e;
        }).average().orElse(0.0);

        System.out.println("AVG===="+avg);
    }
}
