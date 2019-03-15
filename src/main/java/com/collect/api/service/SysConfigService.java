package com.collect.api.service;

import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.collect.api.bean.SysConfig;
import com.collect.api.dao.SysConfigDao;
import com.collect.common.base.BaseService;

@Service
public class SysConfigService extends BaseService<SysConfigDao,SysConfig> {
	
	public SysConfig selectByKey(String key) {
		return dao.selectByKey(key);
	}
	
	public String getValue(String key) {
		SysConfig config = this.selectByKey(key);
		return config == null ? null : config.getcValue();
	}
	
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = this.getValue(key);
		if (StringUtils.isNotBlank(value)) {
			return new Gson().fromJson(value, clazz);
		}
		
		try {
			//newInstance调用无参构造函数
			return clazz.newInstance();
		} catch(Exception e) {
			throw new RuntimeException("获取参数失败");
		}
	}
	
}
