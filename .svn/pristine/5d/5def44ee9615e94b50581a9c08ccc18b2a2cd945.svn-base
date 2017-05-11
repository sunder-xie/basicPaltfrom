$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function importData() {
	var isValid = $('#upload').validatebox('isValid');
	if (!isValid) {
		return;
	}
	var epath = $('#upload').val();
	if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase() == "xlsx") {
		$.messager.alert('Tips', '03以上版本Excel导入暂不支持!', 'warning');
		return;
	}
	if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase() != "xls") {
		$.messager.alert('Tips', '导入文件类型必须为excel!', 'warning');
		return;
	}
	$.messager.progress();
	var form = window.document.forms[0];
	form.action = appUrl + '/data/dataManageAction!importData.jspa';
	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.parent.closeMaintWindow();
			window.parent.reLoad();
		});
	}
}