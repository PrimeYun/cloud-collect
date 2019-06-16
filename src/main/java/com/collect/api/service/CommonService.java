package com.collect.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.collect.api.bean.CollectSort;
import com.google.common.collect.Maps;

@Service
public class CommonService {
	
	@Autowired
	private CollectSortService sortService;
	
	@Autowired
	private StringRedisTemplate stringTemplate;
	
//	@Autowired
//	private SysConfigService configService;
	
	public Map<String, Object> get() {
		Map<String, Object> result = Maps.newHashMap();
		result.put("num", getViewNum());
		result.put("sort", getSort());
		// result.put("weather", getWeather());
		return result;
	}
	
	private Map<String, Object> getViewNum() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("viewNum", stringTemplate.opsForValue().increment("views", 1));
		result.put("accessNum", stringTemplate.opsForValue().get("accessNum"));
		return result;
	}
	
	@Cacheable(value = "collect_sort")
	private List<CollectSort> getSort() {
		Map<String, Object> result = Maps.newHashMap();
		return sortService.selectList(result);
	}
	
//	private WeatherResponse getWeather() {
//		WeatherResponse item = null;
//		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//		String ipAddress = IPUtils.getIpAddr(request);
//		// 本地无法获取正确的天气信息，默认为镇江
//		if ("0:0:0:0:0:0:0:1".equals(ipAddress))
//			ipAddress = "222.186.125.185";
//		String value = stringTemplate.opsForValue().get("weather:" + ipAddress);
//		
//		if (StringUtil.isEmpty(value)) {
//			String location = this.getLocation(ipAddress);
//			value = HttpUtil.get(configService.getValue("weather.url") + location);
//			stringTemplate.opsForValue().set("weather:" + ipAddress, value);
//		}
//		
//		item = JSONObject.parseObject(value, WeatherResponse.class);
//		item.setData(item.getData().subList(0, 1));
//		return item;
//	}
//	
//	private String getLocation(String address) {
//        String location = HttpUtil.get(configService.getValue("ip.address") + address);
//        IpAddressInfo item = null;
//        if (StringUtil.isNotEmpty(location)) {
//        	item = JSONObject.parseObject(location, IpAddressInfo.class);
//        }
//        return item == null ? null : item.getCity().substring(0, item.getCity().lastIndexOf("市"));
//	}
	
}
