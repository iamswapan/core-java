package com.encDecs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Base64;

//import org.springframework.stereotype.Service;

//@Service
public class EncryptionService {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "0123456789"
			+ "abcdefghijklmnopqrstuvxyz";
	private static final String Maruti_Public_key_path="C:\\Users\\ankitsingh01\\Downloads\\maruti_public.pem";


	static String symmetricKey=randomSymmetricKey();

	public DecryptedResponse performDecryption(String encrResp, String gwKey) throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
		PrivateKey pk=RSAUtil.generatePrivateKey();
		byte[] decrKey=RSAUtil.decrypt(gwKey.getBytes(), pk);
		byte[] decrResByte=AESUtil.decrypt(encrResp.getBytes(), decrKey);
		String decrJWSResponse= new String(decrResByte);
		decrJWSResponse=decrJWSResponse.split("\\.")[1];
		System.out.println("==========="+decrJWSResponse);
		String respData=new String(Base64.getUrlDecoder().decode(decrJWSResponse.getBytes()));
		DecryptedResponse response=new DecryptedResponse();
		response.setDecryptedPayload(respData);
		return response;
	}


	public EncryptedResponse performEncryption(String payload) throws Exception {

		payload="{\"Request\":{\"partner_code\":\"MUWP\",\"partner_uname\":\"MSIL_LOAN\",\"partner_pwd\":\"cgSSeMxLm733B8pGz7+tUA==\",\"TrnRefNo_Partner\":\"000648\",\"mobile_no\":\"9979583681\",\"identifier_name\":\"PANCardNo\",\"identifier_value\":\"AYEPK3339A\",\"SourceName\":\"SourceName\",\"ChannelName\":\"ChannelName\",\"udf_1\":\"udf_1\",\"udf_2\":\"udf_2\",\"udf_3\":\"udf_3\",\"udf_4\":\"udf_4\",\"udf_5\":\"udf_5\",\"udf_6\":\"udf_6\",\"udf_7\":\"udf_7\",\"udf_8\":\"udf_8\",\"udf_9\":\"udf_9\",\"udf_10\":\"udf_10\",\"udf_11\":\"udf_11\",\"udf_12\":\"udf_12\",\"udf_13\":\"udf_13\",\"udf_14\":\"udf_14\",\"udf_15\":\"udf_15\"}}";
		EncryptedResponse encryptedResponse=new EncryptedResponse();
		String jwsHeader=encodeBase64UTF(jwsProtectedHeader());
		String jwsPayload=encodeBase64UTF(payload);
		String jwsSignature=encodeBase64UTF(RSAUtil.sign((jwsHeader+"."+jwsPayload)));
		String signature=generateDigitalSignature(jwsHeader,jwsPayload,jwsSignature);
		
		PublicKey pk=RSAUtil.generatePublicKey(Maruti_Public_key_path);
		
		System.out.println("Signature Verification======"+RSAUtil.verify(jwsHeader+"."+jwsPayload, jwsSignature, pk));
		String encryptedSignature= Base64.getEncoder().encodeToString(AESUtil.encrypt(signature,symmetricKey.getBytes()));
		System.out.println(encryptedSignature);
		encryptedResponse.setEncryptedPayload(encryptedSignature);
		
		System.out.println("Random Generated Symmetry Key :"+symmetricKey);
		String symmetryKeyEncrypted=RSAUtil.encryptAsymm(symmetricKey);
		encryptedResponse.setEncryptedKey(symmetryKeyEncrypted);
		encryptedResponse.setOauthTokenValue("");
		return encryptedResponse;
	}



	private String encodeBase64UTF(String input) throws UnsupportedEncodingException {
		return new String(
				Base64.getEncoder().encodeToString(input.getBytes("UTF-8")));
	}

	private String encodeBase64UTF(byte[] input) throws UnsupportedEncodingException {
		return new String(
				Base64.getEncoder().encodeToString(input));
	}

	private String jwsProtectedHeader() {
		String header= "{\"typ\":\"JWT\",\"alg\":\"RS256\"}";
		return header;
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

}
