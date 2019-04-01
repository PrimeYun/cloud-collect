package com.collect.common.service;

public interface RedisService {
	
	String get(String key);
	
	String set(String key, String value);
	
	Long incr(String key);
	
	Long incrBy(String key, Long integer);
	
}
