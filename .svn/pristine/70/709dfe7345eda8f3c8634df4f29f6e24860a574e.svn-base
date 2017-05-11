$(document).ready(function() {
	$('#hideFrame2').bind('load', promgtMsg);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame2");
	var failResult = hideFrame.contentWindow.failResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} 
}


function searchAllUserInfo(userId) {
	var url = appUrl + "/alluser/allUserAction!searchAllUserInfo.jspa?userId="
			+ userId;
	var WWidth = 460;
	var WHeight = 500;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);

	window.open(url, "searchAllUserInfo", "left=" + WLeft
					+ ",top=" + WTop + ",width=" + WWidth + ",height="
					+ WHeight + ",toolbar=no,menubar=no,scrollbars=yes");
}

function shortcuts(userName, userId, orgId) {
	window.parent.shortcuts(userName, userId, orgId);
}

function iframeResize() {
//	try {
//		document.getElementById("iframe1").style.height = iframe1.document.body.scrollHeight;
//	} catch (e) {
//		document.getElementById("iframe1").style.height = "350px";
//	}
	var iframe = document.getElementById("iframe1");
 	try{
 		var bHeight = iframe.contentWindow.window.document.body.scrollHeight;
 		var dHeight = iframe.contentWindow.window.document.documentElement.scrollHeight;
 		iframe.height =  Math.max(bHeight, dHeight);
 	}catch (e){
 		iframe.height = "350px";
 	}
//	var ifm= document.getElementById("iframe1"); 
//	var subWeb = document.frames ? document.frames["iframe1"].document : ifm.contentDocument; 
//	if(ifm != null && subWeb != null) { 
//		ifm.height = subWeb.body.scrollHeight; 
//	} 
}

function downLoad(fileId){
	var form = window.document.forms[0];
	form.action = appUrl + "/file/fileAction!downLoadFile.jspa?fileId="+fileId;
	form.submit();
}

function shortcuts(userName, userId, orgId) {
	window.parent.shortcuts(userName, userId, orgId);
}
