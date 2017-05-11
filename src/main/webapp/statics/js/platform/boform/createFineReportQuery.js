$(function() {
	checkRight();
	initDate();
	settionPreMonth();
});

function initDate() {
	var myDate = new Date();
	myDate.setTime(myDate.getTime()-1000*60*60*24);
	var year = myDate.getFullYear() + '';
	var month = myDate.getMonth()<9?'0'+(myDate.getMonth()+1):myDate.getMonth()+1;
	month = month + '';
	var day = myDate.getDate()<10?'0'+myDate.getDate():myDate.getDate();
	day = day + '';
	$('#cont_4').val(year+month+day);
	$('#v_date').val(year+month+day);
}

//默认前一个月
function settionPreMonth() {
	var myDate = new Date();
	var year = myDate.getFullYear() + '';
	var month = myDate.getMonth()<9?'0'+(myDate.getMonth()+1):myDate.getMonth()+1;
	month = month + '';
	var day = myDate.getDate()<10?'0'+myDate.getDate():myDate.getDate();
	day = day + '';
	var month2 = month==1?12:month-1;
	month2 = month2<10?'0'+month2:month2;
	var year2 = month==1?year-1:year;
	$('#cont_8').val(year2);
	$('#year').val(year2);
	$('#cont_16').val(month2);
	$('#month').val(month2);
}

//创建窗口对象
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

//*********选择客户编号 begin********************
function newtree1() {
	initMaintWindow('报表参数查询','/boformAction!searchQueryParameter.jspa?pid='
			+ '682',650,550);
	//$('#countNum0').val(i);
}

function returnValue0(s){
	var vs = new Array();
	vs=s;
	var i=2;
	document.all("cont_" + i).value = s[1];
	document.all("coni_" + i).value = s[0];
}
//*********选择客户编号 end***************

//*********选择组织树 begin********************
function newtree2() {
	initMaintWindow('选择组织', '/newOrgAction!newOrgTree.jspa',300,400);
    //$('#countNum').val(i);
}

function returnValue(selectedId, selectedName) {
	//var i=$('#countNum').val();
	var i = 1;
	document.all("cont_" + i).value = selectedName;
	document.all("coni_" + i).value = selectedId;
}
//*********选择组织树 end***************

function ac(ci, i) {
	document.all("coni_" + i).value = ci;
}

function viewFineReport() {
	var fr_url = 'http://xppfntest.zjxpp.com:7176/FReport/ReportServer?op=view&reportlet='
		+ $('#frreport').html();
	var param = "";
	var right = parseInt($("#right").html());
	var nodes = $("table tr");
	for (var i=0;i<nodes.length;i++) {
		var node = nodes[i];
		if (node.id == "") {
			continue;
		} else if ((node.id & right) != 0) {
			var tdNode = document.all("coni_" + node.id);
			param = param + "&" + tdNode.id + "=" + tdNode.value;
			if (tdNode.id=="org_id" && tdNode.value=="") {
				$.messager.alert("提示","销售区域不能为空");
				return;
			} else if (tdNode.id=="kunnr" && tdNode.value=="") {
				$.messager.alert("提示","客户编号不能为空");
				return;
			}
		}
	}
	
	//后台生成对账单访问日志（对账单反馈）
	if($('#frreport').html().indexOf("EXP[5bf9][8d26][5355][603b]")>0){
		var a=$('#frreport').html().indexOf("EXP[5bf9][8d26][5355][603b]");
		$.ajax({
			type : "post",
			url : appUrl + "/fineReportAction!createReportLog.jspa?1=1"+param,
			success:function(){
				var usrcode = "&USRCODE=" + getCookie("PS");
				location.href=fr_url + param + usrcode;
			},
			error : function (XMLHttpRequest, textStatus, errorThrown) {
				var msg=errorThrown;
			}
		});
	}else{
		var usrcode = "&USRCODE=" + getCookie("PS");
		location.href=fr_url + param + usrcode;
	}
	
	
}

//读取cookie
function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
}

function closeOrgTree(){
	$("#maintWindow").window('close');
}

function checkRight() {
	var right = parseInt($("#right").html());
	var nodes = $("table tr");
	for (var i=0;i<nodes.length;i++) { 
		var node = nodes[i];
		if (node.id == "") {
			continue;
		} else if ((node.id & right) == 0) {
//			node.remove();
			node.parentNode.removeChild(node);
		}
	}
}