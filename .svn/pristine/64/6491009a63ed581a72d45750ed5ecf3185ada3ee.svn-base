$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg); 
	
	$.post(appUrl + '/file/fileAction!previewFile.jspa?fileId='+$("#fileId").val(), function(msg,status){
	console.info(msg);
		$("#htmltable").html(msg);	
	},"html");
});

function promgtMsg() {
	$.messager.progress('close');
	var hideFrameWfe = document.getElementById("hideFrameWfe");
	var failResult = hideFrameWfe.contentWindow.failResult;
	var successResult = hideFrameWfe.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			$('#nextUserDialog').dialog('close');
			window.parent.search();
			window.parent.closeMaintWindow();
			/*window.close();
			window.opener.location.reload();*/
			
		});
	}
}