$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '数据信息',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		pagination : true,
		nowrap : true,
		striped : true,
		pageSize : 10,
		pageNumber : 1,
		rownumbers : true,
		height : height * 0.96
	});

	$('#tableName')
			.combogrid(
					{
						panelWidth : 450,
						panelHight : 500,
						width : 200,
						idField : 'tableName',
						textField : 'tableName',
						pagination : true,
						rownumbers : true,
						collapsible : false,
						striped : true,
						fit : true,
						method : 'post',
						editable : false,
						multiple : false,
						required : true,
						missingMessage : "请选择表名!",
						validType : 'validTableName',
						url : appUrl
								+ '/data/dataManageAction!getTableAuthorizationJsonList.jspa?empId='
								+ $('#empId').val(),
						columns : [ [ {
							field : 'tableName',
							title : '表名',
							width : 400,
							align : 'center'
						} ] ],
						onSelect : function(record) {
							loadDataGrid($('#tableName').combogrid('getValue'));
						},
						toolbar : '#toolbar'
					});

}

function loadDataGrid(tableName) {
	$.messager.progress();
	$.ajax({
		type : "post",
		url : appUrl + "/data/dataManageAction!getDataGridInfo.jspa?time="
				+ new Date(),
		data : {
			page : 1,
			rows : 10,
			tableName : tableName
		},
		success : function(dataGridInfo) {
			$.messager.progress('close');
			var options = $("#datagrid").datagrid("options");
			options.columns = eval(dataGridInfo.gridColumn.replace(/[\r\n]+/g, '\\n'));//换行符转义
			$("#datagrid").datagrid(options);
			$("#datagrid").datagrid('load');
			var data = eval('(' + dataGridInfo.gridData + ')');
			$('#datagrid').datagrid('loadData', data); // 将数据绑定到datagrid
			loadPage(tableName);
		}
	});
}

function getAjaxData(pageNumber, pageSize, tableName) {
	$.messager.progress();
	$.ajax({
		type : "post",
		url : appUrl + "/data/dataManageAction!getData.jspa",
		data : {
			page : pageNumber,
			rows : pageSize,
			tableName : tableName
		},
		success : function(gridData) {
			$.messager.progress('close');
			var data = eval('(' + gridData + ')');
			$('#datagrid').datagrid('loadData', data); // 将数据绑定到datagrid
		}
	});
}

/**
 * 绑定分页栏事件
 */
function loadPage(tableName) {
	$('#datagrid').datagrid('getPager').pagination({
		displayMsg : '当前显示 {from} - {to} 条记录	共{total}条记录',
		onSelectPage : function(pPageIndex, pPageSize) {
			getAjaxData(pPageIndex, pPageSize, tableName);
		}
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#tableName')
			.combogrid(
					{
						url : appUrl
								+ '/data/dataManageAction!getTableAuthorizationJsonList.jspa?empId='
								+ $('#empId').val() + '&searchStr=' + val
					});
	$('#tableName').combogrid("grid").datagrid('reload');
}

function exportDataTemplate() {
	if (!$('#tableName').combobox('isValid')) {
		$.messager.alert('Tips', '请选择表名!', 'error');
		return;
	}
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl + '/data/dataManageAction!exportDataTemplate.jspa';
	form.target = "hideFrame";
	form.submit();
}

function exportData() {
	if (!$('#tableName').combobox('isValid')) {
		$.messager.alert('Tips', '请选择表名!', 'error');
		return;
	}
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl + '/data/dataManageAction!exportDataTemplate.jspa?type=Y';
	form.target = "hideFrame";
	form.submit();
}

function deleteData() {
	if (!$('#tableName').combobox('isValid')) {
		$.messager.alert('Tips', '请选择表名!', 'error');
		return;
	}
	$.messager.confirm('Confirm', '确认清空表中数据?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + '/data/dataManageAction!deleteData.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function toDataImport() {
	if (!$('#tableName').combobox('isValid')) {
		$.messager.alert('Tips', '请选择表名!', 'error');
		return;
	}
	initMaintWindow('数据上载',
			'/data/dataManageAction!toDataImport.jspa?tableName='
					+ $('#tableName').combobox('getValue'), 'maintWindow',
			'550', '200');
}

function openTime() {
	var timer = setInterval(function() {
		$.ajax({
			type : "post",
			url : appUrl + "/data/dataManageAction!checkDownLoadOver.jspa",
			success : function(data) {
				if (data == 'Yes') {
					clearInterval(timer);
					$.messager.progress('close');
				}
			}
		});
	}, 500);
}

function initMaintWindow(title, url, id, WWidth, WHeight) {

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

function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function reLoad() {
	getAjaxData(1, 10, $('#tableName').combogrid('getValue'));
}

$.extend($.fn.validatebox.defaults.rules, {
	validTableName : {
		validator : function(value) {
			var rules = $.fn.validatebox.defaults.rules;
			var result = $.ajax({
				async : false,
				type : 'post',
				url : appUrl + '/data/dataManageAction!validTableName.jspa',
				data : {
					tableName : value
				}
			}).responseText;

			if (result == '1') {
				rules.validTableName.message = '表名不存在!';
				return false;
			} else if (result == '2') {
				rules.validTableName.message = '验证失败!';
				return false;
			}
			return true;
		},
		message : ''
	}
});

function reLoad() {
	getAjaxData(1, 10, $('#tableName').combogrid('getValue'));
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			reLoad();
		});
	}
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