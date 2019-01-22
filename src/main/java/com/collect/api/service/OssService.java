package com.collect.api.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collect.api.bean.Oss;
import com.collect.api.dao.OssDao;
import com.collect.api.oss.config.CloudStorageConfig;
import com.collect.common.base.BaseService;
import com.collect.common.utils.Constant;
import com.collect.common.utils.IdGen;

@Service
public class OssService extends BaseService<OssDao, Oss>{
	
	@Autowired
	private ConfigService configService;
	
	@Transactional(readOnly = false)
	public boolean insert(Oss entity) {
		entity.setId(IdGen.getObjectId());
		if (getCloudStorageConfig().getType() == Constant.CloudService.LOCAL.getValue()) {
			entity.setUrl(entity.getUrl() + entity.getId());
		}
		return super.insert(entity);
	}
	
	private CloudStorageConfig getCloudStorageConfig() {
		return configService.getConfigObject("oss.config", CloudStorageConfig.class);
	}
	
	public boolean isImage(String fileName) {
		Pattern p = Pattern.compile("^(jpeg|jpg|png|gif|bmp|webp)([\\w-./?%&=]*)?$");
		Matcher m = p.matcher(fileName.substring(fileName.lastIndexOf(".") + 1));
		if (m.find()) {
			return true;
		}
		return false;
	}
	
	public boolean isVedio(String fileName) {
		Pattern p = Pattern.compile("^(avi|mov|asf|wmv|navi|3gp|mkv|flv|f4v|rmvb|mp4)([\\w-./?%&=]*)?$");
		Matcher m = p.matcher(fileName.substring(fileName.lastIndexOf(".") + 1));
		if (m.find()) {
			return true;
		}
		return false;
	}
}
