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
 * ����ļ���ʽ
 * @returns {Boolean}
 */
function checkFile() {
	var file = document.all.file0.value;
	if (file == null || file == "") {
		return true;
	}
	if (file.lastIndexOf(".") == -1) {
		$.messager.alert('Tips', "·������ȷ", 'warning');
		return false;
	}
	var allImgExt = ".jpg|.jpeg|.gif|.bmp|.png|.JPG|.JPEG|.GIF|.BMP|.PNG|";
	var extName = file.substring(file.lastIndexOf("."));

	// f.lastIndexOf(".")����file������Ǹ����㡱��λ�ã����֣���f.substring��n ���֣������ַ�����ʼ����n+1���ַ���
	if (allImgExt.indexOf(extName + "|") != -1) {
		errMsg = "���ļ����Ͳ������ϴ������ϴ� " + allImgExt + " ���͵��ļ�����ǰ�ļ�����Ϊ" + extName;
		$.messager.alert('Tips', errMsg, 'warning');
		return false;
	}
	return true;
	/* document.uploadForm.submit() ; */
}
/**
 * �ύ����
 */
function save() {
	if(document.getElementById('orgs').value==""){
		if(document.getElementById('orgs2').value==""){
			var cFlag;
			$("[name='cFlag']").each(function() {
				if (this.checked) {
				} else {
					$.messager.alert('Tips', "��ѡ�����̻�˾��֯", 'warning');
					return;
				}
			});
		}
	}
	var cFlag;
	$("[name='cFlag']").each(function() {
		if (this.checked) {
			document.getElementById('orgs2').value="51235";
			document.getElementById('orgName2').value="������";
		} else {
			
		}
	});
	var s = $("#totalParentTotal").combobox("getValue");
	$("#totalShow").val(s.split(",")[1]);
	$("#totalParentId").val(s.split(",")[0])
	if ($("#delailTitle").val() == "") {
		$.messager.alert('Tips', "���������", 'warning');
		return;
	}
	var t = s.split(",")[1];
	if (t == "Y") {
		if (checkFile()) {
		} else {
			$.messager.alert('Tips', "���ϴ�ͼƬ", 'warning');
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
		alert("��̨�����ʼ���Ҫһ��ʱ��,1���Ӻ�,�ر�ҳ�漴��");
	} else {
		$.messager.alert('Tips', "����д����", 'warning');
	}
}
/*
 * function gettype(){ var cc=$("#totalParentTotal").combobox("getValue");
 * $("#totalShow").val(cc.split(",")[1]);
 * $("#totalParentId").val(cc.split(",")[0]) alert(cc); $.ajax({//
 * ���ݿ�ȶ�����֤�����Ƿ��ظ� async : false, type : 'post', url : 'RyxxBean.jsp', data : {
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
 * ����֯�����°�Ŀɵ�ѡ����֯��
 * */
function newtree2(zzjg){
	document.getElementById('zzjg').value = zzjg;
	if(zzjg=="gszz"){
		initMaintWindow('ѡ����֯', '/newOrgAction!newOrgTree.jspa',300,400);
	}
	if(zzjg == 'jxszz'){
		initMaintWindow('ѡ������', '/newOrgAction!newOrgTreeForDealer.jspa',300,400);
	}
}
/**�����µĴ���
 * title,���ڵ����� 
 * url, ��ת��·��
 * width, ����
 * height,�߶�
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
 * ����ֵ����
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