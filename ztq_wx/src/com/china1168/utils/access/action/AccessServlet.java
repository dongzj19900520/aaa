package com.china1168.utils.access.action;

import java.io.BufferedOutputStream;
import java.io.IOException;
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

import com.china1168.ReadProp;
import com.china1168.utils.InputUtils;
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



public class AccessServlet extends HttpServlet{

	
	private static Log log = LogFactory.getLog(AccessServlet.class);
	private static final long serialVersionUID = 7071619212577352776L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		 String signature = request.getParameter("signature");
		    String timestamp = request.getParameter("timestamp");
		    String nonce = request.getParameter("nonce");
		    String echostr = request.getParameter("echostr");

		    String data = "signature=" + signature + ";timestamp=" + timestamp + ";nonce=" + nonce + ";echostr=" + echostr;
		    System.out.println("data---"+data);
		    log.debug("接收到微信验证URL有效性请求 data=" + data);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("有Post消息过来了哦...");
		
		InputUtils utils =new InputUtils();
        
		String inputData =utils.readInput(request);
		
		
//		inputData="<xml><ToUserName><![CDATA[gh_5b86c5c633d0]]>"+
//					"</ToUserName>"+
//					"<FromUserName><![CDATA[ogyUetzQGVZ3IQ11wbQLF538SuP8]]></FromUserName>"+
//					"<CreateTime>1400126034</CreateTime>"+
//				    "<MsgType><![CDATA[text]]></MsgType>"+
//					"<Content><![CDATA[啊啊啊了]]></Content>"+
//					"<MsgId>6013495526508310711</MsgId>"+
//					"</xml>";
		System.out.println("微信报文--"+inputData);
		
		
		ResponsePacket responsePacket = null;
		Logger logger = LogManager.getLogger("AccessServlet");
		String outData = "";
		PacketParser packetParser = PacketParserFactory.getInstance().createPacketParser(PacketParserFactory.XML_PACKET_PARSER_TYPE);
		try {
			RequestPacket requestPacket = packetParser.parser(inputData);
			if(requestPacket != null){
				if(requestPacket instanceof TextRequestPacket){
					TextRequestPacket textRequestPacket = (TextRequestPacket)requestPacket;
					
					requestPacket.getFromUserName();
					
					HttpSession sesson =  request.getSession();
					sesson.setAttribute("ACCESSID", requestPacket.getFromUserName());
					
					String cont=textRequestPacket.getContent();
					String city ="";
					String type="";
					int in=0;
					if(cont.indexOf("+")<=-1){
						city=cont;
						type="1";
					}else{
						in=cont.indexOf("+");
						city=cont.substring(0, in);
						type=cont.substring(in+1, cont.length()).trim();
						
					}
					PackageJson pj = new PackageJson();
					String pj_json = pj.judgewei_city_db(city, type);
					 Date begin_time =new Date();
					String res_json= SendUtils.sendJson(ReadProp.readValue("POST_URL"), pj_json);
					 Date end_time =new Date();
					 long ss =end_time.getTime()-begin_time.getTime();
					logger.info("点播信息，请求接口名称点播信息，用户id"+requestPacket.getFromUserName()+"请求——响应差时间："+ss+"毫秒");
					ResolvePackets rp = new ResolvePackets();
					JSONObject  dataJson= JSONObject.fromObject(res_json);
					
					System.out.println("點播响应报文："+dataJson.toString());
					JSONObject json_b = dataJson.getJSONObject("b");
					JSONObject wei_city_db= json_b.getJSONObject("weixin_dianbo");
					JSONObject db = wei_city_db.getJSONObject("db");
					String msg = db.getString("msg");
					
					TextResponsePacket textResponsePacket = new TextResponsePacket(requestPacket);
					textResponsePacket.setContent(msg);
					out(response,textResponsePacket.toXml().getBytes("utf-8"));
				}
			}else{
				log.error("解析传入的数据包失败! data=" + inputData);
			}
		} catch (PacketParserException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(responsePacket != null){
			outData = responsePacket.toXml();
		}
		if(responsePacket != null){
			out(response,outData.getBytes("utf-8"));
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
