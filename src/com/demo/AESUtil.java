package com.demo;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
	
    private static final String initVector = "encryptionIntVec";
    
    

	   public static String encrypt(String value,String symmetricKey) {
			try {
				IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
				SecretKeySpec skeySpec = new SecretKeySpec(symmetricKey.getBytes("UTF-8"), "AES");

				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

				byte[] encrypted = cipher.doFinal(value.getBytes());
				return Base64.getEncoder().encodeToString(encrypted);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}
	   
	   public static String decrypt(String encrypted, String symmetricKey) {
			try {
				IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
				SecretKeySpec skeySpec = new SecretKeySpec(symmetricKey.getBytes("UTF-8"), "AES");

				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
				cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		        byte[] decoded = Base64.getDecoder().decode(encrypted);        
		        byte[] cipherData = cipher.doFinal(decoded);
		        return new String(cipherData);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			return null;
		}
		

}
