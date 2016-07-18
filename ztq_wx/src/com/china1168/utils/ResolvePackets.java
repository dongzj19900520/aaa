package com.china1168.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.china1168.Constant;
import com.china1168.po.AQIInfo;
import com.china1168.po.CcInfo;
import com.china1168.po.CityAirInfo;
import com.china1168.po.CityInfo;
import com.china1168.po.CityListInfo;
import com.china1168.po.City_shzsInfo;
import com.china1168.po.CtsInfo;
import com.china1168.po.DwsBase;
import com.china1168.po.DwsInfo;
import com.china1168.po.FcInfo;
import com.china1168.po.IdxInfo;
import com.china1168.po.Pro_City_yjInfo;
import com.china1168.po.UserCity;
import com.china1168.po.WeatherResp;
import com.china1168.po.YjInfo;
/**
 * 
 * @author Administrator
 *
 */
public class ResolvePackets {

	/**
	 * 解析用户判断接口返回报文
	 * @param json
	 * @return
	 */
	public static String  resolveUserJson(String json){
	
		String remsg ="";	//返回相应信息
		
		JSONObject  dataJson= JSONObject.fromObject(json);
		//解析头部信息
		JSONObject h_json = dataJson.getJSONObject("h");
		String is =(String) h_json.getString("is");
		if(is.equals("-1")){
			return Constant.REQUEST_ERROR;	//请求出错
			
		}
		//解析body
		JSONObject  response=dataJson.getJSONObject("b");
		JSONObject ztq_w_user =(JSONObject) response.get("weixin_yhdl");
		JSONObject cts = (JSONObject) ztq_w_user.getJSONObject("cts");
		String type = cts.getString("TYPE");
		if(type.equals("1")){
			String cityID =  cts.getString("CITY_ID");	//用户已经设置的城市id
			
			return cityID;
			
		}else {
			return Constant.USER_NOT_EXIST;	//
		}

	}
	/**
	 * 解析天气预报 返回报文
	 * @param weatherRes
	 * @return
	 */
	
	public static WeatherResp  resolveWeatherRes(String weatherRes){
			
		
		JSONObject  dataJson= JSONObject.fromObject(weatherRes);
//		System.out.println("天气预报报文解析---"+dataJson.toString());
		WeatherResp resp = new WeatherResp();
		String idx ="";
		JSONObject cc= new JSONObject ();
		JSONObject yj = new JSONObject ();
		JSONArray fc = new JSONArray ();
		List<YjInfo> yj_list = new ArrayList<YjInfo>();
		List<FcInfo> fc_list = new ArrayList<FcInfo>();
		CcInfo ccInfo = new CcInfo();
		YjInfo yjInfo = new YjInfo();
		//解析头部信息
		JSONObject h_json = dataJson.getJSONObject("h");
		String is =(String) h_json.getString("is");
		resp.setIs(is);
		JSONObject  json_b=dataJson.getJSONObject("b");
		JSONObject  index=json_b.getJSONObject("weixin_index");
		resp.setCity_name(index.getString("city_name"));
		resp.setIdx(index.getString("idx"));
		//idx 迭代 待定，需要确定 idx 格式
		resp.setPm25(index.getString("pm25"));
		//实时天气
		ccInfo.setWind(index.getString("wind"));
		ccInfo.setHumidity(index.getString("humidity"));
		ccInfo.setCt(index.getString("ct"));
		
		resp.setCcInfo(ccInfo);
		//预警信息
		yj = index.getJSONObject("yj");
//		System.out.println(yj.size());
		if(yj.size()>0){
			YjInfo info =new YjInfo();
			info.setId(yj.getString("id"));
			info.setDesc(yj.getString("desc"));
			info.setColor(yj.getString("color"));
			info.setPut_str(yj.getString("put_str"));
			info.setPt(yj.getString("pt"));
			info.setIco(yj.getString("ico"));
			info.setEt( yj.getString("et"));
			info.setInfo(yj.getString("info"));
			yj_list.add(info);
		}
		
		resp.setYj_list(yj_list);
		resp.setType(index.getString("TYPE"));
		//一周天气
		fc = index.getJSONArray("fc");
		if(fc.size()>0){
			for (int i = 0; i < fc.size(); i++) {
				Map tem1 = new HashMap();
				JSONObject temObj1=fc.getJSONObject(i);
				FcInfo info = new FcInfo();
				info.setHigt( temObj1.getString("higt"));
				info.setWd(temObj1.getString("wd"));
				info.setLowt(temObj1.getString("lowt"));
				info.setWd_ico(temObj1.getString("wd_ico"));
				fc_list.add(info);
			}
		}
		
		resp.setFc_list(fc_list);
		
		return resp;
	}
	/**
	 * 解析城市列表选择
	 * @param cityJSON
	 * @return
	 */
	public CityListInfo resolvewei_city(String cityJSON){
	//cityJSON="{\"h\":{\"is\":0,\"pt\":\"wei_city\"},\"b\":{\"wei_city\":{\"city\":{\"citylist\":\"[{\"NAME\"=\"北京\", \"ID\"=\"1278\", \"PARENTID\"=\"25147\", \"CITY\"=\"北京\", \"PY\"=\"BJ\", \"PINGYIN\"=\"BeiJing\"}]\",\"ACCESSID\":\"o1kmLt8zfruABXGenuzKzJhU1Neg\",\"TYPE\":\"1\"}}},\"e\":{}}";
		CityListInfo list = new  CityListInfo();
//		System.out.println("解析城市列表选择返回报文---"+cityJSON);
		JSONObject  dataJson= JSONObject.fromObject(cityJSON);
		JSONObject h_json = dataJson.getJSONObject("h");
		//解析头部
		//list.setIs(h_json.getString("is"));
		//解析正文
		list.setIs(h_json.getString("is"));
		if(h_json.getString("is").equals("-100") || h_json.getString("is").equals("-1")){
			return list;
		}
		JSONObject b_json = dataJson.getJSONObject("b");
		JSONObject wei_city = b_json.getJSONObject("wei_city");
		JSONObject city = wei_city.getJSONObject("city");
		list.setType(city.getString("TYPE"));
		if(city.getString("TYPE").equals("0")){
			
			return list;
		}
		JSONArray list2 =city.getJSONArray("citylist");
		
		//String  citylist1=(String) city.get("citylist");
	//	JSONArray citylist= city.getJSONArray("citylist");
		List<CityInfo> infoList = new ArrayList<CityInfo>();
		
		for (int i = 0; i < list2.size(); i++) {
			CityInfo city_tem = new CityInfo();
			JSONObject json_city = list2.getJSONObject(i);
			city_tem.setName(json_city.getString("NAME"));
			city_tem.setId(json_city.getString("ID"));
			city_tem.setParentid(json_city.getString("CITY"));
			city_tem.setCity(json_city.getString("CITY"));
			//city_tem.setPy(json_city.getString("PY"));
			//city_tem.setPingyin(json_city.getString("PINGYIN"));
			infoList.add(city_tem);
			
		}
		list.setCityInfo(infoList);
		String  accessid = city.getString("ACCESSID");
		String  type = city.getString("TYPE");
		list.setAccessid(accessid);
		list.setType(type);
		
		return list;
	}
	/**
	 * 微信用户天气城市选择
	 * @param UserCityJSON
	 * @return
	 */
	public UserCity resolvewei_user_city(String UserCityJSON){
		UserCity userCity = new UserCity();
		JSONObject  dataJson= JSONObject.fromObject(UserCityJSON);
		JSONObject h_json = dataJson.getJSONObject("h");
		JSONObject b_json = dataJson.getJSONObject("b");
		JSONObject json_city = b_json.getJSONObject("weixin_yhdq").getJSONObject("city");
		
		
		//解析头部
		userCity.setIs(h_json.get("is").toString());
		userCity.setAccessid(json_city.getString("ACCESSID"));
		userCity.setType(json_city.getString("TYPE"));
		userCity.setCity_id(json_city.getString("CITY_ID"));
		
		return userCity;
	}
	/**
	 * 微信用户空气质量详细
	 * @param airJSON
	 * @return
	 */
	public CityAirInfo resolvewei_city_air(String airJSON){
		CityAirInfo airInfo = new CityAirInfo();
		JSONObject  dataJson= JSONObject.fromObject(airJSON);
		JSONObject h_json = dataJson.getJSONObject("h");
		JSONObject b_json = dataJson.getJSONObject("b");
		JSONObject air_json = b_json.getJSONObject("weixin_air");
		
		
		//解析头部
		airInfo.setIs(h_json.getString("is"));
		//解析包体
		//airInfo.setArea(air_json.getString("area"));
		airInfo.setHealth_advice(air_json.getString("health_advice"));
		airInfo.setType(air_json.getString("TYPE"));
		if(!air_json.getString("TYPE").equals("0")){
			airInfo.setAccessid(air_json.getString("ACCESSID"));
			airInfo.setQuality(air_json.getString("quality"));
			airInfo.setAqi(air_json.getString("aqi"));
			airInfo.setFirst(air_json.getString("first"));
			airInfo.setImpact(air_json.getString("impact"));
			airInfo.setUpdateTime(air_json.getString("updateTime"));
		}else {
			return airInfo;
		}
		
		
		
		
		JSONArray onelist = air_json.getJSONArray("AQIList");
		List<AQIInfo> aqiInfo = new ArrayList<AQIInfo>();
		for (int i = 0; i < onelist.size(); i++) {
			AQIInfo info = new AQIInfo();
			JSONObject tem = onelist.getJSONObject(i);
			info.setAqi(tem.getString("aqi"));
			info.setTime_hour(tem.getString("time_hour"));
//			info.setCity(tem.getString("city"));
			aqiInfo.add(info);
		}
		airInfo.setOneList(aqiInfo);
		return airInfo;
	}
	/**
	 * 微信用户生活指数详细
	 * @param shzs_json
	 * @return
	 */
	public City_shzsInfo resolvewei_city_shzs(String shzs_json){
		City_shzsInfo city_shzsInfo = new City_shzsInfo();
		JSONObject  dataJson= JSONObject.fromObject(shzs_json);
		JSONObject h_json = dataJson.getJSONObject("h");
		JSONObject b_json = dataJson.getJSONObject("b");
		List<IdxInfo> idx_list = new ArrayList<IdxInfo>();
		//解析
		city_shzsInfo.setIs(h_json.getString("is"));
		if(h_json.getString("is").equals("-101")){
			return city_shzsInfo;
		}
		JSONObject shzsJson = b_json.getJSONObject("weixin_shzs");
		city_shzsInfo.setAccessid(shzsJson.getString("ACCESSID"));
		JSONArray jsonArr = shzsJson.getJSONArray("idx");
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject tem = jsonArr.getJSONObject(i);
			IdxInfo info = new IdxInfo();
			info.setPub_time(tem.getString("pub_time"));
			info.setDesc(tem.getString("desc"));
			info.setLv(tem.getString("lv"));
			info.setIco(tem.getString("ico"));
			info.setShzs_fx(tem.getString("shzs_fx"));
			info.setType(tem.getString("type"));
			info.setInfo(tem.getString("info"));
			info.setNm(tem.getString("nm"));
			idx_list.add(info);
		}
		city_shzsInfo.setIdxList(idx_list);
		return city_shzsInfo;
		
	}
	/**
	 * 微信用户预警信息详细
	 * @param city_yj
	 * @return
	 */
	public Pro_City_yjInfo resovlewei_city_yj(String city_yjJson){
		JSONObject  dataJson= JSONObject.fromObject(city_yjJson);
		JSONObject h_json = dataJson.getJSONObject("h");
		JSONObject b_json = dataJson.getJSONObject("b");
		
		
		CtsInfo ctsInfo = new CtsInfo();	//包含省、 市信息
		Pro_City_yjInfo pro_city_yjInfo = new Pro_City_yjInfo();
		pro_city_yjInfo.setIs(h_json.getString("is"));
		if(h_json.getString("is").equals("-101")){
			return pro_city_yjInfo;
			
		}
		
		
		
		JSONObject city_yj = b_json.getJSONObject("gz_query_warn_by_id");
		JSONObject warn = city_yj.getJSONObject("warn");
		
		
		
		
		
//		JSONArray cityList =cityYj.getJSONArray("yj");
		
		
		
		//城市信息
		DwsBase city = new DwsBase();
		List<DwsInfo> dwsList = new ArrayList<DwsInfo>();
//		for (int i = 0; i < cityList.size(); i++) {
			
//			JSONObject tem1 = cityList.getJSONObject(i);
//			ctsInfo.setHas_yj( tem1.getString("has_yj"));
//			ctsInfo.setCitys(tem1.getString("dws_name"));
			//city.setDws_name(tem1.getString("dws_name"));
			//JSONArray dwsList_tem = tem1.getJSONArray("dws");
			
				String station_name = warn.getString("station_name");
				String put_str = warn.getString("pt");
				
				DwsInfo dwsInfo = new DwsInfo();
				dwsInfo.setDesc(warn.getString("title"));
//				dwsInfo.setColor(tem1.getString("color"));
				dwsInfo.setPut_str(station_name+put_str+"发布");
//				dwsInfo.setPt(tem1.getString("pt"));
				dwsInfo.setIco(warn.getString("ico"));
//				dwsInfo.setEt(tem1.getString("et"));
				dwsInfo.setInfo(warn.getString("content"));
				dwsList.add(dwsInfo);
			
//		}
		city.setDwsInfo(dwsList);
		ctsInfo.setDws_city(city);
		
		
		
		pro_city_yjInfo.setCity_yjInfo(ctsInfo);
		
		return pro_city_yjInfo;
	}
	
}
