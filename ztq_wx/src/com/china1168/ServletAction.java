package com.china1168;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.china1168.utils.PackageJson;
import com.china1168.utils.ResolvePackets;
import com.china1168.utils.SendUtils;
import com.demon.weixin.core.packet.abs.RequestPacket;
import com.demon.weixin.core.packet.req.TextRequestPacket;
import com.demon.weixin.core.parser.PacketParserException;
import com.demon.weixin.core.parser.PacketParserFactory;
import com.demon.weixin.core.parser.abs.PacketParser;
import com.demon.weixin.core.pull.packet.rev.WebAccessTokenRevPacket;
import com.demon.weixin.core.pull.packet.send.WebAccessTokenSendPacket;

public class ServletAction {

	/**
	 * 点播
	 * @return
	 */
	public String getdbMsg(String inputData,String code,HttpServletRequest request, HttpServletResponse respons){
		PacketParser packetParser = PacketParserFactory.getInstance().createPacketParser(PacketParserFactory.XML_PACKET_PARSER_TYPE);
		try {
			RequestPacket requestPacket = packetParser.parser(inputData);
			if(requestPacket != null){
				if(requestPacket instanceof TextRequestPacket){
					TextRequestPacket textRequestPacket = (TextRequestPacket)requestPacket;
					
					requestPacket.getFromUserName();
					String appid ="wxffbf421b778aea3f"; 
					String secret = "f05b52d9c2b145d41f44e9c9e2c8d7d3";
					WebAccessTokenSendPacket webAccessTokenSendPacket = new WebAccessTokenSendPacket(appid,secret,code);
					WebAccessTokenRevPacket revPacket = webAccessTokenSendPacket.send();
					
					String openid = revPacket.getOpenid();
					System.out.println("openid--"+openid);
					
					String cont=textRequestPacket.getContent();
					String[] arr = cont.split("+");
					String city =arr[0];
					String type=arr[1];
					
					PackageJson pj = new PackageJson();
					String pj_json = pj.judgewei_city_db(city, type);
					String res_json= SendUtils.sendJson(Constant.POST_URL, pj_json);
					ResolvePackets rp = new ResolvePackets();
					JSONObject  dataJson= JSONObject.fromObject(res_json);
					JSONObject json_b = dataJson.getJSONObject("b");
					String msg = json_b.getString("msg");
					
//					TextResponsePacket textResponsePacket = new TextResponsePacket(requestPacket);
//					textResponsePacket.setContent(msg);
					
					return msg;
					
					
				}
				

//				
			}else{
				//log.error("解析传入的数据包失败! data=" + inputData);
			}
		} catch (PacketParserException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	/**
	 * 反馈
	 * @return
	 */
	
	public String getfkMsg(String inputData,String code,HttpServletRequest request, HttpServletResponse respons){
		PacketParser packetParser = PacketParserFactory.getInstance().createPacketParser(PacketParserFactory.XML_PACKET_PARSER_TYPE);
		try {
			RequestPacket requestPacket = packetParser.parser(inputData);
			if(requestPacket != null){
				if(requestPacket instanceof TextRequestPacket){
					TextRequestPacket textRequestPacket = (TextRequestPacket)requestPacket;
					
					requestPacket.getFromUserName();
					String appid ="wxffbf421b778aea3f"; 
					String secret = "f05b52d9c2b145d41f44e9c9e2c8d7d3";
					WebAccessTokenSendPacket webAccessTokenSendPacket = new WebAccessTokenSendPacket(appid,secret,code);
					WebAccessTokenRevPacket revPacket = webAccessTokenSendPacket.send();
					
					String openid = revPacket.getOpenid();
					System.out.println("openid--"+openid);
					
					String cont=textRequestPacket.getContent();

					PackageJson pj = new PackageJson();
					String pj_json = pj.judgewei_fankui(cont, openid);
					String res_json= SendUtils.sendJson(Constant.POST_URL, pj_json);
					ResolvePackets rp = new ResolvePackets();
					JSONObject  dataJson= JSONObject.fromObject(res_json);
					JSONObject json_b = dataJson.getJSONObject("b");
					String msg = json_b.getString("msg");
					
//					TextResponsePacket textResponsePacket = new TextResponsePacket(requestPacket);
//					textResponsePacket.setContent(msg);
					
					return msg;
					
					
				}
				

//				
			}else{
				//log.error("解析传入的数据包失败! data=" + inputData);
			}
		} catch (PacketParserException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
