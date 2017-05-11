$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});
function save() {
	var p = $("#password").val();
	var t = $("#suer_pass").val();
	if (!(p && t)) {
		return;
	}
	if (p != t) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction1/allUserAction1!updatePass.jspa";
	form.target = "hideFrame";
	form.submit();
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
				self.location.href = appUrl;
			}
		});

	}
}
$.extend($.fn.validatebox.defaults.rules, {
	/* 必须和某个字段相等 */
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '密码不一致！'
	}
});
