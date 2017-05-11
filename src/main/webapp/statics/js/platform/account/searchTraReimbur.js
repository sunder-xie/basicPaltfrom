$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	
	$('#playMoneyFlag').combobox({
		valueField : 'flagValue',
		textField : 'flagText',
		data : [
			{'flagValue' : 'Y', 'flagText' : '已打款'},
			{'flagValue' : 'N', 'flagText' : '未打款'}
		],
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto',
		onLoadSuccess : function() {
			if(flag == 'Y'){
				$('#playMoneyFlag').combobox("setValue", 'N');
			}		
		}
	});
	
	$("#status").combobox({
		valueField : 'flagValue',
		textField : 'flagText',
		data : [
			{'flagValue' : '0', 'flagText' : '未处理'},
			{'flagValue' : '1', 'flagText' : '处理中'},
			{'flagValue' : '2', 'flagText' : '已完成'}
		],
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto',
		onLoadSuccess : function() {
			if(flag == 'Y'){
				$('#status').combobox("setValue", '2');
			}		
		}
		
	});
	
	var status = $('#status').combobox('getValue');
	var playMoneyFlag = $('#playMoneyFlag').combobox('getValue');
	loadGrid(status, playMoneyFlag);
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
		search();
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

function loadGrid(status, playMoneyFlag) {
	if(flag == 'Y'){
		$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/account/accountAction!searchTraReimbur.jspa',
						queryParams:{  
        					'status' : status,  
        					'playMoneyFlag' : playMoneyFlag  
   						},  
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height*0.95,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},{
									field : 'plan_id',
									title : '报销单号',
									width : setColumnWidth(0.06),
									align : 'center'
								},{
									field : 'transaction_id',
									title : '事务ID',
									width : setColumnWidth(0.06),
									align : 'center'
								},{
									field : 'title',
									title : '事务标题',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'pay_ee',
									title : '收款人',
									width : setColumnWidth(0.06),
									align : 'center'
								},{
									field : 'cost_org_name',
									title : '费用承担部门',
									width : setColumnWidth(0.08),
									align : 'center'
								},{
									field : 'cost_center_name',
									title : '项目',
									width : setColumnWidth(0.08),
									align : 'center'
								},{
									field : 'audit_money',
									title : '总金额',
									width : setColumnWidth(0.05),
									align : 'center'
								},{
									field : 'pay_type',
									title : '支付方式',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(v){
										if(v == '1'){
											return "现金";
										}else if(v == '2'){
											return "银行";
										}else if(v == '3'){
											return "其他";
										}
									}
								},{
									field : 'has_play_money',
									title : '是否打款',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(v){
										if(v == 'Y'){
											return "已打款";
										}else if(v == 'N'){
											return "未打款";
										}
									}
								},{
									field : 'status',
									title : '事务状态',
									width : setColumnWidth(0.05),
									align : 'center',
									formatter : function(value) {
										if (value == 0) {
											return "未处理";
										}
										if (value == 1) {
											return "处理中";
										}
										if (value == 2) {
											return "已完成";
										}
										if (value == 3) {
											return "已作废";
										}
										if (value == 4) {
											return "已取消";
										}
									}
								},{
									field : 'create_date',
									title : '提报时间',
									width : setColumnWidth(0.11),
									align : 'center',
									formatter : function(v) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"));
									}
								},{
									field : 'financial_doc_num',
									title : '会计凭证号',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : {
										type : 'text',
										options : {
											valueField : 'financial_doc_num',
											textField : 'financial_doc_num',
											required : true
										}
									}
								},{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.11),
									align : 'center',
									formatter : function(value, row, index) {
										var rid = row.plan_id;
										var returnStr;
										var html = '<img style="cursor:pointer"  onclick=searchSingleDetail("'
													+ rid
													+ '") title="查看明细" src='
													+ imgUrl
													+ '/images/actions/action_view.png align="absMiddle"></img>'
													+ '&nbsp;&nbsp;<img style="cursor:pointer"  onclick=printTraReimbur("'
													+ rid
													+ '","'
													+ row.transaction_id 
													+ '") title="打印报销单" src='
													+ imgUrl
													+ '/images/actions/action_print.png align="absMiddle"></img>';
										
										if (row.editing) {
											var s = '<a href="#" onclick="save(this)">保存</a> ';
											var c = '<a href="#" onclick="cancel(this)">取消</a>';
											returnStr = s + c + '&nbsp;&nbsp;' + html;
										} else {
											var e = '<img style="cursor:pointer"  title="修改会计凭证号" onclick="editrow(this)" src='
													+ imgUrl
													+ '/images/actions/action_edit.png align="absMiddle"></img> ';
											returnStr = e + '&nbsp;&nbsp;' + html;
										}
										return returnStr;
									}
								} ] ],
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
						onLoadSuccess:function(data){
							// 初始化置灰
           					selectedFile($(this),data.rows); 
           					
           					$(".datagrid-header-check input").unbind('click').bind('click',function(e){
								var checkArr = new Array();
								for(var i=0; i<data.rows.length; i++) {
									if(2 == data.rows[i].status) {
										checkArr.push(i);
									}
								}
								for(var i=0; i<checkArr.length; i++) {
									if($(".datagrid-header-check input").attr('checked')=='checked'){
										$('#datagrid').datagrid('selectRow', checkArr[i]);
									}else{
										$('#datagrid').datagrid('unselectRow', checkArr[i]);
									}	
								}
							});
						},
						onCheck : function(rowIndex,rowData){
							if(rowData['status'] != 2){
								$('#datagrid').datagrid('unselectRow', rowIndex);
								$('#datagrid').datagrid('unCheckRow', rowIndex);
							}
						},
						toolbar : [ "-", {
							text : '打款',
							iconCls : 'icon-ok',
							handler : function() {
								playMoney();
							}
						}, "-"]
					});
	}else{
		$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/account/accountAction!searchTraReimbur.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height*0.9,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},{
									field : 'plan_id',
									title : '报销单号',
									width : setColumnWidth(0.06),
									align : 'center'
								},{
									field : 'transaction_id',
									title : '事务ID',
									width : setColumnWidth(0.06),
									align : 'center'
								},{
									field : 'title',
									title : '事务标题',
									width : setColumnWidth(0.16),
									align : 'center'
								},{
									field : 'pay_ee',
									title : '收款人',
									width : setColumnWidth(0.06),
									align : 'center'
								},{
									field : 'cost_org_name',
									title : '费用承担部门',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'cost_center_name',
									title : '项目',
									width : setColumnWidth(0.08),
									align : 'center'
								},{
									field : 'audit_money',
									title : '总金额',
									width : setColumnWidth(0.06),
									align : 'center'
								},{
									field : 'pay_type',
									title : '支付方式',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(v){
										if(v == '1'){
											return "现金";
										}else if(v == '2'){
											return "银行";
										}else if(v == '3'){
											return "其他";
										}
									}
								},{
									field : 'has_play_money',
									title : '是否打款',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(v){
										if(v == 'Y'){
											return "已打款";
										}else if(v == 'N'){
											return "未打款";
										}
									}
								},{
									field : 'status',
									title : '事务状态',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value) {
										if (value == 0) {
											return "未处理";
										}
										if (value == 1) {
											return "处理中";
										}
										if (value == 2) {
											return "已完成";
										}
										if (value == 3) {
											return "已作废";
										}
										if (value == 4) {
											return "已取消";
										}
									}
								},{
									field : 'create_date',
									title : '提报时间',
									width : setColumnWidth(0.12),
									align : 'center',
									formatter : function(v) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"));
									}
								},{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										var rid = row.plan_id;
										var html = '<img style="cursor:pointer"  onclick=searchSingleDetail("'
													+ rid
													+ '") title="查看明细" src='
													+ imgUrl
													+ '/images/actions/action_view.png align="absMiddle"></img>'
													+ '&nbsp;&nbsp;<img style="cursor:pointer"  onclick=printTraReimbur("'
													+ rid
													+ '","'
													+ row.transaction_id 
													+ '") title="打印报销单" src='
													+ imgUrl
													+ '/images/actions/action_print.png align="absMiddle"></img>';
										return html;
									}
								} ] ]
					});
	}
	
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

/**
 * 初始化置灰的选项
 * */
function selectedFile(grid, rows){
	for(var j=0;j<rows.length;j++){
		if(2 != rows[j]['status']){
			$(".datagrid-row[datagrid-row-index="+j+"] input[type='checkbox']")[0].disabled=true;
		}
	}
}

function search() {
	$("#datagrid").datagrid('reload',
		{'status' : $('#status').combobox('getValue'),
		 'playMoneyFlag' : $('#playMoneyFlag').combobox('getValue'),
		 'eventId' : encodeURIComponent($("#eventId").val()),
		 'title' : encodeURIComponent($("#title").val()),
		 'userName' : encodeURIComponent($("#userName").val()),
		 'startDate' : $("#startDate").datebox('getValue'),
		 'endDate' : $("#endDate").datebox('getValue')
		}
	 );
}

function toImport(){
	initMainFrame('会计凭证号导入', '/account/accountAction!toImportFinancialDocNum.jspa', 500, 200);
}


function searchSingleDetail(rid){
	initMainFrame('报销明显查询', '/account/accountAction!searchSingleDetailPrepare.jspa?planId='+rid, 780, 400);
}

function printTraReimbur(rid, transaction_id){
	var WWidth = 950;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + '/account/accountAction!printTraReimbur.jspa?planId='+rid+'&transaction_id='+transaction_id, "printExpenseForm",
			"left=" + WLeft + ",top=20" + ",width=" + WWidth + ",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}


function playMoney(rids){
	$.messager.confirm('Confirm', '是否批量打款?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '请选择数据!', 'warning');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].plan_id);
			}
			$("#planId").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl + "/account/accountAction!playMoney.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function downLoadExcel(){
	var form = window.document.forms[0];
	form.action = appUrl + "/account/accountAction!downLoadExcel.jspa";
	form.target = "hideFrame";
	form.submit();
}

function closeMainFrame(){
	$("#maintFrame").window('close');
}

//创建窗口对象
function initMainFrame(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintFrame")
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
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}

function save(target) {
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rows = $('#datagrid').propertygrid('getChanges');
	if (rows.length == 0) {
		return;
	}
	var	transaction_id = rows[0].transaction_id;
	var	financial_doc_num = rows[0].financial_doc_num;
	var pattern=  /^\d{10}$/;
	if (!pattern.test(financial_doc_num)) {
		$.messager.alert('Tips', '会计凭证号必须为10位阿拉伯数字', 'warning');
		search();
		return;
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + "/account/accountAction!updateFinancialDocNum.jspa?transaction_id=" + transaction_id + "&financial_doc_num=" + financial_doc_num;
		form.target = "hideFrame";
		form.submit();
	}
}
function cancel(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}
