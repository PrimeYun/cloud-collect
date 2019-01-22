package com.collect.api.dao;

import com.collect.api.bean.Content;
import com.collect.common.base.BaseDao;

public interface ContentDao extends BaseDao<Content> {
	
	Integer insertCollect(Content collect);
}
