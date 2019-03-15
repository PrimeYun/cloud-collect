package com.collect.api.bean;

import java.io.Serializable;

public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String cKey;

    private String cValue;

    private String remarks;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getcKey() {
		return cKey;
	}

	public void setcKey(String cKey) {
		this.cKey = cKey;
	}

	public String getcValue() {
		return cValue;
	}

	public void setcValue(String cValue) {
		this.cValue = cValue;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
}
