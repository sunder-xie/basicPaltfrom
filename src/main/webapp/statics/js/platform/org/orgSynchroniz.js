$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var successResult = hideFrame.contentWindow.successResult;
	var failResult = hideFrame.contentWindow.failResult;
	if (successResult) {
		$('#p').progressbar('setValue', 0);
		$.messager
				.alert(
						'Tips',
						successResult.split("#")[1],
						'info',
						function() {
							if (successResult.split("#")[0] == 'org') {
								$.messager
										.confirm(
												'Confirm',
												'是否继续同步用户信息...',
												function(r) {
													if (r) {
														progress();
														var form = window.document.forms[0];
														form.action = appUrl
																+ "/allUserAction!userSynchroniz.jspa";
														form.target = "hideFrame";
														form.submit();
													}
												});
							}
						});
	} else {
		$.messager.alert('Tips', failResult, 'warning');
	}
}

function sync() {
	progress();
	var form = window.document.forms[0];
	form.action = appUrl + "/orgAction!orgSynchroniz.jspa";
	form.target = "hideFrame";
	form.submit();
}

function progress() {
	var value = $('#p').progressbar('getValue');
	if (value < 500) {
		value += Math.floor(Math.random() * 10);
		$('#p').progressbar('setValue', value);
		setTimeout(arguments.callee, 40);
	}
}