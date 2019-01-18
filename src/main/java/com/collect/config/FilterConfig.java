package com.collect.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.collect.api.filter.CollectFilter;

@Configuration
public class FilterConfig {
	
	@Bean
	public CollectFilter collectFilter() {
		return new CollectFilter();
	}
	
	@Bean
	public FilterRegistrationBean<Filter> registCollectFilter() {
		final FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(collectFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
	
}
