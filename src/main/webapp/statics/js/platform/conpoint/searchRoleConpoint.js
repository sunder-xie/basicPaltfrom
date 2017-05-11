$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#role_con_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '角色权限列表',
						height : 350,
						striped : true,
						url : appUrl
								+ '/conpoint/conpoint!getRoleConpointJsonList.jspa?roleId='
								+ roleId + '&ran=' + Math.random(),

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									title : '角色权限点ID',
									field : 'roleConpointId',
									width : 80,
									align : 'center',
									sortable : true,
									hidden : true
								},
								{
									title : '权限点ID',
									field : 'conpointId',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'conpointName',
									title : '权限点名称',
									align : 'center',
									width : 120,
									sortable : true
								},
								{
									field : 'conpointNum',
									title : '权限点编号',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'menuId',
									title : '菜单ID',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'menuName',
									title : '菜单名称',
									align : 'center',
									width : 180,
									sortable : true
								},
								{
									field : 'closeFlag',
									title : '权限点开关',
									width : 80,
									align : 'center'

								},
								{
									field : 'oper',
									title : '操作',
									width : 80,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.roleConpointId;
										return '<img style="cursor:pointer" onclick="edit('
												+ id
												+ ')" title="修改资料" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove ',
							handler : function() {
								remove();
							}
						}, "-" ]
					});
	// 分页控件
	var p = $('#role_con_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
// 创建窗口对象
function initMaintWindow(title, url) {
	var url = appUrl + url;
	var WWidth = 600;
	var WHeight = 350;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						draggable : true,
						closed : true,// /
						closable : true,//
						minimizable : false,//
						maximizable : false,//
						collapsible : false
					});

	$win.window('open');
}

/**
 * 新建权限点
 */
function add() {
	initMaintWindow('角色权限点创建',
			'/conpoint!createRoleConpointPrepare.jspa?roleId='
					+ $("#roleId").val());
}
/**
 * 修改权限点
 */
function edit(id) {

	initMaintWindow('角色权限点修改',
			'/conpoint!updateRoleConpointPrepare.jspa?roleConpointId=' + id);

}

function remove() {
	$.messager.confirm('Confirm', '是否批量刪除角色权限点?',
			function(r) {
				if (r) {
					var rows = $('#role_con_list').datagrid('getSelections');
					if (rows == '') {
						$.messager.alert('提示', '  未选中任何信息!');
						return;
					}
					var ids = [];
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].roleConpointId);
					}
					$("#ids").val(ids);
					var form = window.document.forms[0];
					form.action = appUrl
							+ '/conpoint/conpoint!deleteRoleConpoint.jspa';
					form.target = "hideFrame";
					form.submit();

				}
			});
}

// 关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
}
function search() {
	$('#role_con_list').datagrid('load');
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
