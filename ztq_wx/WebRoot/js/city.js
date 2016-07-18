var cityName="";
var strGetQuery =document.location.search;
var cityid = pcs.common.getQueryString(strGetQuery,'cityid');
$(document).ready(function(){
	bgCommon.initBg();
	area.getProvince();
})

var area = {
	getProvince : function(){
		 $.getJSON('json/province.json', function(data) {
			var str = "";
			$(data.ROWDATA.ROW).each(function(index, ele) {
				if(ele.ID == '0'){
					str +='<div class="province_box" data-key="">';
				}else{
					str +='<div class="province_box" data-key="'+ele.NAME+','+ele.SHOW_NAME+','+ele.PIN_YIN.toUpperCase()+','+ele.PY.toUpperCase()+'">';
				}
				str +=' <div class="province_nam" onClick="city_son(this)">'+ele.NAME+'<em></em></div>';
				str +='<ul class="province_city" id="province_'+ele.ID+'">';
				str +='</ul></div>';
//	        
//				areaArr.push({
//					'id' : ele.ID,
//					'name' : ele.NAME,
//					'ext2' : ele.EXT2,
//					'p_name' : ele.P_NAME
//				});
			});
			$('#province_box').html(str);
			area.getCity();
		})
	},
	getCity:function(){
		 $.getJSON('json/city.json', function(data) {
				var tempObj = {};
				$(data.ROWDATA.ROW).each(function(index, ele) {
					if(tempObj[ele.PARENT_ID] == null){
						 tempObj[ele.PARENT_ID] = "";
					}
					tempObj[ele.PARENT_ID] += '<li  data-key="'+ele.NAME+','+ele.SHOW_NAME+','+ele.PIN_YIN.toUpperCase()+','+ele.PY.toUpperCase()+'" onclick="area.setCity(\''+ele.ID+'\')">'+ele.SHOW_NAME+'</li>';
				});
				 for ( var p in tempObj ){ // 方法 
					 $('#province_'+p).html(tempObj[p]);
				 }
		})
	},
	search : function(obj){
		var v = obj.value.toUpperCase();
		$('#ext_city').remove();
		if(v != ''){
			v = v.toUpperCase();
			$("div[data-key]").hide();
			$("div[data-key*='"+v+"']").show();
			var $li = $("li[data-key*='"+v+"']");
			if($li.length>0){
				var str ='<div class="province_box" id="ext_city">';
				str +=' <div class="province_nam classT" onClick="city_son(this)">城市<em class="em_bg"></em></div>';
				str +='<ul class="province_city" style="display:block" id="ext_city_list">' ;
				str +='</ul></div>';
				$('#province_box').append(str);
				$('#ext_city_list').append($li.clone());
			}
		}else{
			$("div[data-key]").show();
		}
		
	},
	setCity:function(id){
		$.ajax({    
	    	type: "post",  
	    	async:false,
	    	url : 'city.jsp',   
	    	dataType:'json', 
	    	data: 'cityid='+id, 
//	    	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	    	success: function(data){ 
	            if(data.success){
					location.href='index.html';
				}else{
					alert(data.errors.errmsg);
				}
			} 
	    }); 
	}
}

