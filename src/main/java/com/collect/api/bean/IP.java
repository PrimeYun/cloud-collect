package com.collect.api.bean;

import java.io.Serializable;

public class IP implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ip;

	private String createTime;

	private String updateTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
