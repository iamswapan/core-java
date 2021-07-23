package com.demo;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class EncryptionService {
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";
	
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
	private static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";

	    
     static String symmetricKey=randomSymmetricKey();

    
    private static final String ALGO_AES="AES";
    
	public Response performEncryption(String requestJson) throws Exception {
		Response response=new Response();
		String jwsHeader=encodeBase64UTF(jwsProtectedHeader());
		String jwsPayload=encodeBase64UTF(requestJson);
		String jwsSignature=jwsSignature();
		String signature=generateDigitalSignature(jwsHeader,jwsPayload,jwsSignature);

		System.out.println("^^^^^^^^^^^^^"+signature);
		
       // String encryptedString= encryptData(signature);
        String encryptedString2= AESUtil.encrypt(signature,symmetricKey);
        
        response.setEncryptedPayload(encryptedString2);
        
        String ss=RSAUtil.encrypt(symmetricKey,publicKey);
        response.setEncryptedKey(ss);
        response.setTransactionId(symmetricKey);
        response.setScope(RSAUtil.decrypt(ss,privateKey));
        response.setOauthTokenValue(AESUtil.decrypt(encryptedString2,symmetricKey));
        return response;
	}
	
	
	private String encodeBase64(String input) {
		return new String(
	            Base64.getEncoder().encodeToString(input.getBytes()));
	}
	
	private String encodeBase64UTF(String input) throws UnsupportedEncodingException {
		return new String(
	            Base64.getEncoder().encodeToString(input.getBytes("UTF-8")));
	}
	
	
	private String encodeBase64UsingSHA(String input) throws NoSuchAlgorithmException {
		return new String(
	            Base64.getEncoder().encode(MessageDigest.getInstance("SHA-256").digest(input.getBytes(StandardCharsets.UTF_8))));
	}
	
	private String jwsProtectedHeader() {
		String header= "{\"typ\":\"JWT\",\"alg\":\"RS256\"}";
		return header;
	}
	
	private String jwsSignature() {
        String strPk = "-----BEGIN PRIVATE KEY-----\nMIIEvwIBADANBgkqhkiG9"
                + "w0BAQEFAASCBKkwggSlAgEAAoIBAQDJUGqaRB11KjxQ\nKHDeG"
                + "fdsfsfsdfdf..."
                + "Ldt0hAPNl4QKYWCfJm\nNf7Afqaa/RZq0+y/36v83NGENQ==\n"
                + "-----END PRIVATE KEY-----\n";
		String realPK = strPk.replaceAll("-----END PRIVATE KEY-----", "").replaceAll("-----BEGIN PRIVATE KEY-----", "")
				.replaceAll("\n", "");
        return realPK;
	}
	
	
	private String generateDigitalSignature(String header,String payload,String signature) {
		String joinedString = String.join(".", header, payload, signature);
		return joinedString.toString();
	}
	

	public static String randomSymmetricKey() {
		int count=32;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
		}
	/*
	
	private String encryptData(String inputData) throws Exception {
        Key key = generateKey(ALGO_AES);
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encVal = cipher.doFinal(inputData.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
	}
	
*/
	   


}
