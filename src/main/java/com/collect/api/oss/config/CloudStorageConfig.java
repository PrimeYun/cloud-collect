package com.collect.api.oss.config;

import java.io.Serializable;

public class CloudStorageConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//类型   0:本地 ，1:阿里云
	private Integer type;
	
	private LocalStorageConfig local;
	
	private AliStorageConfig ali;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public LocalStorageConfig getLocal() {
		return local;
	}

	public void setLocal(LocalStorageConfig local) {
		this.local = local;
	}

	public AliStorageConfig getAli() {
		return ali;
	}

	public void setAli(AliStorageConfig ali) {
		this.ali = ali;
	}
}
