package com.oauth1;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionTest {
    public static void main(String[] args) throws Exception {
        getEncryptedTextFor("D3PTD7J4YTVZ9TR6HLCE700000D3PTD7J4YTVZ9TR6HLCE");
        getEncryptedTextFor("D3PTD7J4YTVZ9TR6HLCE60D3PTD7J4YTVZ9TR6HLCE");
        getEncryptedTextFor("D3PTD7J4YTVZ9TR6HLCE9000D3PTD7J4YTVZ9TR6HLCE");
        getEncryptedTextFor("D3PTD7J4YTVZ9TR6HLCE5000D3PTD7J4YTVZ9TR6HLCE");
        getEncryptedTextFor("D3PTD7J4YTVZ9TR6HLCE10.5D3PTD7J4YTVZ9TR6HLCE");
    }

    public static String getEncryptedTextFor(String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];
        String key = "HDFCBANK!@#987MOBAPP";
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
        String value = new String(Base64.getEncoder().encodeToString(results));
        System.out.println(value);
        return value;
    }
}
