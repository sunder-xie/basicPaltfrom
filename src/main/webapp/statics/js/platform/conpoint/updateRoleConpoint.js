$(document)
		.ready(
				function() {
					$('#hideFrame').bind('load', promgtMsg);

					$('#conpointName').combobox('setValue', con_name);
					$('#closeFlag').combobox('setValue', close_flag);

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

function save_role_con() {
	var p = $("#conpointName").combobox('isValid');
	var t = $("#closeFlag").combobox('isValid');
	if (!(p && t)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/conpoint/conpoint!updateRoleConpoint.jspa";
	form.target = "hideFrame";
	form.submit();
}

/**
 * πÿ±’“≥√Ê
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
