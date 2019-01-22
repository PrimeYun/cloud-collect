package com.collect.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collect.api.bean.Collect;
import com.collect.api.dao.CollectDao;
import com.collect.api.vo.CollectVO;
import com.collect.common.base.BaseService;
import com.collect.common.base.Result;
import com.collect.common.utils.DateUtils;

import cn.hutool.core.bean.BeanUtil;

@Service
public class CollectService extends BaseService<CollectDao, Collect>{
	
	@Transactional(readOnly = false)
	public Result create(CollectVO collectVO) {
		Collect collect = new Collect();
		BeanUtil.copyProperties(collectVO, collect);
		collect.setCreateDate(DateUtils.getDate());
		super.insert(collect);
		return Result.success();
	}
}
