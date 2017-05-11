$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);

	$('#planAttDataType').combobox({
		url : appUrl + '/wfe/modelAttributeAction!searchMprDatatype.jspa',
		valueField : 'itemValue',
		textField : 'itemName',
		onLoadSuccess : function() {
			if(type == 'modify'){
				$('#planAttDataType').combobox('setValue', planAttDataType);
			}	
		},
		editable : false,
		required : true
	});
	
	if(planAttIsNull=='Y'){
		$("#radio_Y").attr('checked', true);
	}else{
		$("#radio_N").attr('checked', true);
	}
	
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			close();
			window.parent.search_2();
		});
	}
}

function submit(){
	var n = $("#planAttDataType").combobox('isValid');
	var r = $("#planAttContent").validatebox('isValid');
	if(!(n && r)){
		return;
	}	
	var form = window.document.forms[0];
	if(type == 'modify'){
		form.action = appUrl + '/wfe/modelAttributeAction!updateAttDetail.jspa';
	}else{
		form.action = appUrl + '/wfe/modelAttributeAction!createAttDetail.jspa';
	}
	form.submit();
}

function close() {
	window.parent.closeMaintModelAtt();
}