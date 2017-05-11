$(document).ready(function() {
	loadGrid(); // Ȩ�޵��ѯ
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#con_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						height : 350,
						striped : true,
						url : appUrl + '/conpoint!searchConpoint.jspa?ran='
								+ Math.random(),

						loadMsg : '����Զ��������,��ȴ�...',
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
									title : 'Ȩ�޵�ID',
									field : 'conpointId',
									width : 120,
									align : 'center',
									sortable : true
								},
								{
									field : 'conpointName',
									title : 'Ȩ�޵�����',
									align : 'center',
									width : 250,
									sortable : true
								},
								{
									field : 'conpointNum',
									title : 'Ȩ�޵���',
									width : 150,
									align : 'center',
									sortable : true
								},
								{
									field : 'menuId',
									title : '�˵�ID',
									width : 120,
									align : 'center',
									sortable : true
								},
								{
									field : 'menuName',
									title : '�˵�����',
									align : 'center',
									width : 250,
									sortable : true
								},
								{
									field : 'oper',
									title : '����',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.conpointId;
										return '<img style="cursor:pointer" onclick="edit('
												+ id
												+ ')" title="�޸�����" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  '
												+ '&nbsp;&nbsp;<img style="cursor:pointer" onclick="search_role('
												+ id
												+ ')" title="�鿴��ɫ" src='
												+ imgUrl
												+ '/images/actions/action_roles.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : 'ɾ��',
							iconCls : 'icon-remove ',
							handler : function() {
								remove();
							}
						}, "-" ]
					});
	// ��ҳ�ؼ�
	var p = $('#con_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function search() {
	var queryParams = $('#con_list').datagrid('options').queryParams;
	queryParams.conpointId = $("#con_id").val();
	queryParams.conpointName = encodeURIComponent($("#con_name").val());
	queryParams.conpointNum = encodeURIComponent($("#con_num").val());
	$("#con_list").datagrid('load');
}

// �������ڶ���
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
 * �½�Ȩ�޵�
 */
function add() {
	initMaintWindow('Ȩ�޵㴴��', '/conpoint!forAddConpoing.jspa', 400, 220);
}
/**
 * �޸�Ȩ�޵�
 */
function edit(id) {

	initMaintWindow('Ȩ�޵��޸�', '/conpoint!forModifyConpoint.jspa?conpointId='
			+ id, 400, 220);

}

function remove() {
	$.messager.confirm('Confirm', '�Ƿ������h��Ȩ�޵�?', function(r) {
		if (r) {
			var rows = $('#con_list').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('��ʾ', '  δѡ���κ���Ϣ!');
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
 * �鿴��ɫ����
 */
function search_role(id) {
	initMaintWindow('��ɫ��ѯ',
			'/conpoint/conpoint!searchRole4Config.jspa?conpointId=' + id
					+ '&ran=' + Math.random(), 600, 460);
}
// �رմ���ҳ��
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