package com.collect.common.base;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	private int pageNum = 1;
	private int pageSize = 5;

	public Query() {
		super();
	}

	public Query(Map<String, Object> params) {
		this.putAll(params);

		// 分页参数
		if (params.get("pageNum") != null) {
			pageNum = Integer.parseInt((String) params.get("pageNum"));
		}
		if (params.get("pageSize") != null) {
			pageSize = Integer.parseInt((String) params.get("pageSize"));
		}

		this.put("pageNum", pageNum);
		this.put("pageSize", pageSize);

	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
