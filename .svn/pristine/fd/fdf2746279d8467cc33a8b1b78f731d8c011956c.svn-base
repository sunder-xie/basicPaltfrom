$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});

function loadGrid() {
	$('#roleIds').combogrid({
		panelWidth : 500,
		idField : 'roleId',
		textField : 'roleId',
		pagination : true,// 是否分页
		rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		fit : true,// 自动大小
		method : 'post',
		multiple : true,
		url : appUrl + '/roleAction!getSelectedRoleJsonListJosn.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'roleId',
			title : '角色id',
			width : 60
		}, {
			field : 'roleName',
			title : '角色名称',
			width : 100
		}, {
			field : 'descn',
			title : '角色描述',
			width : 120
		} ] ],
		toolbar : '#toolbar'
	});
	var q = $('#roleIds').combogrid("grid").datagrid();
	$(q).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	$('#datagridRole').datagrid({
		iconCls : 'icon-list',
		title : '查询结果',
		url : appUrl + '/roleAction!getSelectedRoleJsonList.jspa?stationId='+stationId,
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		pagination : true,
		nowrap : true,
		remoteSort : true,
		height : height,
		columns : [ [ {
			field : 'id',
			checkbox : true
		}/*
			 * ,{ field : 'id', checkbox : true }
			 */
		, {
			field : 'roleId',
			title : '角色id',
			width : setColumnWidth(0.2),
			align : 'center'
		}, {
			field : 'roleName',
			title : '角色名称',
			width : setColumnWidth(0.2),
			align : 'center'
		}, {
			field : 'descn',
			title : '角色描述',
			width : setColumnWidth(0.4),
			align : 'center'
		} ] ],
		toolbar : [ "-", {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				deleteRole();
			}
		}, "-" ]
	});

	// 分页控件
	var p = $('#datagridRole').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});

	/*
	 * $('#roleId1s').datagrid({ iconCls : 'icon-list', title : '查询结果', url :
	 * appUrl + '/roleAction!getSelectedRoleJsonList.jspa', loadMsg :
	 * '数据远程载入中,请等待...', singleSelect : false, pagination : true, nowrap : true,
	 * remoteSort : true, height : height, columns : [[ { field : 'ck',checkbox :
	 * true}, {field:'roleId',title:'角色id',width:60},
	 * {field:'roleName',title:'角色名称',width:100},
	 * {field:'descn',title:'角色描叙',width:120}]] });
	 */

}
function searcher(val) {
	/*
	 * var queryParams1 = $('#roleIds').combogrid('options').queryParams;
	 * queryParams1.roleId=val;
	 */
	/* $("#roleIds").combogrid("grid").datagrid("reload",{'roleId':val}); */
	/*
	 * var queryParams1 =
	 * $('#roleIds').combogrid("grid").datagrid('options').queryParams;
	 * queryParams1.roleId=val;
	 */
	val = encodeURIComponent(val)
	$('#roleIds')
			.combogrid(
					{
						url : appUrl
								+ '/roleAction!getSelectedRoleJsonListJosn.jspa?roleId='
								+ val
					});
	/* $('#roleIds').combogrid("grid").datagrid({queryParams: queryParams1}); */
	$('#roleIds').combogrid("grid").datagrid('reload');

}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning');
		$('#roleIds').combobox('setText', '');
		search();
	}
}

function close() {
	window.parent.closeMaintStation();
}

function search() {
	var queryParams = $('#datagridRole').datagrid('options').queryParams;
//	queryParams.stationId = $("#stationId").val();
	queryParams.roleId = encodeURIComponent($("#roleId").val());
	queryParams.roleName = encodeURIComponent($("#roleName").val());
	$("#datagridRole").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
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

function deleteRole() {
	$.messager.confirm('Confirm', '确认批量删除角色?', function(r) {
		if (r) {
			var rows = $('#datagridRole').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  no selected rows!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$("#sids").val(ids);
			var form = window.document.forms[0];
			form.action = "roleAction!deleteSelectedRole.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});

}

function selectRole() {
	if ($('#roleIds').combobox('getValue') == '') {
		warn('请选择角色！');
		return;
	}
	/* var params = []; */
	// document.getElementById("roleList").value = Ext.util.JSON.encode(params);
	var form = window.document.forms[0];
	form.action = "roleAction!selectRole.jspa";
	form.target = "hideFrame";
	form.submit();
}