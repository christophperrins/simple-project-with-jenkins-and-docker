package com.nationwide.dto.token;

import java.util.Date;

public class ResponseTokenDto {

	private String usernameId;
	private String bearerToken;
	private Date expireDate;
	
	public String getUsernameId() {
		return usernameId;
	}
	public void setUsernameId(String usernameId) {
		this.usernameId = usernameId;
	}
	public String getBearerToken() {
		return bearerToken;
	}
	public void setBearerToken(String bearerToken) {
		this.bearerToken = bearerToken;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	
	
}
