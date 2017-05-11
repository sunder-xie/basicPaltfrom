$(document).ready(function() {
	$('#sign').combobox({
		
	});
	$('#sign').combobox({
		valueField : 'value',  
		textField : 'text',
		data : [{
			value : '=',
			text : '等于(=)'
		}, {
			value : '<>',
			text : '不等于(<>)'
		}, {
			value : '<',
			text : '小于(<)'
		}, {
			value : '>',
			text : '大于(>)'
		}, {
			value : 'in',
			text : '包含(in)'
		}],
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto',
		onLoadSuccess : function() {
			$('#sign').combobox("setValue", '=');
		}
	});
	
	// 下拉框赋值
	$('#target').combobox('setValue', targetValue);
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info',function(){
		close();
		window.parent.search_2()
});
		
	}
}

function submit() {
	var p = $("#memo").validatebox('isValid');
	var n = $("#value").validatebox('isValid');
	if (!(p && n)) {
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/boformAction!createRoleforBOdt.jspa";
	form.submit();
}

function close() {
	window.parent.closeMaintModelAtt();
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
