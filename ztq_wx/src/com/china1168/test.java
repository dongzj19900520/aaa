package com.china1168;

import com.demon.weixin.core.packet.abs.RequestPacket;
import com.demon.weixin.core.packet.resp.TextResponsePacket;
import com.demon.weixin.core.parser.PacketParserException;
import com.demon.weixin.core.parser.PacketParserFactory;
import com.demon.weixin.core.parser.abs.PacketParser;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;

public class test {

	public static void main(String[] args) {
	
		String str="+22";
		String s1="";
		String s2="";
		int in=0;
		System.out.println(str.indexOf("+"));
		if(str.indexOf("+")<=-1){
			
			s1=str;
			s2="1";
		}else{
			in=str.indexOf("+");
			//System.out.println(tem.length);
			s1=str.substring(0, in);
			s2=str.substring(in+1, str.length());
		}
		System.out.println("in+"+in);
		System.out.println("s1="+s1);
		System.out.println("s2="+s2);
	}
}
