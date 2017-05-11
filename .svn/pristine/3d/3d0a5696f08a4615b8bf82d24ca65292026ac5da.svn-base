$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	$('#menuIds').numberbox('disable');
	// if($("#pageIds").val() ==("")){
	// $("#pageIds").val("1");
	// }
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '查询结果',
		url : appUrl + '/menuAction!getMenuJsonList.jspa?ran=' + Math.random(),
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		pagination : true,
		nowrap : false,
		remoteSort : true,
		height : height,
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			clicked : function() {
				addMenu();
			}
		}, {
			field : 'id',
			title : '菜单编号',
			width : setColumnWidth(0.15),
			align : 'center',
			sortable : true
		}, {
			field : 'name',
			title : '菜单名称',
			width : setColumnWidth(0.3)
		}, {
			field : 'roleId',
			title : '角色名称',
			width : setColumnWidth(0.3)
		}, {
			field : 'target',
			title : '菜单目标',
			width : setColumnWidth(0.15),
			align : 'center'
		} ] ],
		toolbar : [ {
			text : '选定',
			iconCls : 'icon-add',
			handler : function() {
				addMenu();
			}
		}, "-", {
			text : '回删',
			iconCls : 'icon-remove ',
			handler : function() {
				deleteMenu();
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

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.id = encodeURIComponent($("#id").val());
	queryParams.name = encodeURIComponent($("#name").val());
	// queryParams.roleId = encodeURIComponent($("#roleId").val());
	$("#datagrid").datagrid('load');

}

function selectMenu4Role(ids) {
	var ids = encodeURIComponent($("#menuIds").val());
	$.messager.confirm('Confirm', '是否添加菜单?', function(r) {
		if (r) {
			if ($('#menuIds').val() == "") {
				$.messager.alert('Tips', '  选中菜单栏无数据!');
				return;
			}
			var form = window.document.forms[0];
			form.action = "menuAction!selectMenu4Role.jspa?menuIds=" + ids;
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function deleteMenu() {
	var rows = $('#datagrid').datagrid('getSelections');
	if ($("#menuIds").val() != "") {
		$("#menuIds").val("");
	}
}

function addMenu() {
	var rows = $('#datagrid').datagrid('getSelections');
	var p = $('#datagrid').datagrid('options');
	var ids1 = [];
	var ids2 = [];
	var pds = [];
	if ($("#pageIds").val() == "") {
		for ( var i = 0; i < rows.length; i++) {
			ids1.push(rows[i].id);
		}
		pds.push(p.pageNumber);
		$("#menuIds").val(ids1);
		$("#pageIds").val(pds);
	} else if (p.pageNumber == $("#pageIds").val() && $("#menuIds").val() == "") {
		for ( var i = 0; i < rows.length; i++) {
			ids1.push(rows[i].id);
		}
		$("#menuIds").val(ids1);
		// if (rows == '') {
		// $("#menuIds").val("");
		// }
	} else if (p.pageNumber == $("#pageIds").val() && $("#menuIds").val() != "") {
		for ( var i = 0; i < rows.length; i++) {
			var w2 = $("#menuIds").val().split(",");
			var w3 = 0;
			for ( var j = 0; j < $("#menuIds").val().split(",").length; j++) {
				if (rows[i].id == w2[j]) {
					w3 = 1;
				}
			}
			if (w3 != 1) {
				ids1.push(rows[i].id)
			}
		}
		if (ids1.valueOf() != "" && $("#menuIds").val() != "") {
			w1 = $("#menuIds").val() + "," + ids1
			$("#menuIds").val(w1);
		} else if (ids1.valueOf() != "" && $("#menuIds").val() == "") {
			$("#menuIds").val(ids1);
		} else if (ids1.valueOf() == "") {
			w1 = $("#menuIds").val()
			$("#menuIds").val(w1);
		}
		// if (rows == '') {
		// $("#menuIds").val("");
		// }
	} else if (p.pageNumber != $("#pageIds").val() && $("#menuIds").val() != "") {
		for ( var i = 0; i < rows.length; i++) {
			var w2 = $("#menuIds").val().split(",");
			var w3 = 0;
			for ( var j = 0; j < $("#menuIds").val().split(",").length; j++) {
				if (rows[i].id == w2[j]) {
					w3 = 1;
				}
			}
			if (w3 != 1) {
				ids1.push(rows[i].id)
			}
		}
		if (ids1.valueOf() != "" && $("#menuIds").val() != "") {
			w1 = $("#menuIds").val() + "," + ids1
			$("#menuIds").val(w1);
		} else if (ids1.valueOf() != "" && $("#menuIds").val() == "") {
			$("#menuIds").val(ids1);
		} else if (ids1.valueOf() == "") {
			w1 = $("#menuIds").val()
			$("#menuIds").val(w1)
		}
		// if (rows == '') {
		// $("#menuIds").val("");
		// }
	} else if (p.pageNumber != $("#pageIds").val() && $("#menuIds").val() == "") {
		for ( var i = 0; i < rows.length; i++) {
			ids2.push(rows[i].id);
		}
		pds.push(p.pageNumber);
		var w1 = ids2;
		$("#menuIds").val(w1);
		// if (rows == '') {
		// $("#menuIds").val("");
		// }
	}
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function renderStyle(value) {
	return '<a tooltip="' + value + '" class="easyui-tips">' + value + '</a>';
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		close();
		window.parent.search();
	}
}

function close() {
	window.parent.closeMaintRole4add();
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