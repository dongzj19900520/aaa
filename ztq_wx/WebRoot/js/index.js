var cityName="";
//var strGetQuery =document.location.search;
//var cityid = pcs.common.getQueryString(strGetQuery,'cityid');
$(document).ready(function(){
	bgCommon.initBg();
	weather.getInfo();
})

var weather = {
	airIsValid : false,
	getInfo : function(){
		$.post('data.jsp',{}, function(data){
			$('#loading').hide();
			if(data.success){
				showCityName(data.b.weixin_index_new.city_name);
				showSstq(data.b.weixin_index_new);
				showAirinfo(data.b.weixin_index_new.pm25);
				showShzs(data.b.weixin_shzs.idx);
				showWeek(data.b.weixin_index_new.fc);
				showYjxx(data.b.weixin_yjxx.warn_list);
			}else{
				alert(data.errors.errmsg);
			}
		},"json");
		function showSstq(sstq){
			if(sstq.sys_time == null){
				$('#update_time').html('暂无信息');
				return;
			}
			if(sstq.ct.indexOf('.') >-1){
				var wh = sstq.ct.split('.');
				$('#Temperature').prepend(wh[0]);
				$('#Temperature2').prepend("."+wh[1]);
			}else{
				$('#Temperature').prepend(sstq.ct);
			}
			$('#humidity').html(sstq.humidity );
			$('#rainfall').html(sstq.rainfall + 'mm');
			$('#wind').html(sstq.wind + 'm/s');
			$('#visibility').html(sstq.visibility + 'm');
			$('#wind').html(sstq.wind);
			$('#update_time').html(pcs.common.toChar(Number(sstq.sys_time),"hh24:mi") + '更新');
		}
		function showYjxx(yjxx){
//			if(!$.isEmptyObject(yjxx)){
			if(yjxx.length>0){
				$('#warn').html('<img src="images/weather_ico/yjxx/'+yjxx[0].ico+'.png">')
					.attr("href","weatherWarning.html");
//				localStorage.setItem("warn", JSON.stringify(yjxx));  
			}
		}
		function showAirinfo(airinfo){
			if(airinfo == ''){
				weather.airIsValid = false;
			}else{
				weather.airIsValid = true;
				$('#air').attr("href","AQI.html?cityname=" +encodeURI(cityName));
			}
			var img = weatherCommon.getAqiImg(airinfo);
			$('#air_img').attr("src",img);
		}
		function showShzs(zsinfo){
			var isValid = false;
			if(zsinfo.length==0){
			}else{
				for(var i= 0 ;i<zsinfo.length;i++){
					var obj = zsinfo[i];
					if(obj.type == '穿衣'){
						isValid  = true;
						$('#cyzs').html(obj.nm+obj.lv+"级");
						$('#poup_o').html(obj.info);
						$(".icon_box_r").click(function(){
							$(".poup_box").css("display","block");
						});
						$(".poup_box").click(function(){
							$(".poup_box").css("display","none");
						});
					}
				}
			}
			if(!isValid){
				$('#cyzs_div').remove();
			}
			if(!isValid && !weather.airIsValid){
				$('.two_icon_box').remove();
			}
		}
		function showWeek(weatherData){
			if(weatherData == null){
				return;
			}
			var l = weatherData.length;
//			if(l>=6){
//				l = 6;
//			}
			for(var i= 0 ;i<l;i++){
				var obj = weatherData[i];
				var str= '<li id="weather'+i+'"';
				if(i == 0){
					str += ' ><span>昨天</span>';
				}else if(i == 1){
					str += '> <span>今天</span>';
				}else{
					str += '  > <span>'+obj.week+'</span>';
				}
				str += '<span>'+obj.wd_day+'</span><em><img src="images/weather_ico/weather_daytime/w'+obj.wd_day_ico+'.png" /></em>';
				str += '<span>'+obj.higt+'°</span>';
				str += '<div class="tab_posi"></div>';
				str += '<span>'+obj.lowt+'°</span>';
				str += '<em><img src="images/weather_ico/weather_night/n'+obj.wd_night_ico+'.png" /></em><span>'+obj.wd_night+'</span>';
				str += '<span>'+obj.gdt.replace('月','/').replace('日','')+'</span> </li>';
				$('#weather_list').append(str);
				$('#weather'+i).data("weather",obj);
			}
			$("#weather_list li").click(function(){
				 $("#weather_list li").removeClass("current_li");
				 $(this).addClass("current_li");
				 var data = $(this).data("weather");
				 $('#detail_date').html(data.gdt + ' '+data.week );
				 $('#detail_weather').html(cityName + data.weather +'，白天最高气温'+data.higt+'℃，夜间最低气温'+data.lowt+'℃');
			});
			$("#weather_list li:eq(1)").click();
			createWeekChart(weatherData);
		}
		function showCityName(name){
			$('#city_name').html(name);
			cityName = name;
		}
		function createWeekChart(weatherData){
			var dataOneLeft = [],dataOneRight = [],dataTwoLeft = [],dataTwoRight = [];
			for(var i= 0 ;i< weatherData.length;i++){
				var data = weatherData[i];
				data.higt = Number(data.higt);
				data.lowt = Number(data.lowt);
				if(i==0){
					dataOneLeft.push(data.higt);
					dataTwoLeft.push(data.lowt);
				}else if(i==1){
					dataOneLeft.push(data.higt);
					dataTwoLeft.push(data.lowt);
					dataOneRight.push( {x: i, y: data.higt});
					dataTwoRight.push( {x: i, y: data.lowt});
				}else{
					dataOneRight.push( {x: i, y: data.higt});
					dataTwoRight.push( {x: i, y: data.lowt});
				}
			}
			$('#container').highcharts({
		        chart: {
		            type: 'spline',
		            backgroundColor: '',
//		            plotBackgroundColor: '',
		            margin: [0, 0, 0, 0]
		        },
				title: null,
				subtitle:  null,
				legend:{
		        	enabled:false
		        },
		        credits: {  
		      	  enabled: false  
		        },
		        xAxis: {
					title: null,
//					tickColor:null,
					categories: [0,1,2,3,4,5],
					gridLineWidth: 0,
					lineWidth:0,
					labels:{ enabled:false }
		        },
		        yAxis: {
		            title: null,
					gridLineWidth: 0,
					labels:{ enabled:false }
		        },
		        tooltip: {
		        	enabled:false,
		            crosshairs: null,
		            shared: null,
		        },
		        series:[{
		            name: '1',
		            color: '#FFFF00',
		            lineWidth:1,
		            data: dataOneLeft,
		            dashStyle: 'longdash',
		            marker: {
		              symbol: 'circle',
		              radius: 5,
		            	  fillColor:'#FFFF00'
		            },
		        }, {
		            name: '2',
		            color: '#FFFFFF',
		            lineWidth:1,
		            marker: {
		                symbol: 'circle',
		                	radius: 5,
		                	 fillColor:'#ffffff'
		              },
		            data: dataOneRight
		        },
		        
		        {
		            name: '3',
		            color: '#FFFF00',
		            lineWidth:1,
		            data: dataTwoLeft,
		            dashStyle: 'longdash',
		            marker: {
		              symbol: 'circle',
		              radius: 5,
		            	  fillColor:'#FFFF00'
		            },
		        }, {
		            name: '4',
		            color: '#FFFFFF',
		            lineWidth:1,
		            marker: {
		                symbol: 'circle',
		                	radius: 5,
		                	 fillColor:'#ffffff'
		              },
		            data: dataTwoRight
		        }
		        
		        ]
		    });
		}
	}
}
