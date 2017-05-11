$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						striped : true,
						url : appUrl
								+ '/wfe/authorizeEventAction!getEventReaderList.jspa?eventId='+ $("#eventId").val(),
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'eventLookUpId',
									title : 'lookupId',
									hidden : true
								},
								{
									field : 'orgName',
									title : '查阅人所属组织',
									width : setColumnWidth(0.25),
									align : 'center'
								},
								{
									field : 'userName',
									title : '查阅人',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'creator',
									title : '授权人',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'createDate',
									title : '授权时间',
									width : setColumnWidth(0.28),
									align : 'center',
									formatter : function(v) {
										return utcToDate(v.replace(
												/\/Date\((\d+)\+\d+\)\//gi,
												"new Date($1)"));
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createEventReader();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								deleteEventReader();
							}
						}, "-" ]
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
	queryParams.userName = encodeURIComponent($("#userName").val());
	$("#datagrid").datagrid('load');
}

function createEventReader() {
	var eventId = $("#eventId").val();
	initWindow('授权',
			'/wfe/authorizeEventAction!toAuthorizeAddMain.jspa?eventId='
					+ eventId, 'authorizeAddMain', 660, 430);
}

function deleteEventReader() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!');
		return;
	}
	$.messager.confirm('Confirm', '确认批量删除事务查阅人？', function(r) {
		if (r) {
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].eventLookUpId);
			}
			$("#ids").val(ids);

			var form = window.document.forms[0];
			form.action = appUrl
					+ "/wfe/authorizeEventAction!deleteAuthorization.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});

}

// 创建窗口对象
function initWindow(title, url, id, WWidth, WHeight) {

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

// 关闭创建页面
function closeWindow() {
	$("#authorizeAddMain").window('close');
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
