package com.java8new.lambda;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

interface Addition {
    public int add(int a, int b);
}

interface Subtraction {
    public int sub(int a, int b);
}

public class LambdaEx1 {
    public static void main(String[] args) {
        /*Addition addLam = (a, b) -> (a + b);
        System.out.println("Add==" + addLam.add(5, 7));
        Function<Integer, Integer> f1 = a -> (a * 2);
        System.out.println("Double====" + f1.apply(5));

        DoubleBinaryOperator d1 = (a, b) -> a;
        System.out.println("double=" + d1.applyAsDouble(3, 4));

        ArrayList<Integer> ar=new ArrayList<>();
        ar.add(1);
        ar.add(2);
        ar.add(2);
        ar.add(2);
        ar.add(3);
        ar.add(4);
        ar.add(5);
        ar.add(6);
        ar.add(7);
        System.out.println("================="+ar);

        Integer in=ar.parallelStream().filter(i->{
            System.out.println(Thread.currentThread().getName()+"=============="+i+"========"+System.nanoTime());
            if(i>2)
                return true;
            else
                return false;
        }).findFirst().orElse(null);

        System.out.println("result===="+in);

        evenOddFindWithout(132346876);*/

        for (Integer i = 1; i <= 31; i++) {
            System.out.println(calenderFind(i));
        }


    }

    public static Integer convertStringToMilli(String[] duration) {
        Integer timeMilli = null;
        if (Objects.nonNull(duration) && duration.length == 3) {
            int hrs = Integer.parseInt(duration[0]);
            int min = Integer.parseInt(duration[1]);
            int sec = Integer.parseInt(duration[2]);
            timeMilli = (hrs * 3600 + min * 60 + sec) * 1000;
        }
        return timeMilli;
    }

    public static void evenOddFindWithout(Integer val) {
        String str = val.toString();
        Character last = str.charAt(str.length() - 1);
        switch (last) {
            case '1':
            case '3':
            case '5':
            case '7':
            case '9':
                System.out.println("Odd");
                break;
            case '2':
            case '4':
            case '6':
            case '8':
            case '0':
                System.out.println("Even");
                break;
        }
    }

    public static String calenderFind(Integer val) {
        /*Set<Character> s1 = new HashSet<>();
        Set<Character> s2 = new HashSet<>();
        s1.add('1');s1.add('2');s1.add('0');s1.add('3');s1.add('4');s1.add('5');
        s2.add('1');s2.add('2');s2.add('7');s2.add('8');s2.add('9');s2.add('0');

        char v1 = val.toString().charAt(0);
        char v2 ='0';
        if(val>10) {
            v2 = val.toString().charAt(1);
        }
            if (s1.contains(v1)) {
                if (s2.contains(v2)) {
                    return (val + "::Exists v1-v2");
                } else if (s1.contains(v2)) {
                    if (s2.contains(v1)) {
                        return (val + "::Exists v2-v1");
                    }
                }
            }
        }*/

        return "Not Found";
    }
}
