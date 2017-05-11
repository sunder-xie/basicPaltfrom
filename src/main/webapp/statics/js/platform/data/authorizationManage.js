$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#empId').combogrid({
		panelWidth : 450,
		panelHight : 500,
		idField : 'userId',
		textField : 'loginId',
		pagination : true,
		rownumbers : true,
		collapsible : false,
		striped : true,
		fit : true,
		method : 'post',
		editable : false,
		multiple : false,
		required : true,
		missingMessage : "请选择用户!",
		url : appUrl + '/data/dataManageAction!getAllUserJsonList.jspa',
		columns : [ [ {
			field : 'userId',
			title : 'ID',
			width : 100,
			align : 'center'
		}, {
			field : 'loginId',
			title : '用户名',
			width : 140,
			align : 'center'
		}, {
			field : 'userName',
			title : '姓名',
			width : 160,
			align : 'center'
		} ] ],
		onSelect : function(record) {
			var r = $('#empId').combogrid('grid').datagrid('getSelected');
			search(r.userId);
		},
		toolbar : '#empInfoToolbar'
	});

	$('#tableNames').combogrid({
		panelWidth : 450,
		panelHight : 500,
		width : 200,
		idField : 'tableName',
		textField : 'tableName',
		pagination : true,
		// rownumbers : true,
		collapsible : false,
		striped : true,
		fit : true,
		method : 'post',
		editable : false,
		multiple : true,
		required : true,
		missingMessage : "请选择表名!",
		url : appUrl + '/data/dataManageAction!getTableNameJsonList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'tableName',
			title : '表名',
			width : 400,
			align : 'center'
		} ] ],

		toolbar : '#tableNameToolbar'
	});

	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '授权信息',
						url : appUrl
								+ '/data/dataManageAction!getTableAuthorizationJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height * 0.96,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'id',
									title : 'ID',
									align : 'center',
									hidden : true
								},
								{
									field : 'empName',
									title : '姓名',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'tableName',
									title : '表名',
									width : setColumnWidth(0.25),
									align : 'center'
								},
								{
									field : 'authorizerName',
									title : '授权人',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'authorizeDate',
									title : '授权时间',
									width : setColumnWidth(0.3),
									align : 'center',
									formatter : function(v) {
										if (v != null && v != undefined) {
											return utcToDate(v.replace(
													/\/Date\((\d+)\+\d+\)\//gi,
													"new Date($1)"), '');
										}
									}
								} ] ],
						toolbar : [ "-", {
							text : '取消授权',
							iconCls : 'icon-remove',
							handler : function() {
								cancelAuthorization();
							}
						}, "-" ]
					});

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

function search(empId) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.empId = empId;
	$("#datagrid").datagrid('load');
}

function empInfoSearcher(val) {
	val = encodeURIComponent(val);
	$('#empId')
			.combogrid(
					{
						url : appUrl
								+ '/data/dataManageAction!getAllUserJsonList.jspa?searchStr='
								+ val
					});
	$('#empId').combogrid("grid").datagrid('reload');
}

function tableNameSearcher(val) {
	val = encodeURIComponent(val);
	$('#tableNames')
			.combogrid(
					{
						url : appUrl
								+ '/data/dataManageAction!getTableNameJsonList.jspa?searchStr='
								+ val
					});
	$('#tableNames').combogrid("grid").datagrid('reload');
}

function addAuthorization() {
	if (!($('#empId').combobox('isValid') && $('#tableNames').combogrid(
			'isValid'))) {
		$.messager.alert('Tips', '验证未通过,请仔细检查!', 'error');
		return;
	}
	$.messager.confirm('Confirm', '确认授权?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/data/dataManageAction!addAuthorization.jspa';
			form.target = 'hideFrame';
			form.submit();
		}
	});
}

function cancelAuthorization() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!', 'warning');
		return;
	}
	$.messager.confirm('Confirm', '确认取消授权?', function(r) {
		if (r) {
			$.messager.progress();
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/data/dataManageAction!cancelAuthorization.jspa?ids='
					+ ids;
			form.target = "hideFrame";
			form.submit();
		}
		;
	});
}

function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search($('#empId').combobox('getValue'));
		});
	}
}

function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}
