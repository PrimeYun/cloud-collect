package com.collect.api.oss;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;

import com.collect.api.oss.config.CloudStorageConfig;
import com.collect.common.utils.DateUtils;
import com.collect.common.utils.IdGen;

import cn.hutool.core.util.StrUtil;

public class LocalCloudStorageService extends CloudStorageService {
	
	public LocalCloudStorageService(CloudStorageConfig config) {
		this.config = config;
	}
	
	@Override
	public String getPath(String prefix, String suffix) {
		String uuid = IdGen.uuid();

		StringBuilder path = new StringBuilder();

		if (StringUtils.isNotBlank(prefix)) {
			path.append(prefix);

			if (!StrUtil.endWith(path, "/")) {
				path.append("/");
			}
		}

		path.append(DateUtils.getYear()).append("/").append(DateUtils.getMonth()).append("/").append(DateUtils.getDay()); // .append("/").append(uuid);

		File folder = new File(path.toString());
		if (!folder.exists()) {
			folder.mkdirs();
		}

		path.append("/").append(uuid);
		return path.toString() + suffix;
	}
	
	@Override
	public String upload(byte[] data, String path) {
		try {
			//复制文件
			FileCopyUtils.copy(data, new File(path));
		} catch (IOException e) {
			throw new RuntimeException("文件上传失败，请核对本地配置", e);
		}
		
		//本地存储  拼接 url + "|" + path
		StringBuilder sb = new StringBuilder();
		String domain = config.getLocal().getDomain();
		sb.append(domain);
		if (!domain.endsWith("/")) {
			sb.append("/");
		}
		sb.append("|").append(path);
		
		return sb.toString();
	}

	@Override
	public String uploadSuffix(byte[] data, String suffix) {
		return upload(data, getPath(config.getLocal().getPrefix(), suffix));
	}

	@Override
	public String upload(InputStream inputStream, String path) {
		try {
			byte[] data = IOUtils.toByteArray(inputStream);
			return this.upload(data, path);
		} catch (IOException e) {
			throw new RuntimeException("文件上传失败", e);
		}
	}

	@Override
	public String uploadSuffix(InputStream inputStream, String suffix) {
		return upload(inputStream, getPath(config.getLocal().getPrefix(), suffix));
	}

}
