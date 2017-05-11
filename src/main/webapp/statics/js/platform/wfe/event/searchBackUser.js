
$(document).ready(function() {
	loadGrid();
});

function loadGrid() {
	$('#datagrid').datagrid(
			{	
				iconCls : 'icon-list',
				title : '��ɫ�б�',
				striped : true,
				url : appUrl + '/wfe/eventAction!getBackListByEventId.jspa?eventId=' + $("#eventId").val()+'&time='+new Date(),
				loadMsg :  '����Զ��������,��ȴ�...',
				singleSelect : true,
				pagination : false,
				nowrap : true,
				remoteSort : true,
				height : 300,
				columns : [ [
						{
							field : 'ck',
							checkbox : true
						},
						{
							field : 'curUserId',
							title : '�û�Id',
							align : 'center',
							hidden: true
						},
						{
							field : 'nextOrgId',
							title : '��֯Id',
							align : 'center',
							hidden: true
						},
						{
							field : 'curStaId',
							title : '��ɫId',
							align : 'center',
							hidden: true
						},
						{
							field : 'curUserName',
							title : '�û���',
							width : setColumnWidth(0.35),
							align : 'center'
						},
						{
							field : 'roleName',
							title : '��ɫ',
							width : setColumnWidth(0.5),
							align : 'center'
						}
						 ] ],
						toolbar : [ "-", {
							text : 'ȷ��',
							iconCls : 'icon-save',
							handler : function() {
								selectBackUser();
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
function selectBackUser(){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows == ''){
		$.messager.alert('Tips',  '��ѡ������!', 'warning');
		return;
	}
	var rownum= $('#datagrid').datagrid('getRowIndex',$('#datagrid').datagrid('getSelected'));
	var str='';
	var strname='';
	var allrows=$('#datagrid').datagrid('getRows');
	for(var i=rownum;i>=0;i--){
		str+=allrows[i].curUserId+",";
		strname+=allrows[i].curUserName+","
	}
	var x = new Array();
	x[0] = rows[0].curUserId;
	x[1] = rows[0].nextOrgId;
	x[2] = rows[0].curStaId;
	x[3] = rows[0].curUserName;
	x[4] = rows[0].roleName;
	x[5] = str;
	window.parent.saveBackUser(x);
}

