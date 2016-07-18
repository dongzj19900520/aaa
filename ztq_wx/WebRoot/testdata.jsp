<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.net.*"%>
<%@ page import="java.io.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%
    	request.setCharacterEncoding("UTF-8");
        String sql = request.getParameter("sql") ==null? "":request.getParameter("sql");
    %>
    <title>pcsquery</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="" method="post">
    <textarea rows="10" cols="100" name="sql"><%=sql%></textarea>
    <input type="submit">
    </form>
     <br>

<%
String path = request.getContextPath();
	if(sql.length()>0){
		sql = sql.replace(" ", "").replace("\n", "").replace("\r", "");
		sql = java.net.URLEncoder.encode(sql,"utf-8");
		URL url = new URL("http://218.85.78.125:8066/ztq30_gz/service.do?p="+sql);
		System.out.println(url);
		URLConnection conn = url.openConnection();
		
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
		// 这里是关键，表示我们要向链接里输出内容
		conn.setDoOutput(true);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "utf-8"));
		String line = null;
		
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		out.print(sb.toString());
	}
	
		
	
%>
  </body>
</html>

