<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*"%>
<%@page import="com.china1168.po.UserCity"%>
<%@page import="com.china1168.Constant"%>
<%@page import="net.sf.json.JSONObject"%>
<%
	UserCity userbean = (UserCity)session.getAttribute(Constant.SESSION_USER);
	if(userbean!=null)
	{
	}
	else
	{
		userbean = new UserCity();
		userbean.setAccessid("test");
		userbean.setCity_id("1069");
		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("success", false);
		 JSONObject errorsObject = new JSONObject();
		 errorsObject.put("errorcode", "no_login");
		 errorsObject.put("errmsg", "访问超时，请重新从“知天气”公众号点击菜单访问！");
		 jsonObject.put("errors", errorsObject);
	//	 "errors":{"errorcode":"PCS_EC_COMMON_1010","errmsg":"登录超时或用户信息已失效，请重新登录"}
		out.println(jsonObject.toString());
		
		return;
	}
%>