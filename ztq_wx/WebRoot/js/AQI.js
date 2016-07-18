var cityName="";
var strGetQuery =document.location.search;
var cityname = decodeURI(pcs.common.getUrlParameter('cityname'));
$(document).ready(function(){
	bgCommon.initBg();
	aqi.getInfo();
})

var aqi = {
	updateTimeCount : 0,
	getInfo : function(){
		$.post('AQI.jsp',{cityname:cityname}, function(data){
			if(data.success){
				showDetail(data.b.weixin_air, data.sysdate);
				showAirinfo(data.b.weixin_air.quality);
				createChart(data.b.weixin_air.AQIList);
			}else{
				alert(data.errors.errmsg);
			}
			
		},"json");
		function showDetail(data,nowDate){
			$('#aqi').html(data.aqi );
			$('#health_advice').html('空气质量：'+data.quality + '。' + data.health_advice.replace('建议：',''));
			$('#rank').html(data.rank);
			$('#rank_total').html("/"+data.rank_total);
			var wdDate = data.updateTime;
			var d = new Date(Number(wdDate.substring(0,4)) ,Number(wdDate.substring(5,7))-1 ,Number(wdDate.substring(8,10)), Number(wdDate.substring(11,13)),Number(wdDate.substring(14,16)));
			aqi.updateTimeCount = parseInt((nowDate.time - d.getTime())/1000/60/60);
			$('#update_time').html(aqi.updateTimeCount+"小时前更新");
		}
		function showAirinfo(airinfo){
			console.debug(airinfo);
			var img = weatherCommon.getAqiImg(airinfo);
			$('#air_img').attr("src",img);
		}
		function createChart(wetherData){
			var xArr = [],yArr = [],dataArr = [],min = null,max = null;
			var l = wetherData.length;
			var imgstr = "";
			for(var i= 0 ;i< 6;i++){
				var data = wetherData[l-i-1];
				imgstr = "<li><img src='"+weatherCommon.getAqiImg(data.quality) +"'></li>"+ imgstr;
				data.aqi = Number(data.aqi);
				xArr.unshift(data.time_hour+":00");
				dataArr.unshift(data.aqi);
				if(min == null){
					min = data.aqi;
				}else if(min>data.aqi){
					min  = data.aqi;
				}
				if(max == null){
					max = data.aqi;
				}else if(max < data.aqi){
					max  = data.aqi;
				}
			}
			$('#AQI_ICON').html(imgstr);
			var avg =( min + max )/2;
//			var startY = min - (avg - min );
			yArr = [ min, avg, max];
			$('#container').highcharts({
		        chart: {
		            type: 'line',
		            backgroundColor: '',
//		            plotBackgroundColor: '',
		            margin: [20, 0, 80, 0]
//		            marginTop:20
		        },
				title: null,
//				subtitle:  "wwww",
				legend:{
		        	enabled:false
		        },
		        credits: {  
		      	  enabled: false  
		        },
		        xAxis: {
					categories: xArr,
					 offset: 50,
					 tickLength:0,
					labels:{ enabled:true,
						 formatter: function () {
							 if(this.isLast && aqi.updateTimeCount<1){
								 return  '<div style="color:#ffdd53;">现在</div>';
							 }else{
								 return this.value;
							 }
			                },
						 style: {
	                    		color: '#ffffff'
	                    	}
	                    }
		        },
		        yAxis: {
		            title: null,
		            tickPositions: yArr,
		            startOnTick: false,
		            tickmarkPlacement:'on',
		            gridLineDashStyle: 'longdash',
		            gridLineColor: '#cad1db',
	            	 labels: {
	                     align: 'left',
	                     x: 10,
	                     y: -5,
	                     style: {
	                    		color: '#cad1db'
	                    	}
	                 }
		        },
		        tooltip: {
		        	enabled:false,
		        },
		        plotOptions: {
		            line: {
		                dataLabels: {
		                    enabled: true,
		                    color : '#ffffff'
		                },
		                enableMouseTracking: false
		            }
		        },
		        series:[{
		            name: 'aqi',
		            color: '#FFFFFF',
		            lineWidth:1,
		            marker: {
		                symbol: 'circle',
		                	radius: 5,
		                	 fillColor:'#ffffff'
		              },
		            data: dataArr
		        } ]
		    });
		}
	}
}

