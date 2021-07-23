package com.ds;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

//import com.loopj.android.http.Base64;

public class JwtTokenForReqId {

    public static String getJwtTokenForReqId(String uniqueRequestId, String deviceId) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String secretKey = "";
        String response2 = null;
        String keyToEncode = "";
        StringBuffer sb, sb2, sb3;
        try {
            //STEP 1 //Remove ‘_’ from ApplicationVariables.DEVICE_UNIQUE_ID for below code only.
            String keyString = /*ApplicationVariables.DEVICE_UNIQUE_ID*/deviceId.replace("_", "") + "." + Utility.getCurrentDateIn_yyyymmdd() + "." + uniqueRequestId;

//           Log.e("KEYSTRING", keyString);

            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] result = mDigest.digest(keyString.getBytes());

            sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            String string = "{\"alg\":\"HS256\",\"typ\":\"HQR\"}";
            mDigest = MessageDigest.getInstance("SHA-256");
            byte[] result2 = mDigest.digest(string.getBytes());
            sb2 = new StringBuffer();
            for (int i = 0; i < result2.length; i++) {
                sb2.append(Integer.toString((result2[i] & 0xff) + 0x100, 16).substring(1));
            }
            //STEP 2.2
            String string1 = "{\"tokenId\":43124832,\"userId\":\"" + deviceId + "\",\"username\":\"Guest\",\"validity\":\"2016-05-07 17:07\"}";
            mDigest = MessageDigest.getInstance("SHA-256");
            byte[] result3 = mDigest.digest(string1.getBytes());
            sb3 = new StringBuffer();
            for (int i = 0; i < result3.length; i++) {
                sb3.append(Integer.toString((result3[i] & 0xff) + 0x100, 16).substring(1));
            }
            //STEP 2.3
            keyToEncode = sb2.toString() + sb3.toString();
            // Removing (.) DOT // sb.toString() should be assign to key and keyToEncode should be assign to message in HMAC_SHA256 function
            response2 = sb2.toString() + sb3.toString() + HMAC_SHA256(sb.toString(), keyToEncode);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }return response2;

    }


    private static String HMAC_SHA256(String secret, String message) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            hash = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(message.getBytes()));
        }
        catch (Exception e) {

        }
        return hash.trim();
    }


}

