$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function submit() {
	var p = $("#loginId").val();
	if (!(p)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction1!sendTenderMail.jspa";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		document.getElementById("warn_meg_f").style.display = "block";
	} else if (successResult) {
		document.getElementById("warn_meg_s").style.display = "block";
	}
}
