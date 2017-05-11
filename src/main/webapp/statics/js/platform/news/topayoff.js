$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);

});
function loadGrid() {

}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
		$.messager.progress('close');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		$.messager.progress('close');
		window.parent.reloadDatagrid();
		// close();
	}
}

// function searcher(val) {
// val = encodeURIComponent(val);
// $('#eventId').combogrid({
// url : appUrl + '/subjectSearch!searchAccountTransction.jspa?Id=' + val
// });
// $('#eventId').combogrid("grid").datagrid('reload');
// }

function downStocksExcel() {
	var form = window.document.forms[0];
	form.action = appUrl + '/newsAction!PayOffDownload.jspa';
	form.target = "hideFrame";
	form.submit();
}

function submit() {
	var epath = $('#fileContent').val();
	if (epath == "") {
		$.messager.alert('Tips', '导入文件不能为空!', 'warning');
		return;
	}
	if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase() != 'csv') {
		$.messager.alert('Tips', '导入文件必须为csv格式!', 'warning');
		return;
	}
	$.messager.progress();
	var form = window.document.forms[0];
	form.action = appUrl + "/newsAction!SendPayOff.jspa";
	form.target = "hideFrame";
	form.submit();
}

function close() {
	window.parent.closeMaintEvent();
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
