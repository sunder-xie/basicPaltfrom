$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/wfe/eventAction!getCaJsonList.jspa?ran ='+ Math.random(),
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'eventId',
									title : '事务编号',
									width : setColumnWidth(0.10),
									align : 'center'
								},
								{
									field : 'eventTitle',
									title : '事务标题',
									width : setColumnWidth(0.2),
									align : 'center'

								},
								{
									field : 'modelName',
									title : '事务模板',
									width : setColumnWidth(0.12),
									align : 'center'

								},
								{
									field : 'status',
									title : '事务状态',
									width : setColumnWidth(0.10),
									align : 'center',
									formatter : function(value) {
										if (value == 0) {
											return "未处理";
										}
										if (value == 1) {
											return "处理中";
										}
										if (value == 2) {
											return "已完成";
										}
										if (value == 3) {
											return "已作废";
										}
										if (value == 4) {
											return "已取消";
										}
									}
								},
								{
									field : 'curUserName',
									title : '当前处理人',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'creatdate',
									title : '提出时间',
									width : setColumnWidth(0.14),
									align : 'center'/*,
									formatter : function(value) {
										return utcToDate(value);
									}*/
								},
								{
									field : 'isRead',
									title : '是否审阅',
									width : setColumnWidth(0.10),
									align : 'center',
									formatter : function(value) {
										if(value==1){
											return "已读";
										}else{
											return "未读";
										}
									}
								},
								{
								field : 'operation',
								title : '操作',
								width : setColumnWidth(0.19),
								align : 'center',
								formatter : function(value, row, index) {
									var strReturn = '';
									if(row.isRead=='1'){
										strReturn = '<a href= javascript:setIsread("'
											+row.eventId
											+'","'
											+row.isRead
											+'")>设置提醒<a/>&nbsp;&nbsp;<a href= javascript:searchEventDetail("'
											+ row.eventId
											+ '")>查看审批意见 </a>';
									}else{
										strReturn = '<a href= javascript:setIsread("'
											+row.eventId
											+'","'
											+row.isRead
											+'")>不再提醒<a/>&nbsp;&nbsp;<a href= javascript:searchEventDetail("'
											+ row.eventId
											+ '")>查看审批意见 </a>';
									}
										
									return strReturn;
									}
								} 
								] ]
								
					});

	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	search();
}

function setIsread(eventid,isRead){
	if(isRead=='1'){
		isRead="";
	}else{
		isRead="1";
	}
	$.ajax({
		type : "post",
		url : appUrl + "/wfe/eventAction!setIsRead.jspa?eventId="+eventid+"&isRead="+isRead,		
		success:function(data){
			$.messager.alert('提示','设置成功!','info');
			
		}	,
			error: function (){
			  	$.messager.alert('Tips', "设置失败", 'error');
		  	}
			});
	$('#datagrid').datagrid('reload');
}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.isRead = $("#isRead").val();
	$("#datagrid").datagrid('load');
}

function closeMaintWindow(){
	$("#maintWindow").window('close');
}

function initMaintEvent(ffit,widte,height,title, url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ urls + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');
}

function searchEventDetail(eventId) {
	initMaintEvent(true, '700','400', '流程信息查看', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId ,0 , 0);
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
}

function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] ;
	return date;
}
