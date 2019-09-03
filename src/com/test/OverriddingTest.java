package com.test;

import java.io.IOException;
import java.util.List;

public class OverriddingTest {
    public static void main(String[] args) {
        Parent p1=new Child();
        p1.getData(null);
        Parent p2=new Parent();
        p2.getData(null);
    }
}

class Parent{
    private Integer data;

    /*public void getData(Object data){
        System.out.println("Parent Object="+data);
    }*/
    public void getData(Integer data) {
        System.out.println("Parent List="+data);
    }
}

class Child extends Parent{
    private String data;

    public void getData(Float data) throws ArithmeticException {
        System.out.println("Child="+data);
    }
}
