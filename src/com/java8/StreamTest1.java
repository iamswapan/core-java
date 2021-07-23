package com.java8;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamTest1 {
    public static void main(String[] args) {
        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");


        memberNames.stream().filter(s->s.startsWith("A")).forEach(System.out::println);

        List<String> filterName=memberNames.stream().filter((a) -> a.startsWith("S")).collect(Collectors.toList());
        System.out.println("============================");
        System.out.println(filterName);

        memberNames.stream().sorted().collect(Collectors.toList());

        memberNames.stream().sorted().forEach(e->{
            e=e+" test";
            System.out.println(e);
        });
        System.out.println(memberNames);

        Map<String, String> serializedPageDtoMap=new HashMap<>();
        serializedPageDtoMap.put("","");
        ArrayList maplist=new ArrayList<String>(serializedPageDtoMap.values());
        System.out.println("Map list========"+maplist);

        EmployeePredicate emp1=new EmployeePredicate("Rahul", 25);
        EmployeePredicate emp2=new EmployeePredicate("Sachin", 35);
        EmployeePredicate emp3=new EmployeePredicate("Sourav", 15);
        EmployeePredicate emp4=new EmployeePredicate("Laxman", 275);

        List<EmployeePredicate> empList= Arrays.asList(emp1, emp2, emp3, emp4);

        List<String> nameList= empList.stream().map(EmployeePredicate::getName).collect(Collectors.toList());
        List<EmployeePredicate> empNameList= empList.stream()
                .map(s->new EmployeePredicate().setName(s.getName()))
                .collect(Collectors.toList());
        System.out.println(empNameList);

        testCountTheNumberOfUpperCaseCharacters();
    }

    public static void testCountTheNumberOfUpperCaseCharacters() {
        String testStr = "abcdefghijkTYYtyyQ";
        String regEx = "(\\p{Lu})";
//        String regEx = "[A-Z]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(testStr);
        while (matcher.find())
            System.out.printf("Found %d, of capital letters in %s%n",
                    matcher.groupCount(), matcher.toMatchResult());

    }
}
