/**
 * 公共js
 */
 // 模块名称注册
if (pcs == null)
	var pcs = {};
if (pcs.common == null)
	pcs.common = {};

var url = document.location.pathname;
var itmp = url.indexOf("/", 1);
var webpath = itmp < 0 ? url : url.substr(0, itmp);
if (webpath.indexOf('/') == -1) {
	webpath = '/' + webpath;
}

pcs.common = {

	path : window.location.protocol + '//' + window.location.host + webpath,
	
	getQueryString : function (url,name){
		var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
		if (reg.test(url)){
			return unescape(RegExp.$2.replace(/\+/g, " "));
		}
		return "";
	},
	getUrlParameter:function(name) {  
		var value = null;
		var search = document.location.search;
		if (search == null || search == "" || name == null || name == "")
			return value;
		search = search.toLowerCase();
		name = name.toLowerCase();
		name += "=";
		var itmp = search.indexOf(name); 
		if (itmp < 0) {
			return value;
		}
		search = search.substr(itmp + name.length);
		itmp = search.indexOf("&");
		if (itmp < 0) {
			return search;
		} else {
			search = search.substr(0, itmp);
			return search;
		}
	},
	ellipsis : function(str, maxlength, suffix) {
		if (str.length <= maxlength) {
			return str;
		} else {
			return str.substr(0, maxlength - 0) + suffix
		}
	},
	
	
	setCookie : function(name,value,time){
	    document.cookie = name + "="+ escape (value) + (time ?  ";expires=" + new Date(new Date().getTime() +time).toGMTString() :"");
	},

	//读取cookies
	 getCookie : function(name){
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	    if(arr=document.cookie.match(reg))
	        return (unescape(arr[2]));
	    else
	        return null;
	},
	getScript :function (url, callback) {
		  var head = document.getElementsByTagName('head')[0],
		            js = document.createElement('script');
		 
		         js.setAttribute('type', 'text/javascript'); 
		       js.setAttribute('src', url); 
		 
		      head.appendChild(js);
		 
		        //执行回调
	         var callbackFn = function(){
		                if(typeof callback === 'function'){
		                    callback();
		                  }
		           };
		 
		       if (document.all) { //IE
		          js.onreadystatechange = function() {
		                if (js.readyState == 'loaded' || js.readyState == 'complete') {
		                       callbackFn();
		                   }
		              }
		          } else {
		           js.onload = function() {
		                 callbackFn();
		            }
		          }
		     },
     /**
 	 * 功能：转换时间格式
 	 * 描述: datetime 为输入时间，format 为时间格式
 	 */
 	toChar:function(datetime, format) {
 		if(datetime=="" || datetime==null || datetime==undefined){
 			return "";
 		}else{
 			var date = new Date(datetime);
 			var yyyy = date.getFullYear();
 			var mm = date.getMonth()+1;
 			var dd = date.getDate();
 			var hh24 = date.getHours();
 			var mi = date.getMinutes();
 			var ss = date.getSeconds();
 			var s1 = format.replace(/yyyy|YYYY/g, yyyy);
 			var s2 = s1.replace(/mm|MM/g,mm<10 ? "0" + mm : mm);
 			var s3 = s2.replace(/dd|DD/g,dd<10 ? "0" + dd : dd);
 			var s4 = s3.replace(/hh24|HH24/g,hh24<10 ? "0" + hh24 : hh24);
 			var s5 = s4.replace(/mi|MI/g,mi<10 ? "0" + mi : mi);
 			var s6 = s5.replace(/ss|SS/g,ss<10 ? "0" + ss : ss);
 			return s6;
 		}		
 	},
};
var weatherCommon = {
	getAqiImg : function(airinfo){
		var img = "wu";
		if(airinfo == "优"){
			img = "y";
		}else if(airinfo == "良"){
			img = "l";
		}else if(airinfo == "轻度污染"){
			img = "q";
		}else if(airinfo == "重度污染"){
			img = "zz";
		}else if(airinfo == "严重污染"){
			img = "y";
		}else if(airinfo == "中度污染"){
			img = "z";
		}
		return "images/weather_ico/air/"+img+".png";
	}
}
var bgCommon={
	cookieName :"bodyCss",
	bodyCookTime : 7*24 * 60 * 60 * 1000,
	initBg : function(){
		var bodycss = pcs.common.getCookie(bgCommon.cookieName);
		if(bodycss!=null && bodycss!= ''){
			$("body").removeClass().addClass("all_box_bg0"+bodycss);
////			$(".per_style li").removeClass("li_bg");
//			$(".per_style li").eq(Number(bodycss)-1).click();
			pcs.common.setCookie(bgCommon.cookieName,bodycss,bgCommon.bodyCookTime);
		}
	}
}
var downHrefCommon = {
	to:function(){
		location.href='http://218.85.78.125:8099/gz_wap/';
	}
}