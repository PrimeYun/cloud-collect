package com.collect.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collect.api.service.CommonService;
import com.collect.common.base.BaseController;

@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {
	
	@Autowired
	private CommonService commonService;
	
	@GetMapping("num")
	public Object num() {
		return success(commonService.getViewNum());
	}
	
}
