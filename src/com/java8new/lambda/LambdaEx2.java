package com.java8new.lambda;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
interface CalculateStringLength{
    public int findLength(int s);
}

public class LambdaEx2 {
    public static void main(String[] args) {
        Consumer f=(a)-> System.out.println("Hello");
        f.accept(5);

        CalculateStringLength sl= s->s*s;
//        System.out.println(sl.findLength("Maa Durga"));
        System.out.println(sl.findLength(6));

        String str="52724-80384-MAGIC-AUTO - PVT LTD-MARUTI";
        String variant="52724-MAGIC-AUTO - PVT LTD-MARUTI";
        String token[]=str.split("-");
        //System.out.println(Arrays.asList(token));
        //System.out.println(str.indexOf('-'));
        String ntbCode=str.substring(0, str.indexOf('-'));
        String pa=str.substring(str.indexOf('-')+1);
        String paCode=pa.substring(0, pa.indexOf('-'));
        String paName=pa.substring(pa.indexOf('-')+1);
        //System.out.println(pa);
        System.out.println("NTB:"+ntbCode);
        System.out.println("PA Code:"+paCode);
        System.out.println("PA Name:"+paName);

        Calendar cal=Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -21);
        System.out.println(cal.getTime());

        String st12="90% OF ONROAD";
        System.out.println("==================="+st12.contains("ROAD"));
        System.out.println();
    }
}
