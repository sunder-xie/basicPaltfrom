$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/wfe/eventAction!getCaJsonList.jspa?ran ='+ Math.random(),
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
									width : setColumnWidth(0.10),
									align : 'center'
								},
								{
									field : 'eventTitle',
									title : '�������',
									width : setColumnWidth(0.2),
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
									align : 'center'/*,
									formatter : function(value) {
										return utcToDate(value);
									}*/
								},
								{
									field : 'isRead',
									title : '�Ƿ�����',
									width : setColumnWidth(0.10),
									align : 'center',
									formatter : function(value) {
										if(value==1){
											return "�Ѷ�";
										}else{
											return "δ��";
										}
									}
								},
								{
								field : 'operation',
								title : '����',
								width : setColumnWidth(0.19),
								align : 'center',
								formatter : function(value, row, index) {
									var strReturn = '';
									if(row.isRead=='1'){
										strReturn = '<a href= javascript:setIsread("'
											+row.eventId
											+'","'
											+row.isRead
											+'")>��������<a/>&nbsp;&nbsp;<a href= javascript:searchEventDetail("'
											+ row.eventId
											+ '")>�鿴������� </a>';
									}else{
										strReturn = '<a href= javascript:setIsread("'
											+row.eventId
											+'","'
											+row.isRead
											+'")>��������<a/>&nbsp;&nbsp;<a href= javascript:searchEventDetail("'
											+ row.eventId
											+ '")>�鿴������� </a>';
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
	search();
}

function setIsread(eventid,isRead){
	if(isRead=='1'){
		isRead="";
	}else{
		isRead="1";
	}
	$.ajax({
		type : "post",
		url : appUrl + "/wfe/eventAction!setIsRead.jspa?eventId="+eventid+"&isRead="+isRead,		
		success:function(data){
			$.messager.alert('��ʾ','���óɹ�!','info');
			
		}	,
			error: function (){
			  	$.messager.alert('Tips', "����ʧ��", 'error');
		  	}
			});
	$('#datagrid').datagrid('reload');
}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.isRead = $("#isRead").val();
	$("#datagrid").datagrid('load');
}

function closeMaintWindow(){
	$("#maintWindow").window('close');
}

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
	initMaintEvent(true, '700','400', '������Ϣ�鿴', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId ,0 , 0);
}

function promgtMsg() {
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
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
}

function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] ;
	return date;
}
