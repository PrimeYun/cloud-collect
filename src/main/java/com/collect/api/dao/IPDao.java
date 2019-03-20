package com.collect.api.dao;

import com.collect.api.bean.IP;
import com.collect.common.base.BaseDao;

public interface IPDao extends BaseDao<IP> {
	
	IP selectByIP(String ip);
	
}
