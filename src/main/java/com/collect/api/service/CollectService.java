package com.collect.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collect.api.bean.Collect;
import com.collect.api.dao.CollectDao;
import com.collect.common.base.BaseService;
import com.collect.common.utils.DateUtils;

@Service
public class CollectService extends BaseService<CollectDao, Collect>{
	
	@Transactional(readOnly = false)
	public void insertCollect(Collect collect) {
		collect.setCreateDate(DateUtils.getDate());
		dao.insert(collect);
	}
}
