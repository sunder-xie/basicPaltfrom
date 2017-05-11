$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/roleAction!getPositionType4RoleJsonList.jspa?roleId='+roleId,
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height,
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
								},
								/*
								 * { field : 'stationName', title : '岗位名称',
								 * width : setColumnWidth(0.3), align : 'center' },
								 */
								{
									field : 'stationName',
									title : '岗位名称',
									width : setColumnWidth(0.3),
									align : 'left'
								},
								{
									field : 'orgName',
									title : '公司名称',
									width : setColumnWidth(0.3)
								}] ]
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
//	queryParams.roleId = encodeURIComponent($("#roleId").val());
	queryParams.stationId = encodeURIComponent($("#stationId").val());
	queryParams.stationName = encodeURIComponent($("#stationName")
			.val());
	// alert(encodeURIComponent($("#positionTypeName").val()));
	$("#datagrid").datagrid('load');
}
/**
 * 关闭权限点新建页面
 */
function cencel() {
	window.parent.closeMaintWindow();
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
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				cencel();
				window.parent.search();
			}
		});
		// $.messager.alert('Tips', successResult, 'info');

	}
}
