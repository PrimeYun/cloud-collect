package com.collect.api.oss;

import org.springframework.beans.factory.annotation.Value;

import com.collect.api.oss.config.CloudStorageConfig;
import com.collect.api.service.ConfigService;
import com.collect.common.utils.Constant;
import com.collect.common.utils.SpringContextHolder;

public class OSSFactory {
	
	@Value("${oss.config}")
	private static String key;
	
	private static ConfigService configService;
	
	static {
		OSSFactory.configService = SpringContextHolder.getBean(ConfigService.class);
	}
	
	public static CloudStorageService buile() {
		
		CloudStorageConfig config = configService.getConfigObject(key, CloudStorageConfig.class);
		if (null != config) {
			if (config.getType() == Constant.CloudService.LOCAL.getValue()) {
				return new LocalCloudStorageService(config);
			}
			if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
				return new AliCloudStorageService(config);
			}
		}
		return null;
	}
}
