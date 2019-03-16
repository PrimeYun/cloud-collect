package com.collect.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	public Map<String, Object> getViewNum() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("viewNum", redisTemplate.opsForValue().increment("views", 1));
		return result;
	}
	
}
