package com.collect.api.web;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.collect.api.bean.Oss;
import com.collect.api.oss.OSSFactory;
import com.collect.api.service.OssService;
import com.collect.common.base.BaseController;
import com.collect.common.utils.DateUtils;

import cn.hutool.core.convert.Convert;

@RestController
@RequestMapping("/oss")
public class OssController extends BaseController{
	
	@Autowired
	private OssService ossService;
	
	@PostMapping("/post")
	public Object post(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RuntimeException("上传文件不能为空");
		}
		//上传文件
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String result = OSSFactory.buile().uploadSuffix(file.getBytes(), suffix);
		
		String[] arr = StringUtils.splitByWholeSeparator(result, "|");
		
		Oss oss = new Oss();
		oss.setName(file.getOriginalFilename());
		oss.setUrl(arr[0]);
		oss.setPath(arr[1]);
		oss.setSuffix(FilenameUtils.getExtension(oss.getName()));
		oss.setCreateDate(Convert.toStr(DateUtils.getDate()));
		
		ossService.insert(oss);
		return success(oss);
	}
}
