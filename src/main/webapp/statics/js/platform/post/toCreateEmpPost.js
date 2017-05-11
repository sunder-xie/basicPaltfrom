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
	} else if (successResult) {
		$.messager.confirm("提示","职务创建成功，确认要关闭当前页面么？", function (data) {
	if (data){
		window.parent.closeMaintWindow();
		window.parent.search();
	}
	else {
		return;
	}
	});
	}
	
}

function submit() {
	var empPostName = $('#empPostName').val();
	var empOrgId = $('#empOrgId').val();
	var empOrgName = encodeURIComponent($('#empOrgName').val());
	var p = $("#empOrgName").validatebox('isValid');
	var f = $("#empPostName").validatebox('isValid');
	if (!(p && f)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/postAction!createEmpPost.jspa?empPostName="
			+ empPostName + "&empOrgId=" + empOrgId+"&empOrgName=" + empOrgName;
	form.target = "hideFrame";
	form.submit();
}

function close() {
	window.parent.closeMaintWindow();
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
function choseOrg() {
	var url = appUrl + '/orgAction!orgTreePage.jspa';
	var WWidth = 300;
	var WHeight = 300;
	var $win = $("#maintStaff")
			.window(
					{
						title : '组织选择',
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : false
					});
	$win.window('open');
}
function closeOrgTree() {
	$("#maintStaff").window('close');
}
function returnValue(selectedId, selectedName) {
	$("#empOrgId").val(selectedId);
	$("#empOrgName").val(selectedName);
}