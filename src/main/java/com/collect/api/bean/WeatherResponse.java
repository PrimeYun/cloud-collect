package com.collect.api.bean;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class WeatherResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String city;
	
	private List<WeatherData> data = Lists.newArrayList();

	public List<WeatherData> getData() {
		return data;
	}

	public void setData(List<WeatherData> data) {
		this.data = data;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
