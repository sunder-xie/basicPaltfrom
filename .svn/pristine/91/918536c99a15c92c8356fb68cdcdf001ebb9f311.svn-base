$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#dict_type_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/dictAction!getCmsTbDictTypeJsonList.jspa?ran='
								+ Math.random(),

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
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
									id : 'dictTypeId',
									title : '序号',
									field : 'dictTypeId',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable : true
								},
								{
									field : 'dictTypeName',
									title : '类型名称',
									align : 'center',
									width : setColumnWidth(0.2),
									sortable : true
								},
								{
									field : 'dictTypeValue',
									title : '值',
									width : setColumnWidth(0.2),
									align : 'center',
									sortable : true
								},
								{
									field : 'remark',
									title : '备注',
									width : setColumnWidth(0.2),
									align : 'center',
									sortable : true
								},
								{
									field : 'lastModify',
									title : '最后修改时间',
									align : 'center',
									width : setColumnWidth(0.1),
									sortable : true,
									formatter : function(v) {
										return utcToDate(v.replace(
												/\/Date\((\d+)\+\d+\)\//gi,
												"new Date($1)"));
									}

								},
								{
									field : 'oper',
									title : '字典条目',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.dictTypeId;
										return '<img style="cursor:pointer" onclick="edit('
												+ id
												+ ')" title="修改资料" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  '
												+ '&nbsp;&nbsp;<img style="cursor:pointer" onclick="search_dict('
												+ id
												+ ')" title="查看字典条目" src='
												+ imgUrl
												+ '/images/actions/action_view.png align="absMiddle"></img>';
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
	var p = $('#dict_type_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function search() {
	var queryParams = $('#dict_type_list').datagrid('options').queryParams;
	queryParams.dictTypeName = encodeURIComponent($("#dictTypeName").val());
	queryParams.dictTypeValue = encodeURIComponent($("#dictTypeValue").val());
	queryParams.remark = encodeURIComponent($("#remark").val());
	$("#dict_type_list").datagrid('load');
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
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}

function add() {
	initMaintWindow('字典类型创建', '/dictAction!toCreateDictType.jspa', 600, 200);
}

function edit(id) {

	initMaintWindow('字典类型修改', '/dictAction!toUpdateDictType.jspa?dictTypeId='
			+ id, 600, 200);

}

function remove() {
	var ids = '';
	var rows = $('#dict_type_list').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids = rows[i].dictTypeId;
	}
	if (ids == '') {
		$.messager.alert('提示', '未选中任何信息！', '提示');
		return;
	} else {
		$.messager.confirm('Confirm', '是否批量刪除?', function(r) {
			if (r) {
		var form = window.document.forms[0];
		form.action = appUrl
				+ '/dictAction/dictAction!DeleteDictType.jspa?dictTypeId='
				+ ids;
		form.target = "hideFrame";
		form.submit();
	}});}

}

function search_dict(id) {
	initMaintWindow('字典条目查询',
			'/dictAction/dictAction!searchCmsTbDict.jspa?dictTypeId=' + id,
			1000, 450);

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
		search();
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
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