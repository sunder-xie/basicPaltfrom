$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	$('#updatePwd').dialog({
		title : "您的密码依然为初始密码，请重新设置",
		modal : true,
		closable : false,
		buttons : [ {
			text : '确定',
			iconCls : 'icon-ok',
			handler : function() {
				updatePassWord();
			}
		} ]
	});
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			$('#updatePwd').dialog('close');
			$('#password').val($('#repassWd').val());
			var form = window.document.forms[0];
			form.action = appUrl + "/loginAction!login.jspa";
			form.target = "_self";
			form.submit();
		});
	}
}

function updatePassWord() {
	var passport = $('#passport').val();
	var repassWd = $('#repassWd').val();
	var passWd = $("#passWd").val();
	if (!passWd || !repassWd) {
		$.messager.alert('Tips', '请正确输入密码!', 'warning');
		return;
	} else if (passWd.length < 6) {
		$.messager.alert('Tips', '密码输入长度至少6位', 'warning');
		return;
	} else if (passWd != repassWd) {
		$.messager.alert('Tips', '两次密码输入不匹配!', 'warning');
		return;
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + "/allUserAction!updateKunnrInitPwd.jspa?loginId="
				+ passport + "&repassWd=" + repassWd;
		form.submit();
	}
}

$.extend($.fn.validatebox.defaults.rules, {
	/* 必须和某个字段相等 */
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '两次密码输入不匹配'
	}
});

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		updatePassWord();
	}
	return true;
};
