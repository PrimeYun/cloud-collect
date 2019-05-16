package com.collect.api.bean;

import java.io.Serializable;

public class WeatherData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String date;
	
	private String week;
	
	private String wea;
	
	private Integer air;
	
	private String air_level;
	
	private String tem;
	
	private String tem1;
	
	private String tem2;

	public String getTem() {
		return tem;
	}

	public void setTem(String tem) {
		this.tem = tem;
	}

	public String getTem1() {
		return tem1;
	}

	public void setTem1(String tem1) {
		this.tem1 = tem1;
	}

	public String getTem2() {
		return tem2;
	}

	public void setTem2(String tem2) {
		this.tem2 = tem2;
	}

	public String getAir_level() {
		return air_level;
	}

	public void setAir_level(String air_level) {
		this.air_level = air_level;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getWea() {
		return wea;
	}

	public void setWea(String wea) {
		this.wea = wea;
	}

	public Integer getAir() {
		return air;
	}

	public void setAir(Integer air) {
		this.air = air;
	}
	
}
