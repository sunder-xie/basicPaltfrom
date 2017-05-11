$(document).ready(function() {
	load_role_data(); // Ȩ�޵��ѯ
//	$('#hideFrame').bind('load', promgtMsg);
});

function load_role_data() {
	$('#role_list').datagrid({
		iconCls : 'icon-list',
		title : '��ɫ�б�',
		height : 350,
		striped : true,
		url : appUrl + '/role/roleAction!getRole4ConfigJsonList.jspa'
		+ '?conpointId=' + conpointId+'&ran=' + Math.random(),

		loadMsg : '����Զ��������,��ȴ�...',
		singleSelect : true,
		nowrap : true,
		pagination : true,
		rownumbers : true,
		// frozenColumns : [ [ ] ],
		columns : [ [

		{
			id : 'roleId',
			title : '��ɫID',
			field : 'roleId',
			width : 120,
			align : 'center',
			sortable : true
		}, {
			field : 'roleName',
			title : '��ɫ����',
			align : 'center',
			width : 250,
			sortable : true
		}, {
			field : 'descn',
			title : '��ɫ����',
			width : 150,
			align : 'center',
			sortable : true
		} ] ]
	});
	
	// ��ҳ�ؼ�
	var p = $('#role_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function s_role_search() {
	var queryParams = $('#role_list').datagrid('options').queryParams;
	queryParams.roleId = $("#s_role_id").val();
	queryParams.roleName = encodeURIComponent($("#s_role_name").val());
	$("#role_list").datagrid('load');
}