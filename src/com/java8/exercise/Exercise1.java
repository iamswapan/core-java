package com.java8.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

public class Exercise1 {
    public static void main(String[] args) {
        List<Person> personList= Arrays.asList(
            new Person("Sachin", 35),
            new Person("Rahul", 28),
            new Person("Sourav", 42),
            new Person("Yuvi", 33),
            new Person("Amit", 19)
        );
        PrintListLambda printName=(pl)->{
            for(Object p:pl) {
                System.out.println(p);
            }
        };

        StartsWithCheckLambda startsWith=(pl, ch)->{
            System.out.format("Starts with %s=============", ch);
            for(Object p:pl){
                Person per=(Person) p;
                if(per.getName().startsWith(ch)){
                    System.out.println(per);
                }
            }
        };

        printName.display(personList);
        startsWith.display(personList, "A");
        System.out.println("Before sort===="+personList);
//        Collections.sort(personList, (p,q)-> p.getName().compareTo(q.getName()));
        Collections.sort(personList, Comparator.comparing(Person::getAge));
        System.out.println("After sort===="+personList);

        printConditionally(personList, p->true);
        printConditionally(personList, (p)->p.getName().startsWith("R"));
        BiConsumer<Integer, Integer> sum=(a,b)->addNumbers(a,b);
        sum.accept(5,7);

        BiConsumer<Integer, Integer> sum1=Exercise1::addNumbers;
        sum1.accept(8,9);

        System.out.println("From Return="+addNumbersReturn((a,b)->a+b));

        System.out.println("*******"+addNumbersReturn((a,b)->a-b));

        Comparator<String> c=(s1,s2)->s1.length()-s2.length();



    }

    public static void printConditionally(List<Person> p, Predicate<Person> cn){
        System.out.println("Print on condition==============");
        for(Person per:p){
            if(cn.test(per)){
                System.out.println(per);
            }
        }
    }

    public static void addNumbers(Integer a, Integer b){
        System.out.println("Sum===="+(a+b));
    }

    public static Integer addNumbersReturn(BiFunction<Integer, Integer, Integer> fn){
        return fn.apply(5,7);
    }
}

interface PrintListLambda{
    void display(List l);
    String toString();     //TODO: Functional interface didn't consider Object class methods. So it is a valid FI.
}

interface StartsWithCheckLambda{
    void display(List l, String ch);
}
