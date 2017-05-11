$(document).ready(function() {
		/*$('#orgId').combobox({
				url:appUrl + '/station!getCompanyJsonList.jspa',
				valueField:'orgId',
				textField:'orgName'
			});*/
			/*$('#orgId').combobox('setValue',$('#orgId').val());*/
	
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if(successResult){
		$.messager.alert('Tips', successResult, 'warning');
		close();
		window.parent.search();
	}
}
function submit() {
	var n = $("#stationId").validatebox('isValid');
	var p = $("#stationName").validatebox('isValid');
	var t = $("#orgId").combobox('isValid');
	var h = $("#oaStationId").combobox('isValid');
	/*var p = $("#stationId").numberbox('isValid');
	var n = $("#stationName").validatebox('isValid');
	var t = $("#target").combobox('isValid');
	var o = $("#orderBy").numberspinner('isValid');*/
	if (!(n && p && t && h)) {
		$.messager.alert('Tips', "请将信息填写完整", 'warning');
		return;
	}
	/*$.messager.alert('Tips', $("#orgId").combobox('getValue'), 'warning');*/
	var form = window.document.forms[0];
	form.action = appUrl + "/station!updateStation.jspa";
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