var eventIdGloab = "";
$(document).ready(function() {
	loadGrid();
	//����״̬��0Ϊδ����1Ϊ�����У�2Ϊ������ɣ�3Ϊ�Ѿܾ���4��ʾȡ����
	var data=[{'text':'-��ѡ��-','value':''},{'text':'������','value':'1'},{'text':'�����','value':'2'},{'text':'������','value':'3'},{'text':'��ȡ��','value':'4'}];
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/wfe/eventAction!getRoleEventList.jspa',
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
									field : 'eventId',
									title : '������',
									width : setColumnWidth(0.05),
									align : 'center'
								},{
									field : 'currentDetailid',
									title : 'detailId',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
								},
								{
									field : 'eventTitle',
									title : '�������',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'empName',
									title : '�����',
									width : setColumnWidth(0.10),
									align : 'center'
								},
								{
									field : 'modelName',
									title : '����ģ��',
									width : setColumnWidth(0.12),
									align : 'center'

								},
								{
									field : 'curUserName',
									title : '��ǰ������',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'creatdate',
									title : '���ʱ��',
									width : setColumnWidth(0.14),
									align : 'center'

								},
								{
									field : 'operation',
									title : '����',
									width : setColumnWidth(0.25),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn = '<a href= javascript:ontransferEvent('+row.eventId+','+row.currentDetailid+')>����ת������ </a>|'
											+'<a href= javascript:searchEventDetail("'
											+ row.eventId
											+ '")>�鿴������� </a>'
										return strReturn;
									}
								} ] ],
						toolbar : [ "-", {
							text : '����������ѡ����',
							iconCls : 'icon-cancel',
							handler : function() {
								ontransferEvent();
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

/**
 * ��ʼ���ûҵ�ѡ��
 */


function search() {
//	var queryParams = $('#datagrid').datagrid('options').queryParams;
//	queryParams.eventId = encodeURIComponent($("#eventId").val());
//	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
//	queryParams.initator = encodeURIComponent($("#initator").val());
//	queryParams.curUserName = encodeURIComponent($("#curUserName").val());
//	queryParams.modelName = encodeURIComponent($("#modelName").val());
//	queryParams.status = encodeURIComponent($("#status").combobox('getValue'));
	$('#datagrid').datagrid('reload');
}

//// �������ڶ���
//function initWindow(title, url, id, WWidth, WHeight) {
//
//	var url = appUrl + url;
//	var $win = $("#" + id)
//			.window(
//					{
//						title : title,
//						width : WWidth,
//						height : WHeight,
//						content : '<iframe frameborder="no" width="100%" height="100%" src='
//								+ url + '/>',
//						shadow : true,
//						modal : true,
//						closed : true,
//						closable : true,
//						minimizable : false,
//						maximizable : false,
//						collapsible : false,
//						draggable : true
//					});
//
//	$win.window('open');
//}

//�������ڶ���
function initMaintEvent(ffit,widte,height,title, url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ urls + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');

}

//function searchProEventReader(eventId) {
//	initMaintEvent(true,'700','400','��������˹���','/wfe/authorizeEventAction!toSearchEventReader.jspa?eventId='+ eventId, 0, 0);
//}

function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','������Ϣ�鿴', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}
//ת������
function ontransferEvent(event,currentDetailid){
//	$("#maintWindow").window('close');
	var rows = $('#datagrid').datagrid('getSelections');
	var eventIds=rows[0].eventId;
	var currentDetailids=rows[0].currentDetailid;
	for ( var i = 1; i < rows.length; i++) {
		eventIds = eventIds+","+rows[i].eventId;
		currentDetailids=currentDetailids+","+rows[i].currentDetailid;
	}
	//�в�������ʹ�ò����ģ�û�еĻ����ù�ѡ��
	 eventIds=event!=null?event:eventIds;
	 currentDetailids=currentDetailid!=null?currentDetailid:currentDetailids;
	$.messager.confirm('Confirm', 'ȷ�Ͻ���������:'+eventIds+"������?", function(r) {
		if (r) {
				$.messager.progress();
				var form = window.document.forms[0];
				form.action = "eventAction!transferEvent.jspa?eventIds=" + currentDetailids;
				form.target = "hideFrame";
				form.submit();
			}}
		);
}
//function cancelEvent(eventId,subFolders) {
//	var rows = $('#datagrid').datagrid('getSelections');
//	if (rows == '') {
//		$.messager.alert('Tips', '��ѡ������!');
//		return;
//	}
//	$.messager.confirm('Confirm', 'ȷ��ȡ������?', function(r) {
//		if (r) {
//			$.messager.progress();
//			var subFolders = "";
//			for ( var i = 0; i < rows.length; i++) {
//				eventIdGloab = rows[i].eventId;
//				subFolders = rows[i].subFolders;
//			}
//			  $.ajax({
//					type : "post",
//					url : appUrl
//							+ "/wfe/eventAction!selectAnyExecuteAction.jspa?time="
//							+ new Date(),
//					data : {
//						eventId : eventId,
//						operation : "N"
//					},
//					success : function(userUtil) {
//						if (userUtil != null&& userUtil.processInstanceId == "success") { // ���һ��������ʱ����
//							if (userUtil.executeAction != null&& userUtil.executeAction != "") {
//							$.ajax({
//									type : "get",
//									url : userUtil.executeAction,
//									data : {
//										eventId : eventId,
//										subFolders : subFolders,
//										operation : "N"
//									  },
//									  	dataType : 'jsonp',
//										jsonpCallback : "callback"
//									});
//						} else {
//							var form = window.document.forms[0];
//							form.action = "eventAction!cancelEvent.jspa?eventIds=" + eventId;
//							form.target = "hideFrame";
//							form.submit();
//						}
//					}
//				},
//				error : function() {
//					$.messager.progress('close');
//					$.messager.alert('Tips',"ϵͳ����", 'error');
//				}
//			});
//		}
//		;
//	});
//}

//function backProEvent(eventId, currentDetailid, curStaId, modelId,userId) {
//	$.messager.confirm('Confirm', 'ȷ��ȡ�ظ���������?', function(r) {
//		if (r) {
//			var form = window.document.forms[0];
//			form.action = "eventAction!backAdminProEvent.jspa?eventIds=" + eventId
//					+ "&toDoDetail=" + currentDetailid + "&curStaId="
//					+ curStaId + "&modelId=" + modelId+"&userId="+userId;
//			form.target = "hideFrame";
//			form.submit();
//		}
//	});
//}

function promgtMsg() {
	$.messager.progress('close');
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

//function callback(executeResult) {
//	var res = executeResult.result;
//	var msg = executeResult.code;
//
//    if (res == "true") {
//    	var form = window.document.forms[0];
//		form.action = "eventAction!cancelEvent.jspa?eventIds=" + eventIdGloab;
//		form.target = "hideFrame";
//		form.submit();
//	} else {
//		if(msg != null){
//			$.messager.alert('Tips', msg, 'error', function() {
//				$.messager.progress('close');
//			});
//		}else{
//			$.messager.alert('Tips', "�������", 'error', function() {
//					$.messager.progress('close');
//			});
//		}
//	}
//}