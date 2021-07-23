package com.ds;

import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

public class Sample1 {
    public static void main(String[] args) {
        String str="{\n  \"Request\": {\n    \"partner_code\": \"MUWP\",\n    \"partner_uname\": \"Maruti Udyog\",\n    \"partner_pwd\": \"cgSSeMxLm733B8pGz7+tUA==\",\n    \"TrnRefNo_Partner\": \"001302\",\n    \"mobile_no\": \"9824182865\",\n    \"identifier_name\": \"DateOfBirth\",\n    \"identifier_value\": \"01-APR-1983\",\n    \"SourceName\": \"SourceName\",\n    \"ChannelName\": \"ChannelName\",\n    \"udf_1\": \"udf_1\",\n    \"udf_2\": \"udf_2\",\n    \"udf_3\": \"udf_3\",\n    \"udf_4\": \"udf_4\",\n    \"udf_5\": \"udf_5\",\n    \"udf_6\": \"udf_6\",\n    \"udf_7\": \"udf_7\",\n    \"udf_8\": \"udf_8\",\n    \"udf_9\": \"udf_9\",\n    \"udf_10\": \"udf_10\",\n    \"udf_11\": \"udf_11\",\n    \"udf_12\": \"udf_12\",\n    \"udf_13\": \"udf_13\",\n    \"udf_14\": \"udf_14\",\n    \"udf_15\": \"udf_15\"\n  }\n}";
        System.out.println(str);
        str=str.replace("\n","");
        System.out.println(str);

        String number = "1000500000.574";
        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###.00");

        Random random=new Random();
        random.nextInt(5);

        System.out.println("========="+new Date().getTime());

        System.out.println(formatter.format(amount));

        System.out.println(Base64.getEncoder().encodeToString(("wso2:wso2admin").getBytes()));

    }
}
