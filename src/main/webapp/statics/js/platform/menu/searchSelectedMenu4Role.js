$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#menuIds').combogrid({
		panelWidth : 500,
		panelHight : 500,
		idField : 'id',
		textField : 'id',
		pagination : true,// �Ƿ��ҳ
		rownumbers : true,// ���
		collapsible : false,// �Ƿ���۵���
		fit : true,// �Զ���С
		method : 'post',
		multiple : true,
		url : appUrl + '/menuAction!getMenuJsonList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'id',
			title : '�˵�ID',
			width : 60
		}, {
			field : 'name',
			title : '�˵���',
			width : 100
		}, {
			field : 'pname',
			title : '�����˵���',
			width : 100
		}, {
			field : 'target',
			title : '�˵�Ŀ��',
			width : 100
		} ] ],
		toolbar : '#toolbar'
	});
	var q = $('#menuIds').combogrid();
	$(q).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
	
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl
								+ '/menuAction!getSelectedMenu4RoleJsonList.jspa?roleId='
								+ $("#roleId").val(),
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						remoteSort : true,
						height : height,
						columns : [ [ {
							field : 'ck',
							checkbox : true
						}, {
							field : 'id',
							title : '�˵����',
							width : setColumnWidth(0.15),
							align : 'center',
							sortable : true
						}, {
							field : 'name',
							title : '�˵�����',
							width : setColumnWidth(0.3)
						}, {
							field : 'roleId',
							title : '��ɫ����',
							width : setColumnWidth(0.3)
						}, {
							field : 'target',
							title : '�˵�Ŀ��',
							width : setColumnWidth(0.15),
							align : 'center'
						} ] ],
						toolbar : [
						// {
						// text : '����',
						// iconCls : 'icon-add',
						// handler : function() {
						// addMenu();
						// }
						// }, "-",
						{
							text : 'ɾ��',
							iconCls : 'icon-remove ',
							handler : function() {
								deleteMenu();
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

function initAddMenu(title, url) {
	var url = appUrl + url;
	var WWidth = 750;
	var WHeight = 450;
	var $win = $("#addMenu")
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

function searcher(val) {
	val = encodeURIComponent(val);
	$('#menuIds').combogrid({
		url : appUrl + '/menuAction!getMenuJsonListforRole.jspa?id=' + val
	});
	$('#menuIds').combogrid("grid").datagrid('reload');

}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.id = encodeURIComponent($("#id").val());
	queryParams.name = encodeURIComponent($("#name").val());
	$("#datagrid").datagrid('load');

}
function selectMenu4Role(ids) {
	// alert($('#menuIds').combobox('getValue'));
	var ids = encodeURIComponent($('#menuIds').combobox('getValue'));
	$.messager.confirm('Confirm', '�Ƿ���Ӳ˵�?', function(r) {
		if (r) {
			if ($('#menuIds').combobox('getValue') == "") {
				$.messager.alert('Tips', '  ѡ�в˵���������!');
				return;
			}
			var form = window.document.forms[0];
			form.action = "menuAction!selectMenu4Role.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function addMenu() {
	initAddMenu('�˵����', '/menu/menuAction!searchSelectedMenu4Add.jspa');
}
function closeMaintRole4add() {
	$("#addMenu").window('close');
}
function deleteMenu() {
	$.messager.confirm('Confirm', '�Ƿ������h����ɫ?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  û���б�ѡ��!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "menuAction!deleteSelectedMenu4Role.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
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
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}

function close() {
	window.parent.closeMaintRole4menu();
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