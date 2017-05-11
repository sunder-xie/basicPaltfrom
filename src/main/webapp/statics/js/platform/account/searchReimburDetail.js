$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$("#status").combobox({
		valueField : 'value',
		textField : 'text',
		data : [
			{'value' : '0', 'text' : 'δ����'},
			{'value' : '1', 'text' : '������'},
			{'value' : '2', 'text' : '�����'}
		],
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto'
		
	});
	$('#datagrid')
			.datagrid(
				{
					iconCls : 'icon-list',
					title : '���ϸ',
					url : appUrl + '/account/accountAction!getReimburDetailJsonList.jspa',
					loadMsg : '����Զ��������,��ȴ�...',
					singleSelect : true,
					pagination : true,
					nowrap : true,
					rownumbers:true,
					striped : true,
					height : height,
					columns : [ [
							{
								field : 'transaction_id',
								title : '����ID',
								width : setColumnWidth(0.08),
								align : 'center'
							},
							{
								field : 'payee',
								title : '�տ���',
								width : setColumnWidth(0.07),
								align : 'center'
							},
							{
								field : 'project',
								title : '��Ŀ',
								width : setColumnWidth(0.08),
								align : 'center'
							},
							{
								field : 'project_manager',
								title : '��Ŀ����',
								width : setColumnWidth(0.07),
								align : 'center'
							},
							{
								field : 'cost_type_content',
								title : '��������',
								width : setColumnWidth(0.07),
								align : 'center'
							},
							{
								field : 'cost_date',
								title : '��������',
								width : setColumnWidth(0.08),
								align : 'center',
								formatter : function(v) {
									if (v!=null && v!=undefined) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"), '');
									}
								}
							},
							{
								field : 'cost_purpose',
								title : '��֧��;',
								width : setColumnWidth(0.1),
								align : 'center'
							},
							{
								field : 'invoice_num',
								title : '��Ʊ����',
								width : setColumnWidth(0.07),
								align : 'center'
							},
							{
								field : 'invoice_amount',
								title : '��Ʊ���',
								width : setColumnWidth(0.07),
								align : 'center'
							},
							{
								field : 'audit_money',
								title : 'ʵ�ʽ��',
								width : setColumnWidth(0.07),
								align : 'center'
							
							},
							{
								field : 'cost_memo',
								title : '��ע',
								width : setColumnWidth(0.08),
								align : 'center'
							
							},
							{
								field : 'financial_operate_date',
								title : '������ʱ��',
								width : setColumnWidth(0.11),
								align : 'center',
								formatter : function(v) {
									if (v!=null && v!=undefined) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"), 'timestamp');
									}
								}
							} ] ],
							toolbar : [ "-", {
								text : '����Excel',
								iconCls : 'icon-download',
								handler : function() {
									exportExcel();
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.transactionId = $("#transactionId").val();
	queryParams.payee = encodeURIComponent($("#payee").val());
	queryParams.project = encodeURIComponent($("#project").val());
	queryParams.costTypeContent = encodeURIComponent($("#costTypeContent").val());
	queryParams.status = $("#status").combobox('getValue');
	queryParams.startDate = $("#startDate").datebox('getValue');
	queryParams.endDate = $("#endDate").datebox('getValue');
	$("#datagrid").datagrid('load');
}

function exportExcel(){
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl + '/account/accountAction!exportReimberDetailList.jspa';
	form.target = "hideFrame";
	form.submit();
}

function openTime(){
	var timer = setInterval(function(){
		$.ajax({
		type : "post",
		url : appUrl + "/account/accountAction!checkDownLoadOver.jspa",
		success : function(data) {
				if(data == 'Yes'){
					clearInterval(timer);
					$.messager.progress('close');
				}
			}
		});
	}, 500);
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	}
}

function utcToDate(utcCurrTime, type) {
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
	date = date + month[str[1]] + "-" + str[2];
	if (type == "timestamp") {
		date = date + " " + str[3];
	}
	return date;
}



