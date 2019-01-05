package com.collect.api.dao;

import com.collect.api.bean.Config;
import com.collect.common.base.BaseDao;

public interface ConfigDao extends BaseDao<Config> {

	Config selectByKey(String key);

}
