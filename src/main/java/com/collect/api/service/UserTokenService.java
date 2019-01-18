package com.collect.api.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collect.api.bean.UserToken;
import com.collect.api.dao.UserTokenDao;
import com.collect.common.base.BaseService;
import com.collect.common.utils.IdGen;

import cn.hutool.core.util.ObjectUtil;

@Service
public class UserTokenService extends BaseService<UserTokenDao, UserToken> {
	
	//一个月后过期
	private final static int EXPIER = 720 * 3600;
	
	public UserToken selectByToken(String token) {
		return dao.selectByToken(token);
	}
	
	@Transactional(readOnly = false)
	public UserToken createToken(String userId) {
		//生成token 
		String token = IdGen.uuid();
		
		//当前时间
		Date now = new Date();
		
		//过期时间
		Date expireDate = new Date(now.getTime() + EXPIER * 1000);
		
		UserToken userToken = super.selectById(userId);
		if (ObjectUtil.isNotNull(userToken)) {
			userToken.setToken(token);
			userToken.setUpdateDate(now);
			userToken.setExpireDate(expireDate);
			
			dao.update(userToken);
		} else {
			userToken = new UserToken();
			userToken.setUserId(userId);
			userToken.setToken(token);
			userToken.setUpdateDate(now);
			userToken.setExpireDate(expireDate);
			
			dao.insert(userToken);
		}
		
		return userToken;
	}
	
	public String removeToken(String userId) {
		//退出时用新token覆盖旧的
		String token = IdGen.uuid();
		// dao.update(userToken)
		return token;
	}
	
}
