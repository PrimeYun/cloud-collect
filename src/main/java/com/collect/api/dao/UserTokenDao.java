package com.collect.api.dao;

import com.collect.api.bean.UserToken;
import com.collect.common.base.BaseDao;

public interface UserTokenDao extends BaseDao<UserToken> {
	
	UserToken selectByToken(String token);
	
}
