package com.java8new.singletone;

import java.io.Serializable;

public class MySingletone implements Serializable, Cloneable {
    private static MySingletone obj;

    private MySingletone(){}

    public static MySingletone getInstance(){
        if(obj==null){
            obj=new MySingletone();
        }
        return obj;
    }

    public String toString(){
        System.out.println("Object hash value="+this.hashCode());
        return String.valueOf(this.hashCode());
    }

    public Object readResolve(){
        return obj;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
