$(document).ready(function() {
	loadGrid();
});

function loadGrid() {
	$('#datagrid').datagrid(
					{	
						iconCls : 'icon-list',
						title : '��Ա�б�',
						striped : true,
						url : appUrl + '/wfe/eventAction!getLinkManJsonList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						pagination : false,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'linkManId',
									title : '��ԱID',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								{
									field : 'linkManName',
									title : '��Ա����',
									width : setColumnWidth(0.4),
									align : 'center'
								},
								{
									field : 'orgId',
									title : '����ID',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								{
									field : 'orgName',
									title : '����',
									width : setColumnWidth(0.4),
									align : 'center'
								}
								 ] ],
						toolbar : [ "-", {
							text : 'ȷ��',
							iconCls : 'icon-save',
							handler : function() {
								selectLinkMan();
							}
						}, "-" ]
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

/**
 * ѡ��ȷ����ϵ��
 */
function selectLinkMan(){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows == ''){
		$.messager.alert('Tips', '��ѡ������!', 'warning');
		return;
	}
	var x = new Array();
	x[0] = rows[0].linkManId;
	x[1] = rows[0].linkManName;
	x[2] = rows[0].orgId;
	x[3] = rows[0].orgName;
	window.parent.saveUser(x);
}

