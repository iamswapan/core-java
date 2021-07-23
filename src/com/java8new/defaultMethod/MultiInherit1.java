package com.java8new.defaultMethod;

/**
 * Created by ttnd on 9/9/18.
 */
interface First{
    default void display(){
        System.out.println("From First");
    }
}

interface Second{
    default void display(){
        System.out.println("From Second");
    }
}

public class MultiInherit1 implements First, Second {
    public void display(){
        System.out.println("From Implemented class");
    }

    public static void main(String[] args) {
        MultiInherit1 obj1=new MultiInherit1();
        obj1.display();
    }
}
