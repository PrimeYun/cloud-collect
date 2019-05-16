package com.collect.common.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collect.common.service.RedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service("redisServer")
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} finally {
			close(jedis);
		}
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.set(key, value);
		} finally {
			close(jedis);
		}
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incr(key);
		} finally {
			close(jedis);
		}
	}

	@Override
	public Long incrBy(String key, Long integer) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incrBy(key, integer);
		} finally {
			close(jedis);
		}
	}
	
	@Override
	public Long expire(String key, Integer i) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.expire(key, i);
		} finally {
			close(jedis);
		}
	}
	
	@Override
	public Long setAndExp(String key, String value, Integer second) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
			return jedis.expire(key, second);
		} finally {
			close(jedis);
		}
	}
	
	private void close(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}
	
}
