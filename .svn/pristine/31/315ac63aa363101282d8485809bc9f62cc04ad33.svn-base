$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$("#status").combobox({
		valueField : 'value',
		textField : 'text',
		data : [
			{'value' : '0', 'text' : '未处理'},
			{'value' : '1', 'text' : '处理中'},
			{'value' : '2', 'text' : '已完成'}
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
					title : '活动明细',
					url : appUrl + '/account/accountAction!getReimburDetailJsonList.jspa',
					loadMsg : '数据远程载入中,请等待...',
					singleSelect : true,
					pagination : true,
					nowrap : true,
					rownumbers:true,
					striped : true,
					height : height,
					columns : [ [
							{
								field : 'transaction_id',
								title : '事务ID',
								width : setColumnWidth(0.08),
								align : 'center'
							},
							{
								field : 'payee',
								title : '收款人',
								width : setColumnWidth(0.07),
								align : 'center'
							},
							{
								field : 'project',
								title : '项目',
								width : setColumnWidth(0.08),
								align : 'center'
							},
							{
								field : 'project_manager',
								title : '项目经理',
								width : setColumnWidth(0.07),
								align : 'center'
							},
							{
								field : 'cost_type_content',
								title : '费用类型',
								width : setColumnWidth(0.07),
								align : 'center'
							},
							{
								field : 'cost_date',
								title : '费用日期',
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
								title : '开支用途',
								width : setColumnWidth(0.1),
								align : 'center'
							},
							{
								field : 'invoice_num',
								title : '发票张数',
								width : setColumnWidth(0.07),
								align : 'center'
							},
							{
								field : 'invoice_amount',
								title : '发票金额',
								width : setColumnWidth(0.07),
								align : 'center'
							},
							{
								field : 'audit_money',
								title : '实际金额',
								width : setColumnWidth(0.07),
								align : 'center'
							
							},
							{
								field : 'cost_memo',
								title : '备注',
								width : setColumnWidth(0.08),
								align : 'center'
							
							},
							{
								field : 'financial_operate_date',
								title : '财务处理时间',
								width : setColumnWidth(0.11),
								align : 'center',
								formatter : function(v) {
									if (v!=null && v!=undefined) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"), 'timestamp');
									}
								}
							} ] ],
							toolbar : [ "-", {
								text : '导出Excel',
								iconCls : 'icon-download',
								handler : function() {
									exportExcel();
								}
							}, "-"]
				});

// 分页控件
var p = $('#datagrid').datagrid('getPager');
$(p).pagination({
	pageSize : 10,
	pageList : [ 10, 20, 30 ],
	beforePageText : '第',
	afterPageText : '页    共 {pages} 页',
	displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
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
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	if (type == "timestamp") {
		date = date + " " + str[3];
	}
	return date;
}



