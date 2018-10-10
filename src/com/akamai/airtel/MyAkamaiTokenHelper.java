package com.akamai.airtel;

import java.util.Hashtable;

/**
 * Created by ttnd on 24/9/18.
 */
public class MyAkamaiTokenHelper {

    private static final String LOG_TAG = "AkamaiHelper";
    // Used to load the 'native-lib' library on application startup.
    /*static {
        System.loadLibrary("native-lib");
    }*/


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String getCDNSecretKeyFromJNI();

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String getVRCDNSecretKeyFromJNI();

    // TODO: hide the key somewhere
    private static final String SECRET = "78068EB0F78D0AA8660F3FDFB1C54D28";

    private static final String TOKEN_NAME = "hdnea";

    public static void main(String[] args) {
        System.out.println("************My Auth Token============" + authorizeUrl("catalog-api.hotstar.com/show/search?"));
    }

    public static String authorizeUrl(String url) {

        System.out.println("authorizeUrl: " + url);

        // setup necessary params set
        Hashtable tokenConfig = new Hashtable();
        tokenConfig.put("key", SECRET);
        tokenConfig.put("token_name", "TOKEN_NAME");
        tokenConfig.put("acl", "/*");

        System.out.println("systemTime: " + System.currentTimeMillis());

        long startTime = System.currentTimeMillis();

        // pass start time timestamp in seconds
        tokenConfig.put("start_time", startTime / 1000);
        // TODO: check if "window_seconds" is necessary. For now works without it
        tokenConfig.put("window_seconds", "6000");

        // default value will be input url
        // in case of exception will return unauthorized url
        String result = url;
        try {
            // append generated token part to url
            String sec_token=AkamaiToken.generateToken(tokenConfig);
            System.out.println("************* TOKEN======"+sec_token);
            result = result + "&" + sec_token;
            System.out.println("AkamaiToken: " + result);
        } catch (AkamaiTokenException e) {
            System.out.println("Error during Akamai token generation"+e.getMessage());
        }

        return result;
    }


    public static String authorizeAdFormPostUrl(String url) {

        System.out.println("authorizeUrl: " + url);

        // setup necessary params set
        Hashtable tokenConfig = new Hashtable();
        tokenConfig.put("key", SECRET);
        tokenConfig.put("token_name", "TOKEN_NAME");
        tokenConfig.put("acl", "/*");

//        long serverTimeDelta = CacheManager.getInstance().getLong(CacheManager.KEY_SERVER_TIME_DELTA);

        System.out.println("systemTime: " + System.currentTimeMillis());

        // startTime = current time - delta - additional 2 minutes in order to avoid problems
        // if server time is a little out of sync
        long startTime = System.currentTimeMillis();

        // pass start time timestamp in seconds
        tokenConfig.put("start_time", startTime / 1000);
        // TODO: check if "window_seconds" is necessary. For now works without it
        tokenConfig.put("window_seconds", "600");

        // default value will be input url
        // in case of exception will return unauthorized url
        String result = url;
        try {
            // append generated token part to url
            result = result + "?" + AkamaiToken.generateToken(tokenConfig);
            System.out.println("AkamaiToken: " + result);
        } catch (AkamaiTokenException e) {
            System.out.println("Error during Akamai token generation" + e.getMessage());
        }

        return result;
    }


    /**
     * Method to tokanize the VR content url.
     * @param url content url.
     * @return tokenized akamai url
     */
    public static String authorizeStreamUrl(String url) {

        System.out.println("authorizeUrl: " + url);

        // setup necessary params set
        Hashtable tokenConfig = new Hashtable();
        tokenConfig.put("key", getVRCDNSecretKeyFromJNI());
        tokenConfig.put("token_name", "hdnea");
        tokenConfig.put("acl", "/*");

//        long serverTimeDelta = CacheManager.getInstance().getLong(CacheManager.KEY_SERVER_TIME_DELTA);

        System.out.println("systemTime: " + System.currentTimeMillis());

        // startTime = current time - delta - additional 2 minutes in order to avoid problems
        // if server time is a little out of sync
//        long startTime = System.currentTimeMillis() + serverTimeDelta - (2 * 60 * 1000);
        long startTime = System.currentTimeMillis();

        // pass start time timestamp in seconds
        tokenConfig.put("start_time", startTime / 1000);
        // TODO: check if "window_seconds" is necessary. For now works without it
        tokenConfig.put("window_seconds", "3600"); //TODO : Should be reduced to 10 minutes after tokenization issue resolved

        // default value will be input url
        // in case of exception will return unauthorized url
        String result = url;
        try {
            // append generated token part to url
            result = result + "?" + AkamaiToken.generateToken(tokenConfig);
            System.out.println("AkamaiToken: " + result);
        } catch (AkamaiTokenException e) {
            System.out.println("Error during Akamai token generation"+ e.getMessage());
        }

        return result;
    }

    public static String getAkamaiTokenForCDN() {
        String SECRET = getCDNSecretKeyFromJNI();

        // setup necessary params set
        Hashtable tokenConfig = new Hashtable();
        tokenConfig.put("key", SECRET);
        tokenConfig.put("token_name", "TOKEN_NAME");
        tokenConfig.put("acl", "/*");

//        long serverTimeDelta = CacheManager.getInstance().getLong(CacheManager.KEY_SERVER_TIME_DELTA);

//        Log.d(LOG_TAG, "systemTime: " + System.currentTimeMillis() + " delta: " + serverTimeDelta);

        // startTime = current time - delta - additional 2 minutes in order to avoid problems
        // if server time is a little out of sync
//        long startTime = System.currentTimeMillis() + serverTimeDelta - (2 * 60 * 1000);
        long startTime = System.currentTimeMillis();

        // pass start time timestamp in seconds
        tokenConfig.put("start_time", startTime / 1000);
        // TODO: check if "window_seconds" is necessary. For now works without it
        tokenConfig.put("window_seconds", "600");


        String result = "";
        try {
            // append generated token part to url
            result = AkamaiToken.generateToken(tokenConfig);
            System.out.println(("AkamaiToken: " + result));
        } catch (AkamaiTokenException e) {
            System.out.println("Error during Akamai token generation"+e.getMessage());
        }

        return result;
    }
}
