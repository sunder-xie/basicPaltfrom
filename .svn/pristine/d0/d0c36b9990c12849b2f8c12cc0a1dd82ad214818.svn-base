$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

function loadGrid() {
	$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/account/accountAction!searchSingleDetail.jspa?planId='+planId,
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [{
									field : 'detail_id',
									title : '明显ID',
									align : 'center',
									hidden : true
								},{
									field : 'cost_type_content',
									title : '费用类型',
									width : setColumnWidth(0.15),
									align : 'center'
								},{
									field : 'cost_date',
									title : '费用日期',
									width : setColumnWidth(0.15),
									align : 'center',
									formatter : function(v) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"));
									}
								},{
									field : 'cost_purpose',
									title : '开支用途',
									width : setColumnWidth(0.2),
									align : 'center'
								},{
									field : 'invoice_num',
									title : '发票张数',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'invoice_amount',
									title : '发票金额',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'cost_memo',
									title : '备注',
									width : setColumnWidth(0.25),
									align : 'center'
								} ] ]
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
	queryParams.cost_date = $("#costDate").datebox("getValue");
	queryParams.cost_purpose = encodeURIComponent($("#costPurpose").val());
	$("#datagrid").datagrid('reload');
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
	date = date + month[str[1]] + "-" + str[2];
	return date;
}
