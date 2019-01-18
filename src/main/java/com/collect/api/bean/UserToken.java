package com.collect.api.bean;

import java.io.Serializable;
import java.util.Date;

public class UserToken implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String token;
	
	private Date expireDate;
	
	private Date updateDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String toString() {
		return userId + "++" + token + "++" + expireDate + "++" + updateDate;
	}
	
}
