package com.collect.common.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
@SuppressWarnings("unchecked")
public class RedisService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JedisPool jedisPool;
	
	public Object excuteByJedis(Function<Jedis, Object> f) {
		try (Jedis jedis = jedisPool.getResource()) {
            return f.apply(jedis);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
	}
	
	public Map<String, Object> getKeysSize() {
        long dbSize = (long) this.excuteByJedis(
                j -> {
                    Client client = j.getClient();
                    client.dbSize();
                    return client.getIntegerReply();
                }
        );
        Map<String, Object> map = new HashMap<>();
        map.put("create_time", System.currentTimeMillis());
        map.put("dbSize", dbSize);
        return map;
    }
	
	public Map<String, Object> getMemoryInfo() {
        String info = (String) this.excuteByJedis(
                j -> {
                    Client client = j.getClient();
                    client.info();
                    return client.getBulkReply();
                }
        );
        String[] strs = Objects.requireNonNull(info).split("\n");
        Map<String, Object> map = null;
        for (String s : strs) {
            String[] detail = s.split(":");
            if ("used_memory".equals(detail[0])) {
                map = new HashMap<>();
                map.put("used_memory", detail[1].substring(0, detail[1].length() - 1));
                map.put("create_time", System.currentTimeMillis());
                break;
            }
        }
        return map;
    }
	
	public Set<String> getKeys(String pattern) {
        return (Set<String>) this.excuteByJedis(j -> j.keys(pattern));
    }
	
	public String get(String key) {
		return (String) this.excuteByJedis(j -> j.get(key));
	}

	public String set(String key, String value) {
		return (String) this.excuteByJedis(j -> j.set(key, value));
	}

	public Long del(String... key) {
		return (Long) this.excuteByJedis(j -> j.del(key));
	}

	public Boolean exists(String key) {
		return (Boolean) this.excuteByJedis(j -> j.exists(key));
	}

	public Long pttl(String key) {
		return (Long) this.excuteByJedis(j -> j.pttl(key));
	}

	public Long pexpire(String key, Long milliseconds) {
		return (Long) this.excuteByJedis(j -> j.pexpire(key, milliseconds));
	}
}
