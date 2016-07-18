package com.china1168.utils;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 报文发送
 * @author wuhp
 *
 */
public class SendUtils {

	//public static 
	/**
	 * json报文发送
	 * @param strPostUrl
	 * @param strJson
	 * @return
	 * @throws Exception
	 */
	 public static String sendJson(String strPostUrl, String strJson) throws Exception
	  {
	    URL url = new URL(strPostUrl);
	    URLConnection connection = url.openConnection();

	    HttpURLConnection httpConn = (HttpURLConnection)connection;
	    byte[] b = strJson.getBytes();
	    httpConn.addRequestProperty("Content-Length", String.valueOf(b.length));
	    httpConn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=gbk");
	    httpConn.setRequestMethod("POST");
	    httpConn.setConnectTimeout(30000);
	    httpConn.setReadTimeout(30000);
	    httpConn.setDoOutput(true);
	    httpConn.setDoInput(true);

	    OutputStream out = httpConn.getOutputStream();
	    BufferedWriter bw = null;
	    bw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
	    bw.write(strJson);
	    bw.flush();
	    bw.close();

	    byte[] bRecByte = new byte[1025];
	    DataInputStream dis = new DataInputStream(httpConn.getInputStream());
	    BufferedInputStream bis = new BufferedInputStream(dis);
	    int nByte = -1;
	    ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
	    nByte = bis.read(bRecByte, 0, 1024);
	    while (nByte != -1)
	    {
	      baoStream.write(bRecByte, 0, nByte);
	      nByte = bis.read(bRecByte, 0, 1024);
	    }
	    byte[] abyte = baoStream.toByteArray();
	    String strTempRecXml = new String(abyte, "UTF-8");
	   // strTempRecXml = strTempRecXml.replaceAll("&lt;", "<");
	    //strTempRecXml = strTempRecXml.replaceAll("&gt;", ">");

	    dis.close();
	    bis.close();
	    baoStream.flush();
	    baoStream.close();
	    return strTempRecXml;
	  }
}
