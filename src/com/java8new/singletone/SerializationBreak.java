package com.java8new.singletone;

import java.io.*;

public class SerializationBreak {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MySingletone obj1=MySingletone.getInstance();
        System.out.println(obj1);

        // Serialization
        ObjectOutput output=new ObjectOutputStream(new FileOutputStream("MysingletoneScr.txt"));
        output.writeObject(obj1);
        output.close();

        // De-Serialization
        ObjectInput input=new ObjectInputStream(new FileInputStream("MysingletoneScr.txt"));
        MySingletone newObj1=(MySingletone) input.readObject();
        System.out.println(newObj1);
        // TODO: hash value is different for these two objects
        // TODO: that means singletome property is break, to resolve: override readResolve() method.
    }
}
