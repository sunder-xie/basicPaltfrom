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
		missingMessage : "��ѡ���û�!",
		url : appUrl + '/data/dataManageAction!getAllUserJsonList.jspa',
		columns : [ [ {
			field : 'userId',
			title : 'ID',
			width : 100,
			align : 'center'
		}, {
			field : 'loginId',
			title : '�û���',
			width : 140,
			align : 'center'
		}, {
			field : 'userName',
			title : '����',
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
		missingMessage : "��ѡ�����!",
		url : appUrl + '/data/dataManageAction!getTableNameJsonList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'tableName',
			title : '����',
			width : 400,
			align : 'center'
		} ] ],

		toolbar : '#tableNameToolbar'
	});

	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��Ȩ��Ϣ',
						url : appUrl
								+ '/data/dataManageAction!getTableAuthorizationJsonList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
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
									title : '����',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'tableName',
									title : '����',
									width : setColumnWidth(0.25),
									align : 'center'
								},
								{
									field : 'authorizerName',
									title : '��Ȩ��',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'authorizeDate',
									title : '��Ȩʱ��',
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
							text : 'ȡ����Ȩ',
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
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
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
		$.messager.alert('Tips', '��֤δͨ��,����ϸ���!', 'error');
		return;
	}
	$.messager.confirm('Confirm', 'ȷ����Ȩ?', function(r) {
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
		$.messager.alert('Tips', '��ѡ������!', 'warning');
		return;
	}
	$.messager.confirm('Confirm', 'ȷ��ȡ����Ȩ?', function(r) {
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
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}
