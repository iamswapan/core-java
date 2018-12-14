package com.java8new.mapSort;

import com.example.InterfaceInitilization;
import com.java8.StreamTest1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by ttnd on 30/9/18.
 */
public class SortExample1 {
    public static void main(String[] args) {
        Map<String, Integer> map1=new HashMap<>();
        map1.put("a", 3);
        map1.put("b", 1);
        map1.put("aa", 3);
        map1.put("dd", 5);
        map1.put("c", 2);

        System.out.println("Main Map===================");
        map1.entrySet().forEach(System.out::println);

        Map<String, Integer> filterMap=map1.entrySet().stream()
                .filter(e -> e.getValue() > 2)
                .distinct()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Filter Map===================");
        filterMap.entrySet().forEach(System.out::println);

        HashMap<String, Integer> filterHashMap=map1.entrySet().stream()
                .filter(e -> e.getValue() > 2)
                .distinct()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e1, HashMap::new));
        System.out.println("Filter Hash Map===================");
        filterHashMap.entrySet().forEach(System.out::println);

        Map sortedKeyMap=map1.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e1, LinkedHashMap::new));
        System.out.println("Sorted Key Map===================");
        sortedKeyMap.entrySet().forEach(System.out::println);

        Map sortedValueMap=map1.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e1, LinkedHashMap::new));
        System.out.println("Sorted Value Map===================");
        sortedValueMap.entrySet().forEach(System.out::println);







    }
}
