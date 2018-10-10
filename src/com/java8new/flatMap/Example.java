package com.java8new.flatMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ttnd on 22/9/18.
 */
public class Example {
    public static void main(String[] args) {
        List<String> first= Arrays.asList("one", "two", "three");
        List<String> sec= Arrays.asList("three", "four", "five");
        List<String> thi= Arrays.asList("four", "five", "six");
        List<String> fort= Arrays.asList("seven", "eight", "nine");

        List<List<String>> all=new ArrayList<>();
        all.add(first);
        all.add(sec);
        all.add(thi);
        all.add(fort);

        System.out.println(all.stream().flatMap(Collection::stream).collect(Collectors.toList()));
    }
}
