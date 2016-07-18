package com.china1168.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class PackageJson {

	/**
	 * 判断用户是否存在
	 * @return
	 */
	public String judgeUserReq(String accessid){
		
		StringBuffer json = new StringBuffer();
		json.append("p={\"h\":{\"p\":\"20001-A00000306D73F3-2012061418583091883\"},\"b\":{\"weixin_yhdl\":{\"ACCESSID\":\"");
		json.append(accessid);
		json.append("\"}}}");
		System.out.println("城市天气接口请求报文--"+json.toString());
		
		return json.toString();
	}
	/**
	 * 城市天气请求
	 * @param cityID
	 * @param accessid
	 * @return
	 */
	public String judgeCityWeather(String cityID,String accessid ){
		StringBuffer json = new StringBuffer();
		json.append("p={\"h\":{\"p\":\"20001-A00000306D73F3-2012061418583091883\"},\"b\":{\"weixin_index_new\":{\"CITY_ID\":\"");
		json.append(cityID);
		json.append("\"}");
		json.append(",\"weixin_shzs\":{\"CITY_ID\":\"");
		json.append(cityID);
		json.append("\"}");
		json.append(",\"weixin_yjxx\":{\"CITY_ID\":\"");
		json.append(cityID);
		json.append("\"}");
		json.append("}}");
		System.out.println("城市天气接口请求报文--"+json.toString());
		
		return json.toString();
	}
	/**
	 * 微信城市列表请求包
	 * @param accessid
	 * @param parentid
	 * @return
	 */
	public String judgewei_city(String accessid,String parentid ){
		Map map_h= new HashMap();
		Map map_b = new HashMap();
		
		map_h.put("dt", "1");
		map_h.put("p", "10001-A00000306D73F3-2012061418583091883");
		map_h.put("n", "0");
		map_h.put("pt","wei_city");
		
		map_b.put("PARENTID", parentid);
		map_b.put("ACCESSID",accessid);
		
		return publicUtil(map_h,map_b);
	}
	/**
	 * 微信用户天气城市选择请求包
	 * @param city_id
	 * @param accessid
	 * @return
	 */
	public String judgewei_user_city(String city_id,String accessid ){
		StringBuffer json = new StringBuffer();
		json.append("p={\"h\":{\"p\":\"20001-A00000306D73F3-2012061418583091883\"},\"b\":{\"weixin_yhdq\":{\"ACCESSID\":\"");
		json.append(accessid);
		json.append("\",\"CITY_ID\":\"");
		json.append(city_id);
		json.append("\"}}}");
		System.out.println("城市天气接口请求报文--"+json.toString());
		
		return json.toString();
	}
	/**
	 * 微信用户空气质量详细请求包
	 * @param city_name
	 * @param accessid
	 * @return
	 */
	public String judgewei_city_air(String city_name,String accessid){
		StringBuffer json = new StringBuffer();
		json.append("p={\"h\":{\"p\":\"20001-A00000306D73F3-2012061418583091883\"},\"b\":{\"weixin_air\":{\"ACCESSID\":\"");
		json.append(accessid);
		json.append("\",\"CITY_NAME\":\"");
		json.append(city_name);
		json.append("\"}}}");
		System.out.println("城市天气接口请求报文--"+json.toString());
		
		return json.toString();
	}
	/**
	 * 微信用户生活指数详细请求包
	 * @param city_id
	 * @param accessid
	 * @return
	 */
	public String judgewei_city_shzs(String city_id,String accessid){
		StringBuffer json = new StringBuffer();
		json.append("p={\"h\":{\"p\":\"20001-A00000306D73F3-2012061418583091883\"},\"b\":{\"weixin_shzs\":{\"ACCESSID\":\"");
		json.append(accessid);
		json.append("\",\"CITY_ID\":\"");
		json.append(city_id);
		json.append("\"}}}");
		System.out.println("城市天气接口请求报文--"+json.toString());
		
		return json.toString();
	}
	/**
	 * 微信用户预警信息详细请求包
	 * @param city_name
	 * @return
	 */
	public String judgewei_city_yj(String id){
		StringBuffer json = new StringBuffer();
		json.append("p={\"h\":{\"p\":\"20001-A00000306D73F3-2012061418583091883\"},\"b\":{\"gz_query_warn_by_id\":{");
		json.append("\"id\":\"");
		json.append(id);
		json.append("\"}}}");
		System.out.println("城市天气接口请求报文--"+json.toString());
		
		return json.toString();
	}
	/**
	 * 预警信息列表
	 * @param cityid
	 * @return
	 * yejf 20160311 add
	 */
	public String judgewei_city_yjxx(String cityid){
		StringBuffer json = new StringBuffer();
		json.append("p={\"h\":{\"p\":\"20001-A00000306D73F3-2012061418583091883\"},\"b\":{\"weixin_yjxx\":{");
		json.append("\"CITY_ID\":\"");
		json.append(cityid);
		json.append("\"}}}");
//		System.out.println("城市天气接口请求报文--"+json.toString());
		
		return json.toString();
	}
	/**
	 * 微信用户点播
	 * @param city_name
	 * @param type
	 * @return
	 */
	public String judgewei_city_db(String city_name, String type){
		StringBuffer json = new StringBuffer();
		json.append("p={\"h\":{\"p\":\"20001-A00000306D73F3-2012061418583091883\"},\"b\":{\"weixin_dianbo\":{\"CITY_NAME\":\"");
		json.append(city_name);
		json.append("\",\"TYPE\":\"");
		json.append(type);
		json.append("\"}}}");
		System.out.println("城市天气接口请求报文--"+json.toString());
		
		return json.toString();
	}
	/**
	 * 微信用户反馈信息
	 * @param msg
	 * @param accessid
	 * @return
	 */
	public String judgewei_fankui(String msg,String accessid){
		
		StringBuffer json = new StringBuffer();
		json.append("p={\"h\":{\"p\":\"20001-A00000306D73F3-2012061418583091883\"},\"b\":{\"gz_suggest\":{\"call_way\":\"");
		json.append(accessid);
		json.append("\",\"msg\":\"");
		json.append(msg);
		json.append("\"}}}");
		System.out.println("城市天气接口请求报文--"+json.toString());
		
		return json.toString();
	}
	
	public String publicUtil(Map map_h,Map map_b ){
		JSONObject jObj = new JSONObject();
		jObj.put("h", map_h);
		jObj.put("b", map_b);
		
		return "p="+jObj.toString();
	}
}
