$(document).ready(function() {
	loadGrid();
	$('#hideFrameStation').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid(
					{	
						iconCls : 'icon-list',
						title : '��ɫ�б�',
						striped : true,
						url : appUrl + '/wfe/eventAction!getStationListByEventId.jspa?eventId=' + $("#eventId").val(),
						loadMsg : '����Զ��������,��ȴ�...',
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
							text : '����',
							iconCls : 'icon-ok',
							handler : function() {
								backEvent();
							}
						}, "-" ]
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function backEvent() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '��ѡ������!');
		return;
	}
	$.messager.confirm('Confirm', 'ȷ�ϻ�������?', function(r) {
		if (r) {
			var modelId=parent.window.$('#modelId').val();
			//alert(modelId);
			var str=modelId.substring(0,3);
			//alert(str);
			if('sem'==str){
				$.messager.progress();
				var form = window.parent.document.forms[0];
				form.action = appUrl
				+ "/wfe/eventAction!processBackSem.jspa?nextUserBack=" + rows[0].curUserId+"&operation=H";
				form.target = "hideFrameStation";
				form.submit();
			}else{
				$.messager.progress();
				var form = window.parent.document.forms[0];
				form.action = "eventAction!backEvent.jspa?curStaIdBack=" + rows[0].curStaId;
				form.target = "hideFrameStation";
				form.submit();
			}
		}
	});
}

function promgtMsg() {
	$.messager.progress('close');

	var hideFrameStation = document.getElementById("hideFrameStation");
	var failResult = hideFrameStation.contentWindow.failResult;
	var successResult = hideFrameStation.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.parent.closeStation();
		});
	}
}

