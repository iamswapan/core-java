package com.java8new.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<Integer> optEmpty= Optional.empty();
        Optional<Integer> optVal= Optional.of(5);
        Optional<Integer> optNull= Optional.ofNullable(null);

        System.out.println(optEmpty.isPresent());
        System.out.println(optVal.isPresent());
        System.out.println(optNull.isPresent());
        // orElase() will return the value if present else return the passing value eg: 8 if value not present
        System.out.println(optEmpty.orElse(8));
        System.out.println(optVal.orElse(9));
        System.out.println(optNull.orElse(10));
        // value fetch or exception occurred :: java.util.NoSuchElementException: No value present
        System.out.println(optEmpty.get());


        List<Integer> integerList=new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.stream().filter(e->e==4).forEach(System.out::println);
    }
}
