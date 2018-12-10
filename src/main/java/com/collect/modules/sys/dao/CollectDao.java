package com.collect.modules.sys.dao;

import com.collect.common.base.BaseDao;
import com.collect.modules.sys.bean.Collect;

public interface CollectDao extends BaseDao<Collect> {
	
	Integer insertCollect(Collect collect);
}
