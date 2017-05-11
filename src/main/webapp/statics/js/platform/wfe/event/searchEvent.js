var eventIdGloab = "";
$(document).ready(function() {
	loadGrid();
	//����״̬��0Ϊδ����1Ϊ�����У�2Ϊ������ɣ�3Ϊ�Ѿܾ���4��ʾȡ����
	var data=[{'text':'δ����','value':'0'},{'text':'������','value':'1'},{'text':'�����','value':'2'},{'text':'������','value':'3'},{'text':'��ȡ��','value':'4'}];
	$('#status').combobox(
			{
				data:data,
				valueField : 'value',
				textField : 'text',
				multiple:false,
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
						url : appUrl + '/wfe/eventAction!getEventJsonList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
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
									width : setColumnWidth(0.10),
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
									width : setColumnWidth(0.19),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn = '';
										if (row.backStatus == "1") {
											strReturn = '<a href= javascript:searchEventDetail("'
													+ row.eventId
													+ '")>�鿴������� </a>|'
													+ '<a href=javascript:graphTrace("'
													+ row.eventId
													+ '") > ���� </a>|'
													+ '<a href=javascript:searchProEventReader("'
													+ row.eventId
													+ '") > ��Ȩ�鿴</a>|'
													+ '<a href= javascript:backProEvent("'
													+ row.eventId
													+ '","'
													+ row.currentDetailid
													+ '","'
													+ row.curStaId
													+ '","'
													+ row.modelId
													+ '")>ȡ�� </a>';
										} else {
											strReturn = '<a href= javascript:searchEventDetail("'
													+ row.eventId
													+ '")>�鿴������� </a>|'
													+ '<a href=javascript:graphTrace("'
													+ row.eventId
													+ '") > ���� </a>|'
													+ '<a href=javascript:searchProEventReader("'
													+ row.eventId
													+ '") > ��Ȩ�鿴</a>';
										}
										return strReturn;
									}
								} ] ],
						onLoadSuccess : function(data) {
							// ����ȫѡ����
							// $(".datagrid-header-check")[0].disabled=true;
							// ��ʼ���û�
							selectedFile($(this), data.rows);

							// ����в�ѡ��checkbox
							// function bindRowsEvent(){
							// var panel = $('#datagrid').datagrid('getPanel');
							// var rows = panel.find('tr[datagrid-row-index]');
							// rows.unbind('click').bind('click',function(e){
							// return false;
							// });
							// rows.find('div.datagrid-cell-check
							// input[type=checkbox]').unbind().bind('click',
							// function(e){
							// var index =
							// $(this).parent().parent().parent().attr('datagrid-row-index');
							// if ($(this).attr('checked')){
							// $('#datagrid').datagrid('selectRow', index);
							// } else {
							// $('#datagrid').datagrid('unselectRow', index);
							// }
							// e.stopPropagation();
							// });
							// }
							// setTimeout(function(){
							// bindRowsEvent();
							// }, 10);
							$(".datagrid-header-check input")
									.unbind('click')
									.bind(
											'click',
											function(e) {
												var checkArr = new Array();
												for ( var i = 0; i < data.rows.length; i++) {
													if (0 == data.rows[i].status
															|| 1 == data.rows[i].status) {
														checkArr.push(i);
													}
												}
												var checkedAll = true;
												for ( var i = 0; i < checkArr.length; i++) {
													var chk = $(".datagrid-row[datagrid-row-index="
															+ checkArr[i]
															+ "] input[type='checkbox']")[0].checked;
													if (!chk) {
														checkedAll = false;
														break;
													}
												}
												if (checkedAll) {
													for ( var i = 0; i < data.rows.length; i++) {
														$('#datagrid')
																.datagrid(
																		'unselectRow',
																		i);
													}
												} else {
													for ( var i = 0; i < data.rows.length; i++) {
														if (0 == data.rows[i].status
																|| 1 == data.rows[i].status) {
															$('#datagrid')
																	.datagrid(
																			'selectRow',
																			i);
														}
													}
													$(
															".datagrid-header-check input")
															.attr('checked',
																	true);
												}
											});
						},
						onCheck : function(rowIndex, rowData) {
							if (rowData['status'] == 2
									|| rowData['status'] == 3
									|| rowData['status'] == 4) {
								$('#datagrid')
										.datagrid('unselectRow', rowIndex);
								$('#datagrid').datagrid('unCheckRow', rowIndex);
							}
						},
						toolbar : [ "-", {
							text : 'ȡ������',
							iconCls : 'icon-cancel',
							handler : function() {
								cancelEvent();
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
function selectedFile(grid, rows) {
	for ( var j = 0; j < rows.length; j++) {
		if (2 == rows[j]['status'] || 3 == rows[j]['status']
				|| 4 == rows[j]['status']) {
			$(".datagrid-row[datagrid-row-index=" + j
					+ "] input[type='checkbox']")[0].disabled = true;
		}
	}
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.initator = encodeURIComponent($("#initator").val());
	queryParams.modelName = encodeURIComponent($("#modelName").val());
	queryParams.status = encodeURIComponent($("#status").combobox('getValue'));
	
	$("#datagrid").datagrid('reload');
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

function searchProEventReader(eventId) {
	initMaintEvent(true,'700','400','��������˹���','/wfe/authorizeEventAction!toSearchEventReader.jspa?eventId='+ eventId, 0, 0);
}

function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','������Ϣ�鿴', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}

function cancelEvent(eventId) {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '��ѡ������!');
		return;
	}
	//��Ϊȡ�����������ǵ�ѡ�ģ�����ֻ�жϵ�һ��
	if (rows[0].modelId == 'fix_marketFlzz'&&rows[0].curStaId!='start') {
		$.messager.alert('Tips', '�г�����-��������,�Ƿ����˽ڵ㲻����ȡ��!');
		return;
	}
	$.messager.confirm('Confirm', 'ȷ��ȡ������?', function(r) {
		if (r) {
			$.messager.progress();
			var subFolders = "";
			for ( var i = 0; i < rows.length; i++) {
				eventIdGloab = rows[i].eventId;
				subFolders = rows[i].subFolders;
			}
			  $.ajax({
					type : "post",
					url : appUrl
							+ "/wfe/eventAction!selectAnyExecuteAction.jspa?time="
							+ new Date(),
					data : {
						eventId : eventIdGloab,
						operation : "N"
					},
					success : function(userUtil) {
						if (userUtil != null&& userUtil.processInstanceId == "success") { // ���һ��������ʱ����
							if (userUtil.executeAction != null&& userUtil.executeAction != "") {
							$.ajax({
									type : "get",
									url : userUtil.executeAction,
									data : {
										eventId : eventIdGloab,
										subFolders : subFolders,
										operation : "N"
									  },
									  	dataType : 'jsonp',
										jsonpCallback : "callback"
									});
						} else {
							var form = window.document.forms[0];
							form.action = "eventAction!cancelEvent.jspa?eventIds=" + eventIdGloab;
							form.target = "hideFrame";
							form.submit();
						}
					}
				},
				error : function() {
					$.messager.progress('close');
					$.messager.alert('Tips',"ϵͳ����", 'error');
				}
			});
		}
		;
	});
}

function backProEvent(eventId, currentDetailid, curStaId, modelId) {
	$.messager.confirm('Confirm', 'ȷ��ȡ�ظ���������?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = "eventAction!backProEvent.jspa?eventIds=" + eventId
					+ "&toDoDetail=" + currentDetailid + "&curStaId="
					+ curStaId + "&modelId=" + modelId;
			form.target = "hideFrame";
			form.submit();
		}
	});
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

function callback(executeResult) {
	var res = executeResult.result;
	var msg = executeResult.code;

    if (res == "true") {
    	var form = window.document.forms[0];
		form.action = "eventAction!cancelEvent.jspa?eventIds=" + eventIdGloab;
		form.target = "hideFrame";
		form.submit();
	} else {
		if(msg != null){
			$.messager.alert('Tips', msg, 'error', function() {
				$.messager.progress('close');
			});
		}else{
			$.messager.alert('Tips', "�������", 'error', function() {
					$.messager.progress('close');
			});
		}
	}
}