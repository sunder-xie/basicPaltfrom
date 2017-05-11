$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	
});

function createNewsTotal() {
	if ($("#totalName").val() == "") {
		$.messager.alert('Tips', "栏目名称必须输入!", 'warning');
		return;
	}
	if ($("#totalCode").val() == "") {
		$.messager.alert('Tips', "排序码必须输入!", 'warning');
		return;
	}
	var form = window.document.forms[0];
	form.action = "newsAction!createNewsTotal.jspa";
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
		$.messager.alert('Tips', successResult, 'info');
		close();
		window.parent.search();
	}
}
function close() {
	window.parent.closeCreateNewsTotal();
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
