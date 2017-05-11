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
						url : appUrl + '/wfe/eventAction!getCcJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'cc_id',
									width : setColumnWidth(0.10),
									align : 'center',
									hidden : true
								},
								{
									field : 'event_id',
									title : '事务编号',
									align : 'center',
									width : setColumnWidth(0.10)
								},
								{
									field : 'event_title',
									title : '事务标题',
									width : setColumnWidth(0.5),
									align : 'center'

								},
								{
									field : 'creator_name',
									title : '提出者',
									width : setColumnWidth(0.12),
									align : 'center'
								},
								{
									field : 'create_date',
									title : '开始时间',
									width : setColumnWidth(0.14),
									align : 'center',
									formatter : function(value) {
										return utcToDate(value);
									}

								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn = '';
										strReturn = '<a href= javascript:searchEventDetail("'
													+ row.cc_id + '","'
													+ row.event_id
													+ '")>处理 </a>';
										return strReturn;
									}
								} ] ]
								
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.eventId = $("#eventId").val();
	queryParams.creator_name = encodeURIComponent($("#creator_name").val());
	queryParams.ccFlag = $("#ccFlag").val();
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
function searchEventDetail(cc_id,eventId) {
	initMaintEvent(true,'700','400','流程信息查看', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId+"&cc_id="+cc_id,0,0); 
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
