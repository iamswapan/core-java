package com.test;

import java.util.*;

public class TestClass {
    public static void main(String[] args) {
        String str="abc";
        System.out.println(Arrays.asList(str.split(",")));
        List<String> str1=new ArrayList<>();
        str1.add("st1");
        str1.add("st2");
        str1.add("st3");
        str1.add("st4");
        System.out.println(str1.indexOf("st3"));

        int a=31;
        int b=a<<3;
        a<<=3;
        a+=8;
        a+=a;
        a>>=3;

        System.out.println("******************="+b);
        System.out.println("******************="+a);

        int data=118;
        System.out.println("========"+(data&15));

        Map<String, Integer> m1=new HashMap();
        System.out.println("put1=@@@@@@=="+m1.put("one",1));
        System.out.println("put2==="+m1.put("two",2));
        System.out.println("put1==="+m1.put("one",3));
        System.out.println("put1==="+m1.put("one",4));

        System.out.println("Date========"+new Date().compareTo(new Date(1598466600000L)));
        System.out.println(new Date().getTime());
        System.out.println(new Date(1598466600000L).getTime());

    }

    public void method1(){
        final int x=5;
        System.out.println("val="+x);

    }


}
