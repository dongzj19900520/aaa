package com.china1168.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

public class InputUtils {

	public  String readInput(HttpServletRequest request) throws IOException{
		
		InputStream is = request.getInputStream();
		
		StringBuffer sb = new StringBuffer();
		
		int buff_len = 10240;
		byte[] buff = new byte[buff_len];  
        int rc = 0;  
        while ((rc = is.read(buff, 0, buff_len)) > 0) {  
             sb.append(new String(buff,0,rc,"UTF-8"));
        } 
        is.close();
        String inputData = sb.toString();
		return inputData;
		
	}
}
