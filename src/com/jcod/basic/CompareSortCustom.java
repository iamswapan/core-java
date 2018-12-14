package com.jcod.basic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ttnd on 28/9/18.
 */
public class CompareSortCustom {
    public static void main(String[] args) {

        EmployeeCompare e1=new EmployeeCompare(40, "cc", "1475", 40000);
        EmployeeCompare e2=new EmployeeCompare(30, "bb", "11", 40000);
        EmployeeCompare e3=new EmployeeCompare(45, "aa", "1", 35000);
        EmployeeCompare e4=new EmployeeCompare(20, "ddddd", "12345", 15000);
        EmployeeCompare e5=new EmployeeCompare(35, "bb", "123", 45000);
        EmployeeCompare[] empArr={e1, e2, e3, e4, e5};

        /*System.out.println("before sort:"+ Arrays.toString(empArr));
        Arrays.sort(empArr);
        System.out.println("[Comparable: natural order]::after sort:" + Arrays.toString(empArr));
        Arrays.sort(empArr, new NameComparator());
        System.out.println("[CompareTo: custom order: NAME]::after sort:" + Arrays.toString(empArr));
        Arrays.sort(empArr, new DesignationComparator());
        System.out.println("[CompareTo: custom order: DESIG]::after sort:" + Arrays.toString(empArr));*/

        List<EmployeeCompare> employeeList=new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e5);
        employeeList.add(e4);
        employeeList.add(e3);
        employeeList.add(e2);
        System.out.println("before sort list====" + employeeList);
        /*Collections.sort(employeeList);
        System.out.println("[Comparator: natural order]::after sort list===="+employeeList);
        Collections.sort(employeeList, new NameComparator());
        System.out.println("[Compare: custom order: NAME]::after sort list====" +employeeList);
        Collections.sort(employeeList, new DesignationComparator());
        System.out.println("[Compare: custom order: DESIG]::after sort list====" + employeeList);*/

       /* Collections.sort(employeeList, new Comparator<EmployeeCompare>() {
            @Override
            public int compare(EmployeeCompare o1, EmployeeCompare o2) {
                return o1.getName().length()-o2.getName().length();
            }
        });
        System.out.println("[annonyms comparator]:: name length::"+employeeList);*/
        /////////////////////// J*************** AVA 8 Comparator *************=================================
        Collections.sort(employeeList, (el1, el2)->(el1.getName().length()-el2.getName().length()));
//        System.out.println("[annonyms comparator java8]:: name length::"+employeeList);
        Comparator<EmployeeCompare> ageCom=Comparator.comparing(EmployeeCompare::getAge).reversed();
        Collections.sort(employeeList, ageCom);
        System.out.println("Age Com JAVA8====" + employeeList);
        Collections.sort(employeeList, Comparator.comparing(EmployeeCompare::getName).reversed());
        System.out.println("Name Rev Com JAVA8====" + employeeList);
        Collections.sort(employeeList, Comparator.comparing(EmployeeCompare::getName).thenComparing(EmployeeCompare::getAge));

    }
}


class EmployeeCompare implements Comparable<EmployeeCompare>{
    private int age;
    private String name;
    private String desig;
    private int salary;

    public EmployeeCompare(){}

    public EmployeeCompare(int age, String name, String desig, int salary){
        this.age=age;
        this.name=name;
        this.desig=desig;
        this.salary=salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeCompare{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", desig='" + desig + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(EmployeeCompare o) {
        if(this.getSalary()>o.getSalary())
            return -5;
        if(this.getSalary()<o.getSalary())
            return 10;
        return 0;
    }
}

class NameComparator implements Comparator<EmployeeCompare>{
    @Override
    public int compare(EmployeeCompare o1, EmployeeCompare o2) {
        return (o1.getName().compareTo(o2.getName()));
    }
}

class DesignationComparator implements Comparator<EmployeeCompare>{

    @Override
    public int compare(EmployeeCompare o1, EmployeeCompare o2) {
        return (o2.getDesig().length()-o1.getDesig().length());
    }
}

