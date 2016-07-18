var cityName="";
var strGetQuery =document.location.search;
var cityid = pcs.common.getQueryString(strGetQuery,'cityid');
$(document).ready(function(){
	bgCommon.initBg();
	warn.getInfo();
})

var warn = {
	updateTimeCount : 0,
	getInfo : function(){
		$.post('weatherWarning.jsp',{cityid:cityid}, function(data){
			if(data.success){
				showDetail(data.b.weixin_yjxx.warn_list);
			}else{
				alert(data.errors.errmsg);
			}
		},"json");
		function showDetail(data){
			var str = "";
			for(var i=0;i<data.length;i++){
				var obj = data[i];
				if((i+1)==data.length){
					str += '<div class="warn_list" style="background-image:none;">'; 
				}else{
					str += '<div class="warn_list">';
				}
				str += '<div class="warn_title">';
				str += '<img src="images/weather_ico/yjxx/'+obj.ico+'.png">';
				str += '<ul>';
				str +=' <li class="warn_nam">'+obj.title+'</li>';
				str +='   <li class="warn_time">'+obj.station_name + obj.pt +'发布</li>';
				str +='      </ul>';
				str +='   </div>';
				str +='   <p class="warn_text">' + obj.content + '</p>';
				str +='   <p class="warn_text">&nbsp;</p>';
		        str += '<p class="warn_text">'+ obj.defend + '</p>';
		        str +=' </div>';
			}
			$('#warn_box').html(str);
		}
	}
}

