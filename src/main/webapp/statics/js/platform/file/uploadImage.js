$(document).ready(function() {
	$('#hideFrame3').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame3");
	var failResult = hideFrame.contentWindow.failResult;	
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', "ͼƬ�ϴ��ɹ�!", 'info', function(){
			window.returnValue = successResult;
			window.close();
		});
	}
}

function addFile()
{
	var filePath = document.getElementById("uploadImgFile");
	$("#input").hide();
	var paths = filePath.value.split("\\");
    var name = paths[paths.length-1];
	var str1 =
	'<div style="background-color:#E7EBF7;width:100%">'+
	'<font size="2">'+name+'</font>'+
	'<a href="#"><img onclick="removeFile(this)" src="'+imgUrl+'/images/actions/action_del.png" border="0" alt="ɾ������"/></a>'+
	'</div>';
	$("#_file").show();
	var _file = document.getElementById("_file");
		_file.insertAdjacentHTML("beforeend",str1);
}
function removeFile(id) {
	  var new_tr = id.parentNode.parentNode;
	  var _file = document.getElementById("_file");
����      try {
����          // Ϊ����ie��firefox�¶�������ʹ��,��Ҫ����һ����������,��ȡ��һ��ĸ����,Ȼ��remove.
����           _file.removeChild(new_tr);
		$("#_file").hide();
		$("#input").show();
		var objFile=document.getElementById("uploadImgFile"); 
		objFile.outerHTML=objFile.outerHTML.replace(/(value=\").+\"/i,"$1\"");
����       } catch(e) {}
}
function uploadImage(){
	var form = window.document.forms[0];
	form.action = appUrl + "/file/fileAction!uploadImage.jspa";
	if(checkFile()){
		form.submit();
	}
}

function checkFile() {
	var file = $("#uploadImgFile").val();
	if (file == null || file == "") {
		return false;
	}
	if (file.lastIndexOf(".") == -1) {
		$.messager.alert('Tips', "·������ȷ", 'warning');
		return false;
	}
	var allImgExt = ".jpg|.jpeg|.gif|.bmp|.png|.JPG|.JPEG|.GIF|.BMP|.PNG|";
	var extName = file.substring(file.lastIndexOf("."));
	
	// f.lastIndexOf(".")����file������Ǹ����㡱��λ�ã����֣���f.substring��n ���֣������ַ�����ʼ����n+1���ַ���
	if (allImgExt.indexOf(extName + "|") == -1) {
		errMsg = "���ļ����Ͳ������ϴ�,���ϴ� " + allImgExt + " ���͵��ļ�,��ǰ�ļ�����Ϊ" + extName;
		$.messager.alert('Tips', errMsg, 'warning');
		return false;
	}
	return true;
}
