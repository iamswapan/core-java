package com.akamai.airtel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ttnd on 21/11/18.
 */
/*public class Curl {
    public static void main(String[] args) {

        try {

            String url = "https://catalog-api.hotstar.com/show/search?fromStartDate=0&toStartDate=1&orderBy=startDate&order=desc&offset=1&size=10&partner=tatasky&hdnea=st=1540892852~exp=1540979252~acl=/*~hmac=0905108e59cba4bd4bf5a002027bfe592ce3692505736d3080e1e864ea938018";

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

//            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            conn.setRequestMethod("GET");


            String userpass = "user" + ":" + "pass";
            String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
//            conn.setRequestProperty ("Authorization", basicAuth);

//            String data =  "{\"format\":\"json\",\"pattern\":\"#\"}";
             *//*data = new JSONObject();*//*
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
//            out.write(data);
            out.close();

//            InputStreamReader is=new InputStreamReader(conn.getInputStream());

            BufferedReader reader = new BufferedReader( new InputStreamReader(conn.getInputStream());
            for (String line; (line = reader.readLine()) != null;) {
                System.out.println(line);
            }
            reader.close();
            out.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}*/
