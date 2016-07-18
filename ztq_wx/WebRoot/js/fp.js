// JavaScript Document

jQuery(function($){
//	/*每天天气切换*/
//	$(".weath_list li").click(function(){
//		$(".weath_list li").removeClass("current_li");
//		$(this).addClass("current_li");
//		});
//	
//		
//	/*指数的弹窗*/	
//	$(".icon_box_r").click(function(){
//		$(".poup_box").css("display","block");
//		})	
//		
//	$(".poup_box").click(function(){
//		$(".poup_box").css("display","none");
//		})	
	

	/*自定义背景色*/	
	$(".top_r").click(function(){
		$(".per_style").css("display","block");
		})
	
	$(".per_style em").click(function(){
		$(".per_style").css("display","none");
		})
		
	$(".per_style li").click(function(){
		var a = $(this).index() + 1;
		$(".per_style li").removeClass("li_bg");
		$(this).addClass("li_bg");
//		$("body").removeClass("all_box_bg01");
//		$("body").removeClass("all_box_bg02");
//		$("body").removeClass("all_box_bg03");
//		$("body").removeClass("all_box_bg04");
//		$("body").removeClass("all_box_bg05");
//		$("body").removeClass("all_box_bg06");
//		$("body").removeClass("all_box_bg07");
//		$("body").removeClass("all_box_bg08");
		$("body").removeClass().addClass("all_box_bg0"+a);
		pcs.common.setCookie(bgCommon.cookieName,a,bgCommon.bodyCookTime);
	})	
	
	var bodycss = pcs.common.getCookie(bgCommon.cookieName);
	if(bodycss!=null && bodycss!= ''){
//		$("body").removeClass().addClass("all_box_bg0"+a);
//		$(".per_style li").removeClass("li_bg");
		$(".per_style li").eq(Number(bodycss)-1).click();
	}
})
	