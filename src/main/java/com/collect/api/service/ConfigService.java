package com.collect.api.service;

import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.collect.api.bean.Config;
import com.collect.api.dao.ConfigDao;
import com.collect.common.base.BaseService;

@Service
public class ConfigService extends BaseService<ConfigDao,Config> {
	
	public Config selectByKey(String key) {
		return dao.selectByKey(key);
	}
	
	public String getValue(String key) {
		Config config = this.selectByKey(key);
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
