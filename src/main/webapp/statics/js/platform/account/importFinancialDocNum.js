$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	}else {
		$.messager.alert('Tips', successResult, 'info', function(){
			window.parent.closeMainFrame();
			window.parent.search();
		});
	}
}

function importData(){
		var epath = $('#uploadFile').val()
		
		if(epath==""){
			$.messager.alert('Tips', '导入文件不能为空!', 'warning');
			return;
		}
		
		if(epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()=="xlsx"){
			$.messager.alert('Tips', '03以上版本Excel导入暂不支持!', 'warning');
			return;
		}
		if (epath.substring(epath.lastIndexOf(".") + 1).toLowerCase()!="xls") {
			$.messager.alert('Tips', '导入文件类型必须为excel!', 'warning');
			return;
		}
		$.messager.progress();
		
		var form = window.document.forms[0];
		form.action = appUrl + "/account/accountAction!importData.jspa";
		form.submit();	
}