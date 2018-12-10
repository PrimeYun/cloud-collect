package com.collect.modules.sys.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.collect.common.base.BaseController;
import com.collect.modules.sys.bean.Collect;
import com.collect.modules.sys.service.CollectService;

@RestController
@RequestMapping("/sys/collect")
public class CollectController extends BaseController {
	
	@Autowired
	CollectService collectService;
	
	@GetMapping("list")
	public Object list(@RequestParam Map<String, Object> params) {
		return success(collectService.selectPage(params));
	}
	
	@PostMapping("insert")
	public Object insert(@RequestBody Collect collect) {
		collectService.insertCollect(collect);
		return success();
	}
}
