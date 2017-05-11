$(document).ready(function() {
	loadGrid();
});

function loadGrid() {
	$('#datagrid').datagrid(
					{	
						iconCls : 'icon-list',
						title : '人员列表',
						striped : true,
						url : appUrl + '/wfe/eventAction!getLinkManJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : false,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'linkManId',
									title : '人员ID',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								{
									field : 'linkManName',
									title : '人员姓名',
									width : setColumnWidth(0.4),
									align : 'center'
								},
								{
									field : 'orgId',
									title : '部门ID',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								{
									field : 'orgName',
									title : '部门',
									width : setColumnWidth(0.4),
									align : 'center'
								}
								 ] ],
						toolbar : [ "-", {
							text : '确定',
							iconCls : 'icon-save',
							handler : function() {
								selectLinkMan();
							}
						}, "-" ]
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

/**
 * 选择确定联系人
 */
function selectLinkMan(){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows == ''){
		$.messager.alert('Tips', '请选择数据!', 'warning');
		return;
	}
	var x = new Array();
	x[0] = rows[0].linkManId;
	x[1] = rows[0].linkManName;
	x[2] = rows[0].orgId;
	x[3] = rows[0].orgName;
	window.parent.saveUser(x);
}

