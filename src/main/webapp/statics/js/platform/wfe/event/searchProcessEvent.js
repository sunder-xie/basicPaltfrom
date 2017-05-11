var flag = "";
var userUtil;
var eventIdGlobal="";
var toDoDetailGlobal ="";
$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#modelName')
		.combogrid(
			{
				panelWidth : 450,
				panelHight : 500,
				idField : 'modelId',
				textField : 'modelName',
				pagination : true,
				rownumbers : true,
				striped : true,
				fit : true,
				method : 'post',
				editable : false,
				multiple : false,
				required : false,
				url : appUrl + '/wfe/eventAction!getModelJsonList.jspa',
				columns : [ [
						{
							field : 'modelId',
							title : 'ģ��ID',
							width : 150,
							align : 'center'
						},
						{
							field : 'modelName',
							title : 'ģ������',
							width : 250,
							align : 'center'
						} ] ],
				toolbar : '#toolbar'
			});
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/wfe/eventAction!getProcessEventJsonList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'eventId',
									title : '������',
									width : setColumnWidth(0.08),
									align : 'center'
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
									width : setColumnWidth(0.13),
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
									width : setColumnWidth(0.1),
									align : 'center'
									
								},
								{
									field : 'orgId',
									title : '�������֯Id',
									width : setColumnWidth(0.10),
									align : 'center',
									hidden : true
									
								},
								{
									field : 'orgName',
									title : '�������֯',
									width : setColumnWidth(0.10),
									align : 'center'
									
								},
								{
									field : 'curStaId',
									title : '��ɫId',
									hidden : true
									
								},
								{
									field : 'modelId',
									title : '����ģ��id',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
									
								},
								{
									field : 'keys',
									title : 'keys',
									hidden : true
								},
								{
									field : 'cc_id',
									title : 'cc_id',
									hidden : true
								},
								{
									field : 'modelName',
									title : '���̷���',
									width : setColumnWidth(0.15),
									align : 'center'
									
								},
								{
									field : 'eventType',
									title : '��������',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value){
										if (value == 1) {
											return "��ת������";
										}else if (value == 2) {
											return "��ǩ����";
										}else{
											return "��������";
										}
									}
								},
								{
									field : 'creatdate',
									title : '���ʱ��',
									width : setColumnWidth(0.16),
									align : 'center'
									
								},
								{
									field : 'operation',
									title : '����',
									width : setColumnWidth(0.18),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn="";
										if(row.eventType==1){
											strReturn = '<a href= javascript:ontransferEvent('
												+row.eventId+','+row.currentDetailid+')>ǩ������ </a>|'
											+'<a href= javascript:searchEventDetail("'
											+ row.eventId
											+ '")>�鿴������� </a>'
										}else if (row.eventType==2){
											strReturn = '<a href= javascript:CountersignEvent("'
												+ row.cc_id + '","'
												+ row.eventId
												+ '")>��ǩ���� </a>';
										}else{
											 strReturn = '<a href= javascript:processEvent("'
												+ row.eventId + '","'+ row.currentDetailid + '","'
												+ row.keys + '","'
												+ row.modelId +'","'
												+ row.curStaId +'")>���� </a>|'
												+'<a href=javascript:graphTrace("'
												+ row.eventId + '") > ���� </a>|'
												+ '<a href=javascript:searchProEventReader("'
												+ row.eventId + '") > ��Ȩ�鿴</a>';
										}
										return strReturn;
									}
								} 
								] ]
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

function searcher_model(val) {
	val = encodeURIComponent(val);
	$('#modelName')
			.combogrid(
					{
						url : appUrl + '/wfe/eventAction!getModelJsonList.jspa?searchStr=' + val
					});
	$('#modelName').combogrid("grid").datagrid('reload');
}

function batchAgreeEvent(){
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '��ѡ������!');
		return;
	}
	$.messager.confirm('Confirm', 'ȷ������ͬ������?', function(r) {
		if (r) {
			for ( var i = 0; i < rows.length; i++) {
				handleEvent("Y",rows[i].eventId,rows[i].currentDetailid,rows[i].modelId,rows[i].curStaId,rows[i].subFolders);
			}
			$.messager.progress('close');
		}
		;
	});
}
function batchRefuseEvent(){
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '��ѡ������!');
		return;
	}
	$.messager.confirm('Confirm', 'ȷ��������������?', function(r) {
		if (r) {
			for ( var i = 0; i < rows.length; i++) {
				handleEvent("N",rows[i].eventId,rows[i].currentDetailid,rows[i].modelId,rows[i].curStaId,rows[i].subFolders);
			}
			$.messager.progress('close');
		};
	});
}
function  handleEvent(operation,eventId,toDoDetail,modelId,curStaId,subFolders){
	eventIdGlobal = eventId;
	toDoDetailGlobal = toDoDetail;
	var fix_key = modelId.substr(0, 3);
	$.messager.progress();
	if (fix_key == "fix") {
		$.ajax({    type : "post",
					url : appUrl
							+ "/wfe/eventAction!selectProcessNexUser.jspa?time="
							+ new Date(),
					data : {
						operation : operation,
						eventId : eventId,
						toDoDetail : toDoDetail,
						modelId : modelId,
						curStaId : curStaId
					},
					success : function(userUtil1) {
					   userUtil = userUtil1;
					   if (userUtil == null || userUtil == "" || userUtil.processInstanceId == undefined) {
							$.messager.progress('close');
							$.messager.alert('Tips',"���̳�����ά����",'error');
							return;
						}else if (userUtil.processInstanceId == "-1") {
							$.messager.progress('close');
							$.messager.alert('Tips',"���̳�����ά����",'error');
							return;
						}
						else if (userUtil != null&& userUtil.processInstanceId == "success") { // ���һ��������ʱ����
							if (userUtil.executeAction != null&& userUtil.executeAction != "") {
								flag = "1";
								$.ajax({    type : "get",
											url : userUtil.executeAction,
											data : {
												eventId : eventId,
												subFolders : subFolders,
												operation : operation
											},
											dataType : 'jsonp',
											jsonpCallback : "callback"
										});
							} else {
								var form = window.document.forms[0];
								form.action = appUrl
										+ "/wfe/eventAction!uploadAttachments.jspa";
								form.target = "hideFrameWfe";
								form.submit();
							}
						} else if (userUtil != null&& userUtil.processInstanceId == "last") {
							if (userUtil.executeAction != null&& userUtil.executeAction != "") {
								flag = "2";
								$.ajax({    type : "get",
											url : userUtil.executeAction,
											data : {
												eventId : eventId,
												subFolders : subFolders,
												operation : operation
											},
											dataType : 'jsonp',
											jsonpCallback : "callback"
										});
							} else {
								$.messager.progress('close');
								$.messager.alert("����ɹ���",	'info',function() {
									window.parent.search();
									window.parent.closeMaintWindow();
								});
							}
						}else if ($("#curStaId").val() == "start" && userUtil != null 
							&& userUtil.executeAction != null&& userUtil.executeAction != "") {
								flag = "4";
								$.ajax({    type : "get",
											url : userUtil.executeAction,
											data : {
												eventId : eventId,
												subFolders : subFolders,
												operation : operation
											},
											dataType : 'jsonp',
											jsonpCallback : "callback"
										});
						} else{
							contineProcess();
						}
					},
					error : function() {
						$.messager.progress('close');
						$.messager.alert('Tips',
								"ϵͳ����", 'error');
					}
				});
				} else if (fix_key == "sem") {
				$.ajax({
					type : "post",
					url : appUrl
							+ "/wfe/eventAction!selectSemUser.jspa?time="
							+ new Date(),
					data : {
						eventId : $("#eventId").val()
					},
					success : function(userUtil) {
						if (userUtil != null&& userUtil.processInstanceId == "success") { // ���һ��������ʱ����
							if (userUtil.executeAction != null&& userUtil.executeAction != "") {
								flag = "3";
								$.ajax({
											type : "get",
											url : userUtil.executeAction,
											data : {
												eventId : eventId,
												subFolders : subFolders,
												operation : operation
											},
											dataType : 'jsonp',
											jsonpCallback : "callback"
										});
							} else {
								var form = window.document.forms[0];
								form.action = appUrl
										+ "/wfe/eventAction!processEvent.jspa?operation="
										+ operation;
								form.target = "hideFrameWfe";
								form.submit();
							}
						}
					},
					error : function() {
						$.messager.progress('close');
						$.messager.alert('Tips',"ϵͳ����", 'error');
					}
				});

	} else {
		var form = window.document.forms[0];
		form.action = appUrl
				+ "/wfe/eventAction!processEvent.jspa?operation="
				+ operation;
		form.target = "hideFrameWfe";
		form.submit();
	}

}
function callback(executeResult) {
	var res = executeResult.result;
	var msg = executeResult.code;
	if (flag == "1") {
		if (res == "true") {
			var form = window.document.forms[0];
			form.action = appUrl
					+ "/wfe/eventAction!completeEndTask.jspa?operation="
					+ golbalOperation + "&successResult="
					+ encodeURIComponent(msg);
			form.target = "hideFrameWfe";
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
	} else if (flag == "2") {
		$.messager.alert('Tips', "����ɹ���", 'info', function() {
			window.parent.search();
			window.parent.closeMaintWindow();
		});
	} else if (flag == "3"){
		if (res == "true") {
			var form = window.document.forms[0];
			form.action = appUrl
					+ "/wfe/eventAction!processEvent.jspa?operation="
					+ golbalOperation;
			form.target = "hideFrameWfe";
			form.submit();
		} else {
			$.messager.alert('Tips', "�������", 'error', function() {
				$.messager.progress('close');
			});
		}

	}else if (flag == "4"){
		if (res == "true") {
			contineProcess();
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

}

function contineProcess(){
	var total = 0;
	var nextUserId = "";
	$.each(userUtil.result,function(i,	v) {
		total = i+1;
		nextUserId = v.userId;
	});
	if(total == 1){
		var form = window.document.forms[0];
		form.action = appUrl+ "/wfe/eventAction!processCommit.jspa?nextUserId="+ nextUserId+"&eventId="+eventIdGlobal+"&toDoDetail="+toDoDetailGlobal;;
		form.target = "hideFrameWfe";
		form.submit();
	}else{
		$.messager.progress('close');
		var positionHtml = "<div class='easyui-panel' title='�¸�����' data-options='collapsible:true'>"
				+ "<table width='100%' border='0' cellpadding='0' cellspacing='1'>"
				+ "<tr><td class='head' noWrap>������</td>"
				+ "<td><select id='nextUserId' name='nextUserId'>";
		$.each(userUtil.result,function(i,	v) {
			positionHtml += "<option value='"
						 + v.userId
						 + "'>"
						 + v.userName
						 + "----"
						 + v.stationName
						 + "</option>";
		});
		positionHtml += "</select></td></tr></table></div>";
		if ($('#nextUserDialog').length < 1) {
			$('<div/>',{
						id : 'nextUserDialog',
						title : 'ѡ���¸�������',
						html : "<div id='nextUser'>"
								+ positionHtml
								+ "</div>"
								+ "</div>"
					}).appendTo('body');
		} else {
			$('#nextUser').html(positionHtml);
		}
		$('#nextUserDialog').dialog(
						{
							modal : true,
							resizable : false,
							dragable : false,
							closable : false,
							open : function() {
								$('#nextUserDialog').css('padding','0.4em');
								$('#nextUserDialog .ui-accordion-content').css('padding','0.4em').height(
								$('#nextUserDialog').height() - 75);
							},
							buttons : [{
										text : 'ȷ��',
										handler : function() {
											if ($("#nextUserId").val() == ""|| $("#nextUserId").val() == null) {
												$.messager.alert('Tips',"û���¸������ˣ���ά����",'error');
												return;
											}
											$.messager.progress();
											var form = window.document.forms[0];
											form.action = appUrl+ "/wfe/eventAction!processCommit.jspa?nextUserId="+ $("#nextUserId").val()
											+"&eventId="+eventIdGlobal+"&toDoDetail="+toDoDetailGlobal;
											form.target = "hideFrameWfe";
											form.submit();
										}
									},
									{
										text : 'ȡ��',
										handler : function() {
											$('#nextUserDialog').dialog('close');
											//self.location.reload();
										}
									} ],
									width : document.documentElement.clientWidth * 0.50,
									height : document.documentElement.clientHeight * 0.40
		});
	}

}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.initator = encodeURIComponent($("#initator").val());
	queryParams.modelName = encodeURIComponent($("#modelName").combogrid('getText'));
	queryParams.orgName = encodeURIComponent($("#orgName").val());
	$("#datagrid").datagrid('load');
}

function searchEventDetail(eventId) {
	var WWidth = 860;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + "/wfe/eventAction!searchEventDetail.jspa?eventId="
			+ eventId, "searchEventDetail", "left=" + WLeft + ",top=20"
			+ ",width=" + WWidth + ",height=" + (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}
//��ǩ����
function CountersignEvent(cc_id,eventId) {
	initMaintEvent(true,'700','400','��ǩ����', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId+"&cc_id="+cc_id,0,0); 
}
//ת������ǩ��
function ontransferEvent(event,currentDetailid){

	$.messager.confirm('Confirm', 'ȷ�Ͻ���������:'+event+"������?", function(r) {
		if (r) {
				$.messager.progress();
				var form = window.document.forms[0];
				form.action = appUrl+"/wfe/eventAction!transferEvent.jspa?eventIds=" + currentDetailid;
				form.target = "hideFrame";
				form.submit();
			}}
		);
}

//�������ڶ���
function initWindow(title, url, id, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#"+id)
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

function searchProEventReader(eventId) {
	initMaintEvent(true,'700','400','��������˹���','/wfe/authorizeEventAction!toSearchEventReader.jspa?eventId='+ eventId, 0, 0);
}

function processEvent(eventId, toDoDetail,keys,modelId,curStaId) {
	initMaintEvent(true,800, 480,'��������',"/wfe/eventAction!toProcessEvent.jspa?event_id="
			+ eventId + "&toDoDetail=" + toDoDetail + "&modelId=" + modelId + "&curStaId=" + curStaId+"&operationType=process",0,0);
	//ת���ļ�
	$.ajax({
		async : true,
		type : "post",
		url : appUrl
				+ '/file/fileAction!fileChange.jspa',
		data: {eventId:eventId},
		success : function() {
		}
	});
	/*window.open(appUrl + "/wfe/eventAction!toProcessEvent.jspa?event_id="
			+ eventId + "&toDoDetail=" + toDoDetail + "&modelId=" + modelId + "&curStaId=" + curStaId+"&operationType=process", 
			"","left=" + WLeft + ",top=20,toolbar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no","toProcessEvent");*/

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
function closeMaintWindow(){
	$("#maintWindow").window("close");
}

/**
 * ����֯��
 */
function selectOrgTree() {
	initWindow('ѡ����֯', '/orgAction!orgTreePage.jspa','maintWindow',300,450);
}

/**
 * ��֯���ķ���ֵ����
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
function closeOrgTree() {
	$("#maintWindow").window('close');
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

