$(document).ready(function() {
			loadGrid();
			$('#hideFrame').bind('load', promgtMsg);
		});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '查询结果',
		url : appUrl + '/menuAction!ssoSearch.jspa',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		pagination : true,
		nowrap : true,
		height : height,
		striped : true,
		columns : [[{
					field : 'name',
					title : '系统名称',
					width : setColumnWidth(0.1),
					align : 'center',
					sortable : true
				}, {
					field : 'redirectUrl',
					title : '系统跳转地址',
					width : setColumnWidth(0.45),
					align : 'center',
					sortable : true,
					formatter : function(value, row, index) {
						return "<strike>" + value + "</strike>"
					}
				}, {
					field : 'validateType',
					title : '验证方式',
					width : setColumnWidth(0.1),
					align : 'center',
					sortable : true
				}, {
					field : 'ssoUser',
					title : '账号参数',
					width : setColumnWidth(0.1),
					align : 'center',
					sortable : true
				}, {
					field : 'ssoPwd',
					title : '密码参数',
					width : setColumnWidth(0.1),
					align : 'center',
					sortable : true
				}, {
					field : 'operate',
					title : '操作',
					width : setColumnWidth(0.1),
					align : 'center',
					formatter : function(value, row, index) {
						var e = '<img style="cursor:pointer"  title="修改参数" onclick="createSSO('
								+ row.id
								+ ')" src='
								+ imgUrl
								+ '/images/actions/action_edit.png align="absMiddle"></img> ';
						return e;
					}
				}]]
	});

}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		search();
		$.messager.alert('Tips', successResult, 'info');

	}
}
// 创建窗口对象
function initMaintMenu(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintSso").window({
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
		maximizable : true,
		collapsible : true,
		draggable : true
	});

	$win.window('open');

}

function createSSO(id) {
	initMaintMenu('单点登录创建', '/menuAction!ssoCreatePre.jspa?id=' + id, 600, 220);
}

// 关闭创建页面
function closeMaintSso() {
	$("#maintSso").window('close');
}
