$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/boform/boformAction!getReportParameterJsonList.jspa?ran='
								+ Math.random(),

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						// rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									title : "参数ID",
									field : 'pid',
									width : 80,
									hidden : false,
									sortable : true,
									align : 'center'
								},
								{
									title : "报表编号",
									field : 'bid',
									width : 110,
									sortable : true,
									align : 'center'
								},
								{
									title : "表名",
									field : 'tableName',
									width : 120,
									sortable : false,
									align : 'left'
								},
								{
									title : "ID字段",
									field : 'zdid',
									width : 60,
									sortable : false,
									align : 'left'
								},
								{
									title : "描述字段",
									field : 'zdtxt',
									width : 60,
									sortable : false,
									align : 'left'
								},
								{
									title : "查询Lable",
									field : 'memo',
									width : 100,
									sortable : false,
									align : 'left'
								},
								{
									title : "参数可选数量",
									field : 'amount',
									width : 110,
									sortable : false,
									align : 'left',
									formatter : function(value, row, rec) {
										var v = row.amount;
										if (v == 0) {
											return '手动填';
										}
										if (v == 1) {
											return '单值';
										}
										if (v == 2) {
											return '多值';
										}
										if (v == 3) {
											return '选日期';
										}
										if (v == 4) {
											return 'OA组织树';
										}
										if (v == 5) {
											return '年';
										}
										if (v == 6) {
											return '月';
										}
										if (v == 7) {
											return '水站';
										}if (v == 8) {
											return 'SAP组织树';
										}
										if (v == 20) {
											return '多值一页显示n条记录';
										}
									}
								},
								{
									title : "有无描述",
									field : 'txt',
									width : 60,
									sortable : false,
									align : 'center',
									formatter : function(value, row, rec) {
										var v = row.txt;
										if (v == 0) {
											return '无';
										}
										if (v == 1) {
											return '有';
										}
									}
								},
								{
									title : "是否必填",
									field : 'che',
									width : 60,
									sortable : false,
									align : 'center',
									formatter : function(value, row, rec) {
										var v = row.che;
										if (v == 0) {
											return '不是';
										}
										if (v == 1) {
											return '必填';
										}
									}
								},
								{
									title : "查询条件",
									field : 'd',
									width : 70,
									sortable : false,
									align : 'left'
								},
								{
									title : "别名",
									field : 'nickname',
									width : 90,
									sortable : false,
									align : 'left'
								},
								{
									title : "校验方式",
									field : 'checkWay',
									width : 90,
									sortable : false,
									align : 'left',
									formatter : function(value, row, rec) {
										var v = row.checkWay;
										if (v == '0') {
											return '手动填写的校验';
										}
										if (v == '1') {
											return '英文';
										}
										if (v == '2') {
											return '英文数字';
										}
										if (v == '3') {
											return '金额';
										}
										if (v == '4') {
											return '数字';
										}
									}
								},
								{
									field : 'oper',
									title : '操作',
									width : 100,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.pid;
										return '<img style="cursor:pointer" onclick="updateReportParameter('
												+ id
												+ ')" title="修改资料" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createReportParameter();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove ',
							handler : function() {
								deleteReportParameter();
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

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.bid = encodeURIComponent($("#bid").val());
	$("#datagrid").datagrid('load');
}

// 创建窗口对象
function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						draggable : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');
}

/**
 * 创建
 */
function createReportParameter() {
	initMaintWindow('报表参数配置',
			'/boformAction!createReportParameterPrepare.jspa', 1120, 400);
}
/**
 * 修改
 */
function updateReportParameter(pid) {
	initMaintWindow('报表参数配置修改',
			'/boformAction!updateReportParameterPrepare.jspa?pid=' + pid, 1120,
			400);

}
/**
 * 删除
 */
function deleteReportParameter() {
	$.messager.confirm('Confirm', '是否批量刪除?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('提示', '  未选中任何信息!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push({
					'bid' : rows[i].bid,
					'pid' : rows[i].pid
				});
			}
			$("#reportParameterList").val($.toJSON(ids));
			var form = window.document.forms[0];
			form.action = appUrl + '/boformAction!deleteReportParameter.jspa';
			form.target = "hideFrame";
			form.submit();

		}
	});

}
// 关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		search();
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
};