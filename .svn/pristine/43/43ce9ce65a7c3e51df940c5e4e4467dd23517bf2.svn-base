$(document).ready(function() {

	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		alert(successResult);
		close();
		window.parent.search();
	}
}

function submit() {
	var p = $("#stationId").val();
	var n = $("#stationName").val();
	var t = $("#orgId").combobox('isValid');

	/*
	 * var p = $("#stationId").numberbox('isValid'); var n =
	 * $("#stationName").validatebox('isValid'); var t =
	 * $("#target").combobox('isValid'); var o =
	 * $("#orderBy").numberspinner('isValid');
	 */

	if (!t) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/station!createStation.jspa";
	form.submit();
}

function close() {
	window.parent.closeMaintStation();
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