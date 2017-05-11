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
						url : appUrl + '/roleAction!getRoleJsonList.jspa?ran='
								+ Math.random(),
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						idField : 'roleId',
						nowrap : true,
						remoteSort : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									id : 'roleId',
									field : 'roleId',
									title : '角色编号',
									width : setColumnWidth(0.2),
									align : 'center',
									sortable : true
								},
								{
									field : 'roleName',
									title : '角色名称',
									width : setColumnWidth(0.2),
									align : 'center'

								},
								{
									field : 'roleType',
									title : '角色类别',
									width : setColumnWidth(0.1),
									align : 'center'

								},
								{
									field : 'descn',
									title : '角色描述',
									width : setColumnWidth(0.2),
									align : 'center'

								},
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.2),
									align : 'center',
									formatter : function(value, row, index) {
										var id = row.roleId;
										return '<img style="cursor:pointer" onclick=updateRole("'
												+ id
												+ '") title="修改资料"  src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>'
												+ '  <img style="cursor:pointer" onclick=searchSelectedMenu4Role("'
												+ id
												+ '") title="查看菜单" src='
												+ imgUrl
												+ '/images/actions/action_roles.png align="absMiddle"></img>'
												+ '  <img style="cursor:pointer" onclick=searchRoleConpoint("'
												+ id
												+ '") title="添加控制点" src='
												+ imgUrl
												+ '/images/actions/action_add.png align="absMiddle"></img>'
												+ '  <img style="cursor:pointer" onclick=searchPositionType4Role("'
												+ id
												+ '") title="查看岗位" src='
												+ imgUrl
												+ '/images/actions/action_station.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createRole();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove ',
							handler : function() {
								deleteRole();
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

function renderStyle(value) {
	return '<a tooltip="' + value + '" class="easyui-tips">' + value + '</a>';
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.roleId = encodeURIComponent($("#roleId").val());
	queryParams.roleName = encodeURIComponent($("#roleName").val());
	$("#datagrid").datagrid('load');
}

function initMaintRole(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintRole")
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
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}
function closeMaintRole() {
	$("#maintRole").window('close');
}
function closeMaintRole4menu() {
	$("#maintRole4menu").window('close');
}

function createRole() {
	initMaintRole('角色创建', '/roleAction!createRolePrepare.jspa', 600, 350);
}
function searchSelectedMenu4Role(id) {
	var id = encodeURIComponent(id);
	initMaintRole('操作角色菜单', '/menuAction!searchSelectedMenu4Role.jspa?roleId='
			+ id, 850, 450);
}
function updateRole(id) {
	var id = encodeURIComponent(id);
	initMaintRole('角色配置', '/roleAction!updateRolePrepare.jspa?roleId=' + id,
			600, 350);
}
function searchRoleConpoint(id) {
	var id = encodeURIComponent(id);
	initMaintRole('角色权限点配置', '/conpoint!searchRoleConpoint.jspa?roleId=' + id,
			900, 400);
}
function searchPositionType4Role(id) {
	var id = encodeURIComponent(id);
	initMaintRole('岗位查询 ',
			'/roleAction!searchPositionType4Role.jspa?roleId=' + id,
			800, 400);
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}
function deleteRole() {
	$.messager.confirm('Confirm', '是否批量刪除角色?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  没有行被选中!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].roleId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "roleAction!deleteRole.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
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
