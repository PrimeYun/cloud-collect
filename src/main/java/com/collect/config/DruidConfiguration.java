package com.collect.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

//@Configuration
public class DruidConfiguration {
	
	//@Bean
	//@ConfigurationProperties(value="spring.datasource")
	public DataSource druidDataSource() {
		return new DruidDataSource();
	}
	
	/**
	 * 注册一个StatViewServlet
	 * @return
	 */
	//@Bean
	public ServletRegistrationBean druidStatViewServlet() {
		//org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		
		//初始化参数：initParams
		//白名单
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		//IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
		servletRegistrationBean.addInitParameter("deny", "192.168.1.110");
		//登录查看信息的用户名和密码
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		//是否能够重置数据
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}
	
	/**
	 * 注册一个WebStatFilter
	 * @return
	 */
	//@Bean
	public FilterRegistrationBean druidStatFilter() {
		
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		
		//添加过滤规则
		filterRegistrationBean.addUrlPatterns("/*");
		
		//添加不需要过滤的信息
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		//filterRegistrationBean.addUrlPatterns("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		
		return filterRegistrationBean;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
