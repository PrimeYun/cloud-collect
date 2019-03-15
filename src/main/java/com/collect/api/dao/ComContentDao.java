package com.collect.api.dao;

import com.collect.api.bean.ComContent;
import com.collect.common.base.BaseDao;

public interface ComContentDao extends BaseDao<ComContent> {
	
	Integer insertCollect(ComContent collect);
	
	ComContent selectById(Integer id);
}
