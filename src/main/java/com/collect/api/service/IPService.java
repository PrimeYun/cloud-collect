package com.collect.api.service;

import org.springframework.stereotype.Service;

import com.collect.api.bean.IP;
import com.collect.api.dao.IPDao;
import com.collect.common.base.BaseService;

@Service
public class IPService extends BaseService<IPDao, IP> {
	
	public IP selectByIP(String ip) {
		return dao.selectByIP(ip);
	}
	
}
