<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.ehCache.EHCacheManager"%> 
<%@ include file="public/safe.jsp"%>
<%@ include file="public/Global.jsp"%>

<%
request.setCharacterEncoding("UTF-8");
String path = request.getContextPath();
String cacheKey = "data";
try{
	String cityid= userbean.getCity_id();
	
	String prefixKey = cacheKey + "."  + cityid;
	//Object obj = EHCacheManager.getInstance().get(cacheKey, prefixKey);
		Object obj = null;

	if (obj == null) {
		PackageJson pj = new PackageJson();
		String weatherJSON = pj.judgewei_city_yjxx(cityid);
		String service_url = ReadProp.readValue("POST_URL");
		String weather_resp  = SendUtils.sendJson(service_url,weatherJSON);
		weather_resp = weather_resp.replace("\\r\\n", "<br />").replace("\\n", "<br />");
		JSONObject jsonObject = JSONObject.fromObject(weather_resp);
		jsonObject.put("success", true);
		out.print(jsonObject.toString());
	//	EHCacheManager.getInstance().put(cacheKey, prefixKey, jsonObject.toString());
	} else {
		out.print(obj.toString());
	}
	
}catch(Exception e){
	Logger logger = LogManager.getLogger(path);
	logger.error("异常", e);
	out.print(errorCommonMsg);
}
%>

