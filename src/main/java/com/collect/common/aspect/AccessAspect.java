package com.collect.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.collect.api.bean.IP;
import com.collect.api.service.IPService;
import com.collect.common.utils.DateUtils;
import com.collect.common.utils.HttpContextUtils;
import com.collect.common.utils.IPUtils;

import cn.hutool.core.util.ObjectUtil;

/**
 * 访问IP统计
 * @author zhangxingyun 2019年3月20日上午9:18:05
 *
 */
@Aspect
@Component
public class AccessAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessAspect.class);
	
	@Autowired
	private IPService ipService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Pointcut("@annotation(com.collect.common.annotation.Access)")
	public void pointcut() {
		// do something
	}
	
	@Before("pointcut()")
	public void doBefore(JoinPoint joinPoint) {
		 // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 获取IP地址
        String ip = IPUtils.getIpAddr(request);
        logger.info("当前访问IP为：" + ip);
        String time = DateUtils.getDate();
        if (ObjectUtil.isNull(ipService.selectByIP(ip))) {
        	stringRedisTemplate.opsForValue().increment("accessNum", 1);
        	IP item = new IP();
        	item.setIp(ip);
        	item.setCreateTime(time);
        	item.setUpdateTime(time);
        	ipService.insert(item);
        } else {
        	IP item = new IP();
        	item.setIp(ip);
        	item.setUpdateTime(time);
        	ipService.update(item);
        }
		
	}
}
