package com.collect.common.service;

public interface RedisService {
	
	String get(String key);
	
	String set(String key, String value);
	
	Long setAndExp(String key, String value, Integer second);
	
	Long incr(String key);
	
	Long incrBy(String key, Long integer);
	
	Long expire(String key, Integer second);
	
}
