package com.china1168.po;

import java.util.List;

public class CityAirInfo {

	public String is;
	public String  accessid;
	public String  type;
	public String city_name;
	public String area;
	public String health_advice;
	public String quality;
	public String aqi;
	public String first;
	public String impact;
	public String updateTime;
	public List<AQIInfo> oneList;
	public List<AQIInfo> twoList;
	
	
	public String getAccessid() {
		return accessid;
	}
	public void setAccessid(String accessid) {
		this.accessid = accessid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	

	public String getIs() {
		return is;
	}
	public void setIs(String is) {
		this.is = is;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getHealth_advice() {
		return health_advice;
	}
	public void setHealth_advice(String health_advice) {
		this.health_advice = health_advice;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getAqi() {
		return aqi;
	}
	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public List<AQIInfo> getOneList() {
		return oneList;
	}
	public void setOneList(List<AQIInfo> oneList) {
		this.oneList = oneList;
	}
	public List<AQIInfo> getTwoList() {
		return twoList;
	}
	public void setTwoList(List<AQIInfo> twoList) {
		this.twoList = twoList;
	}
	
	
	

}
