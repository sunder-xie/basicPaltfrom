$(document).ready(function() {
	loadGrid(); // Ȩ�޵��ѯ
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/roleAction!getPositionType4RoleJsonList.jspa?roleId='+roleId,
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'stationId',
									title : '��λ���',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								/*
								 * { field : 'stationName', title : '��λ����',
								 * width : setColumnWidth(0.3), align : 'center' },
								 */
								{
									field : 'stationName',
									title : '��λ����',
									width : setColumnWidth(0.3),
									align : 'left'
								},
								{
									field : 'orgName',
									title : '��˾����',
									width : setColumnWidth(0.3)
								}] ]
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
//	queryParams.roleId = encodeURIComponent($("#roleId").val());
	queryParams.stationId = encodeURIComponent($("#stationId").val());
	queryParams.stationName = encodeURIComponent($("#stationName")
			.val());
	// alert(encodeURIComponent($("#positionTypeName").val()));
	$("#datagrid").datagrid('load');
}
/**
 * �ر�Ȩ�޵��½�ҳ��
 */
function cencel() {
	window.parent.closeMaintWindow();
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
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				cencel();
				window.parent.search();
			}
		});
		// $.messager.alert('Tips', successResult, 'info');

	}
}
