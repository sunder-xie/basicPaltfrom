$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	
	$('#modelName').combobox('setValue', modelName);
	$("#modelId").val(modelId);
	
	$('#modelName').combobox({
		textField : 'name',
		valueField : 'name',
		onChange : function(newValue, oldValue) {
			if (newValue != null) {
				var urlStr = appUrl + "/wfe/modelAttributeAction!blurSearchModel.jspa?modelName="
					+ encodeURIComponent(newValue);
				$("#modelName").combobox("reload", urlStr);
			}
		},
		onSelect : function(r) {
			$("#modelId").val(r.key);
		}
	});
	
	$('#planAttFlag').combobox({
		valueField : 'flagValue',
		textField : 'flagText',
		data : [
			{'flagValue' : 'Y', 'flagText' : '∆Ù”√'},
			{'flagValue' : 'N', 'flagText' : 'Ω˚”√'}
		],
		editable : false,
		onLoadSuccess : function() {
			if(planAttFlag != ""){
				$('#planAttFlag').combobox("setValue", planAttFlag);
			}else{
				$('#planAttFlag').combobox("setValue", 'Y');
			}
			
		}
	});

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
			window.parent.search_1();
		});
	}
}

function submit(){
	var n = $("#modelName").combobox('isValid');
	var r = $("#planTypeName").validatebox('isValid');
	if (!(n && r)) {
		return;
	}
	var form = window.document.forms[0];
	if(type == 'modify'){
		form.action = appUrl + "/wfe/modelAttributeAction!updateModelAtt.jspa";
	}else{
		form.action = appUrl + "/wfe/modelAttributeAction!createModelAtt.jspa";
	}
	form.submit();
}

function close() {
	window.parent.closeMaintModelAtt();
}