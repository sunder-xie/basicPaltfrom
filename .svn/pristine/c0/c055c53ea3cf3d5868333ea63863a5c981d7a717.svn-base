$(document).ready(function() {
	loadGrid();
});
function loadGrid() {
	$('#datagrid').datagrid(
			{
				iconCls : 'icon-list',
				title : '查询结果',
				url : appUrl + '/newsAction!searchNewsD.jspa?total_id='
						+ $("#total_id").val(),
				loadMsg : '数据远程载入中,请等待...',
				singleSelect : false,
				pagination : true,
				nowrap : true,
				height : height,
				striped : true,
				columns : [ [
						{
							field : 'delail_title',
							title : '标题',
							width : setColumnWidth(0.4),
							align : 'center',
							formatter : function(value, row, index) {
								if (row.css_flag == 'Y') {
									return "<a href=javascript:oneNews('"
											+ row.delail_id + "','"
											+ $("#totalShow").val()
											+ "');><font color='red'>"
											+ row.delail_title + "</font></a>";
								}
								return "<a href=javascript:oneNews('"
										+ row.delail_id + "','"
										+ $("#totalShow").val() + "');>"
										+ row.delail_title + "</a>";
							}
						}, {
							field : 'detail_date',
							title : '发布时间',
							align : 'center',
							width : setColumnWidth(0.2),
							formatter : function(value, row, index) {
								return utcToDate(value);
							}
						}, {
							field : 'delail_operator',
							title : '发布作者',
							align : 'center',
							width : setColumnWidth(0.2)
						}, {
							field : 'clicks_ratio',
							title : '点击率',
							align : 'center',
							width : setColumnWidth(0.2)
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

function search(value) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.total_Name = encodeURIComponent(value);
	$("#datagrid").datagrid('load');

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
function oneNews(detailId, totalShow) {
	var d1 = new Date().getTime();
	var WWidth = 950;
	var WHeight = 650;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	var url = appUrl + "/newsAction!getOneNews.jspa?detailId=" + detailId
			+ "&totalShow=" + totalShow + "&rtime=" + d1;
	window.open(url, "editRequirePoTotal", "left=" + WLeft + ",top=" + WTop
			+ ",width=" + WWidth + ",height=" + WHeight
			+ ",toolbar=no,menubar=no,scrollbars=yes");
}