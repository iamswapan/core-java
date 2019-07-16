package com.java8new.basic;

import java.util.ArrayList;

public class InterProg {
    public static void main(String[] args) {
        InterProg obj=new InterProg();
        System.out.println(obj.validateReturnWithTry());
        obj.testOverloading(null);
    }

    public int validateReturnWithTry(){
        try {
            System.out.println("Inside try");
            return 1;
        }catch (Exception ex){
            System.out.println("Inside catch");
            return 2;
        }finally {
            System.out.println("Inside finally");
            return 3;
        }
    }

    public void testOverloading(Object obj){
        System.out.println("Object type");
    }

    public void testOverloading(String obj){
        System.out.println("String type");
    }

    //TODO: both the below menthod will occurred compile time error because of ambiguity.
    /*public void testOverloading(ArrayList obj){
        System.out.println("Arraylist type");
    }*/
    /*public void testOverloading(FilterTest1 obj){
        System.out.println("Object type");
    }*/
}
