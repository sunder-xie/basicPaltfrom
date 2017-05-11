$(document).ready(function() {
	inad();
});

function inad() {
	if ($("#inAd").attr("checked")) {
		document.getElementById("ad").style.display = "";
	} else {
		document.getElementById("ad").style.display = "none";
	}
}

function connect() {
	var v1 = $("#jdbcUrl").validatebox('isValid');
	var v2 = $("#jdbcUsername").validatebox('isValid');
	var v3 = $("#jdbcPassword").validatebox('isValid');
	var v4 = $("#jdbcSid").validatebox('isValid');
	var l1 = $("#ldapHost").validatebox('isValid');
	var l2 = $("#ldapUser").validatebox('isValid');
	var l3 = $("#ldapPassword").validatebox('isValid');
	var l4 = $("#ldapDomain").validatebox('isValid');
	if (!(v1 && v2 && v3 && v4)) {
		return;
	}
	if (!$("#inAd").attr("checked")) {
		$("#ldapHost").val('');
		$("#ldapUser").val('');
		$("#ldapPassword").val('');
		$("#ldapDomain").val('');
	} else {
		if (!(l1 && l2 && l3 && l4)) {
			return;
		}
	}
	document.getElementById("connect").style.display = "none";
	document.getElementById("pdiv").style.display = "";
	var value = $('#p').progressbar('getValue');
	var form = window.document.forms[0];
	form.action = appUrl + "/loginAction!initConnect.jspa";
	form.submit();
	if (value < 500) {
		value += Math.floor(Math.random() * 10);
		$('#p').progressbar('setValue', value);
		setTimeout(arguments.callee, 40);
	}
}