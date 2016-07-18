<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.china1168.utils.PackageJson"%>
<%@page import="com.china1168.utils.SendUtils"%>
<%@page import="com.china1168.Constant"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>意见反馈</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" name="viewport">
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
	 <script src="<%=basePath%>/js/jquery/jquery-1.4.4.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/share.js"></script>
	<link rel="stylesheet" type="text/css" href="css/among.css"/>
	<script type="text/javascript">
		function sub(){
			var msg = document.getElementById("msg").value;
			
			if(msg==''){
				alert('意见反馈不能为空！');
				return;
			}else if(msg.length>300){
				alert('意见反馈字数不能超过300！');
				return;
			}
			
			
			 document.getElementById('frm').submit();
		}
	
	</script>
	 </head>
  
  <body>
  <%
  		request.setCharacterEncoding("utf-8");
  		String openid=  request.getParameter("openid");
  		//openid="o1kmLt8zfruABXGenuzKzJhU1Neg";
  		PackageJson pj = new PackageJson();
  		 String type =request.getParameter("type");
  		 
  		if(type!=null){
  			if(type.equals("1")){
  				out.println("<script>alert('反馈成功!')</script>");
	  		}else if(type.equals("0")){
	  			out.println("<script>alert('亲,您输入的反馈信息太长,录入失败！')</script>");
	  		}else if(type.equals("3")){
	  			out.println("<script>alert('反馈意见不能为空！')</script>");
	  		}
  		}
  		
  		
  		//if(msgtem!=null){
  		//	if(msgtem.equals("success")){
  		//		out.println("<script>alert('反馈成功!')</script>");
  		////	}
  		//}
  		
  		
   %>
   <div class="feedback">
   		<form action="fkServlet" method="post" id="frm">
   			<input type="hidden" id="openid" name="openid" value="<%=openid %>"/>
   			<input type="hidden" id="type" name="type" value="<%=type %>"/>
			<div class="feedback_text">
				请在这里输入意见或建议:
				<textarea id="msg" name="msg"></textarea>
			</div>
			<div class="submit">
				<input type="button"  value="提&nbsp;交" onclick="sub()" style="width:85%" />
			</div>
		</form>
	</div>
  </body>
</html>
