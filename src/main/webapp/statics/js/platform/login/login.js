$(document).ready(function() {
	setInterval("changecolor()", 300);
	//������汾��ʾ
	if('Chrome'!=exp_type||'28.0.1500.72'!=exp_version){//��Ϊ�˰汾����ʾ���汾��������
		var msg='<html><a href="'+imgUrl + '/js/exploere/Chrome28.0.1500.72.exe"><font class=fontStyle>���ذ�װ�ļ�</font></a>'+
		'</br>�ȶ�����������ȸ�28(Chrome28.0.1500.72),'+
			'ʹ����������������޷��������ʵ�ҳ��,���������ذ�װ�ļ�������,��װ�ð汾���������!;'+
			'</html>';
		$.messager.show({
            title: '��ܰС��ʿ',
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
 * ��ϵ��Ϣ��
 */
function openView(){
	$('#openView').show('close');
	var msg='<html>'+
	'Email:<a href="#"><font color="blue" size="2">exp@zjxpp.com</font></a> �̺�:<a href="#"><font color="blue" size="2">721151</font></a> '
	+'</br>�ֻ�:<a href="#"><font color="blue" size="2">18657270151</font></a> ����:<a href="#"><font color="blue" size="2">057128806887</font></a>'
    +'</br>QQȺ:<a href="#"><font color="blue" size="2">316257628</font></a></html>';
/*$.messager.show({
	id:'openView',
    title: '��ϵ��Ϣ��',
    msg: msg,
    timeout: 5000,
    showType: 'show',
    width:260,
    height:130
});*/
	$.messager.alert('��ϵ��Ϣ��', msg, 'info');
}