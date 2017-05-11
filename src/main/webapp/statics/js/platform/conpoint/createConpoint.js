$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});
function loadGrid() {

	$('#menuName').combobox(
			{
				textField : 'pname',
				valueField : 'pname',
				onChange : function(newValue, oldValue) {
					if (newValue != null) {
						var urlStr = appUrl
								+ "/menuAction!blurSearchMenu.jspa?pname="
								+ encodeURIComponent(newValue);
						$("#menuName").combobox("reload", urlStr);
					}
				},
				onSelect : function(r) {
					$("#menuId").val(r.pid);
				}
			});
}
function save_con() {
	var p = $("#c_con_name").validatebox('isValid');
	var t = $("#menuName").combobox('isValid');
	if (!(p && t)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/conpoint/conpoint!addConpoint.jspa";
	form.target = "hideFrame";
	form.submit();
}

/**
 * 关闭权限点新建页面
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
		// $.messager.alert('Tips', successResult, 'info');

	}
}