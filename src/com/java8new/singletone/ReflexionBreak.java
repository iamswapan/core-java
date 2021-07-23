package com.java8new.singletone;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflexionBreak {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        MySingletone obj1=MySingletone.getInstance();
        MySingletone obj2=MySingletone.getInstance();
        //TODO: Both the object have same hash code, so both are the same object.
        System.out.println(obj1);
        System.out.println(obj2);

        Constructor<?> reflectCons[]=MySingletone.class.getDeclaredConstructors();
        for(Constructor cons:reflectCons){
            cons.setAccessible(true);
            MySingletone refObj1=(MySingletone) cons.newInstance();
            System.out.println("Ref obj1======"+refObj1);
            MySingletone refObj2=(MySingletone) cons.newInstance();
            System.out.println("Ref obj2======"+refObj2);
            //TODO: So we observed that these two new object we crete have different hash code value.
            // We are able to create object of Singletone class.
            // only ENUMS can prevent that reflexion.
        }
    }
}


