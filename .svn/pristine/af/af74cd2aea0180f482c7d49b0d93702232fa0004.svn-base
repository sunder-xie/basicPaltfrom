$(document).ready(function() {

	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#gridList')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/newsAction!searchNewsDetailList.jspa',
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
									field : 'delail_id',
									title : 'id',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'delail_title',
									title : '标题',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'total_name',
									title : '所属栏目',
									width : setColumnWidth(0.15),
									align : 'center'
								},
								{
									field : 'optionOrgName',
									title : '可见组织',
									width : setColumnWidth(0.15),
									align : 'center',
									formatter : function(value, row, index) {
										if (!value)
											return "所有";
										else
											return value;
									}
								},
								{
									field : 'delail_operator',
									title : '创建人',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'detail_date',
									title : '操作时间',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										return utcToDate(value);
									}

								},
								{
									field : 'clicks_ratio',
									title : '点击率',
									width : setColumnWidth(0.05),
									align : 'center'
								},
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										var rid = row.delail_id;
										return '<img style="cursor:pointer"  onclick=updateStation("'
												+ rid
												+ '") title="修改资料"  src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  ';
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createNewsDetail();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								deleteNewsDetail();
							}
						}, "-" ]
					});

	// 分页控件
	var p = $('#gridList').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}
function search() {
	var queryParams = $('#gridList').datagrid('options').queryParams;

	queryParams.delail_title = encodeURIComponent($("#delail_title").val());
	queryParams.total_Name = encodeURIComponent($("#total_Name").val());
	// queryParams.orgId = $('#orgId').combobox('getValue');
	$("#gridList").datagrid('reload');
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
function deleteNewsDetail() {
	$.messager.confirm('Confirm', '是否批量刪除菜单?', function(r) {
		if (r) {
			var rows = $('#gridList').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  no selected rows!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].delail_id);
			}
			$("#delail_ids").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl + "/newsAction!deleteNewsDetail.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});

}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

// 创建窗口对象
function initNewsDetail(title, url) {
	var url = appUrl + url;
	var WWidth = 850;
	var WHeight = 500;
	var $win = $("#newsDetail")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : false,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});
	$win.window('open');
}

/*
 * //创建窗口对象 function initMaintStationUser(title, url) { var url = appUrl + url;
 * var WWidth = 600; var WHeight = 400; var $win = $("#maintStation1") .window( {
 * title : title, width : WWidth, height : WHeight, content : '<iframe
 * frameborder="no" width="100%" height="100%" src=' + url + '/>', shadow :
 * true, modal : true, closed : true,/// closable : true,// minimizable :
 * false,// maximizable : true,// collapsible : false,// draggable : false });
 * 
 * $win.window('open'); }
 */
function closeNewsDetail() {
	// 关闭创建页面
	$("#newsDetail").window('close');
	// $("#maintStation1").window('close');

}
function createNewsDetail() {
	initNewsDetail('创建栏目明细', '/newsAction!newsDetail_add.jspa');
}
/**
 * 修改栏目明细
 * @param id
 */
function updateStation(id) {
	initNewsDetail('修改栏目明细', '/newsAction!updateNewsDetailPre.jspa?detailId='
			+ id);
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