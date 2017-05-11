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
		$.messager.alert('Tips', successResult, 'info');
		close();
		window.parent.search();
	}
}

function submit() {
	if ($("#totalName").val() == "") {
		$.messager.alert('Tips', "栏目名称必须输入!", 'warning');
		return;
	}
	var form = window.document.forms[0];
	form.action = "newsAction!updateNewsTotal.jspa";
	form.target = "hideFrame";
	form.submit();
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
function close() {
	window.parent.closeCreateNewsTotal();
}