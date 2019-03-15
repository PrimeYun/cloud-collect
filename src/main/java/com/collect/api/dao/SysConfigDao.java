package com.collect.api.dao;

import com.collect.api.bean.SysConfig;
import com.collect.common.base.BaseDao;

public interface SysConfigDao extends BaseDao<SysConfig> {

	SysConfig selectByKey(String key);

}
