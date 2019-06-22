package com.test;

public class CaseTest {
    public static void main(String[] args) {
        String data="b";
        switch(data){
            case "s":
            case "b":
            case "tv":
                System.out.println("S-B-TV");
                break;
            case "vod":
                System.out.println("VOD");
                break;
                default:
                    System.out.println("default");
        }
    }
}
