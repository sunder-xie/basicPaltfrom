$(document).ready(function() {
	loadGrid();
});

function loadGrid() {

	$('#choosedatagrid').datagrid({
		iconCls : 'icon-list',
		title : '��ѯ���',
		url : appUrl + '/station!searchUser.jspa',
		loadMsg : '����Զ��������,��ȴ�...',
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
			title : '�û�ID',
			width : setColumnWidth(0.4),
			align : 'center'
		}, {
			field : 'userName',
			title : '�û�����',
			width : setColumnWidth(0.6),
			align : 'center'
		} ] ],
		toolbar : [ "-", {
			text : '����',
			iconCls : 'icon-save',
			handler : function() {
				save();
			}
		}, "-" ]
	});

	// ��ҳ�ؼ�
	var p = $('#choosedatagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
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
	 * alert("��ѡ��˾����"); return; }
	 */
	var searchKey = encodeURIComponent($("#searchKey").val());
	if (searchKey == '') {
		alert("�������û���");
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
	 * alert("��ѡ��˾����"); return; }
	 */
	var searchKey = encodeURIComponent($("#searchKey").val());
	if (searchKey == '') {
		alert("�������û���");
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
	 * '��ʾ', msg : '��ѡ��һ������Ŀ', buttons : Ext.Msg.OK, icon : Ext.Msg.INFO });
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
