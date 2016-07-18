// JavaScript Document

function city_son(obj){
	if($(obj).hasClass("classT")){
		$(".province_nam").removeClass("classT");
		$(".province_city").css("display","none");
		$(".province_nam em").removeClass("em_bg");
		}else{
			$(".province_nam").removeClass("classT");
			$(obj).addClass("classT");
			$(".province_city").css("display","none");
			$(".province_nam em").removeClass("em_bg");
			$(obj).parents(".province_box").children(".province_city").css("display","block");
			$(obj).children("em").addClass("em_bg");;
			}
	
	}