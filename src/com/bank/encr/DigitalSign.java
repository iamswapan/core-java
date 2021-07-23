package com.bank.encr;

import java.util.Base64;

public class DigitalSign {
    public static void main(String[] args) {
        DigitalSign ds=new DigitalSign();
        System.out.println(ds.createJWSSignature());
    }

    public String createJWSSignature(){
        String sign=getJWSHeader()+"."+getJWSPayload()+"."+getJWSPrivateSign();

        return sign;
    }

    public String getJWSHeader(){
        String sign=null;
        String header="{\"typ\":\"JWT\", \"alg\":\"RS256\"}";
        sign=encodeBase64(header);
        return sign;
    }

    public String getJWSPayload(){
        String sign=null;
        String payload="{\"typ\":\"JWT\", \"alg\":\"RS256\"}";
        sign=encodeBase64(payload);
        return sign;
    }

    public String getJWSPrivateSign(){
        String sign=null;
        String key="MIIEpAIBAAKCAQEAwQh3oRTpqGZ7MWTd828y/mjwgKjWc2XqTmGioVAv8kmVzLqz\n" +
                "7xufnjZ9ln28306gDSk/uIle06NeM7v9TwF8dYeNuB0UKsRP2dJVY/ShZC3qVOfE\n" +
                "izXA68eAgDmxy2+wMl9COlYltkFe7uIwi3o+2pt0lhvvFA6QszzL7q79Rz+yNdY6\n" +
                "wjzTSLAbruucfHC5Bh6Cj9mE+mZyzvCsi3NhCr6J3OGS0RL/WA1llmnElVXLAE6c\n" +
                "8ze6ZTRc2MrmdIcnkg0pT1pFI//YbNBxKcGnAyiN+DYFItMqfa8g47elnlZDK6gH\n" +
                "zNF6LTkE91bkp49x/GfuJltLY8++RLWSoXTiVQIDAQABAoIBAQCJavy2+MtKbgJf\n" +
                "bH4xMcVspcoo3YklR7VgrVL4YbxwUIBK6dL0cobUO9h2DPcrRa7dD7NkLK8MHS4P\n" +
                "yFGRL+6pQKHcIx9bPrvVxk2xxdIrHiDG5uPdcpvvfev13Jy8xbr6cSQVoYRhVfI+\n" +
                "P1ntwU4DlE2QGzy8242Ze7t2JK78S5iRJQ0dwinRg77+4rt+ak/bSgXlvRS0zwGa\n" +
                "qdjQTRBdlBRHwC0HzKrIT7lHQu/PEwjoWCwhTg9t+5KC/A3BiD8a5ng66DIZn/cD\n" +
                "3vZsHac40E10ZLkCsbQWlcA/wiVQd2/5ZLY1dVyQ4HYOY0t2nc4xoa1h2cYEkpSa\n" +
                "5ZjnjBy1AoGBAN8KoqPrLscTIF7XuH+zIz65vdbz02qtNYS4QmPgQfXLw+GdSakL\n" +
                "JkU4T0A4nynS5FI+6jRlQbvJzF5Kj8ZdDAizxdji//b4bEWdd7jRtohcQ+/L0THV\n" +
                "gmpBOe+o7aLEaiaGwVEFLpew4QOXSgw9Bo8xSY5XfbKEdXDFMQb4esQ3AoGBAN2O\n" +
                "pszVgu+YiNDN/mr5/DIvC1ha76cSxY50u4K3PkyIARr2XaFrqe9e7HyVbH7A3otJ\n" +
                "6nz9xpUE0ZGUOalnIEA9a+a9aqgmAFAjI9V9qwAm8qFikR7UV90Ge3Fox5oWpM4G\n" +
                "rs2Bo85wqV+tVXfAKinTrHkmZ93xJ7HBaqL5UZ/TAoGBANhBYJZcdntw0QcEVBAE\n" +
                "6ZxszWM3//nDvUtUuJJMIsgmbGM5dO0hMVcWFMko3THZF6Wi+MQalVR1geC+nI5x\n" +
                "MzTQ0XjMB/A2yAvrdORO+ZNXGkvLq6aM5KkPWxRDDz2tAVsfdxqOUrGjP1iS/KnH\n" +
                "FbAK0s3fiLFo+tF/cFzY2XebAoGAd/gJi/rFNrXSx7XzYLvSet4Md6BuCnrYrKW6\n" +
                "7zIIMuqmJpJm6sRgtTQiO+exYwVOu/+fUGUeRYzhacHoYzlrNj3MEppYiYpiOwJ8\n" +
                "Ids1YBS25vp3eEkk9pLO/v7srhw6yzDFMJRVPbC1lhBGqlxMuG9dAhDwxzYQ92iz\n" +
                "NGJqd28CgYAFzbdEJkWsvlsCJEDt7kVVsvV2tEMAMj4lgugAj39NGyXPS9hrD82y\n" +
                "vhuoo7EWXl4Ci6eMlmEMlTEBx8Bs7XfiU1AFA90eAJPloKi6GmqFU6FHwIlfHLyw\n" +
                "r81KPzVK+I4RzYEZ19/DT5Y/L3CrIwLTCQe6opBFVoZDkarFbWyDng==";
        sign=encodeBase64(key);
        return sign;
    }

    public String encodeBase64(String input){
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
}
