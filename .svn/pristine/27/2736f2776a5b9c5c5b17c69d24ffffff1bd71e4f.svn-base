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
						url : appUrl + '/account/accountAction!getPayeeInfoJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
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
									field : 'payee',
									title : '收款单位',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'payAccount',
									title : '收款账号',
									width : setColumnWidth(0.12),
									align : 'center'
									
								},
								{
									field : 'payArea',
									title : '收款地区',
									width : setColumnWidth(0.1),
									align : 'center'
									
								},
								{
									field : 'payBank',
									title : '收款银行',
									width : setColumnWidth(0.12),
									align : 'center'
									
								},
								{
									field : 'payAreaCode',
									title : '收款人地区代码',
									width : setColumnWidth(0.08),
									align : 'center'
									
								},
								{
									field : 'payBankAlias',
									title : '收款别行名称',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'payBankAliCode',
									title : '收款行别代码',
									width : setColumnWidth(0.08),
									align : 'center'
									
								},
								{
									field : 'payBankCode',
									title : '收款银行代码',
									width : setColumnWidth(0.08),
									align : 'center'
									
								},
								{
									field : 'email',
									title : '电子邮箱',
									width : setColumnWidth(0.1),
									align : 'center'
									
								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.07),
									align : 'center',
									formatter : function(value, row, rec) {
										return '<img style="cursor:pointer" onclick="update('
												+ row.id
												+ ')" title="修改" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
									}
								}] ],
								toolbar : [ "-", {
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										add();
									}
								}, "-", {
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										remove();
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
	queryParams.payee = encodeURIComponent($("#payee").val());
	queryParams.payAccount = $("#payAccount").val();
	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function add() {
	initWindow('新增收款人', '/account/accountAction!toAddPayeeInfo.jspa', 'maintWindow', 500, 400);
}

function update(id) {
	initWindow('修改收款人信息', '/account/accountAction!toModifyPayeeInfo.jspa?id=' + id + '&operateFlag=modify', 'maintWindow', 500, 400);
}

function remove() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!');
		return;
	}
	$.messager.confirm('Confirm', '确认批量删除收款人?', function(r) {
		if (r) {
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			var form = window.document.forms[0];
			form.action = appUrl+"/account/accountAction!removePayeeInfo.jspa?ids=" + ids;
			form.submit();
		}
	});
}

//创建窗口对象
function initWindow(title, url, id, WWidth, WHeight) {
	
	var url = appUrl + url;
	var $win = $("#"+id)
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
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
}

function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}
