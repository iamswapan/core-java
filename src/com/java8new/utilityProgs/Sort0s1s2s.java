package com.java8new.utilityProgs;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Sort0s1s2s {
    public static void main(String[] args) {
        TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata");
        Calendar cal=Calendar.getInstance(istTimeZone);
        //System.out.println(cal);
        Date myDate=new Date(cal.getTimeInMillis());
        System.out.println(myDate);
    }
}
