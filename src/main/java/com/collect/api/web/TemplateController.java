package com.collect.api.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {
	
	@RequestMapping(value = {"/", "/index"})
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = {"/404"})
	public String error() {
		return "404";
	}
}
