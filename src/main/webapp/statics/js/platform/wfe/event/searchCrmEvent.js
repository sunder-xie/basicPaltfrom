var eventIdGloab = "";
$(document).ready(function() {
	$('#modelName').combobox({
		valueField : 'id',
		textField : 'text',
		data : [{
					id : '����',
					text : '��������'
				},  {
					id : '�ػ�',
					text : '�ػ�����'
				},  {
					id : '�޸�',
					text : '�޸�����'
				}],
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto'
	});
	$('#modelName').combobox('setValue','����');
	
	loadGrid();
	//����״̬��0Ϊδ����1Ϊ�����У�2Ϊ������ɣ�3Ϊ�Ѿܾ���4��ʾȡ����
	var data=[{'text':'-��ѡ��-','value':''},{'text':'������','value':'1'},{'text':'�����','value':'2'},{'text':'������','value':'3'},{'text':'��ȡ��','value':'4'}];
	$('#status').combobox(
			{
				data:data,
				multiple:false,
				valueField : 'value',
				textField : 'text',
				onLoadSuccess : function() {
				},
				editable : false
			});

	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/wfe/eventAction!getAdminEventJsonList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						queryParams : {
							modelName:encodeURIComponent($("#modelName").combobox('getValue'))
						},
						columns : [ [
//								{
//									field : 'ck',
//									checkbox : true
//								},
								{
									field : 'eventId',
									title : '������',
									width : setColumnWidth(0.05),
									align : 'center'
								},{
									field : 'subFolders',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
								},
								{
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
									field : 'initator',
									title : '�����id',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
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
									field : 'status',
									title : '����״̬',
									width : setColumnWidth(0.10),
									align : 'center',
									formatter : function(value) {
										if (value == 0) {
											return "δ����";
										}
										if (value == 1) {
											return "������";
										}
										if (value == 2) {
											return "�����";
										}
										if (value == 3) {
											return "������";
										}
										if (value == 4) {
											return "��ȡ��";
										}
									}
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
										var strReturn = '';
										if (row.backStatus == "1") {
											strReturn = '<a href= javascript:searchEventDetail("'
													+ row.eventId
													+ '")>�鿴������� </a>|'
													+ '<a href=javascript:graphTrace("'
													+ row.eventId
													+ '") > ���� </a>';
										} else {
											strReturn = '<a href= javascript:searchEventDetail("'
													+ row.eventId
													+ '")>�鿴������� </a>|'
													+ '<a href=javascript:graphTrace("'
													+ row.eventId
													+ '") > ���� </a>';
										}
										return strReturn;
									}
								} ] ],
//						onLoadSuccess : function(data) {
//							// ����ȫѡ����
//							// $(".datagrid-header-check")[0].disabled=true;
//							// ��ʼ���û�
//							selectedFile($(this), data.rows);
//						},
//						onCheck : function(rowIndex, rowData) {
//							if (rowData['status'] == 2
//									|| rowData['status'] == 3
//									|| rowData['status'] == 4) {
//								$('#datagrid')
//										.datagrid('unselectRow', rowIndex);
//								$('#datagrid').datagrid('unCheckRow', rowIndex);
//							}
//						},
//						toolbar : [ "-", {
//							text : 'ת�Ƶ�ǰ������',
//							iconCls : 'icon-cancel',
//							handler : function() {
//								transferEvent();
//							}
//						}, "-" ]
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

///**
// * ��ʼ���ûҵ�ѡ��
// */
//function selectedFile(grid, rows) {
//	for ( var j = 0; j < rows.length; j++) {
//		if (2 == rows[j]['status'] || 3 == rows[j]['status']
//				|| 4 == rows[j]['status']) {
//			$(".datagrid-row[datagrid-row-index=" + j
//					+ "] input[type='checkbox']")[0].disabled = true;
//		}
//	}
//}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.initator = encodeURIComponent($("#initator").val());
	queryParams.curUserName = encodeURIComponent($("#curUserName").val());
	queryParams.modelName = encodeURIComponent($("#modelName").combobox('getValue'));
	queryParams.status = encodeURIComponent($("#status").combobox('getValue'));
//		queryParams.semModelUserName='';
		$("#datagrid").datagrid('load');
	  
}

// �������ڶ���
function initWindow(title, url, id, WWidth, WHeight) {

	var url = appUrl + url;
	var $win = $("#" + id)
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


function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','������Ϣ�鿴', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}
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
