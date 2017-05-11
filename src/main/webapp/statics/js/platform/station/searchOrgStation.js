$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/station!searchStation.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						height : 360,
						width : 850,
						remoteSort : true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'stationId',
									title : '岗位编号',
									width : setColumnWidth(0.2),
									align : 'center'
								},{
									field : 'stationName',
									title : '岗位名称',
									width : setColumnWidth(0.25),
									align : 'center'
								},
								{
									field : 'orgName',
									title : '公司名称',
									width : setColumnWidth(0.25)
								},
								{
									field : 'oatypeName',
									title : '岗位类型',
									width : setColumnWidth(0.2)
								}] ],
						toolbar : [ "-", {
							text : '确认',
							iconCls : 'icon-add',
							handler : function() {
							   selectStation();
							}
						}]
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

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.stationId = encodeURIComponent($("#stationId").val());
	queryParams.stationName = encodeURIComponent($("#stationName").val());
	$("#datagrid").datagrid('reload');
}
function selectStation(){
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择岗位!');
		return;
	}
	window.parent.returnStation(rows[0].stationId, rows[0].stationName);
	window.parent.closeStation();
	
}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

// 创建窗口对象
function initMaintStation(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintStation")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : false,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');
}
function closeMaintStation() {
	// 关闭创建页面
	$("#maintStation").window('close');
}
function getByStation(name, id) {
	initMaintStation('查看人员',
			"/station!configStationUserPagePre.jspa?stationId=" + id
					+ "&&stationName=" + name, 800, 400);
}
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};