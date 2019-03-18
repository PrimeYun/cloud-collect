package com.collect.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collect.api.service.RedisService;
import com.collect.common.base.BaseController;

@RestController
@RequestMapping("/redis")
public class RedisController extends BaseController {
	
	@Autowired
	private RedisService redisService;
	
	@GetMapping("num")
	public Object num() {
		return success(redisService.getViewNum());
	}
	
	@GetMapping("sort")
	public Object sort() {
		return success(redisService.getCollectSort());
	}
}
