package com.collect.api.service;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	@Resource
	private RedisTemplate<Object, Object> redisTemplate;
	
	public void setString(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}
	
	public String getString(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}
	
	public void setList(String key, Object value) {
		redisTemplate.opsForList().leftPush(key, value);
	}
	
	public String getList(String key) {
		return (String) redisTemplate.opsForList().rightPop(key);
	}

}
