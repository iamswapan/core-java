package com.java8;


import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Basic1 {
    public static void main(String[] args) {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute the thread");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finish the thread");
            }
        }).start();

        new Thread(()->{
            System.out.println("Another thread start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Another thread end");
        }).start();

        List l1=new ArrayList<>();
        l1.add(5);
        l1.add(10);
        l1.add(15);
        l1.add(25);
        l1.forEach(System.out::println);
        l1.stream().collect(Collectors.toCollection(ArrayList::new));
        String en;
        try {
            en=URLEncoder.encode("cB6DN4YIU0ej/N6Yw7yRLQ==", "UTF-8");
            System.out.println(en);
        }catch (Exception e){

        }*/

        /*ArrayList al=null;
        System.out.println(al);
        if(true){
//            al=new ArrayList();
            Basic1 obj1=new Basic1();
            //al=obj1.setListValue(al);
        }
        System.out.println("==========="+al);

        String s1=new String("MOBILE_BROWSER");
        //String s2=new String("MOBILE");
        System.out.println(s1.contains("MOBILE"));
        System.out.println(s1.equalsIgnoreCase("Mobile"));

        if(s1.trim().equalsIgnoreCase("Mobile")){
            System.out.println("M");
        }else if(s1.trim().equalsIgnoreCase("Browser")){
            System.out.println("B");
        }else if(s1.trim().equalsIgnoreCase("Dongle")){
            System.out.println("D");
        }else if(s1.contains("DONGLE") && s1.contains("MOBILE")) {   //Mobile_Dongle
            System.out.println("D-M");
        } else if(s1.contains("BROWSER") && s1.contains("MOBILE")) {   //Mobile_Dongle
            System.out.println("B-M");
        } else if(s1.contains("DONGLE") && s1.contains("BROWSER")) {   //Mobile_Dongle
            System.out.println("D-B");
        }*/

        ArrayList<Integer> ar=new ArrayList<>();
        ar.add(1);
        ar.add(2);
        ar.add(2);
        ar.add(2);
        ar.add(3);
        ar.add(4);
        ar.add(5);
        ar.add(6);
        ar.add(7);
        System.out.println("================="+ar);

        HashSet hs=ar.stream().filter(v->v%2==0).collect(Collectors.toCollection(HashSet::new));

        System.out.println("filter================="+ar);
        System.out.println("filter================="+hs);

        System.out.println("partiation==========="+ar.stream().filter(e->e>2).collect(Collectors.partitioningBy(e->e>4)));
        System.out.println("map distinct value change==========" + ar.stream().map(e -> e * 5).distinct().collect(Collectors.toCollection(ArrayList::new)));
        // allMatch() validate the condition for all the elemnts if it true for all then return TRUE else return FALSE
        System.out.println("map all match=========="+ar.stream().map(e->e).allMatch(e -> e > 1));
        System.out.println("map limit=========="+ar.stream().map(e->e).limit(2).collect(Collectors.toCollection(ArrayList::new)));
        /////// Find stat of any list or collection data
        DoubleSummaryStatistics ds=ar.stream().mapToDouble(e->e).summaryStatistics();
        System.out.println("max="+ds.getMax());
        System.out.println("min="+ds.getMin());
        System.out.println("avg="+ds.getAverage());
        System.out.println("count="+ds.getCount());
    }
    public ArrayList setListValue(ArrayList al){
        al=new ArrayList<Integer>();
        al.add(5);
        al.add(15);
        al.add(25);
        al.remove(2);
        /*ArrayList<String> newal=new ArrayList<>();
        newal.add("one");
        newal.add("two");
        newal.forEach(a->{
            System.out.println(a+" test");
        });
        ArrayList newList=new ArrayList();
        al.forEach(a->{
            newList.add((int)a+2);
        });


        System.out.println(newList);*/

        return al;
    }


}
