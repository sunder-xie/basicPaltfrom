$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function save_con() {
	var p = $("#c_con_name").validatebox('isValid');
	if (!(p)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/conpoint/conpoint!modifyConpoint.jspa";
	form.target = "hideFrame";
	form.submit();
}

/**
 * �ر�ҳ��
 */
function cencel() {
	window.parent.closeMaintWindow();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				cencel();
				window.parent.search();
			}
		});
	}
}
