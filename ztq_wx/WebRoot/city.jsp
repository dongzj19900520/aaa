<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.china1168.utils.SendUtils"%>
<%@ page import="com.china1168.utils.*"%>
<%@page import="com.china1168.ReadProp"%>
<%@ include file="public/safe.jsp"%>
<%@ include file="public/Global.jsp"%>

<%
String path = request.getContextPath();
String cacheKey = "data";
try{
	String cityid= request.getParameter("cityid") == null?"":request.getParameter("cityid").trim();
	if(cityid.length()<=0){
		out.print(errorCommonMsg);
		return;
	}
	ResolvePackets rp = new ResolvePackets();
	PackageJson pj = new PackageJson();
	String weatherJSON = pj.judgewei_user_city(cityid, userbean.getAccessid());
	String service_url = ReadProp.readValue("POST_URL");
	String weather_resp  = SendUtils.sendJson(service_url,weatherJSON);
	UserCity city = rp.resolvewei_user_city(weather_resp);
	session.setAttribute(Constant.SESSION_USER, city);
	JSONObject jsonObject = JSONObject.fromObject(weather_resp);
	jsonObject.put("success", true);
	out.print(jsonObject.toString());
}catch(Exception e){
	Logger logger = LogManager.getLogger(path);
	logger.error("异常", e);
	out.print(errorCommonMsg);
}
%>

