$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.treegrid(
					{
						iconCls : 'icon-list',
						title : '菜单树列表',
						url : appUrl
								+ '/menuAjaxAction!getMenuTreeListByAjax.jspa?node=1&flag=Y',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						idField : 'id',
						treeField : 'text',
						nowrap : true,
						height : height * 1.2,
						striped : true,
						animate : true,
						rowStyler : function(row) {
							return 'color:green;';
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'id',
									title : '菜单编号',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable : true,
									hidden : true
								},
								{
									field : 'text',
									title : '菜单名称',
									width : setColumnWidth(0.2),
									formatter : function(value, row, index) {
										var child = row.redirectAttributes;
										return child ? value
												+ '<font color=red>(子系统)</font>'
												: value;
									}
								},
								{
									field : 'attributes',
									title : '菜单地址',
									width : setColumnWidth(0.26),
									formatter : function(value, row, index) {
										return '<a title=' + value + '>'
												+ value + '</a>';
									}
								},
								{
									field : 'redirectAttributes',
									title : '子系统跳转地址',
									width : setColumnWidth(0.26),
									formatter : function(value, row, index) {
										if (value)
											return '<a title=' + value + '>'
													+ value + '</a>';
									}
								},
								{
									field : 'target',
									title : '菜单目标',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'NA')
											return '菜单文件夹';
										else if (value == '_blank')
											return '打开新窗口';
										else if (value == 'mainRight')
											return '工作台显示';
									}
								},
								{
									field : 'orderBy',
									title : '菜单顺序',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(value, row, index) {
										return '<font color="blue" style="font-weight:bold;">'
												+ value + '</font>';
									}
								},
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, index) {
										var id = row.id;
										return '<img style="cursor:pointer" onclick="updateMenu('
												+ id
												+ ')" title="修改资料" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  '
												+ ' <img style="cursor:pointer" onclick="searchRole('
												+ id
												+ ')" " title="查看角色" src='
												+ imgUrl
												+ '/images/actions/action_roles.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createMenu();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								deleteMenu();
							}
						}, "-" ],
						onBeforeExpand : function(row) {
							$('#datagrid').treegrid('options').url = appUrl
									+ "/menuAjaxAction!getMenuTreeListByAjax.jspa?node="
									+ row.id + '&flag=Y';
						}
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			self.location.reload();
		});
	}
}
// 创建窗口对象
function initMaintMenu(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintMenu")
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

function createMenu() {
	initMaintMenu('菜单创建', '/menuAction!createMenuPrepare.jspa', 600, 400);
}

function updateMenu(id) {
	initMaintMenu('菜单修改', '/menuAction!updateMenuPrepare.jspa?id=' + id, 600,
			400);
}

function searchRole(id) {
	initMaintMenu('查看角色', '/roleAction!searchRole4Config.jspa?menuId=' + id,
			850, 400);
}

function deleteMenu() {
	$.messager.confirm('Confirm', '是否批量刪除菜单?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  没有行被选中!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl + "/menuAction!deleteMenu.jspa";
			form.target = "hideFrame";
			form.submit();

		}
	});
}

// 关闭创建页面
function closeMaintMenu() {
	$("#maintMenu").window('close');
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