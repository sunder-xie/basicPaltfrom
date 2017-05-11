$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	addUploadButton(editor);
});
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning', function() {
			close();
			window.parent.search();
		});
	}
}
function close() {
	window.parent.closeNewsDetail();
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
/**
 * 检查文件格式
 * @returns {Boolean}
 */
function checkFile() {
	var file = document.all.file0.value;
	if (file == null || file == "") {
		return true;
	}
	if (file.lastIndexOf(".") == -1) {
		$.messager.alert('Tips', "路径不正确", 'warning');
		return false;
	}
	var allImgExt = ".jpg|.jpeg|.gif|.bmp|.png|.JPG|.JPEG|.GIF|.BMP|.PNG|";
	var extName = file.substring(file.lastIndexOf("."));

	// f.lastIndexOf(".")返回file的最后那个“点”的位置（数字），f.substring（n 数字）返回字符串开始到第n+1个字符；
	if (allImgExt.indexOf(extName + "|") != -1) {
		errMsg = "该文件类型不允许上传。请上传 " + allImgExt + " 类型的文件，当前文件类型为" + extName;
		$.messager.alert('Tips', errMsg, 'warning');
		return false;
	}
	return true;
	/* document.uploadForm.submit() ; */
}
/**
 * 提交保存
 */
function save() {
	if(document.getElementById('orgs').value==""){
		if(document.getElementById('orgs2').value==""){
			var cFlag;
			$("[name='cFlag']").each(function() {
				if (this.checked) {
				} else {
					$.messager.alert('Tips', "请选择经销商或公司组织", 'warning');
					return;
				}
			});
		}
	}
	var cFlag;
	$("[name='cFlag']").each(function() {
		if (this.checked) {
			document.getElementById('orgs2').value="51235";
			document.getElementById('orgName2').value="经销商";
		} else {
			
		}
	});
	var s = $("#totalParentTotal").combobox("getValue");
	$("#totalShow").val(s.split(",")[1]);
	$("#totalParentId").val(s.split(",")[0])
	if ($("#delailTitle").val() == "") {
		$.messager.alert('Tips', "请输入标题", 'warning');
		return;
	}
	var t = s.split(",")[1];
	if (t == "Y") {
		if (checkFile()) {
		} else {
			$.messager.alert('Tips', "请上传图片", 'warning');
			return;
		}
	}
	var data = CKEDITOR.instances.delail_content.getData();
	if (data != "") {
		$("#delail_content").val(data);
		var form = window.document.forms[0];
		form.action = "newsAction!createNewsdet.jspa";
		form.target = "hideFrame";
		form.submit();
		alert("后台发送邮件需要一段时间,1分钟后,关闭页面即可");
	} else {
		$.messager.alert('Tips', "请填写内容", 'warning');
	}
}
/*
 * function gettype(){ var cc=$("#totalParentTotal").combobox("getValue");
 * $("#totalShow").val(cc.split(",")[1]);
 * $("#totalParentId").val(cc.split(",")[0]) alert(cc); $.ajax({//
 * 数据库比对身份证号码是否重复 async : false, type : 'post', url : 'RyxxBean.jsp', data : {
 * 'service' : 'isUserCardRepeat', 'param' : param.value }, success :
 * function(date) { if (date === 'true') { return true; } } }); }
 */
function isimg(src) {
	var ext = [ '.gif', '.jpg', '.jpeg', '.png' ];
	var s = src.toLowerCase();
	var r = false;
	for (var i = 0; i < ext.length; i++) {
		if (s.indexOf(ext[i]) > 0) {
			alert(ext[i]);
			r = true;
			break;
		}
	}
	return r;
}
/**
 * 打开组织树：新版的可点选的组织树
 * */
function newtree2(zzjg){
	document.getElementById('zzjg').value = zzjg;
	if(zzjg=="gszz"){
		initMaintWindow('选择组织', '/newOrgAction!newOrgTree.jspa',300,400);
	}
	if(zzjg == 'jxszz'){
		initMaintWindow('选择经销商', '/newOrgAction!newOrgTreeForDealer.jspa',300,400);
	}
}
/**创建新的窗口
 * title,窗口的主题 
 * url, 跳转的路径
 * width, 宽度
 * height,高度
 * */
function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						draggable : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');
}
/**
 * 返回值处理
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	var flag1 = 'orgs';
	var flag2 = 'orgName';
	var val = document.getElementById('zzjg').value;
	if (document.getElementById(flag1).value != null
			&& document.getElementById(flag1).value != undefined
			&& document.getElementById(flag1).value != '' && val == 'gszz') {
		flag1 = 'orgId1';
		flag2 = 'orgName1';

	}
	if (val == 'jxszz') {
		flag1 = 'orgs2';
		flag2 = 'orgName2';
	}
	document.getElementById(flag1).value = selectedId;
	document.getElementById(flag2).value = selectedName;
	$("#maintWindow").window('close');
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}
function clear(flag1, flag2) {
	document.getElementById(flag1).value = "";
	document.getElementById(flag2).value = "";
}