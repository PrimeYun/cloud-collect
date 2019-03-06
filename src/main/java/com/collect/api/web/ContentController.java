package com.collect.api.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.collect.api.service.ContentService;
import com.collect.api.vo.ContentVO;
import com.collect.common.base.BaseController;

@RestController
@RequestMapping("/content")
public class ContentController extends BaseController {
	
	@Autowired
	ContentService contentService;
	
	@GetMapping("list")
	public Object list(@RequestParam Map<String, Object> params) {
		return success(contentService.selectList(params));
	}
	
	@GetMapping("get")
	public Object get(Integer id) {
		return success(contentService.selectById(id));
	}
	
	@PostMapping("insert")
	public Object insert(@RequestBody @Valid ContentVO contentVO) {
		contentService.create(contentVO);
		return success();
	}
}
