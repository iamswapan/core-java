package com.java8new.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateJoining {
    public static void main(String[] args) {
        int a=7;
        int[] arr={2,3, 5, 8, 11, 19, 22};
        IntPredicate even= i->i%2==0;
        IntPredicate grater10=i->i>10;
        System.out.println("Even="+even.test(a));
        System.out.println("Odd="+even.negate().test(a));
        System.out.println(">10 ="+grater10.test(a));
        System.out.println("Even and 10 ="+grater10.and(even).test(a));
        int[] l=Arrays.stream(arr).filter(even.or(grater10).negate()).toArray();
        for(int data:l){
            System.out.println(data);
        }

        String x="3.14";
        Double d=Double.parseDouble(x);
        System.out.println(d);




    }
}
