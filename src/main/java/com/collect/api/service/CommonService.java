package com.collect.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.collect.common.service.RedisService;

@Service
public class CommonService {
	
	@Autowired
	private CollectSortService sortService;
	
	@Autowired
	private RedisService redisService;
	
	public Map<String, Object> getViewNum() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("viewNum", redisService.incr("views"));
		result.put("accessNum", redisService.get("accessNum"));
		return result;
	}
	
	@Cacheable(value = "collect_sort")
	public Map<String, Object> getCollectSort() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sort", sortService.selectList(result));
		return result;
	}
	
}
