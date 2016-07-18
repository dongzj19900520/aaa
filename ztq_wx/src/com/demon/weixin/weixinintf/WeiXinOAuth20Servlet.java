package com.demon.weixin.weixinintf;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.china1168.Constant;
import com.china1168.ReadProp;
import com.china1168.ServletAction;
import com.china1168.utils.InputUtils;
import com.china1168.utils.PackageJson;
import com.china1168.utils.ResolvePackets;
import com.china1168.utils.SendUtils;
import com.demon.weixin.core.packet.abs.RequestPacket;
import com.demon.weixin.core.packet.resp.TextResponsePacket;
import com.demon.weixin.core.parser.PacketParserException;
import com.demon.weixin.core.parser.PacketParserFactory;
import com.demon.weixin.core.parser.abs.PacketParser;
import com.demon.weixin.core.pull.packet.rev.WebAccessTokenRevPacket;
import com.demon.weixin.core.pull.packet.send.WebAccessTokenSendPacket;
import com.sky.demon.utils.StringUtil;

public class WeiXinOAuth20Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8862268424582302044L;

	public WeiXinOAuth20Servlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		response.getWriter().println("request.getQueryString()=" + request.getQueryString());
		response.setCharacterEncoding("utf-8");
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		// state =1  知天气
		//state =2  点播
		//state =3 意见反馈
		InputUtils utils =new InputUtils();
		String inputData =utils.readInput(request);
		if(!StringUtil.isEmpty(code)){
		
			//真实环境  
			/*
			 * String appid ="wxffbf421b778aea3f"; 
			   String secret = "f05b52d9c2b145d41f44e9c9e2c8d7d3";
				
			测试环境
				appid:wx32ee12f270514a7f
				appsecret:104a4575dda83d493d41ddac15e92e87

			 */
			String appid =ReadProp.readValue("APPID");
			String secret = ReadProp.readValue("SECRET");
				
			WebAccessTokenSendPacket webAccessTokenSendPacket = new WebAccessTokenSendPacket(appid,secret,code);
			WebAccessTokenRevPacket revPacket = webAccessTokenSendPacket.send();
			
			String openid = revPacket.getOpenid();
			String url ="index.jsp";
			
			if(openid==null){
				url = "error.jsp";
			}else{
				request.setAttribute("openid", openid);
				ServletAction action = new ServletAction();
				PacketParser packetParser = PacketParserFactory.getInstance().createPacketParser(PacketParserFactory.XML_PACKET_PARSER_TYPE);
				if(state!=null ){
					if(state.equals("1")){
						url = "index.jsp?openid="+openid;
					}else if(state.equals("2")){
						
					}else if(state.equals("3")){
						//反馈
						response.sendRedirect("feedback.jsp?openid="+openid);
						return;
					}
					
				}else{
					url = "error.jsp";
					
				}
			}
			response.sendRedirect(url);
			//request.getRequestDispatcher(url).forward(request, response);
		}else{
			System.out.println("没有获取到OAuth2.0的code!".getBytes("utf-8"));
			response.getWriter().print("没有获取到OAuth2.0的code!".getBytes("utf-8"));
		}
		
	}

	  public void out(HttpServletResponse resp, byte[] bytes)
	    throws IOException
	  {
	    BufferedOutputStream out = null;
	    try {
	      out = new BufferedOutputStream(resp.getOutputStream());
	      out.write(bytes);
	      out.flush();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (out != null)
	        out.close();
	    }
	  }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
