package com.java8new.immutable;

import com.java8new.singletone.MySingletone;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyImmutable {
    private final Integer id;
    private final String name;
    private List<Date> dateList;

    public MyImmutable(Integer id, String name, List<Date> dateList){
        this.id=new Integer(id);
        this.name=name;
        List<Date> tempList=new ArrayList<>();
        tempList.addAll(dateList);
        this.dateList=tempList;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Date> getDateList() {
        return new ArrayList<>(dateList);
    }

    @Override
    public String toString() {
        return "MyImmutable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateList=" + dateList +
                '}';
    }
}
