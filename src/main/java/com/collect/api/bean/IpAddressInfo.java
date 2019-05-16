package com.collect.api.bean;

import java.io.Serializable;
/**
 * 根据ip获取地址信息类
 * @author zhangxingyun 2019年5月16日下午2:00:41
 *
 */
public class IpAddressInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ip;
	
	private String country;
	
	private String province;
	
	private String city;
	
	private String isp;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	@Override
	public String toString() {
		return "IpAddress [ip=" + ip + ", country=" + country + ", province=" + province + ", city=" + city + ", isp="
				+ isp + "]";
	}

}
