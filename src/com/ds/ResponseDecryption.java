/*
package com.ds;

//import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
//import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//import com.loopj.android.http.Base64;

public class ResponseDecryption {

    public static boolean isSuccessResponse(String tokenReceivedFromServer, String unique_req_id, String deviceId) {
        String tokenForThisReq = null;

        try {
            tokenForThisReq = JwtTokenForReqId.getJwtTokenForReqId(unique_req_id, deviceId);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        if (tokenForThisReq == null) {
            System.out.println("RESPONSE Decryption status : false");
            return false;
        }
        if (tokenForThisReq.equals(tokenReceivedFromServer)) {
            System.out.println("RESPONSE Decryption status : true");
            return true;
        }
        System.out.println("RESPONSE Decryption status : false");
        return false;
    }


    public static String getDecryptedValueFor(String encryptedResponseField, String lastReceivedToken) {

        String decryptedValue = null;

        try {

            String decryptedString = getDecryptedTextFor(encryptedResponseField);
            if (decryptedString.contains(lastReceivedToken)) {
                decryptedValue = decryptedString.replaceAll(lastReceivedToken, "");
            } else { // This should never happen
                System.out.println("======== WRONG DATA RECEIVED...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return decryptedValue;

    }

    public static String getDecryptedTextFor(String base64Str) throws Exception {

        byte[] keyBytes = new byte[16];
        String key = "HDFCBANK!@#987MOBAPP";
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        byte[] base64ToByteArray = Base64.decode(base64Str, Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance(ApplicationVariables.AES_CBC_PKCS5_PADDING);

        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        String decryptedStr = new String(cipher.doFinal(base64ToByteArray), "UTF-8");

        return decryptedStr;
    }

}

*/
