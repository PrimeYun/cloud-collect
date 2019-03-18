package com.collect.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	@Autowired
	private StringRedisTemplate stringTemplate;
	
	@Autowired
	private CollectSortService sortService;
	
	public Map<String, Object> getViewNum() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("viewNum", stringTemplate.opsForValue().increment("views", 1));
		return result;
	}
	
	@Cacheable(value = "collect_sort")
	public Map<String, Object> getCollectSort() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sort", sortService.selectList(result));
		return result;
	}
	
}
