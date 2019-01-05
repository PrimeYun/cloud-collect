package com.collect.api.oss.config;

import java.io.Serializable;

public class LocalStorageConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//本地域名
	private String domain;
	
	//前缀
	private String prefix;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
