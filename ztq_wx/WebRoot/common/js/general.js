/**
* author: chenbin
* date: 2008-11-18
* function: Form Operate
*/

/********** form **********/
function doSubmit(formId, action, method, target, enctype) {
	var form = document.getElementById(formId);
	form.action = action || "";
	form.method = method || "POST";
	form.target = target || "_self";
	form.enctype = enctype || "application/x-www-form-urlencoded";
	form.submit();
}
function doReset(formId) {
	var form = document.getElementById(formId);
	form.reset();
}
function addUrlParameter(url, paramName, paramValue) {
	if (url.indexOf("?") < 0) {
		url += "?" + paramName + "=" + paramValue;
	} else {
		url += "&" + paramName + "=" + paramValue;
	}
}

/********** checkbox **********/
//<input type="checkbox" onclick="checkAll('chkName', this)"/>
//<input type="checkbox" name="chkName"/>
function checkAll(checkboxName, checkAll) {
	if (checkAll.checked) {
		checkAllTrue(checkboxName);
	} else {
		checkAllFalse(checkboxName);
	}
}
function checkAllTrue(checkboxName) {
	var checkbox = document.getElementsByName(checkboxName);
	for (var i = 0; i < checkbox.length; i++) {
		checkbox[i].checked = true;
	}
}
function checkAllRevert(checkboxName) {
	var checkbox = document.getElementsByName(checkboxName);
	for (var i = 0; i < checkbox.length; i++) {
		if(checkbox[i].checked){  
			checkbox[i].checked = false;
	    }else{ 
	    	checkbox[i].checked = true;
	    }   
	}
}

function checkAllFalse(checkboxName) {
	var checkbox = document.getElementsByName(checkboxName);
	for (var i = 0; i < checkbox.length; i++) {
		checkbox[i].checked = false;
	}
}
function getCheckedNum(checkboxName) {
	var checkbox = document.getElementsByName(checkboxName);
	var num = 0;
	var count = checkbox.length;
	for (var i = 0; i < count; i++) {
		if (checkbox[i].checked) {
			num++;
		}
	}
	return num;
}
function getCheckedVal(checkboxName) {
	var checkbox = document.getElementsByName(checkboxName);
	var value = "";
	var arr = new Array(getCheckedNum(checkboxName));
	var j = 0;
	var count = checkbox.length;
	for (var i = 0; i < count; i++) {
		if (checkbox[i].checked) {
			arr[j++] = checkbox[i].value;
		}
	}
	value = (arr.join(","));
	return value;
}

function entryLogin(event,triggerId) {
	var trigger = document.getElementById(triggerId);
	event = event ? event : (window.event ? window.event : null);
	if (event.keyCode == 13) {
		trigger.focus();
	}
}

//过滤文件框
function doFilter(input,format){
	var exts=format.split("|");
	var msg="";
	var flag=false;
	for(var i=0;i<exts.length;i++){
		msg+=exts[i]+",";
	}
	var fileName=input.value;
	if(fileName.length<=0){
		return;
	}
	var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
	msg=msg.substring(0,msg.length-1);
	for(var i=0;i<exts.length;i++){
		if(fileType==exts[i]){
			flag=true;
			break;
		}
	}
	if(!flag){		
		alert("\u8bf7\u9009\u62e9"+msg+"\u6587\u4ef6!");
		input.outerHTML=input.outerHTML;		
		return false;
	}
	return true;
}

//checkbox回选
function checkboxBackSelecte(value, selectedName,fn) {
	if(value != null && value.length>0){
		var cbk2 = document.getElementsByName(selectedName);
		for (var i = 0; i < cbk2.length; i++) {
			if (jQuery.inArray(cbk2[i].value, value) > -1) {
				cbk2[i].checked = true;
				if(fn){
					fn(cbk2[i]);
				}
			}
		}
	}
}
//json数据转数组
function json2arr(jsonArr, filed) {
	var arr = new Array();
	if(jsonArr != null){
		for(var i = 0; i < jsonArr.length; i++){
			arr.push(jsonArr[i][filed] + '');
		}
	}
	return arr;
}

//radio初始选择
function radioonload(radioName, radioValue, fn) {
	var radio = document.getElementsByName(radioName);
	for (var k = 0; k < radio.length; k++) {
		if (radio[k].value == radioValue) {
			radio[k].checked = true;
		}
	}
	if(fn !=null){
		fn();
	}
}


//div层通用js，ui中整理提取
function divResize(div){
	var bh=document.documentElement;
	var tk_width=$(div).width();
	var tk_height=$(div).height();
	var bodyh=$('body').height();
	var clientW=bh.clientHeight;
	var _topValue=0;
	var _Scrolltop=0;
	var topValue;
	var leftValue;
	var _Scrolltop=0;
	 tb_position();
	 $(window).scroll(function (){
		_Scrolltop=$(document).scrollTop(); ;
		IEvalue();
	});
	$('.ifrmaeStyle').show();
	$('.cover').show();
	$(div).show();
	tb_position();
	$(div).css('left', leftValue+'px');
	$(div).css('top', topValue+'px');
	$(".ifrmaeStyle").css('height',yScroll +'px');
	$(".cover").css('height',yScroll +'px');
	
	function IEvalue(){
	 _topValue=topValue;	
	_topValue = _Scrolltop+_topValue;
	if(navigator.userAgent.indexOf("MSIE 6.0") > 0){
		
		$(div).css('top',_topValue+'px');
	}
	else{
		$(div).css('top', topValue+'px');
		
	}};
	function tb_position() {
		var w = self.innerWidth || (bh&&bh.clientWidth) || document.body.clientWidth;
		var h = self.innerHeight || (bh&&bh.clientHeight) || document.body.clientHeight;
		leftValue=Math.round((w - tk_width)/2);
		topValue=Math.round((h - tk_height)/2);
		if(clientW>bodyh){
			yScroll=clientW;
		}
		else{
			if (window.innerHeight && window.scrollMaxY) {	
				yScroll = window.innerHeight + window.scrollMaxY;
			} else if (document.body.scrollHeight > document.body.offsetHeight){ 
				yScroll = document.body.scrollHeight;
			} else { 
				yScroll = document.body.offsetHeight;
			}
		}
	}
}

//打开弹窗计算弹窗的位置,来自ui common.js，与上面方法divResize雷同
function popup_window(){
	var bh=document.documentElement;
	var tk_width=div.width();
	var tk_height=div.height();
	var bodyh=$('body').height();
	var clientW=bh.clientHeight;
	var _topValue=0;
	var _Scrolltop=0;
	var topValue;
	var leftValue;
	var _Scrolltop=0;
	 tb_position();
	 $(window).scroll(function (){
		_Scrolltop=$(document).scrollTop(); ;
		IEvalue();
	});
	$('.ifrmaeStyle').show();
	$('.cover').show();
	div.show();
	tb_position();
	div.css('left', leftValue+'px');
	div.css('top', topValue+'px');
	$(".ifrmaeStyle").css('height',yScroll +'px');
	$(".cover").css('height',yScroll +'px');
	
	function IEvalue(){
	 _topValue=topValue;	
	_topValue = _Scrolltop+_topValue;
	if(navigator.userAgent.indexOf("MSIE 6.0") > 0){
		
		div.css('top',_topValue+'px');
	}
	else{
		div.css('top', topValue+'px');
		
	}};
	function tb_position() {
		var w = self.innerWidth || (bh&&bh.clientWidth) || document.body.clientWidth;
		var h = self.innerHeight || (bh&&bh.clientHeight) || document.body.clientHeight;
		leftValue=Math.round((w - tk_width)/2);
		topValue=Math.round((h - tk_height)/2);
		if(clientW>bodyh){
			yScroll=clientW;
		}
		else{
			if (window.innerHeight && window.scrollMaxY) {	
				yScroll = window.innerHeight + window.scrollMaxY;
			} else if (document.body.scrollHeight > document.body.offsetHeight){ 
				yScroll = document.body.scrollHeight;
			} else { 
				yScroll = document.body.offsetHeight;
			}
		}
	}
}

function isNull(str){
	if(str == '' || str == null || str == 'undefined'){
		return true;
	}
	return false;
}

function strToDate(str){
	var reg=/^(\d+)-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
	var ArryTime = str.match(reg);
	ArryTime[2]=ArryTime[2]-1;
	var v_tmpDate=new Date(ArryTime[1],ArryTime[2],ArryTime[3],ArryTime[4],ArryTime[5],ArryTime[6]); 
	return v_tmpDate;
}