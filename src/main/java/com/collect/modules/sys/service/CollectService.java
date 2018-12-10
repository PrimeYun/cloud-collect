package com.collect.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collect.common.base.BaseService;
import com.collect.common.utils.DateUtils;
import com.collect.common.utils.IdGen;
import com.collect.modules.sys.bean.Collect;
import com.collect.modules.sys.dao.CollectDao;

@Service
public class CollectService extends BaseService<CollectDao, Collect>{
	
	@Transactional(readOnly = false)
	public void insertCollect(Collect collect) {
		collect.setId(IdGen.getObjectId());
		collect.setCreateDate(DateUtils.getDate());
		dao.insertCollect(collect);
	}
}
