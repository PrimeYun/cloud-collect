package com.collect.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collect.api.bean.UserToken;

@Service
public class LoginService {
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private UserTokenService tokenService;
	
	public String login(String username) {
		if ("zhang".equals(username)) {
			UserToken userToken = tokenService.createToken("123");
			redisService.setString(userToken.getToken(), userToken);
			return userToken.getToken();
		}
		return null;
	}
	
	
}
