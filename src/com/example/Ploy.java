package com.example;

/**
 * Created by ttnd on 11/8/18.
 */
public class Ploy {
    public int add(int a, int b){
        return a+b;
    }
    public double add(double a, double b){
        return a+b;
    }



    public static void main(String[] args) {
        Ploy obj=new Ploy();
        System.out.println(obj.add(5,7));
    }
}
