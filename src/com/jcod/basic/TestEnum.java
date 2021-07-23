package com.jcod.basic;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestEnum {
    public static void main(String[] args) {
        System.out.println(TypeEnum.getEnumByFirstValue("LIVE_EVENT").getSecondValue());
        System.out.println(TypeEnum.getEnumBySecondValue("content_channel"));
        System.out.println(TypeEnum.getEnumByFirstValue("GENRE"));
        System.out.println(TypeEnum.getAllEnumsByFirstValue("LIVE_EVENT"));
    }
}


enum TypeEnum{
    EDITORIAL_CONTENT_CHANNEL("LIVE", "content_channel"){
        @Override
        public String display(){
            System.out.println("LIVE");
            return "LIVE";
        }
    },
    EDITORIAL_CONTENT_LIVE_EVENT("LIVE_EVENT", "content_epg"){
        @Override
        public String display(){
            System.out.println("LIVE_EVENT");
            return "LIVE_EVENT";
        }
    },
    EDITORIAL_CONTENT_LIVE_EVENT_NEW("LIVE_EVENT", "content_epg_new"){
        @Override
        public String display(){
            System.out.println("LIVE_EVENT");
            return "LIVE_EVENT";
        }
    },
    EDITORIAL_CONTENT_GENRE("GENRE", "content_genre"){
        @Override
        public String display(){
            System.out.println("GENRE");
            return "GENRE";
        }
    };

    private String firstValue;

    private String secondValue;

    private String showType = "*.*";

    TypeEnum(String value, String indexName) {
        this.firstValue = value;
        this.secondValue = indexName;
    }

    TypeEnum(String value, String nextVal, String showType) {
        this.firstValue = value;
        this.secondValue = nextVal;
        this.showType = showType;
    }


    public String getFirstValue() {
        return firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public String getShowType() {
        return showType;
    }

    public static TypeEnum getEnumByFirstValue(String val){
        if(Objects.nonNull(val)){
            for(TypeEnum typeEnum:TypeEnum.values()){
                if(val.equalsIgnoreCase(typeEnum.getFirstValue()))
                    return typeEnum;
            }
        }
        return null;
    }

    public static TypeEnum getEnumBySecondValue(String val){
        if(Objects.nonNull(val)){
            for(TypeEnum typeEnum:TypeEnum.values()){
                if(val.equalsIgnoreCase(typeEnum.getSecondValue()))
                    return typeEnum;
            }
        }
        return null;
    }

    public static List<TypeEnum> getAllEnumsByFirstValue(String val){
        List<TypeEnum> enumList=new ArrayList<>();
        if(Objects.nonNull(val)){
            for(TypeEnum typeEnum:TypeEnum.values()){
                if(val.equalsIgnoreCase(typeEnum.getFirstValue()))
                    enumList.add(typeEnum);
            }
        }
        return enumList;
    }

    public abstract String display();

}