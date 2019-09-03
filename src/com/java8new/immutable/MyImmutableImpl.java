package com.java8new.immutable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyImmutableImpl {
    public static void main(String[] args) {
        List<Date> dateList=new ArrayList<>();
        dateList.add(new Date(1000));
        dateList.add(new Date(3600000));
        dateList.add(new Date(7200000));
        MyImmutable mi=new MyImmutable(1, "asdf", dateList);
        System.out.println(mi);
        mi.getDateList().add(new Date(10800000));
        System.out.println(mi);

    }
}
