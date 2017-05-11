var eventIdGloab = "";
$(document).ready(function(){
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/evtstatistics/evtstatisticsAction!searchEventList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						queryParams : {
							modelId : $("#modelId").combobox('getValue')
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'eventId',
									title : '事务编号',
									width : setColumnWidth(0.05),
									align : 'center'
								},
								{
									field : 'eventTitle',
									title : '事务标题',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'initator',
									title : '提出者id',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
								},
								{
									field : 'initatorName',
									title : '提出者',
									width : setColumnWidth(0.10),
									align : 'center'
								},
								{
									field : 'modelName',
									title : '事务模板',
									width : setColumnWidth(0.12),
									align : 'center'

								},
								{
									field : 'eventState',
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
									field : 'createDate',
									title : '提出时间',
									width : setColumnWidth(0.14),
									align : 'center'

								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.25),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn = '';
										strReturn = '<a href= javascript:searchEventDetail("'
											+ row.eventId
											+ '")>查看审批意见 </a>|'
											+ '<a href=javascript:graphTrace("'
											+ row.eventId
											+ '") > 流程 </a>|';
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
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.startDate = $("#startDate").val();
	queryParams.endDate = $("#endDate").val();
	queryParams.eventId = $("#eventId").val();
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.initatorName = encodeURIComponent($("#initatorName").val());
	queryParams.curUserName = encodeURIComponent($("#curUserName").val());
	queryParams.eventState = $("#eventState").combobox('getValue');
	queryParams.modelId = $("#modelId").combobox('getValue');
	$("#datagrid").datagrid('load'); 
}

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/evtstatistics/evtstatisticsAction!exportOverTimeEvtDtl.jspa';
	form.target = "hideFrame";
	form.submit();
}

function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/evtstatistics/evtstatisticsAction!checkDownLoadOver.jspa?",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
					}
				}
			});
		}, 100);
	}, 500);
}

function promgtMsg() {
	$.messager.progress('close');
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

function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','流程信息查看', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function clearValue(){
	$("#startDate").val("");
	$("#endDate").val("");
	$("#eventId").val("");
	$("#eventTitle").val("");
	$("#initatorName").val("");
	$("#curUserName").val("");
	$("#eventState").combobox('select','');
	$("#modelId").combobox('select','semiautomatic,fix_hrEntrySale,fix_hrEntryFun,fix_hrOut,fix_hrTurnover,fix_hrPositiveStaff');
}

//创建窗口对象
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