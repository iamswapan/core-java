package com.encDecs;

public class EncryptedResponse {
	private String encryptedPayload;
	
	private String encryptedKey;
	
	private String scope;
	
	private String transactionId;
	
	private String oauthTokenValue;

	public String getEncryptedPayload() {
		return encryptedPayload;
	}

	public void setEncryptedPayload(String encryptedPayload) {
		this.encryptedPayload = encryptedPayload;
	}

	public String getEncryptedKey() {
		return encryptedKey;
	}

	public void setEncryptedKey(String encryptedKey) {
		this.encryptedKey = encryptedKey;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOauthTokenValue() {
		return oauthTokenValue;
	}

	public void setOauthTokenValue(String oauthTokenValue) {
		this.oauthTokenValue = oauthTokenValue;
	}
	
	
}
