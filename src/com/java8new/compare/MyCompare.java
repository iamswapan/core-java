package com.java8new.compare;

import com.java8new.domain.Employee;

import java.util.*;

public class MyCompare {
    public static void main(String[] args) {
        List<Integer> myList=new ArrayList<>();
        myList.add(15);
        myList.add(10);
        myList.add(25);
        myList.add(15);
        myList.add(0);
        myList.add(37);
        System.out.println("Before sort===="+myList);
        Collections.sort(myList, new MyComparator());
        System.out.println("Using comparator after sort===="+myList);

        Employee e1=new Employee("Ashish", 55);
        Employee e2=new Employee("Sourabh", 26);
        Employee e3=new Employee("Rahul", 35);
        Employee e4=new Employee("Ashish", 21);
        Employee e5=new Employee("Mohit", 19);
        List<Employee> empList=Arrays.asList(e1,e2,e3,e4,e5);
        System.out.println("Emp Before sort="+empList);
        //Collections.sort(empList, (o1,o2)->o1.getAge()-o2.getAge());
        //Collections.sort(empList, Comparator.comparingInt(Employee::getAge));
        System.out.println("Emp age sort="+empList);

        //Collections.sort(empList, (o1,o2)->o2.getAge()-o1.getAge());
        System.out.println("Emp age sort Desc="+empList);

        Collections.sort(empList, new Employee());
        System.out.println("Emp age sort Desc with out lambda="+empList);

        Collections.sort(empList, Comparator.comparing(Employee::getName));
        System.out.println("Emp name sort="+empList);

        TreeSet<Integer> ts=new TreeSet<>((x,y)->x-y);
        ts.add(15);
        ts.add(5);
        ts.add(25);
        ts.add(37);
        ts.add(2);
        System.out.println("Tree set decending="+ts);
    }
}

class MyComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2-o1;
    }
}