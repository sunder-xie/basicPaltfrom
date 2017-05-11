$(document).ready(function(){
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/evtstatistics/evtstatisticsAction!searchEventModelHrList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						onBeforeEdit : function(index, row) {
							row.editing = true;
							updateActions(index);
						},
						onAfterEdit : function(index, row) {
							row.editing = false;
							updateActions(index);
						},
						onCancelEdit : function(index, row) {
							row.editing = false;
							updateActions(index);
						},
						columns : [ [
								{
									field : 'modelName',
									title : '����ģ��',
									width : setColumnWidth(0.3),
									align : 'center'

								},
								{
									field : 'modelId',
									title : 'ģ����',
									width : setColumnWidth(0.12),
									align : 'center'

								},
								{
									field : 'curUserName',
									title : 'ά����',
									width : setColumnWidth(0.12),
									align : 'center'

								},
								{
									field : 'createDate',
									title : 'ά��ʱ��',
									width : setColumnWidth(0.12),
									align : 'center'

								},
								{
									field : 'operation',
									title : '����',
									width : setColumnWidth(0.12),
									align : 'center',
									formatter : function(value, row, index) {
										return '<img style="cursor:pointer" onclick="toDetail(\'' 
										+ row.modelId + '\')" title="�޸�" src='
										+ imgUrl
										+ '/images/actions/action_view.png align="absMiddle"></img>';
									}
								} ] ]
					,toolbar : [ "-",{
									text : '��������',
									iconCls : 'icon-add ',
									handler : function() {
										importCsv();
									}
								}, "-"]
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

function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

function editrow(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}

function cancelrow(target){
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function saverow(target) {
	var rowIndex = getRowIndex(target);
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rows = $('#datagrid').propertygrid('getChanges');
	if(rows.length==0){
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/evtstatistics/evtstatisticsAction!updateEventModel.jspa",
		data : {
			modelId : rows[0].modelId,
			modelType : rows[0].modelType
		},
		success : function(total) {
			if(total>0){
				$.messager.alert('Tips', '�޸ĳɹ���', 'info');
				loadGrid();
			}else{
				$.messager.alert('Tips', '�޸�ʧ�ܣ�', 'error');
			}
		}
	});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.modelName = encodeURIComponent($("#modelName").val());
	$("#datagrid").datagrid('load'); 
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
			if ($('#excelDialog').length > 0) {
				$('#excelDialog').dialog('close');
			}
			search();
		});
	}
}

function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>ģ��ѡ��</td>'
			+ '<td><select id="modelId" name="modelId">'
			+ '<option value="fix_hrEntrySale">�������̡�������ְ����</option>'
			+ '<option value="fix_hrEntryFun">�������̡�ְ�ܲ�����ְ����</option>'
			+ '<option value="fix_hrOut">���µ�����ְ����</option>'
			+ '<option value="fix_hrTurnover">�������̡���ְ����1</option>'
			+ '<option value="fix_hrPositiveStaff">�������̡�ת������</option>'
			+ '<tr><td class="head" noWrap>ģ������</td>'
			+ '<td><a style="color:blue" onclick=javascript:exportModelCsv();> 1������ģ��</a></td></tr>'
			+ '<tr><td class="head" noWrap>��������</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importModelCsv('��������', html);
}

//csv���ص���ģ��
function exportModelCsv() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ������ģ��?', function(r) {
		if (r) {
			var form =document.getElementById("fileForm");
			form.action = appUrl
					+ '/evtstatistics/evtstatisticsAction!exportEventModelHrCsv.jspa';
			form.submit();
		}
	});

}

//����������Ϣ
function importModelCsv(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : 'ѡ���ϴ��ļ�',
					html : "<div id='import'>"
					// + "</br>"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" // +
							// "</div>"
				}).appendTo('body');
	}
	$('#excelDialog')
			.dialog(
					{
						modal : true,
						resizable : false,
						dragable : false,
						closable : false,
						open : function() {
							$('#excelDialog').css('padding', '0.8em');
							$('#excelDialog .ui-accordion-content').css(
									'padding', '0.4em').height(
									$('#excelDialog').height() - 100);
						},
						buttons : [
								{
									text : 'ȷ��',
									handler : function() {
										/*
										 * if ($('#orgId01').val() == '' ||
										 * $('#orgId01').val() == undefined) {
										 * $.messager.alert("��ʾ", "��ѡ��������֯");
										 * return; } if
										 * ($('#stationUserId01').val() == '' ||
										 * $('#stationUserId01').val() ==
										 * undefined) { $.messager.alert("��ʾ",
										 * "��ѡ��ҵ������"); return; }
										 */
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											openTime();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/evtstatistics/evtstatisticsAction!importEventModelHrCsv.jspa";
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("��ʾ", "�뵼��csv�ļ�");
										}

									}
								}, {
									text : 'ȡ��',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.35,
						height : document.documentElement.clientHeight * 0.55
					});
}

function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/evtstatistics/evtstatisticsAction!checkDownLoadOver.jspa?",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
					}
				}
			});
		}, 100);
	}, 500);
}

/**
 * ����֯��
 */
function selectOrgTree() {
	initMaintWindowForOrg('ѡ����֯', '/orgAction!orgTreePage.jspa');
}

function toDetail(modelId){
	initMaintWindowForOrg('�鿴��ϸ',
			'/evtstatistics/evtstatisticsAction!toSearchEventModelHrDetail.jspa?modelId='+modelId);
}

// �رմ���ҳ��
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','������Ϣ�鿴', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
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

function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 460;
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