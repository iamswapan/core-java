package com.java8new.mapSort;

import com.example.Employee;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ttnd on 30/9/18.
 */
public class SortExample1 {
    public static void main(String[] args) {
        Map<String, Integer> map1=new HashMap<>();
        map1.put("a", 3);
        map1.put("b", 1);
        map1.put("aa", 3);
        map1.put("dd", 5);
        map1.put("c", 2);

        System.out.println("Main Map===================");
        map1.entrySet().forEach(System.out::println);



        Map<String, Integer> filterMap=map1.entrySet().stream()
                .filter(e -> e.getValue() > 2)
                .distinct()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Filter Map===================");
        filterMap.entrySet().forEach(System.out::println);

        HashMap<String, Integer> filterHashMap=map1.entrySet().stream()
                .filter(e -> e.getValue() > 2)
                .distinct()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e1, HashMap::new));
        System.out.println("Filter Hash Map===================");
        filterHashMap.entrySet().forEach(System.out::println);

        Map sortedKeyMap=map1.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e1, LinkedHashMap::new));
        System.out.println("Sorted Key Map===================");
        sortedKeyMap.entrySet().forEach(System.out::println);

        Map sortedValueMap=map1.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e1, LinkedHashMap::new));
        System.out.println("Sorted Value Map===================");
        sortedValueMap.entrySet().forEach(System.out::println);



        double d=calculateMaxFoirAmount(50000, 100.0, 35000);
        System.out.println("FOIR======"+d);

        Employee e1=new Employee("abcd", 33, 50000);





    }

    private static void updateEmp(Employee e){
        e.setName("pqrs");
    }

    private static double calculateMaxFoirAmount(Integer mi, Double foirRes, Integer mo) {
        double maxFoir = 0.0;
        double availableFoir = mi * foirRes / 100;
        double customerCapacity = availableFoir - mo;
        if (customerCapacity <= 0.0) {
            return maxFoir;
        }
        maxFoir = calculateMaxFOIR(customerCapacity, 7.99, 5);

        return maxFoir;
    }

    public static double calculateMaxFOIR(Double emiCapacity, Double rateOfInterest, Integer tenure) {

//        Validator.validateFOIRParameter(emiCapacity, rateOfInterest, tenure);
        DecimalFormat df = new DecimalFormat("0.00");

        double loanAmount = 0;
        try {
            double y = calculateY(rateOfInterest);
            loanAmount = emiCapacity * (y * (1 - Math.pow(y, tenure))) / (1 - y);
        } catch (Exception e) {
            System.out.println(e);
        }

        return Double.parseDouble(df.format(loanAmount));

    }

    private static Double calculateY(Double rateOfInterest) {
        return 1 / (1 + ((rateOfInterest / 100) / 12));
    }
}
