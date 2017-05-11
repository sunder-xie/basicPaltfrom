$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);

});
function loadGrid() {
	$('#stationName')
			.combobox(
					{
						textField : 'stationName',
						valueField : 'stationName',
						onChange : function(newValue, oldValue) {
							if (newValue != null) {
								var urlStr = appUrl
										+ "/staffAmountAction!blurSearchStaff.jspa?positionTypeName="
										+ encodeURIComponent(newValue);
								$("#stationName").combobox("reload", urlStr);

							}
						},
						onSelect : function(r) {
							$("#stationId").val(r.stationId);
						}
					});

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

function submit() {
	var positionId = $('#stationId').val();
	var org_id = $('#orgId').val();
	// var amount = $('#stationId').val();
	/* var orgId = $('#orgId').val(); */
	var p = $("#orgName").validatebox('isValid');
	var t = $("#stationId").numberbox('isValid');
	var o = $("#amount").numberspinner('isValid');
	var r = $("#rzamount").numberspinner('isValid');
	if (!(p && o && t && r)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/staffAmountAction!createStaff.jspa?positionId="
			+ positionId + "&org_id=" + org_id;
	form.target = "hideFrame";
	form.submit();
}

function close() {
	window.parent.closeMaintStaff();
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
	var url = appUrl + '/orgAction!toOrgTreeBySearch.jspa';
	var WWidth = 300;
	var WHeight = 300;
	var $win = $("#maintStaff")
			.window(
					{
						title : '×éÖ¯Ñ¡Ôñ',
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
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
}