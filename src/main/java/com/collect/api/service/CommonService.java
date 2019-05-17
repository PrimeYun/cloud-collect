package com.collect.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.collect.api.bean.CollectSort;
import com.collect.api.bean.IpAddressInfo;
import com.collect.api.bean.WeatherResponse;
import com.collect.common.service.RedisService;
import com.collect.common.utils.HttpContextUtils;
import com.collect.common.utils.IPUtils;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Maps;

import cn.hutool.http.HttpUtil;

import com.alibaba.fastjson.JSONObject;

@Service
public class CommonService {
	
	@Autowired
	private CollectSortService sortService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private SysConfigService configService;
	
	public Map<String, Object> get() {
		Map<String, Object> result = Maps.newHashMap();
		result.put("num", getViewNum());
		result.put("sort", getSort());
		result.put("weather", getWeather());
		return result;
	}
	
	private Map<String, Object> getViewNum() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("viewNum", redisService.incr("views"));
		result.put("accessNum", redisService.get("accessNum"));
		return result;
	}
	
	@Cacheable(value = "collect_sort")
	private List<CollectSort> getSort() {
		Map<String, Object> result = Maps.newHashMap();
		return sortService.selectList(result);
	}
	
	private WeatherResponse getWeather() {
		WeatherResponse item = null;
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		String ipAddress = IPUtils.getIpAddr(request);
		// 本地无法获取正确的天气信息，默认为北京
		// String ipAddress = "222.186.125.185";
		String value = redisService.get("weather:" + ipAddress);
		
		if (StringUtil.isEmpty(value)) {
			String location = this.getLocation(ipAddress);
			value = HttpUtil.get(configService.getValue("weather.url") + location);
			redisService.set("weather:" + ipAddress, value);
		}
		
		item = JSONObject.parseObject(value, WeatherResponse.class);
		item.setData(item.getData().subList(0, 1));
		return item;
	}
	
	private String getLocation(String address) {
        String location = HttpUtil.get(configService.getValue("ip.address") + address);
        IpAddressInfo item = null;
        if (StringUtil.isNotEmpty(location)) {
        	item = JSONObject.parseObject(location, IpAddressInfo.class);
        }
        return item == null ? null : item.getCity().substring(0, item.getCity().lastIndexOf("市"));
	}
	
}
