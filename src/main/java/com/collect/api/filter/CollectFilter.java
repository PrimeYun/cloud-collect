package com.collect.api.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import com.collect.api.bean.User;
import com.collect.api.bean.UserToken;
import com.collect.api.service.UserService;
import com.collect.api.service.UserTokenService;
import com.collect.common.base.Result;
import com.collect.common.utils.Constant;
import com.collect.common.utils.HttpContextUtils;
import com.collect.common.utils.JsonMapper;
import com.collect.common.utils.StringUtils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

public class CollectFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserTokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	String[] excludeUrls = {"/404", "/error/**", "/css/**", "/fav/**", "/img/**", "/js/**", "/"};
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//1.请求过滤
		String path = urlPathHelper.getServletPath(request);
		
		for (String url : excludeUrls) {
			if (antPathMatcher.match(url, path)) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		
		//2. token认证
		String token = getRequestToken(request);
		if (StrUtil.isBlank(token)) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		
		UserToken userToken = tokenService.selectByToken(token);
		if (ObjectUtil.isNull(userToken) || userToken.getExpireDate().getTime() < System.currentTimeMillis()) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		
		User user = userService.selectById(userToken.getUserId());
		if (user.getStatus() == 0) {
			renderToUnauthorized(response, "账户锁定");
			return;
		}
		
		request.setAttribute(Constant.USER_INFO, user);
		
		filterChain.doFilter(request, response);
		
	}
	
	private String getRequestToken(HttpServletRequest request) {
		//从header中获取token
		String token = request.getHeader("token");
		//如果header中没有，从参数中获取
		if (StringUtils.isBlank(token)) {
			token = request.getParameter("token");
		}
		return token;
	}
	
	private void renderToUnauthorized(ServletResponse response, String msg) {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setContentType("application/json;charset=utf-8");
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
		
		try {
			httpResponse.getWriter().print(JsonMapper.toJsonString(new Result(HttpStatus.UNAUTHORIZED.value(), msg)));
			httpResponse.flushBuffer();
		} catch (IOException e) {

		}
	}
	
}
