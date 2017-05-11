$(document).ready(function() {
	loadGrid();
});

function loadGrid() {

	$('#choosedatagrid').datagrid({
		iconCls : 'icon-list',
		title : '查询结果',
		url : appUrl + '/station!searchUser.jspa',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		pagination : true,
		nowrap : true,
		remoteSort : true,
		height : height,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'userId',
			title : '用户ID',
			width : setColumnWidth(0.4),
			align : 'center'
		}, {
			field : 'userName',
			title : '用户名称',
			width : setColumnWidth(0.6),
			align : 'center'
		} ] ],
		toolbar : [ "-", {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				save();
			}
		}, "-" ]
	});

	// 分页控件
	var p = $('#choosedatagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		close();
		window.parent.search();
	}
}

function close() {
	window.parent.closeChooseStation();
}

function search() {
	var queryParams = $('#choosedatagrid').datagrid('options').queryParams;
	/*
	 * var t = $("#kunnrSignBox").combobox('isValid'); if (!t) {
	 * alert("请选择公司类型"); return; }
	 */
	var searchKey = encodeURIComponent($("#searchKey").val());
	if (searchKey == '') {
		alert("请输入用户名");
	} else {
		/* queryParams.custType = $("#kunnrSignBox").combobox('getValue'); */
		queryParams.flag = 'byid';
		queryParams.searchKey = encodeURIComponent(searchKey);
		$("#choosedatagrid").datagrid('reload');
	}
}
function searchName() {
	var queryParams = $('#choosedatagrid').datagrid('options').queryParams;
	/*
	 * var t = $("#kunnrSignBox").combobox('isValid'); if (!t) {
	 * alert("请选择公司类型"); return; }
	 */
	var searchKey = encodeURIComponent($("#searchKey").val());
	if (searchKey == '') {
		alert("请输入用户名");
	} else {
		/* queryParams.custType = $("#kunnrSignBox").combobox('getValue'); */
		queryParams.flag = 'byname';
		queryParams.searchKey = encodeURIComponent(searchKey);
		$("#choosedatagrid").datagrid('reload');
	}
}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function save() {
	var rows = $('#choosedatagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '  no selected rows!');
		return;
	}
	var ids = '';
	var names = '';
	for ( var i = 0; i < rows.length; i++) {
		ids += rows[i].userId + ';';
		names += rows[i].userName + ';';
	}
	window.parent.document.getElementById("userIdReturn").value = ids;
	window.parent.document.getElementById("userNameReturn").value = names;
	window.parent.closeChooseStation();
	/*
	 * Ext.each(rows, function(v, i, a) { ids += v.get('userId') + ';'; names +=
	 * v.get('userName') + ';'; }); if (ids == '') { Ext.Msg.show({ title :
	 * '提示', msg : '请选择一行行项目', buttons : Ext.Msg.OK, icon : Ext.Msg.INFO });
	 * return; }
	 */

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
