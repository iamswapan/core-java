package com.java8new.singletone;

public class CloneBreak {
    public static void main(String[] args) throws CloneNotSupportedException {
        MySingletone obj=MySingletone.getInstance();
        System.out.println(obj);

        MySingletone cloneObj1=(MySingletone) obj.clone();
        System.out.println(cloneObj1);
        //TODO: using clone method we are able to crete new object of singletone class.


    }
}
