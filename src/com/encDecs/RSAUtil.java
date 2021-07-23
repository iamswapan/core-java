package com.encDecs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {

	static final String KEYSTORE_FILE = "C:\\Users\\ankitsingh01\\Downloads\\fmp.p12";
	static final String KEYSTORE_PWD = "password";
	static final String KEYSTORE_ALIAS = "1";
	private static final String Public_key_path = "C:\\Users\\ankitsingh01\\Downloads\\rsa_apikey_4096.pem";
	private static final String RSA_ECB_PKCS1PADDING = "RSA/ECB/PKCS1PADDING";
	private static final String KEYSTORE_JKS = "JKS";

    public static String decryptAsymm(String b64EncryptedMsg)
            throws NoSuchAlgorithmException, NoSuchPaddingException, CertificateException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnrecoverableKeyException, KeyStoreException, IOException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        Key key = loadPrivateKeyFromFile();
        byte[] encryptedMsg = Base64.getDecoder().decode(b64EncryptedMsg);
        cipher.init(Cipher.PRIVATE_KEY, key);
        byte[] decryptedMsg = cipher.doFinal(encryptedMsg);
        return new String(decryptedMsg);
    }

    public static Key loadPrivateKeyFromFile() throws CertificateException, KeyStoreException,
            NoSuchAlgorithmException, IOException, UnrecoverableKeyException {
        Key key = null;
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream(KEYSTORE_FILE), KEYSTORE_PWD.toCharArray());
        key = keyStore.getKey(KEYSTORE_ALIAS, KEYSTORE_PWD.toCharArray());
        return key;
    }

    public static byte[] sign(String data) throws InvalidKeyException, Exception{
        Signature rsa = Signature.getInstance("SHA256withRSA");
        rsa.initSign(generatePrivateKey());
        rsa.update(data.getBytes());
        return rsa.sign();
    }

	public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
		Signature publicSignature = Signature.getInstance("SHA256withRSA");
		publicSignature.initVerify(publicKey);
		publicSignature.update(plainText.getBytes("UTF-8"));
		byte[] signatureBytes = Base64.getDecoder().decode(signature);
		return publicSignature.verify(signatureBytes);
	}

    
    public static PrivateKey generatePrivateKey(){
        PrivateKey key=null;
        try {
            KeyStore ks = KeyStore.getInstance(KEYSTORE_JKS);
            ks.load(new FileInputStream(KEYSTORE_FILE), KEYSTORE_PWD.toCharArray());
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(KEYSTORE_ALIAS, new KeyStore.PasswordProtection(KEYSTORE_PWD.toCharArray()));
            key = privateKeyEntry.getPrivateKey();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return key;
    }

    public static String encryptAsymm(String b64Msg)
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, CertificateException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        PublicKey pk=generatePublicKey(Public_key_path);
    	Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1PADDING); 
		cipher.init(Cipher.ENCRYPT_MODE,pk);
        return Base64.getEncoder().encodeToString(cipher.doFinal(b64Msg.getBytes()));
    }

    public static PublicKey generatePublicKey(String filePath) throws FileNotFoundException, CertificateException {
        FileInputStream fin = new FileInputStream(filePath);
        CertificateFactory factory = CertificateFactory.getInstance("X509");
        X509Certificate bankCertificate = (X509Certificate) factory.generateCertificate(fin);
        PublicKey bankPublicKey = bankCertificate.getPublicKey();
        return bankPublicKey;
    }

    
	public static byte[] decrypt(byte[] data, PrivateKey key) {
		byte[] decryptedValue = null;
		try {
			Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1PADDING);
			data= Base64.getDecoder().decode(data);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decryptedValue = cipher.doFinal(data);
		}
		catch(NoSuchAlgorithmException exp) {
			//TODO handle exception
			exp.printStackTrace();
		}
		catch(NoSuchPaddingException exp) {
			//TODO handle exception
			exp.printStackTrace();
		}
		catch(IllegalBlockSizeException exp) {
			//TODO handle exception
			exp.printStackTrace();
		}
		catch(BadPaddingException exp) {
			//TODO handle exception
			exp.printStackTrace();
		}
		catch(InvalidKeyException exp) {
			//TODO handle exception
			exp.printStackTrace();
		}
		return decryptedValue;
	}	
}


