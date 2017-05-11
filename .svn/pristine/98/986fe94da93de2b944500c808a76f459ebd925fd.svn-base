$(document).ready(function() {
	// ÏÂÀ­¿ò¸³Öµ
	$('#target').combobox('setValue', targetValue);
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info',function(){
		close();
		window.parent.search();
});
		
	}
}

function submit() {
	var p = $("#roleId").validatebox('isValid');
	var n = $("#roleName").validatebox('isValid');
	var t = $("#ownFlag").combobox('isValid');
	var o = $("#descn").validatebox('isValid');
	var q = $("#roleType").combobox('isValid');
	if (!(p && n && o && t && q)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/roleAction!createRole.jspa";
	form.submit();
}

function close() {
	window.parent.closeMaintRole();
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
