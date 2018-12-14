package com.java8new.basic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterTest1 {
    public static void main(String[] args) {
        List<Integer> integerList=new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        integerList=integerList.stream().filter(val->val%2==0).collect(Collectors.toList());

        Map<String, Integer> myMap=new HashMap<>();
        myMap.put("A", 1);
        myMap.put("B", 2);
        myMap.put("C", 3);
        myMap.put("D", 4);
        myMap.put("E", 5);

        myMap.forEach((k,v)->{
            System.out.println("key="+k+"  val="+v);
        });
    }
}
