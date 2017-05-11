$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/account/accountAction!getPayeeInfoJsonList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
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
									title : '�տλ',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'payAccount',
									title : '�տ��˺�',
									width : setColumnWidth(0.12),
									align : 'center'
									
								},
								{
									field : 'payArea',
									title : '�տ����',
									width : setColumnWidth(0.1),
									align : 'center'
									
								},
								{
									field : 'payBank',
									title : '�տ�����',
									width : setColumnWidth(0.12),
									align : 'center'
									
								},
								{
									field : 'payAreaCode',
									title : '�տ��˵�������',
									width : setColumnWidth(0.08),
									align : 'center'
									
								},
								{
									field : 'payBankAlias',
									title : '�տ��������',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'payBankAliCode',
									title : '�տ��б����',
									width : setColumnWidth(0.08),
									align : 'center'
									
								},
								{
									field : 'payBankCode',
									title : '�տ����д���',
									width : setColumnWidth(0.08),
									align : 'center'
									
								},
								{
									field : 'email',
									title : '��������',
									width : setColumnWidth(0.1),
									align : 'center'
									
								},
								{
									field : 'operation',
									title : '����',
									width : setColumnWidth(0.07),
									align : 'center',
									formatter : function(value, row, rec) {
										return '<img style="cursor:pointer" onclick="update('
												+ row.id
												+ ')" title="�޸�" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
									}
								}] ],
								toolbar : [ "-", {
									text : '����',
									iconCls : 'icon-add',
									handler : function() {
										add();
									}
								}, "-", {
									text : 'ɾ��',
									iconCls : 'icon-remove',
									handler : function() {
										remove();
									}
								}, "-" ]
					});

	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
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
	initWindow('�����տ���', '/account/accountAction!toAddPayeeInfo.jspa', 'maintWindow', 500, 400);
}

function update(id) {
	initWindow('�޸��տ�����Ϣ', '/account/accountAction!toModifyPayeeInfo.jspa?id=' + id + '&operateFlag=modify', 'maintWindow', 500, 400);
}

function remove() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '��ѡ������!');
		return;
	}
	$.messager.confirm('Confirm', 'ȷ������ɾ���տ���?', function(r) {
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

//�������ڶ���
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
