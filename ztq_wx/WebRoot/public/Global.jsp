<%@ page contentType="text/html; charset=utf-8" language="java" import="java.io.*,java.util.Date,java.util.Calendar"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="org.apache.log4j.LogManager"%>
<%@ page import="com.china1168.utils.*"%>
<%@ page import="com.china1168.ReadProp"%>
<%
	String errorCommonMsg = "{\"success\":false,\"errors\":{\"errorcode\":\"TIME_OUT\",\"errmsg\":\"请求超时\"}}";
%>
