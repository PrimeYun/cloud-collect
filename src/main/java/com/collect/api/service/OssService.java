package com.collect.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.collect.api.bean.Oss;
import com.collect.api.dao.OssDao;
import com.collect.common.base.BaseService;
import com.collect.common.utils.IdGen;

public class OssService extends BaseService<OssDao, Oss>{
	
	@Autowired
	private ConfigService configService;
	
	@Transactional(readOnly = false)
	public boolean insert(Oss entity) {
		entity.setId(IdGen.getObjectId());
		return true;
	}
	
//	private CloudStorageConfig getCloudStorageConfig() {
//		return configService.getConfigObject("oss.config", CloudStorageConfig.class);
//	}
}
