package com.china1168.utils;

public class StringUtils {

	public static String isNull(String Objstr,String replaceStr){
		String tem="";
		
		return (Objstr==null || Objstr.equals(""))?replaceStr:Objstr;
		
	}
	public static String returnPic(String pic){
		
		return "."+pic+"{background:url(../images/"+pic+".png) no-repeat;}";
	}
	
	public static String returnPul(String  num){
		String pul="";
		int n = Integer.valueOf(num);
		if(0<=n && n<=50){
			pul="优";
		}else if(51<=n && n<=100){
			pul="良";
		}else if (101<=n && n<=150) {
			pul="轻度污染";
		}else if (151<=n && n<=200) {
			pul="中度污染";
		}else if (201<=n && n<=300) {
			pul="重度污染";
		}else if (301<=n && n<=500) {
			pul="严重污染";
		}
		
		return pul;
	}
	
}
