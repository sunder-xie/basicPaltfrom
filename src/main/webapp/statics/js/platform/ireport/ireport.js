$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg); 
	    $.messager.progress();
		openTime();
	$.post(appUrl + '/IreportAction!exportHtml.jspa?parameters='+$("#parameters").val()+'&&bid='+$("#bid").val(), function(msg,status){
		$("#htmltable").html(msg);	
	},"html");
});
function firstpage(){
    $.messager.progress();
	openTime();
	$.post(appUrl + '/IreportAction!exportHtml.jspa?parameters='+$("#parameters").val()+'&&bid='+$("#bid").val()+'&&pageIndex=1', function(msg,status){
	    $("#htmltable").html(msg);	
	},"html");
}
function previouspage(){
    $.messager.progress();
	openTime();
	$.post(appUrl + '/IreportAction!exportHtml.jspa?parameters='+$("#parameters").val()+'&&bid='+$("#bid").val()+'&&pageIndex='+(parseInt($("#pageIndex").val(), 10)-1), function(msg,status){	    
	    $("#htmltable").html(msg);	
	},"html");
}
function nextpage(){
    $.messager.progress();
	openTime();
	$.post(appUrl + '/IreportAction!exportHtml.jspa?parameters='+$("#parameters").val()+'&&bid='+$("#bid").val()+'&&pageIndex='+(parseInt($("#pageIndex").val(), 10)+1), function(msg,status){	    
	    $("#htmltable").html(msg);	
	},"html");
}
function lastpage(){
    $.messager.progress();
	openTime();
	$.post(appUrl + '/IreportAction!exportHtml.jspa?parameters='+$("#parameters").val()+'&&bid='+$("#bid").val()+'&&pageIndex='+$("#lastPage").val(), function(msg,status){  
	    $("#htmltable").html(msg);	
	},"html");
}
function downloadPdf(){
    $.messager.progress();
    openTime();
	var form = window.document.forms[0];
	form.action = appUrl+"/IreportAction!exportPdf.jspa?parameters="+$("#parameters").val()+"&&bid="+$("#bid").val();
	form.target = "hideFrame";
	form.submit();
	

	
}
function downloadExcel(){
    $.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl+"/IreportAction!exportExcel.jspa?parameters="+$("#parameters").val()+"&&bid="+$("#bid").val();
	form.target = "hideFrame";
	form.submit();
	
}
function downloadWord(){
    $.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl+"/IreportAction!exportWord.jspa?parameters="+$("#parameters").val()+"&&bid="+$("#bid").val();
	form.target = "hideFrame";
	form.submit();

}
function printReport(){
    $.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl+"/IreportAction!printReport.jspa?parameters="+$("#parameters").val()+"&&bid="+$("#bid").val();
	form.target = "hideFrame";
	form.submit();

   
}
function openTime(){

	setTimeout(function(){
		var timer = setInterval(function(){
			$.ajax({
			type : "post",
			url : appUrl + "/IreportAction!checkdownloadover.jspa",
			success : function(data) {
				if(data == 'YES'){
					clearInterval(timer);
					$.messager.progress('close');
				}
			}
		});
		}, 200);
	}, 200);
}


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame3");
	var failResult = hideFrame.contentWindow.failResult;	
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', "图片上传成功!", 'info', function(){
			window.returnValue = successResult;
			window.close();
		});
	}
}