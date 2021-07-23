package com.ds;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SBIService {

    // custom gen6
    private static final String SKE_TRANSFORMATION = "AES/GCM/NoPadding";
    //PA gen5
//    	private static final String SKE_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String AKE_TRANSFORMATION = "RSA/ECB/OAEPPadding";
    private static final String AES_ALOGRITHM = "AES";
    private static final String RSA_ENCRYPTION_ALOGRITHM = "RSA";
    private static final String DYNAMIC_KEY_PREFIX = "SBI-lead-creation#^#";
    private static final String DELIMETER = "~";
    private static final String SHA_256 = "SHA-256";
    private static final Random RANDOM = new Random();
    private static final String SBI_CERT_PATH = "/com/ds/certs/EIS_ENC_UAT_2.crt";
    private static final String FMP_CERT_PATH = "com/ds/certs/star_marutifinancemarketplace_com.crt";
    private static final String MSIL_PRIVATE_KEY_PATH = "/com/ds/certs/fmp.p12";
    private static final String KEYSTORE_INSTANCE = "JKS";
    private static final String KEYSTORE_PWD = "password";
    private static final String KEYSTORE_ALIAS = "1";
    private static final String MSIL_CERT_PATH = "star_marutifinancemarketplace_com.crt";
    private static final String DIGI_SIGN_ALGO = "SHA256withRSA";

    private static String prepareDynamicKey() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String curDate = dateFormat.format(date);

        String randomNo = String.valueOf((RANDOM.nextInt(900) + 100));
        return DYNAMIC_KEY_PREFIX + curDate + DELIMETER + randomNo;
    }

    private static String encryptPayload(String plainPayload, String key) {
        //Custom gen6
        GCMParameterSpec ivspec = new GCMParameterSpec(128, Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 12));
        //PA gen5
//		IvParameterSpec ivspec = new IvParameterSpec(Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 16));
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), AES_ALOGRITHM);
        String encryptedPayload = plainPayload;
        try {
            Cipher cipher = Cipher.getInstance(SKE_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);
            byte[] cipherText = cipher.doFinal(plainPayload.getBytes(StandardCharsets.UTF_8));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedPayload = encoder.encodeToString(cipherText);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedPayload;
    }

    private static String encryptKey(String plainKey) {

        String encryptedKey = plainKey;
        try {

            Cipher cipher = Cipher.getInstance(AKE_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, createCert(SBI_CERT_PATH).getPublicKey());
            byte[] cipherText = cipher.doFinal(plainKey.getBytes(StandardCharsets.UTF_8));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedKey = encoder.encodeToString(cipherText);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedKey;
    }

    private static String encryptKeyForFMP(String plainKey) {

        String encryptedKey = plainKey;
        try {

            Cipher cipher = Cipher.getInstance(AKE_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, createCert(FMP_CERT_PATH).getPublicKey());
            byte[] cipherText = cipher.doFinal(plainKey.getBytes(StandardCharsets.UTF_8));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedKey = encoder.encodeToString(cipherText);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedKey;
    }

    private static String prepareDigitalSignature(String plainPayload) {
        String digitalSignature = null;

        try {
            Signature rsa = Signature.getInstance(DIGI_SIGN_ALGO);
            rsa.initSign(generatePrivateKey());
            rsa.update(plainPayload.getBytes());
            digitalSignature = Base64.getEncoder().encodeToString(rsa.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return digitalSignature;
    }

    private static String prepareDigitalSignatureForFMP(String plainPayload) {
        String digitalSignature = null;

        try {
            Signature rsa = Signature.getInstance(DIGI_SIGN_ALGO);
            rsa.initSign(generatePrivateKey());
            rsa.update(plainPayload.getBytes());
            digitalSignature = Base64.getEncoder().encodeToString(rsa.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return digitalSignature;
    }

    private static String decryptPayload(String encryptedPayload, String key) {
        /////////Custom gen6//////////
        GCMParameterSpec ivspec = new GCMParameterSpec(128, Arrays.copyOf(key.getBytes(StandardCharsets.UTF_8), 12));
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), AES_ALOGRITHM);
        //////////////////
        // PA gen5
//        IvParameterSpec ivspec = new IvParameterSpec(Arrays.copyOf(key.getBytes(), 16));
//        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES_ALOGRITHM);
        String plainPayload = encryptedPayload;
        try {
            Cipher cipher = Cipher.getInstance(SKE_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);
            byte[] text = cipher.doFinal(Base64.getDecoder().decode(encryptedPayload));
            plainPayload = new String(text, StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plainPayload;
    }

    private static boolean verifyDigitalSignature(String ds, String decryptedPayload) {
        try {

            Signature rsa = Signature.getInstance(DIGI_SIGN_ALGO);
            rsa.initVerify(createCert(SBI_CERT_PATH));
            rsa.update(decryptedPayload.getBytes());
            return rsa.verify(Base64.getDecoder().decode(ds));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static PrivateKey generatePrivateKey() {
        PrivateKey key = null;
        Class<?> clazz = SBIService.class;
        try (InputStream inputStream = clazz.getResourceAsStream(MSIL_PRIVATE_KEY_PATH)) {
            KeyStore ks = KeyStore.getInstance(KEYSTORE_INSTANCE);
            ks.load(inputStream, KEYSTORE_PWD.toCharArray());
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(KEYSTORE_ALIAS,
                    new KeyStore.PasswordProtection(KEYSTORE_PWD.toCharArray()));
            key = privateKeyEntry.getPrivateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    private static X509Certificate createCert(String filePath) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X509");
            Class<?> clazz = SBIService.class;
            InputStream inputStream = clazz.getResourceAsStream(filePath);
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inputStream);
            return cert;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) {
        submission();
//		status();
    }

    private static void status() {
        Scanner scn = new Scanner(System.in);

        String payload = "{\"application_status\":340005,\"last_updated_timestamp\":\"2020-06-10 03:01:11\",\"status_comment\":\"data updated successfully.\",\"approved_tenure\":\"24\",\"approved_ROI\":\"8.20\",\"approved_loan_amount\":\"0\",\"approver_comment\":\"Not eligible as per documents provided\",\"loan_type_id\":330001,\"applicant_Id\":\"\",\"processing_fee\":2000,\"address1\":\"Present Address\",\"address2\":\"Official\",\"address3\":\"communication address /12\",\"address4\":\"address4\",\"address_type_id\":170002,\"district_id\":180248,\"state_id\":120004,\"pin_code\":124001,\"city_id\":130002,\"country\":\"India\",\"landmark\":\"Near Water Supply Office\"}";

        System.out.println("---------- SBI ----------");
        String key = prepareDynamicKey();
        String encryptedPayload = encryptPayload(payload, key);
        String encryptedKey = encryptKeyForFMP(key);
        String ds = prepareDigitalSignatureForFMP(payload);
        System.out.println("payload: " + payload);
        System.out.println("Key: " + key);
        System.out.println();
        System.out.println("encrypted payload: " + encryptedPayload);
        System.out.println("encrypted Key: " + encryptedKey);
        System.out.println("digital signature: " + ds);
        System.out.println("-------------------------");

        System.out.println("---------- FMP ----------");
        System.out.println("Enter sbi response: ");
        String fmpEncryptedPayload = scn.next();

        String fmpdecryptedPayload = decryptPayload(fmpEncryptedPayload, key);
        System.out.println("Enter sbi ds: ");
        String fmpDS = scn.next();
        System.out.println("encrypted payload: " + fmpEncryptedPayload);
        System.out.println("encrypted Key: " + encryptedKey);
        System.out.println("digital signature: " + fmpDS);
        System.out.println();

        System.out.println("decrypted Key: " + key);
        System.out.println("decrypted payload: " + fmpdecryptedPayload);
        System.out.println("digital signature verified: " + verifyDigitalSignature(fmpDS, fmpdecryptedPayload));

    }

    private static void submission() {
        Scanner scn = new Scanner(System.in);

        // for lead creation pa
//		String payload = "{\"SOURCE_ID\":\"MSIL\",\"DATE_OF_BIRTH\":\"15081964\",\"MOBILE\":\"8871867334\",\"PRODUCT_CATEGORY\":\"12\",\"FIRST_NAME\":\"Sahil\",\"MIDDLE_NAME\":\"K\",\"LAST_NAME\":\"Mehta\",\"LEAD_AMOUNT\":\"100000\",\"PRODUCT\":\"113791\",\"LEAD_SOURCE\":\"86\",\"PREFERRED_BRANCH\":\"18093\",\"EXISTING_CUSTOMER\":\"2\",\"LEAD_RATING\":\"1\",\"YONO_FLAG\":\"N\",\"CAR_MAKE\":\"1001292\",\"CAR_MODEL\":\"1001459\",\"CAR_VARIANT\":\"1001984\",\"ON_ROAD_PRICE\":\"4801001\",\"VEHICLE_CATEGORY\":\"\",\"EX_SHOWROOM_PRICE\":\"4700100\",\"ACTUAL_VEHICLE_COST\":\"4801001\",\"VEHICLE_INSURANCE\":\"37958\",\"REQUEST_REFERENCE_NUMBER\":\"SBIMS20339214250220211148\",\"LOAN_PURPOSE\":\"New car loan\"}";
//		String payload = "{\"SOURCE_ID\":\"MS\",\"DATE_OF_BIRTH\":\"15081964\",\"MOBILE\":\"8871867334\",\"PRODUCT_CATEGORY\":\"12\",\"FIRST_NAME\":\"Sahil\",\"MIDDLE_NAME\":\"K\",\"LAST_NAME\":\"Mehta\",\"LEAD_AMOUNT\":\"100000\",\"PRODUCT\":\"113791\",\"LEAD_SOURCE\":\"86\",\"PREFERRED_BRANCH\":\"0000\",\"EXISTING_CUSTOMER\":\"2\",\"LEAD_RATING\":\"1\",\"YONO_FLAG\":\"N\",\"CAR_MAKE\":\"1001292\",\"CAR_MODEL\":\"1001459\",\"CAR_VARIANT\":\"1001984\",\"ON_ROAD_PRICE\":\"4801001\",\"VEHICLE_CATEGORY\":\"\",\"EX_SHOWROOM_PRICE\":\"4700100\",\"ACTUAL_VEHICLE_COST\":\"4801001\",\"VEHICLE_INSURANCE\":\"37958\",\"REQUEST_REFERENCE_NUMBER\":\"SBIMS20339214250220211148\",\"LOAN_PURPOSE\":\"New car loan\"}";

        // for status check
//		String payload = "{\"SOURCE_ID\":\"MSIL\",\"CRM_LEAD_ID\":\"3978154\"}";


        // for lead creation salaried custom
//        String payload = "{\"PRODUCT_CATEGORY\":\"12\",\"SALUTATION\":\"13\",\"FIRST_NAME\":\"Swapan\",\"MIDDLE_NAME\":\"Kumar\",\"LAST_NAME\":\"Mishra\",\"GENDER\":\"1\",\"MOBILE\":\"9873979617\",\"TELEPHONE\":\"\",\"ADDRESS_1\":\"D-3-33 vashish park\",\"ADDRESS_2\":\"Janak puri\",\"PIN_CODE\":\"110045\",\"EMAIL_ID\":\"swapan.mishra@nagarro.com\",\"DATE_OF_BIRTH\":\"03/09/1989\",\"IDENTIFICATION_PROOF\":\"4\",\"IDENTIFICATION_NUMBER\":\"A123\",\"LOAN_PURPOSE\":\"New car loan\",\"LEAD_AMOUNT\":\"1000000\",\"DESCRIPTION\":\"\",\"PRODUCT\":\"91\",\"LEAD_SOURCE\":\"86\",\"PREFERRED_BRANCH\":\"4384\",\"EXISTING_CUSTOMER\":\"2\",\"CIF_NUMBER\":\"\",\"RESIDENTIAL_STATUS\":\"7\",\"LEAD_RATING\":\"1\",\"ASSIGN_TO_CODE\":\"\",\"YONO_FLAG\":\"N\",\"CAR_MAKE\":\"1001292\",\"CAR_MODEL\":\"1001459\",\"MAKE_YEAR\":\"\",\"CAR_VARIANT\":\"1001987\",\"EX-SHOWROOM_PRICE\":\"900000\",\"ON_ROAD_PRICE\":\"1000000\",\"VEHICLE_CATEGORY\":\"\",\"ACTUAL_VEHICLE_COST\":\"1000000\",\"COST_OF_ACCESSORIES_EXTENDED_WARRANTY_TOTAL_SERVISE_PACKAGE_AMC\":\"90000\",\"ROAD_TAX_OCTROI_REGISTRATION\":\"40000\",\"VEHICLE_INSURANCE\":\"30000\",\"LAYOUT_ID\":\"100043\",\"STATUS_CODE\":\"100002\",\"PAN\":\"ASDFG1234G\",\"CITY\":\"\",\"STATE\":\"7\",\"COUNTRY\":\"100006\",\"NAME_OF_EMPLOYER\":\"\",\"MONTHLY_INCOME\":\"1000000\",\"LOAN_TENURE\":\"60\",\"CIBIL_SCORE\":\"\",\"SOURCE_ID\":\"MS\"}";
        String payload = "{\"PRODUCT_CATEGORY\":\"12\",\"SALUTATION\":\"13\",\"FIRST_NAME\":\"Swapan\",\"MIDDLE_NAME\":\"Kumar\",\"LAST_NAME\":\"Mishra\",\"GENDER\":\"1\",\"MOBILE\":\"9873979617\",\"ADDRESS_1\":\"D-3-33 vashish park\",\"ADDRESS_2\":\"Janak puri\",\"PIN_CODE\":\"110045\",\"EMAIL_ID\":\"swapan.mishra@nagarro.com\",\"DATE_OF_BIRTH\":\"03/09/1989\",\"IDENTIFICATION_PROOF\":\"7\",\"IDENTIFICATION_NUMBER\":\"AYWER9876D\",\"LOAN_PURPOSE\":\"New car loan\",\"LEAD_AMOUNT\":\"1000000\",\"PRODUCT\":\"91\",\"LEAD_SOURCE\":\"86\",\"PREFERRED_BRANCH\":\"4384\",\"RESIDENTIAL_STATUS\":\"7\",\"LEAD_RATING\":\"1\",\"YONO_FLAG\":\"N\",\"CAR_MAKE\":\"1001292\",\"CAR_MODEL\":\"1001459\",\"CAR_VARIANT\":\"1001987\",\"EX_SHOWROOM_PRICE\":\"900000\",\"ON_ROAD_PRICE\":\"1000000\",\"ACTUAL_VEHICLE_COST\":\"1000000\",\"COST_OF_ACCESSORIES_EXTENDED_WARRANTY_TOTAL_SERVISE_PACKAGE_AMC\":\"90000\",\"ROAD_TAX_OCTROI_REGISTRATION\":\"40000\",\"VEHICLE_INSURANCE\":\"30000\",\"LAYOUT_ID\":\"100043\",\"STATUS_CODE\":\"100002\",\"PAN\":\"ASDFG1234G\",\"STATE\":\"7\",\"COUNTRY\":\"100006\",\"MONTHLY_INCOME\":\"1000000\",\"LOAN_TENURE\":\"60\",\"SOURCE_ID\":\"MS\"}";

        // for Loan Status API
        //String payload = "{\"CRM_LEAD_ID\":\"3981189\",\"SOURCE_ID\":\"MS\"}";

        System.out.println("---------- FMP ----------");
        String key = prepareDynamicKey();
        String encryptedPayload = encryptPayload(payload, key);
        String encryptedKey = encryptKey(key);
        String ds = prepareDigitalSignature(payload);
        System.out.println("payload: " + payload);
        System.out.println("Key: " + key);
        System.out.println();
        System.out.println("encrypted payload: " + encryptedPayload);
        System.out.println("encrypted Key: " + encryptedKey);
        System.out.println("digital signature: " + ds);
        System.out.println("-------------------------");

        System.out.println("---------- SBI ----------");
        System.out.println("Enter sbi response: ");
        String sbiEncryptedPayload = scn.next();

        String sbidecryptedPayload = decryptPayload(sbiEncryptedPayload, key);
        System.out.println("Enter sbi ds: ");
        String sbids = scn.next();
        System.out.println("encrypted payload: " + sbiEncryptedPayload);
        System.out.println("encrypted Key: " + encryptedKey);
        System.out.println("digital signature: " + sbids);
        System.out.println();

        System.out.println("decrypted Key: " + key);
        System.out.println("decrypted payload: " + sbidecryptedPayload);
        System.out.println("digital signature verified: " + verifyDigitalSignature(sbids, sbidecryptedPayload));
    }

    private static String decryptKeyAtFin(String encryptedKey) {
        try {

            Cipher cipher = Cipher.getInstance(AKE_TRANSFORMATION);
//			cipher.init(Cipher.DECRYPT_MODE, null);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedKey));
            return new String(plainText, StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean verifyDigitalSignatureAtFin(String digitalSignature, String plainPayload) {

        try {

            Signature rsa = Signature.getInstance(DIGI_SIGN_ALGO);
            rsa.initVerify(createCert(SBI_CERT_PATH));
            rsa.update(plainPayload.getBytes());
            return rsa.verify(Base64.getDecoder().decode(digitalSignature));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static String prepareDigitalSignatureAtFin(String plainPayload) {
        String digitalSignature = null;

        try {
            Signature rsa = Signature.getInstance(DIGI_SIGN_ALGO);
            rsa.initSign(generatePrivateKeyAtFin());
            rsa.update(plainPayload.getBytes());
            digitalSignature = Base64.getEncoder().encodeToString(rsa.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return digitalSignature;
    }

    private static PrivateKey generatePrivateKeyAtFin() {
        PrivateKey key = null;
        Class<?> clazz = SBIService.class;
        try (InputStream inputStream = clazz.getResourceAsStream("")) {
            KeyStore ks = KeyStore.getInstance(KEYSTORE_INSTANCE);
            ks.load(inputStream, KEYSTORE_PWD.toCharArray());
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(KEYSTORE_ALIAS,
                    new KeyStore.PasswordProtection(KEYSTORE_PWD.toCharArray()));
            key = privateKeyEntry.getPrivateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    private static String getMockPublicKey() {
        return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqGKukO1De7zhZj6+H0qtjTkVxwTCpvKe4eCZ0FPqri0cb2JZfXJ/DgYSF6vUpwmJG8wVQZKjeGcjDOL5UlsuusFncCzWBQ7RKNUSesmQRMSGkVb1/3j+skZ6UtW+5u09lHNsj6tQ51s1SPrCBkedbNf0Tp0GbMJDyR4e9T04ZZwIDAQAB";
    }

    private static String getMockPrivateKey() {
        return "MIICXAIBAAKBgQCqGKukO1De7zhZj6+H0qtjTkVxwTCpvKe4eCZ0FPqri0cb2JZfXJ/DgYSF6vUpwmJG8wVQZKjeGcjDOL5UlsuusFncCzWBQ7RKNUSesmQRMSGkVb1/3j+skZ6UtW+5u09lHNsj6tQ51s1SPrCBkedbNf0Tp0GbMJDyR4e9T04ZZwIDAQABAoGAFijko56+qGyN8M0RVyaRAXz++xTqHBLh3tx4VgMtrQ+WEgCjhoTwo23KMBAuJGSYnRmoBZM3lMfTKevIkAidPExvYCdm5dYq3XToLkkLv5L2pIIVOFMDG+KESnAFV7l2c+cnzRMW0+b6f8mR1CJzZuxVLL6Q02fvLi55/mbSYxECQQDeAw6fiIQXGukBI4eMZZt4nscy2o12KyYner3VpoeE+Np2q+Z3pvAMd/aNzQ/W9WaI+NRfcxUJrmfPwIGm63ilAkEAxCL5HQb2bQr4ByorcMWm/hEP2MZzROV73yF41hPsRC9m66KrheO9HPTJuo3/9s5p+sqGxOlFL0NDt4SkosjgGwJAFklyR1uZ/wPJjj611cdBcztlPdqoxssQGnh85BzCj/u3WqBpE2vjvyyvyI5kX6zk7S0ljKtt2jny2+00VsBerQJBAJGC1Mg5Oydo5NwD6BiROrPxGo2bpTbu/fhrT8ebHkTz2eplU9VQQSQzY1oZMVX8i1m5WUTLPz2yLJIBQVdXqhMCQBGoiuSoSjafUhV7i1cEGpb88h5NBYZzWXGZ37sJ5QsW+sJyoNde3xH8vdXhzU7eT82D6X/scw9RZz+/6rCJ4p0=";
    }
}