package com.collect.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collect.api.bean.ComContent;
import com.collect.api.dao.ComContentDao;
import com.collect.api.vo.ContentVO;
import com.collect.common.base.BaseService;
import com.collect.common.utils.DateUtils;

import cn.hutool.core.bean.BeanUtil;

@Service
public class ContentService extends BaseService<ComContentDao, ComContent> {
	
	@Transactional(readOnly = false)
	public void create(ContentVO collectVO) {
		ComContent content = new ComContent();
		BeanUtil.copyProperties(collectVO, content);
		content.setCreateDate(DateUtils.getDate());
		super.insert(content);
	}
	
}
