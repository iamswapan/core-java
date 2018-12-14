/*
package com.akamai.airtel;

*/
/**
 * Created by ttnd on 24/9/18.
 *//*

import android.util.Log;

import java.util.Hashtable;

import in.startv.hotstar.utils.cache.manager.CacheManager;

*/
/**
 * Helper class for Akamai token generation.
 *
 * Created by uldis.indriksons on 03-Dec-15.
 *//*

public class AkamaiHelper {

    private static final String LOG_TAG = "AkamaiHelper";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    */
/**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     *//*

    public static native String getCDNSecretKeyFromJNI();

    */
/**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     *//*

    public static native String getVRCDNSecretKeyFromJNI();

    // TODO: hide the key somewhere
    private static final String SECRET = "<put your own secret key>";

    private static final String TOKEN_NAME = "<put your own token token_name>";

    public static String authorizeUrl(String url) {

        Log.d(LOG_TAG, "authorizeUrl: " + url);

        // setup necessary params set
        Hashtable tokenConfig = new Hashtable();
        tokenConfig.put("key", SECRET);
        tokenConfig.put("token_name", "TOKEN_NAME");
        tokenConfig.put("acl", "*/
/*");

        long serverTimeDelta = CacheManager.getInstance().getLong(CacheManager.KEY_SERVER_TIME_DELTA);

        Log.d(LOG_TAG, "systemTime: " + System.currentTimeMillis() + " delta: " + serverTimeDelta);

        // startTime = current time - delta - additional 2 minutes in order to avoid problems
        // if server time is a little out of sync
        long startTime = System.currentTimeMillis() + serverTimeDelta - (2 * 60 * 1000);

        // pass start time timestamp in seconds
        tokenConfig.put("start_time", startTime / 1000);
        // TODO: check if "window_seconds" is necessary. For now works without it
        tokenConfig.put("window_seconds", "600");

        // default value will be input url
        // in case of exception will return unauthorized url
        String result = url;
        try {
            // append generated token part to url
            result = result + "&" + AkamaiToken.generateToken(tokenConfig);
            Log.d(LOG_TAG, "AkamaiToken: " + result);
        } catch (AkamaiTokenException e) {
            Log.e(LOG_TAG, "Error during Akamai token generation", e);
        }

        return result;
    }


    public static String authorizeAdFormPostUrl(String url) {

        Log.d(LOG_TAG, "authorizeUrl: " + url);

        // setup necessary params set
        Hashtable tokenConfig = new Hashtable();
        tokenConfig.put("key", SECRET);
        tokenConfig.put("token_name", "TOKEN_NAME");
        tokenConfig.put("acl", "*/
/*");

        long serverTimeDelta = CacheManager.getInstance().getLong(CacheManager.KEY_SERVER_TIME_DELTA);

        Log.d(LOG_TAG, "systemTime: " + System.currentTimeMillis() + " delta: " + serverTimeDelta);

        // startTime = current time - delta - additional 2 minutes in order to avoid problems
        // if server time is a little out of sync
        long startTime = System.currentTimeMillis() + serverTimeDelta - (2 * 60 * 1000);

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
            Log.d(LOG_TAG, "AkamaiToken: " + result);
        } catch (AkamaiTokenException e) {
            Log.e(LOG_TAG, "Error during Akamai token generation", e);
        }

        return result;
    }


    */
/**
     * Method to tokanize the VR content url.
     * @param url content url.
     * @return tokenized akamai url
     *//*

    public static String authorizeStreamUrl(String url) {

        Log.d(LOG_TAG, "authorizeUrl: " + url);

        // setup necessary params set
        Hashtable tokenConfig = new Hashtable();
        tokenConfig.put("key", getVRCDNSecretKeyFromJNI());
        tokenConfig.put("token_name", "hdnea");
        tokenConfig.put("acl", "*/
/*");

        long serverTimeDelta = CacheManager.getInstance().getLong(CacheManager.KEY_SERVER_TIME_DELTA);

        Log.d(LOG_TAG, "systemTime: " + System.currentTimeMillis() + " delta: " + serverTimeDelta);

        // startTime = current time - delta - additional 2 minutes in order to avoid problems
        // if server time is a little out of sync
        long startTime = System.currentTimeMillis() + serverTimeDelta - (2 * 60 * 1000);

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
            Log.d(LOG_TAG, "AkamaiToken: " + result);
        } catch (AkamaiTokenException e) {
            Log.e(LOG_TAG, "Error during Akamai token generation", e);
        }

        return result;
    }

    public static String getAkamaiTokenForCDN() {
        String SECRET = getCDNSecretKeyFromJNI();

        // setup necessary params set
        Hashtable tokenConfig = new Hashtable();
        tokenConfig.put("key", SECRET);
        tokenConfig.put("token_name", "TOKEN_NAME");
        tokenConfig.put("acl", "*/
/*");

        long serverTimeDelta = CacheManager.getInstance().getLong(CacheManager.KEY_SERVER_TIME_DELTA);

        Log.d(LOG_TAG, "systemTime: " + System.currentTimeMillis() + " delta: " + serverTimeDelta);

        // startTime = current time - delta - additional 2 minutes in order to avoid problems
        // if server time is a little out of sync
        long startTime = System.currentTimeMillis() + serverTimeDelta - (2 * 60 * 1000);

        // pass start time timestamp in seconds
        tokenConfig.put("start_time", startTime / 1000);
        // TODO: check if "window_seconds" is necessary. For now works without it
        tokenConfig.put("window_seconds", "600");


        String result = "";
        try {
            // append generated token part to url
            result = AkamaiToken.generateToken(tokenConfig);
            Log.d(LOG_TAG, "AkamaiToken: " + result);
        } catch (AkamaiTokenException e) {
            Log.e(LOG_TAG, "Error during Akamai token generation", e);
        }

        return result;
    }

}*/
