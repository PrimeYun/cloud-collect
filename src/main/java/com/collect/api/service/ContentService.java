package com.collect.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collect.api.bean.Content;
import com.collect.api.dao.ContentDao;
import com.collect.api.vo.ContentVO;
import com.collect.common.base.BaseService;
import com.collect.common.utils.DateUtils;

import cn.hutool.core.bean.BeanUtil;

@Service
public class ContentService extends BaseService<ContentDao, Content>{
	
	@Transactional(readOnly = false)
	public void create(ContentVO collectVO) {
		Content content = new Content();
		BeanUtil.copyProperties(collectVO, content);
		content.setCreateDate(DateUtils.getDate());
		super.insert(content);
	}
}
