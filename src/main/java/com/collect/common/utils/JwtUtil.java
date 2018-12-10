package com.collect.common.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtUtil {

	public final static String JWT_NAME = "token";
	public final static String JWT_SECRET = Base64.encode("jhwcryg0okjovj9nwgajnc09c4o0zuu5");
	public final static Integer JWT_EXPIRES_IN = 7200;

	public static String create(String subject) {
		Date now = new Date();
		return Jwts.builder().setSubject(subject).setExpiration(DateUtils.addSeconds(now, JWT_EXPIRES_IN)).signWith(SignatureAlgorithm.HS256, JWT_SECRET).compact();
	}

	public static Claims parse(String jwt) {
		try {
			return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt).getBody();
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
		} catch (MalformedJwtException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSubject(String jwt) {
		String subject = StrUtil.EMPTY;
		if (!StrUtil.isBlank(jwt)) {
			Claims claims = parse(jwt);
			if (claims != null) {
				subject = claims.getSubject();
				return subject;
			}
		}
		return subject;
	}

	public static String getSubject() {
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		String token = request.getHeader("Authorization");
		if (StrUtil.isBlank(token)) {
			token = request.getParameter(JWT_NAME);
		}
		if (StrUtil.isBlank(token)) {
			token = CookieUtils.getCookie(request, JWT_NAME);
		}
		if (StrUtil.isBlank(token)) {
			throw new JwtException("当前token为空,请携带相关token参数.");
		}
		String subject = getSubject(token);
		if (StrUtil.isBlank(subject)) {
			throw new JwtException("invalid token.");
		}
		return subject;
	}
	
	public static void main(String[] args) {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDMzMTY1Njd9.3h7iERrBOHQ02RzsZiu2ZCSs7xCW3celTCpMam28SKo";
		System.out.println(getSubject(token));
		System.out.println(RandomUtil.randomString(32));
		
		
		String jwt = create("1");
		System.out.println(jwt);
		System.out.println(getSubject(jwt));
		
	}
	
}
