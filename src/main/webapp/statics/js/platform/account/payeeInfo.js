$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function submit() {
	var url;
	if(!($('#payee').validatebox('isValid') && $('#payAccount').validatebox('isValid') && $('#payBank').validatebox('isValid'))) {
		return;
	}
	if (operateFlag == 'modify') {
		url = "/account/accountAction!modifyPayeeInfo.jspa";
	} else {
		url = "/account/accountAction!addPayeeInfo.jspa"
	}
	var form = window.document.forms[0];
	form.action = appUrl + url;
	form.submit();
	
}

function close() {
	window.parent.closeMaintWindow();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.parent.closeMaintWindow();
			window.parent.search();
		});
	}
}