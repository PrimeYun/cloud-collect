package com.collect.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collect.api.bean.ComContent;
import com.collect.api.dao.ComContentDao;
import com.collect.api.vo.ContentVO;
import com.collect.common.base.BaseService;
import com.collect.common.utils.DateUtils;

import cn.hutool.core.bean.BeanUtil;

@Service
@CacheConfig(cacheNames = "ContentService")
public class ContentService extends BaseService<ComContentDao, ComContent> {
	
	public List<ComContent> selectList(Map<String, Object> params) {
		return dao.selectList(params);
	}
	
	@Transactional(readOnly = false)
	public void create(ContentVO collectVO) {
		ComContent content = new ComContent();
		BeanUtil.copyProperties(collectVO, content);
		content.setCreateDate(DateUtils.getDate());
		super.insert(content);
	}
	
}
