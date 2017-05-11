$(document).ready(function() {
			loadGrid();
			$('#hideFrame').bind('load', promgtMsg);
		});

function loadGrid() {
	$('#datagrid1').datagrid({
				iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/station!searchStationUserMore.jspa?userId='+$('#userId').val(),
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : 270,
				columns : [[{
							field : 'ck',
							align : 'center',
							checkbox : true
						},{
							field : 'id',
							title : 'ְλID',
							width : 100,
							align : 'center',
							hidden:true
						}, {
							field : 'empCode',
							title : '�û�ID',
							width : 100,
							align : 'center'
						}, {
							field : 'userName',
							title : '�û���',
							width : 100,
							align : 'center'
						}
						, {
							field : 'orgName',
							title : '��֯',
							width : 170,
							align : 'center'
						}
						, {
							field : 'stationName',
							title : '��λ����',
							width : 173,
							align : 'center'
						}
						]]
					});

	// ��ҳ�ؼ�
	var p = $('#datagrid1').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
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
	} else if(successResult){
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}

function close() {
	window.parent.closeMaintStation();
}


function search() {
	$("#datagrid1").datagrid('reload');
}



/**
 * 
 * @param {} e
 * @return {Boolean}
 */


