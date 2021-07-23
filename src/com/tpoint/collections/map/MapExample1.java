package com.tpoint.collections.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ttnd on 30/9/18.
 */
public class MapExample1 {
    public static void main(String[] args) {
        Map<String, Integer> map1=new HashMap<>();
        map1.put("a",1);
        map1.put("a", 2);
        map1.entrySet().forEach(System.out::println);
        String f="Intas Srinagar";
        System.out.println("All length="+f.length());
        String sub_st=f.substring(1, 4);
        System.out.println("sub length="+sub_st.length());
        System.out.println(sub_st);

        List<Demo> l1=new ArrayList<>();
        Demo d1=new Demo();
        Demo d2=new Demo();
        d1.setName("n1");
        d2.setName("n2");
        l1.add(d1);
        l1.add(null);
        l1.add(d2);
        System.out.println(l1);
        List<Demo>l2=new ArrayList<>();
        l1.forEach(e->{
            l2.add(e);
        });
        System.out.println(l2);
    }
}

class Demo{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                '}';
    }
}
