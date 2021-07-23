package com.ds;

import java.util.Calendar;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Utility {

    public static String getCurrentDateIn_yyyymmdd() {
        String month = "";
        String day = "";
        // EEE MMM dd kk:mm:ss z yyyy
        if ((getCurrentMonth() + 1) <= 9) {
            String monthStr = "" + (getCurrentMonth() + 1);
            if (monthStr.length() == 1)
                month = "00" + (getCurrentMonth() + 1);
            else
                month = "0" + (getCurrentMonth() + 1);
        } else {
            month = "0" + (getCurrentMonth() + 1);
        }
        if ((getCurrentDay()) <= 9) {
            String dateStr = "" + getCurrentDay();
            if (dateStr.length() == 1)
                day = "0" + getCurrentDay();
            else
                day = "" + getCurrentDay();
        } else {
            day = "" + getCurrentDay();
        }

        String date = getCurrentYear() + "" + month + "" + day;
        return date;
    }

    public static int getCurrentMonth() {
        Calendar cal=Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    // Definition of HMAC_SHA256
    /*private static String HMAC_SHA256(String secret, String message) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance(ApplicationVariables.H_MAC_SHA_256);
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), ApplicationVariables.H_MAC_SHA_256);
            sha256_HMAC.init(secret_key);

            hash = Base64.encodeToString(sha256_HMAC.doFinal(message.getBytes()), Base64.NO_WRAP);
        } catch (Exception e) {

        }
        return hash.trim();
    }*/
}

