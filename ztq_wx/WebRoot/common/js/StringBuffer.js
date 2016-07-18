/******************************************************
 * 描述：StringBuffer类用来实现字符串的连接!!
 * @Create on:  2009-03-19
 * @Author   :  郭银剑
 ******************************************************/
function StringBuffer() {
	this._stringBuffer = new Array();
}
StringBuffer.prototype.append = function(newStr) {
	this._stringBuffer.push(newStr);
}
StringBuffer.prototype.toString = function() {
	return this._stringBuffer.join("");
}