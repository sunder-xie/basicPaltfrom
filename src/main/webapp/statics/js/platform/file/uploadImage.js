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
		$.messager.alert('Tips', "图片上传成功!", 'info', function(){
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
	'<a href="#"><img onclick="removeFile(this)" src="'+imgUrl+'/images/actions/action_del.png" border="0" alt="删除附件"/></a>'+
	'</div>';
	$("#_file").show();
	var _file = document.getElementById("_file");
		_file.insertAdjacentHTML("beforeend",str1);
}
function removeFile(id) {
	  var new_tr = id.parentNode.parentNode;
	  var _file = document.getElementById("_file");
　　      try {
　　          // 为了在ie和firefox下都能正常使用,就要用另一个方法代替,最取上一层的父结点,然后remove.
　　           _file.removeChild(new_tr);
		$("#_file").hide();
		$("#input").show();
		var objFile=document.getElementById("uploadImgFile"); 
		objFile.outerHTML=objFile.outerHTML.replace(/(value=\").+\"/i,"$1\"");
　　       } catch(e) {}
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
		$.messager.alert('Tips', "路径不正确", 'warning');
		return false;
	}
	var allImgExt = ".jpg|.jpeg|.gif|.bmp|.png|.JPG|.JPEG|.GIF|.BMP|.PNG|";
	var extName = file.substring(file.lastIndexOf("."));
	
	// f.lastIndexOf(".")返回file的最后那个“点”的位置（数字），f.substring（n 数字）返回字符串开始到第n+1个字符；
	if (allImgExt.indexOf(extName + "|") == -1) {
		errMsg = "该文件类型不允许上传,请上传 " + allImgExt + " 类型的文件,当前文件类型为" + extName;
		$.messager.alert('Tips', errMsg, 'warning');
		return false;
	}
	return true;
}
