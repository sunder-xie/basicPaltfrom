$(document)
		.ready(
				function() {
					$('#hideFrame').bind('load', promgtMsg);

					$('#conpointName')
							.combobox(
									{
										textField : 'conpointName',
										valueField : 'conpointName',
										onChange : function(newValue, oldValue) {
											if (newValue != null) {
												var urlStr = appUrl
														+ "/conpoint!searchConpointForJson.jspa?conpointName="
														+ encodeURIComponent(newValue);
												$("#conpointName").combobox(
														"reload", urlStr);
											}
										},
										onSelect : function(r) {
											$("#menuId").val(r.menuId);
											$("#conpointId").val(r.conpointId);
											$("#menuName").val(r.menuName);
											$("#conpointNum")
													.val(r.conpointNum);
										}
									});
				});

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

function save_role_con() {
	var p = $("#conpointName").combobox('isValid');
	var t = $("#closeFlag").combobox('isValid');
	if (!(p && t)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/conpoint!createRoleConpoint.jspa";
	form.submit();
}

function cencel() {
	window.parent.closeMaintWindow();
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