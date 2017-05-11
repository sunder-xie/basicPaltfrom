$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#dict_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/dictAction!getCmsTbDictJsonList.jspa?dictTypeId='
								+ $('#dictTypeId').val() + '&ran='
								+ Math.random(),

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									title : '序号',
									field : 'itemId',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'itemName',
									title : '名称',
									align : 'center',
									width : 100,
									sortable : true
								},
								{
									field : 'itemValue',
									title : '值',
									width : 80,
									align : 'center',
									sortable : true
								},

								{
									field : 'dictTypeId',
									title : '类型ID',
									width : 80,
									hidden : true,
									align : 'center',
									sortable : true
								},

								{
									field : 'parentItemId',
									title : '父级',
									width : 80,
									hidden : true,
									align : 'center',
									sortable : true
								},

								{
									field : 'itemDescription',
									title : '描述',
									width : 180,
									align : 'center',
									sortable : true
								},
								{
									field : 'remark',
									title : '备注',
									width : 150,
									align : 'center',
									sortable : true
								},
								{
									field : 'lastModify',
									title : '最后修改时间',
									align : 'center',
									width : 140,
									sortable : true,
									formatter : function(v) {
										return utcToDate(v.replace(
												/\/Date\((\d+)\+\d+\)\//gi,
												"new Date($1)"));
									}

								},
								{
									field : 'oper',
									title : '操作',
									width : 140,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.itemId;
										return '<img style="cursor:pointer" onclick="edit('
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
								add();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove ',
							handler : function() {
								remove();
							}
						}, "-" ]
					});
	// 分页控件
	var p = $('#dict_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

// 创建窗口对象
function initMaintWindow(title, url) {
	var url = appUrl + url;
	var WWidth = 600;
	var WHeight = 300;
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
						closed : true,// /
						closable : true,//
						minimizable : false,//
						maximizable : false,//
						collapsible : false,//
						draggable : true
					//
					});

	$win.window('open');
}

function add() {
	initMaintWindow('字典创建', '/dictAction!toCreateDict.jspa?dictTypeId='
			+ $('#dictTypeId').val());
}

function edit(id) {
	initMaintWindow('字典修改', '/dictAction!toUpdateDict.jspa?itemId=' + id);

}
function search() {
	$("#dict_list").datagrid('load');
}

function remove() {
	var ids = [];
	$.messager.confirm('Confirm', '是否批量刪除?', function(r) {
		if (r) {
			var rows = $('#dict_list').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('提示', '  未选中任何信息!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].itemId);
			}

			var form = window.document.forms[0];
			form.action = appUrl
					+ '/dictAction/dictAction!DeleteDict.jspa?ids=' + ids;
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
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		$("#dict_list").datagrid('load');
	}
}

/*
 * document.onkeydown = function(e) { var theEvent = e || window.event; var code =
 * theEvent.keyCode || theEvent.which || theEvent.charCode; if (code == 13) {
 * search(); return false; } return true; };
 */

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