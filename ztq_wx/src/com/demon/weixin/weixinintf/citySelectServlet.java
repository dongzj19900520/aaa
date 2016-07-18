package com.demon.weixin.weixinintf;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.china1168.ReadProp;
import com.china1168.po.UserCity;
import com.china1168.utils.PackageJson;
import com.china1168.utils.ResolvePackets;
import com.china1168.utils.SendUtils;

public class citySelectServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String openid= req.getParameter("openid");
		String cityid = req.getParameter("cityid");
		Logger logger = LogManager.getLogger("citySelectServlet");
		PackageJson pj = new PackageJson();
		String pj_json = pj.judgewei_user_city(cityid, openid);
		System.out.println("用户城市请求报文："+pj_json.toString());
		ResolvePackets rp = new ResolvePackets();
		try {
			Date s1 = new Date();
			String resp_json = SendUtils.sendJson(ReadProp.readValue("POST_URL"), pj_json);
			Date s2 = new Date();
			long ss = s2.getTime()-s1.getTime();
			logger.info("用户登录信息，用户id是："+openid+",请求接口名称用户登录，请求——响应差时间："+ss+"毫秒");
			System.out.println("用户城市响应报文："+resp_json.toString());
			UserCity city = rp.resolvewei_user_city(resp_json);
			
			resp.sendRedirect("index.jsp?cityid="+city.getCity_id()+"&task=citySelect&type="+city.getType()+"&openid="+city.getAccessid());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
