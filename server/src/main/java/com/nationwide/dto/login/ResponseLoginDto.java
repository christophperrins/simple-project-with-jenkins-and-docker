package com.nationwide.dto.login;

public class ResponseLoginDto {

	private String username;
	private String bearerToken;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBearerToken() {
		return bearerToken;
	}
	public void setBearerToken(String bearerToken) {
		this.bearerToken = bearerToken;
	}
	
	
}
