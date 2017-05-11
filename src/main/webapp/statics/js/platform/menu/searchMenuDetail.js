$(document)
		.ready(
				function() {
					// ÏÂÀ­¿ò¸³Öµ
					$('#pname').combobox('setValue', pname);
					$('#target').combobox('setValue', targetValue);
					$('#hideFrame').bind('load', promgtMsg);
					$('#pname')
							.combobox(
									{
										textField : 'pname',
										valueField : 'pname',
										onChange : function(newValue, oldValue) {
											if (newValue != null) {
												var urlStr = appUrl
														+ "/menuAction!blurSearchMenu.jspa?pname="
														+ encodeURIComponent(newValue);
												$("#pname").combobox("reload",
														urlStr);
											}
										},
										onSelect : function(r) {
											$("#pid").val(r.pid);
										}
									});
				});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (successResult) {
		close();
		window.parent.location.reload();
	} else {
		$.messager.alert('Tips', failResult, 'warning');

	}
}

function submit() {

	var p = $("#pname").combobox('isValid');
	var n = $("#name").validatebox('isValid');
	var t = $("#target").combobox('isValid');
	var o = $("#orderBy").numberspinner('isValid');
	if (!(p && n && o && t)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/menuAction!updateMenu.jspa";
	form.submit();
}

function close() {
	window.parent.closeMaintMenu();
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