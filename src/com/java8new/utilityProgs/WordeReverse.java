package com.java8new.utilityProgs;

public class WordeReverse {
    public static void main(String[] args) {
        String s="I Love Programming";

        String []strArr=s.split(" ");
        String result="";
        for(int i=strArr.length-1; i>=0; --i){
            result+=strArr[i]+" ";
        }
        System.out.println(result);
    }
}
