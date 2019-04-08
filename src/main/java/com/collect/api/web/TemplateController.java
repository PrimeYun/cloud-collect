package com.collect.api.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {
	
	@RequestMapping(value = {"/"})
	public String index() {
		return "index";
	}
	
	@RequestMapping("/{url}")
	public String book(@PathVariable("url") String url) {
		return url;
	}
}
