$(document).ready(function() {
			$('#hideFrame').bind('load', promgtMsg);
			loadGrid();
			loadGrid1();
			loadGrid2();

			$('#beginDate').datebox({
						onSelect : function(d) {
							$('#beginDate').val(utcToDate(d));
						}
					});
			$('#endDate').datebox({
						onSelect : function(d) {
							$('#endDate').val(utcToDate(d));
						}
					});

		});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		var type = $("#deleteType").val();
		if (type == 'model') {
			search_1();
		} else if (type == 'att') {
			search_2();
		}
	}
}

function loadGrid() {
	$('#datagrid_1').datagrid({
		iconCls : 'icon-list',
		title : '查询结果',
		url : appUrl + '/reportAction!getData.jspa?rptId=2',
		// queryParams: {
		// endDate: $("#endDate").val(),
		// beginDate:$("#beginDate").val(),
		// custId : $("#id").combobox("getValue")
		// },
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : true,
		pagination : true,
		nowrap : true,
		remoteSort : true,
		striped : true,
		height : height,
		columns : [[{
					field : 'KUNNR',
					title : '客户编号',
					width : setColumnWidth(0.25),
					align : 'center',
					sortable : true
				}, {
					field : 'CUST_NAME',
					title : '客户名称',
					width : setColumnWidth(0.25),
					align : 'center'

				}, {
					field : 'ACCOUNTS_MONEY',
					title : '应收人民币',
					width : setColumnWidth(0.25),
					align : 'center'
				}, {
					field : 'PAID_AMOUNT',
					title : '实收人民币',
					width : setColumnWidth(0.25),
					align : 'center'
				}]]
	});

	// 分页控件
	var p = $('#datagrid_1').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
				beforePageText : '第',
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			});
	
}
function loadGrid2() {
	$('#id').combogrid({
				panelWidth : 400,
				panelHight : 600,
				idField : 'kunnr',
				textField : 'name1',
				pagination : true,// 是否分页
				// rownumbers : true,// 序号
				collapsible : false,// 是否可折叠的
				// fit : true,// 自动大小
				method : 'post',
				// multiple : true,
				url : appUrl + '/goal/goalAction!getKunner.jspa',
				columns : [[{
							field : 'ck',
							checkbox : true,
							hidden : true
						}, {
							field : 'id',
							title : '客户ID',
							hidden : true,
							width : 60
						}, {
							field : 'kunnr',
							title : '客户编号',
							align : 'center',
							width : 120
						}, {
							field : 'name1',
							title : '客户名称',
							align : 'center',
							width : 100
						}, {
							field : 'mobNumber',
							title : '手机',
							align : 'center',
							width : 150
						}]],
				toolbar : '#toolbar2'
			});

}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function search_1() {
	var queryParams = $('#datagrid_1').datagrid('options').queryParams;
	queryParams.custId = encodeURIComponent($("#id").combobox("getValue"));
	$("#datagrid_1").datagrid('reload');
}

function clearValue_1() {
	$('#beginDate').val("");
	$('#endDate').val("");
	$("#beginDate").datebox("setValue", '');
	$("#endDate").datebox("setValue", '');
	$("#id").combobox("setValue", '');
}

function search_2() {
	var queryParams = $('#datagrid_2').datagrid('options').queryParams;

	queryParams.recBillId = $("#recBillId").val();
	// queryParams.instock_provide_id = $("#instock_provide_id").val();
	// queryParams.instockdetBatch = $("#instockdetBatch").val();
	// queryParams.matnr =encodeURIComponent( $("#wid").combobox("getValue"));
	$("#datagrid_2").datagrid('reload');
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#id').combogrid({
				url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value='
						+ val
			});
	$('#id').combogrid("grid").datagrid('reload');

}

function searcher1(val) {
	val = encodeURIComponent(val);
	$('#wid').combogrid({
				url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value='
						+ val
			});
	$('#wid').combogrid("grid").datagrid('reload');

}


function closeMaintModelAtt() {
	$("#maintModelAtt").window('close');
}

// 创建窗口对象
function initMaintModelAtt(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintModelAtt").window({
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

// 创建窗口对象
function initMaintUi(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintModelAtt").window({
		title : title,
		width : WWidth,
		height : WHeight,
		content : '<iframe frameborder="no" width="100%" height="100%" src='
				+ url + '/>',
		shadow : true,
		modal : true,
		closed : true,
		closable : true,
		fit : true,
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
	date = date + month[str[1]] + "-" + str[2];
	return date;
}
