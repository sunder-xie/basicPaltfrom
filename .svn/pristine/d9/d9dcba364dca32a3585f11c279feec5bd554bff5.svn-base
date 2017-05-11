$(document).ready(function() {
	setInterval("changecolor()", 300);
	//浏览器版本提示
	if('Chrome'!=exp_type||'28.0.1500.72'!=exp_version){//不为此版本则提示，版本下载链接
		var msg='<html><a href="'+imgUrl + '/js/exploere/Chrome28.0.1500.72.exe"><font class=fontStyle>下载安装文件</font></a>'+
		'</br>稳定版浏览器：谷歌28(Chrome28.0.1500.72),'+
			'使用其它浏览器如有无法正常访问的页面,请点击“下载安装文件”链接,安装该版本浏览器尝试!;'+
			'</html>';
		$.messager.show({
            title: '温馨小贴士',
            msg: msg,
            timeout: 10000,
            showType: 'show',
            width:260,
            height:130
		});
	}
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}
var nextcolor = 0;
function changecolor() {
	var colors = [ "yellow", "red" ];
	document.getElementById("logfail").style.color = colors[nextcolor];
	nextcolor = nextcolor == 0 ? 1 : 0;
}

function forget() {
	var passport = encodeURIComponent($("#passport").val());
	window.open(
			appUrl + '/allUserAction1!sendMailPre.jspa?loginId=' + passport,
			'_blank');
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	return true;
};

function submit() {
	var form = window.document.forms[0];
	form.submit();
}
/**
 * 联系信息部
 */
function openView(){
	$('#openView').show('close');
	var msg='<html>'+
	'Email:<a href="#"><font color="blue" size="2">exp@zjxpp.com</font></a> 短号:<a href="#"><font color="blue" size="2">721151</font></a> '
	+'</br>手机:<a href="#"><font color="blue" size="2">18657270151</font></a> 座机:<a href="#"><font color="blue" size="2">057128806887</font></a>'
    +'</br>QQ群:<a href="#"><font color="blue" size="2">316257628</font></a></html>';
/*$.messager.show({
	id:'openView',
    title: '联系信息部',
    msg: msg,
    timeout: 5000,
    showType: 'show',
    width:260,
    height:130
});*/
	$.messager.alert('联系信息部', msg, 'info');
}