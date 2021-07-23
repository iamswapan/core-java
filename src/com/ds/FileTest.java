package com.ds;

//import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class FileTest {
    public static void main(String[] args) {
        boolean dirFlag = false;

        String s1="rajasthan", s2="RAJasTHAN";

        System.out.println(s1.equalsIgnoreCase(s2));

        // create File object
        /*File stockDir = new File("/opt/myDirectory");

        try {
            dirFlag = stockDir.mkdir();
        } catch (SecurityException Se) {
            System.out.println("Error while creating directory in Java:" + Se);
        }

        if (dirFlag)
            System.out.println("Directory created successfully");
        else
            System.out.println("Directory was not created successfully");*/

        String filler1="QWERT12345";

//        String pf=getDecryptedValueFor("x9ccz3a751tPxsctlfCai9jd1P6QuOvO4AuTLY5En/8h/vPHxUHQEMJz0iU0i2M3", filler1);
//        String roi=getDecryptedValueFor("x9ccz3a751tPxsctlfCai12LxsSLNBbbYyvC3Oni+JGWFUV4mGrpW4zse2raaeUz", filler1);
//        String emiAmount=getDecryptedValueFor("Eg9pyru+CUcXAtklmkVEyb4qxY/6lV4FXR6VLqO9Ylp/faKSyp3/Oc1VSDqYwRWo", filler1);
//        String onholdStatus=getDecryptedValueFor("Eg9pyru+CUcXAtklmkVEyXT2LYrBYMu4q6IYXhhEqsYuTcRs0+BJn6vlBfJRC5+cRqjT9hg3J/NZl5jKBPjaDA==", filler1);
//        System.out.println(pf+"========="+roi);
//        System.out.println(emiAmount+"========="+onholdStatus);
        String activity=getDecryptedValueFor("mIcgLqkLEJVj6ef25eEehqOAeBtQPZ1J8LaJWb4YqcLda5Oi6bSmxYDFd8cmeZB+", filler1);
        //String onhold=getDecryptedValueFor("63dGia0lURTvJPVfe1pYJjgVV5rmu0YAk8OvXr6sPIZecqOaBWYb7ehSOup3daL5", filler1);


        Boolean respDecr=isSuccessResponse("0baad852ac0e6d2282b3ff3d072ee74eb3648bc73397de6c30eaabca4bd87c5a911eee666d340dcd258424a7d1e4919f5d8be2d04c20a634748687590078f8afU5sAkhlIyCPRx/11Reb0LpYNXWQACvmSLOuciueCui0=", "8FX6O28YA626F443MYUK", "Fintech_MSIL210121294033");
        System.out.println(activity+"=============="+"*********************====");
        //System.out.println("ResponseDesc======"+respDecr);


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
//        byte[] base64ToByteArray = Base64.decode(base64Str, "UTF-8");
        byte[] base64ToByteArray = Base64.getDecoder().decode(base64Str);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        String decryptedStr = new String(cipher.doFinal(base64ToByteArray), "UTF-8");

        return decryptedStr;
    }

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

}

