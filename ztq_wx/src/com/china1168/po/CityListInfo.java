package com.china1168.po;

import java.util.List;

public class CityListInfo {

	public String is;
	public String  accessid;
	public List<CityInfo> cityInfo;
	public String type;
	
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
	
	
	public List<CityInfo> getCityInfo() {
		return cityInfo;
	}
	public void setCityInfo(List<CityInfo> cityInfo) {
		this.cityInfo = cityInfo;
	}
	public String getIs() {
		return is;
	}
	public void setIs(String is) {
		this.is = is;
	}
	
	
	

}
