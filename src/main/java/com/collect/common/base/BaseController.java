package com.collect.common.base;

import java.util.Map;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;

public abstract class BaseController {
	
	protected Object success() {
		return Result.success();
	}
	
	protected Object success(Object data) {
		Result result = Result.success();
		if (data != null) {
			if (data instanceof Page) {
				Page<?> page = (Page<?>) data;
				Map<String, Object> table = Maps.newHashMap();
				table.put("total", page.getTotal());
				table.put("list", page.getResult());
				result.setData(table);
			} else {
				result.setData(data);
			}
		}
		return result;
	}
	
	protected Object failure(String message) {
		return Result.failure(message);
	}

}
