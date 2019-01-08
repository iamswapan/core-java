package com.jcod.basic;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionIteration {
    public static void main(String[] args) {
//        List<String> list= Arrays.asList("JAVA", "C", "C++", "JAVA8", "JAVA9", "JAVA7");
        /*List<String> list= new ArrayList<>();
        list.addAll(Arrays.asList("JAVA", "C", "C++", "JAVA8", "JAVA9", "JAVA7"));
        System.out.println(list);
        list.stream().filter(e -> e.matches(".*JAVA.*")).forEach(System.out::println);

        System.out.println(list.contains("JAVA"));
        List<String> linkList=new LinkedList<>();
        linkList.add(0,"JAVA");
        linkList.add("JAVA89");

        System.out.println(linkList+"===="+list.containsAll(linkList));

        List<Integer> integerList=new LinkedList<>();
        for(int i=1; i<10; ++i){
            integerList.add(i);
        }

        ListIterator listIterator=integerList.listIterator();
        System.out.println("list iterator =="+listIterator.hasPrevious()+"=="+listIterator.hasNext());
        System.out.println("next elem="+listIterator.next());
        System.out.println("next elem="+listIterator.next());
        System.out.println("prev elem="+listIterator.previous());
        System.out.println("prev elem="+listIterator.previous());

        integerList.stream().filter(e->e.toString().length()<3).forEach(System.out::println);
        System.out.println("convert to array :: "+integerList.toArray());
        //integerList.parallelStream().forEach(System.out::println);

//        System.out.println(integerList.stream().filter(e -> e%10 == 0).collect(Collectors.summingInt(e -> e)));
        filterCollection(list);
        System.out.println("Filter string=====" + list);
        filterCollection(integerList);
        System.out.println("Filter int=====" + integerList);
        System.out.println("bi ser=="+Collections.binarySearch(integerList, 1));
        Collections.reverse(integerList);
        System.out.println("reverse list=" + (integerList) + "====" + integerList.size());
        System.out.println("bi ser=="+Collections.binarySearch(integerList, 1));*/

        /////////hash map
        HashMap<Integer, String> hashMap=new HashMap<>();
        System.out.println(hashMap.put(1, "A"));
        System.out.println(hashMap.put(1, "B"));
        System.out.println(hashMap.put(1, "C"));
        for(Map.Entry entry:hashMap.entrySet()){
            System.out.println(entry.getKey()+"===="+entry.getValue());
        }
        ////////////////
        System.out.println("Array list change");
        ArrayList<Integer> a1=new ArrayList();
        a1.add(5);
        a1.add(10);
        System.out.println(a1);
        a1=new ArrayList();
        a1.add(20);
        a1.add(25);
        a1.add(26);
        a1.add(35);
        a1.add(45);
        System.out.println(a1);
        for(Iterator<Integer> itr=a1.iterator();itr.hasNext();){
            if(itr.next()==25){
                itr.remove();
            }

            //a1.add(55);
        }
        System.out.println(a1);

        for(Integer in:a1){
            a1.remove(3);
        }

        System.out.println(a1);

        Map<Integer, Integer> chm=new ConcurrentHashMap<>();
        chm.put(1, 10);
        chm.put(2, 50);
        chm.put(3, 30);
        chm.put(4, 90);
        chm.put(7, 60);

        System.out.println("Map Data========================");
        for(Iterator<Map.Entry<Integer, Integer>> itr=chm.entrySet().iterator(); itr.hasNext();){
            Map.Entry entry=itr.next();
            System.out.println(entry);
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("============================");
            chm.put(8,78);
        }


    }

    public static void filterCollection(Collection collection){
        for (Iterator itr=collection.iterator();itr.hasNext();){
            //System.out.println(!itr.next().equals("JAVA")+"=="+(itr.next().toString().length()==3));
            if(itr.next().toString().length()==3){
                itr.remove();
            }
        }
    }

}
