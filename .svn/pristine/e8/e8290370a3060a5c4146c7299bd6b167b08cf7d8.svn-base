$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#con_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : 350,
						striped : true,
						url : appUrl + '/conpoint!searchConpoint.jspa?ran='
								+ Math.random(),

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
									id : 'conpointId',
									title : '权限点ID',
									field : 'conpointId',
									width : 120,
									align : 'center',
									sortable : true
								},
								{
									field : 'conpointName',
									title : '权限点名称',
									align : 'center',
									width : 250,
									sortable : true
								},
								{
									field : 'conpointNum',
									title : '权限点编号',
									width : 150,
									align : 'center',
									sortable : true
								},
								{
									field : 'menuId',
									title : '菜单ID',
									width : 120,
									align : 'center',
									sortable : true
								},
								{
									field : 'menuName',
									title : '菜单名称',
									align : 'center',
									width : 250,
									sortable : true
								},
								{
									field : 'oper',
									title : '操作',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.conpointId;
										return '<img style="cursor:pointer" onclick="edit('
												+ id
												+ ')" title="修改资料" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  '
												+ '&nbsp;&nbsp;<img style="cursor:pointer" onclick="search_role('
												+ id
												+ ')" title="查看角色" src='
												+ imgUrl
												+ '/images/actions/action_roles.png align="absMiddle"></img>';
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
	var p = $('#con_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function search() {
	var queryParams = $('#con_list').datagrid('options').queryParams;
	queryParams.conpointId = $("#con_id").val();
	queryParams.conpointName = encodeURIComponent($("#con_name").val());
	queryParams.conpointNum = encodeURIComponent($("#con_num").val());
	$("#con_list").datagrid('load');
}

// 创建窗口对象
function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}

/**
 * 新建权限点
 */
function add() {
	initMaintWindow('权限点创建', '/conpoint!forAddConpoing.jspa', 400, 220);
}
/**
 * 修改权限点
 */
function edit(id) {

	initMaintWindow('权限点修改', '/conpoint!forModifyConpoint.jspa?conpointId='
			+ id, 400, 220);

}

function remove() {
	$.messager.confirm('Confirm', '是否批量刪除权限点?', function(r) {
		if (r) {
			var rows = $('#con_list').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('提示', '  未选中任何信息!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].conpointId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl + '/conpoint/conpoint!deleteConpoint.jspa';
			form.target = "hideFrame";
			form.submit();

		}
	});
}

/**
 * 查看角色配置
 */
function search_role(id) {
	initMaintWindow('角色查询',
			'/conpoint/conpoint!searchRole4Config.jspa?conpointId=' + id
					+ '&ran=' + Math.random(), 600, 460);
}
// 关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
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

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};