package com.china1168.po;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class WeatherResp {

	public String is;
	public String pt;
	public String city_name;
	public String pm25;
	public CcInfo ccInfo  ;
	public String type ;
	public List<FcInfo> fc_list;
	public String idx;
	public List<YjInfo> yj_list;
	
	public String getIs() {
		return is;
	}
	public void setIs(String is) {
		this.is = is;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	
	public CcInfo getCcInfo() {
		return ccInfo;
	}
	public void setCcInfo(CcInfo ccInfo) {
		this.ccInfo = ccInfo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public List<FcInfo> getFc_list() {
		return fc_list;
	}
	public void setFc_list(List<FcInfo> fc_list) {
		this.fc_list = fc_list;
	}
	public String getPt() {
		return pt;
	}
	public void setPt(String pt) {
		this.pt = pt;
	}
	
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public List<YjInfo> getYj_list() {
		return yj_list;
	}
	public void setYj_list(List<YjInfo> yj_list) {
		this.yj_list = yj_list;
	}
	
	
}
