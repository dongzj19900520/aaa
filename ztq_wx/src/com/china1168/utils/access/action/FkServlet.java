package com.china1168.utils.access.action;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.china1168.Constant;
import com.china1168.ReadProp;
import com.china1168.utils.PackageJson;
import com.china1168.utils.ResolvePackets;
import com.china1168.utils.SendUtils;
import com.demon.weixin.core.packet.abs.RequestPacket;
import com.demon.weixin.core.packet.abs.ResponsePacket;
import com.demon.weixin.core.packet.req.TextRequestPacket;
import com.demon.weixin.core.packet.resp.TextResponsePacket;
import com.demon.weixin.core.parser.PacketParserException;
import com.demon.weixin.core.parser.PacketParserFactory;
import com.demon.weixin.core.parser.abs.PacketParser;
import com.demon.weixin.core.pull.packet.rev.WebAccessTokenRevPacket;
import com.demon.weixin.core.pull.packet.send.WebAccessTokenSendPacket;
import com.sky.demon.utils.StringUtil;



public class FkServlet extends HttpServlet{

	
	private static Log log = LogFactory.getLog(FkServlet.class);
	private static final long serialVersionUID = 7071619212577352776L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		 
		  
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Logger logger = LogManager.getLogger("FkServlet");
			response.setContentType("text/html; charset=UTF-8"); 
			request.setCharacterEncoding("utf-8");
	  		response.setCharacterEncoding("utf-8");
					String msg1 = request.getParameter("msg");
					
					String openid = request.getParameter("openid");
					
					System.out.println("openid--"+openid);
					
					HttpSession sesson =  request.getSession();
					
					if(msg1==null && msg1.equals("")){
						response.sendRedirect("feedback.jsp?openid="+openid+"&type=3");		
						return ;
					}
					
					PackageJson pj = new PackageJson();
					String pj_json = pj.judgewei_fankui(msg1, openid);
					System.out.println("反馈发送报文--："+pj_json);
					String msg="success";
					try {
						Date s1 = new Date();
						SendUtils.sendJson(ReadProp.readValue("POST_URL"), pj_json);
						Date s2 = new Date();
						long ss =s2.getTime()-s1.getTime();
						logger.info("反馈信息，请求接口名称反馈信息，用户id，"+openid+",请求——响应差时间："+ss+"毫秒");
						response.sendRedirect("feedback.jsp?openid="+openid+"&msg="+msg+"&type=1");
					} catch (Exception e) {
						response.sendRedirect("feedback.jsp?openid="+openid+"&msg="+msg+"&type=1");
						e.printStackTrace();
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
	
}
