package com.collect.api.bean;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class ComContent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Excel(name = "标题", width = 40)
	private String name;
	
	@Excel(name = "链接", width = 120)
	private String url;
	
	@Excel(name = "来源", width = 40)
	private String source;
	
	@Excel(name = "创建日期", width = 40)
	private String createDate;
	
	private String remarks;
	
	private Integer sortId;
	
	@Excel(name = "分类")
	private String sort;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public ComContent() {
		
	}
	
	public ComContent(Integer id, String name, String url, String source, String createDate, String remarks,
			Integer sortId, String sort) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.source = source;
		this.createDate = createDate;
		this.remarks = remarks;
		this.sortId = sortId;
		this.sort = sort;
	}

}
