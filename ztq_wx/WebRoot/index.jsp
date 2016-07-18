<%@ page language="java" import="java.util.*" pageEncoding="utf-8"  errorPage="error.jsp" isErrorPage="true" %>
<%@ page import="com.china1168.utils.*"%>
<%@ page import="com.china1168.Constant"%>
<%@ page import="com.china1168.po.WeatherResp"%>
<%@page import="com.china1168.po.YjInfo"%>
<%@page import="com.china1168.po.CcInfo"%>
<%@page import="com.china1168.po.FcInfo"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="org.apache.log4j.LogManager"%>
<%@page import="com.china1168.ReadProp"%>
<%@page import="com.china1168.po.UserCity"%>

  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <%
  		Logger logger = LogManager.getLogger("index.jsp");
  		request.setCharacterEncoding("utf-8");
  		response.setCharacterEncoding("utf-8");
  		String service_url =ReadProp.readValue("POST_URL");
  		PackageJson pj = new PackageJson();
  		String  accessid= request.getParameter("openid");  //gh_5b86c5c633d0		//测试
  		//String accessid= "1111";									//本地开发
  		if(accessid==null){
  			out.print("参数错误！");
  			return ;
  		}else{
  			//accessid= request.getParameter("openid").toString();  //gh_5b86c5c633d0		//测试
  		}
  		String sendJSON =  pj.judgeUserReq(accessid);
  		//发送报文
  		String result = "";
  		//报文解析
  		ResolvePackets resovlue = new ResolvePackets();
  		SendUtils sendUtil = new SendUtils();
		Date begin_time =new Date();
 		 String respJson  = SendUtils.sendJson(service_url,sendJSON);
 		 Date end_time =new Date();
 		 long ss = end_time.getTime()-begin_time.getTime();
 		logger.info("用户登录信息，用户id是："+accessid+",请求接口名称用户登录，请求——响应差时间："+ss+"毫秒");
			result=resovlue.resolveUserJson(respJson);
			UserCity userbean = new UserCity();
			userbean.setAccessid(accessid);
			
			if(result.equals(Constant.REQUEST_ERROR)){
				response.sendRedirect("error.jsp");
				return ;
			}else if(result.equals(Constant.USER_NOT_EXIST)){
				session.setAttribute(Constant.SESSION_USER, userbean);
				response.sendRedirect("city.html");	
			}else{
				userbean.setCity_id(result);
				session.setAttribute(Constant.SESSION_USER, userbean);
				response.sendRedirect("index.html");	
			}
  		
%>