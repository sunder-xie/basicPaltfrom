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
						url : appUrl + '/wfe/eventAction!getTripApplyJsonList.jspa',
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
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'userName',
									title : '提出者',
									width : setColumnWidth(0.12),
									align : 'center',
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
									field : 'orgName',
									title : '所属部门',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'costCenterName',
									title : '所属项目',
									width : setColumnWidth(0.14),
									align : 'center'

								},
								{
									field : 'place',
									title : '出差地点',
									width : setColumnWidth(0.14),
									align : 'center'

								},
								{
									field : 'tripWayName',
									title : '出行方式',
									width : setColumnWidth(0.14),
									align : 'center'

								},
								{
									field : 'beginDate',
									title : '开始时间',
									width : setColumnWidth(0.14),
									align : 'center',
									formatter : function(value) {
										return utcToDate(value);
									}

								},
								{
									field : 'endDate',
									title : '结束时间',
									width : setColumnWidth(0.14),
									align : 'center',
									formatter : function(value) {
										return utcToDate(value);
									}

								},
								{
									field : 'reason',
									title : '事由',
									width : setColumnWidth(0.14),
									align : 'center'

								}] ],
								toolbar : [ "-", {
									text : '导出EXCEL',
									iconCls : 'icon-ok',
									handler : function() {
										download();
									}
						}, "-" ]
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
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.userName = encodeURIComponent($("#userName").val());
	$("#datagrid").datagrid('load');
}

// 创建窗口对象
function initWindow(title, url, id, WWidth, WHeight) {

	var url = appUrl + url;
	var $win = $("#" + id)
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
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
function download(){
	var form = window.document.forms[0];
	form.action = appUrl + "/wfe/eventAction!downloadExecel.jspa";
	form.submit();
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
