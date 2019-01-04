package com.collect.api.dao;

import com.collect.api.bean.Collect;
import com.collect.common.base.BaseDao;

public interface CollectDao extends BaseDao<Collect> {
	
	Integer insertCollect(Collect collect);
}
