package com.collect.api.scheduled;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTask {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
	
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	// @Scheduled(fixedRate=10000)
	@Scheduled(cron="0 30 7 * * ?")
	protected void delTask() {
		Set<String> keys = redisTemplate.keys("weather:" + "*");
		redisTemplate.delete(keys);
		log.info("删除成功===================================");
	}
	
}
