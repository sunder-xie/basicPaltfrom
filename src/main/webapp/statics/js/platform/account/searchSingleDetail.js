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
						title : '��ѯ���',
						url : appUrl + '/account/accountAction!searchSingleDetail.jspa?planId='+planId,
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [{
									field : 'detail_id',
									title : '����ID',
									align : 'center',
									hidden : true
								},{
									field : 'cost_type_content',
									title : '��������',
									width : setColumnWidth(0.15),
									align : 'center'
								},{
									field : 'cost_date',
									title : '��������',
									width : setColumnWidth(0.15),
									align : 'center',
									formatter : function(v) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)"));
									}
								},{
									field : 'cost_purpose',
									title : '��֧��;',
									width : setColumnWidth(0.2),
									align : 'center'
								},{
									field : 'invoice_num',
									title : '��Ʊ����',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'invoice_amount',
									title : '��Ʊ���',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'cost_memo',
									title : '��ע',
									width : setColumnWidth(0.25),
									align : 'center'
								} ] ]
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
	return date;
}
