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
						url : appUrl + '/evtstatistics/evtstatisticsAction!searchOverTimeEvtList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						queryParams : {
							orgId : $("#orgId").val()
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'orgId',
									hidden : true
								},
								{
									field : 'orgName',
									title : '组织',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'numA',
									title : '协同办公',
									width : setColumnWidth(0.12),
									align : 'center',
									formatter : function(value,row,index) {
										if(value>0){
											return '<a href= javascript:toOverTimeEvtDtl('
												+ row.orgId
												+ ',1)>'+value+'</a>';
										}else{
											return value;
										}
									}
								},
								{
									field : 'numB',
									title : '客户关系管理',
									width : setColumnWidth(0.12),
									align : 'center',
									formatter : function(value,row,index) {
										if(value>0){
											return '<a href= javascript:toOverTimeEvtDtl('
												+ row.orgId
												+ ',2)>'+value+'</a>';
										}else{
											return value;
										}
									}
								},
								{
									field : 'numC',
									title : '营销费用管理',
									width : setColumnWidth(0.12),
									align : 'center',
									formatter : function(value,row,index) {
										if(value>0){
											return '<a href= javascript:toOverTimeEvtDtl('
												+ row.orgId
												+ ',3)>'+value+'</a>';
										}else{
											return value;
										}
									}
								},
								{
									field : 'numD',
									title : '人事档案管理',
									width : setColumnWidth(0.12),
									align : 'center',
									formatter : function(value,row,index) {
										if(value>0){
											return '<a href= javascript:toOverTimeEvtDtl('
												+ row.orgId
												+ ',4)>'+value+'</a>';
										}else{
											return value;
										}
									}
								},
								{
									field : 'numE',
									title : '经销商管理',
									width : setColumnWidth(0.12),
									align : 'center',
									formatter : function(value,row,index) {
										if(value>0){
											return '<a href= javascript:toOverTimeEvtDtl('
												+ row.orgId
												+ ',5)>'+value+'</a>';
										}else{
											return value;
										}
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
	queryParams.overDate = $("#overDate").val();
	queryParams.orgId = $("#orgId").val();
	$("#datagrid").datagrid('load'); 
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

/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintWindowForOrg('选择组织', '/orgAction!orgTreePage.jspa');
}

function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
}

function closeOrgTree() {
	$("#orgWindow").window('close');
}

// 关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','流程信息查看', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}

function toOverTimeEvtDtl(orgId,type) {
	initMaintEvent(true,'700','400','超时明细查看', "/evtstatistics/evtstatisticsAction!toSearchOverTimeEvtDtl.jspa?orgId="
			+ orgId + "&startDate=" + $("#startDate").val() + "&endDate=" + $("#endDate").val()
			+ "&overDate=" + $("#overDate").val() + "&modelType=" + type,0,0); 
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function clearValue(){
	$("#startDate").val('');
	$("#endDate").val('');
	$("#overDate").val('');
	$("#orgId").val($("#orgIdValue").val());
	$("#orgName").val($("#orgNameValue").val());
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

function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 460;
	var $win = $("#orgWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}