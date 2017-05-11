$(document).ready(function() {
	load_role_data(); // 权限点查询
//	$('#hideFrame').bind('load', promgtMsg);
});

function load_role_data() {
	$('#role_list').datagrid({
		iconCls : 'icon-list',
		title : '角色列表',
		height : 350,
		striped : true,
		url : appUrl + '/role/roleAction!getRole4ConfigJsonList.jspa'
		+ '?conpointId=' + conpointId+'&ran=' + Math.random(),

		loadMsg : '数据远程载入中,请等待...',
		singleSelect : true,
		nowrap : true,
		pagination : true,
		rownumbers : true,
		// frozenColumns : [ [ ] ],
		columns : [ [

		{
			id : 'roleId',
			title : '角色ID',
			field : 'roleId',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			field : 'roleName',
			title : '角色名称',
			align : 'center',
			width : 250,
			sortable : true
		}, {
			field : 'descn',
			title : '角色描述',
			width : 150,
			align : 'center',
			sortable : true
		} ] ]
	});
	
	// 分页控件
	var p = $('#role_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function s_role_search() {
	var queryParams = $('#role_list').datagrid('options').queryParams;
	queryParams.roleId = $("#s_role_id").val();
	queryParams.roleName = encodeURIComponent($("#s_role_name").val());
	$("#role_list").datagrid('load');
}