package com.collect.api.oss;

import java.io.InputStream;

import com.collect.api.oss.config.CloudStorageConfig;

public class AliCloudStorageService extends CloudStorageService{
	
	public AliCloudStorageService(CloudStorageConfig config) {
		this.config = config;
	}
	
	@Override
	public String upload(byte[] data, String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploadSuffix(byte[] data, String suffix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(InputStream inputStream, String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String uploadSuffix(InputStream inputStream, String suffix) {
		// TODO Auto-generated method stub
		return null;
	}

}
