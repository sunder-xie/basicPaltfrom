$(document).ready(function() {
	loadGrid();
});
function loadGrid() {
	$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/positionTypeAction!searchStationTypeList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'positionTypeId',
									title : '岗位编号',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'positionTypeName',
									title : '岗位名称',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								/*{
									field : 'positionTypeName',
									title : '岗位名称',
									width : setColumnWidth(0.3),
									align : 'left',
									formatter : function(value, row, index) {
									return	"<a href='#' onclick=alert();> " +
											value+"</a>";
										return '<img style="cursor:pointer" onclick="alert()"  src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle">' +
												value+
														'</img>  ';
									}
								},*/
								{
									field : 'positionProperty',
									title : '岗位类型',
									width : setColumnWidth(0.2)
								},{
									field : 'companyName',
									title : '公司',
									width : setColumnWidth(0.2)
								},
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.2),
									align : 'center',
									formatter : function(value, row, index) {
										return '<img style="cursor:pointer" onclick="alert()" title="修改资料" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  '
												+ ' <img style="cursor:pointer" onclick="alert()" title="维护角色" src='
												+ imgUrl
												+ '/images/actions/action_view.png align="absMiddle"></img>'
												+ ' <img style="cursor:pointer" onclick="alert()" title="查询职位" src='
												+ imgUrl
												+ '/images/actions/action_view.png align="absMiddle"></img>'
												+ ' <img style="cursor:pointer" onclick="alert()" title="删除" src='
												+ imgUrl
												+ '/images/actions/action_del.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								alert(1);
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
	queryParams.stationId = $("#stationId").val();
	queryParams.stationName =  $("#stationName").val();
	queryParams.orgId = $("#orgId").val();
	$("#datagrid").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}


//创建窗口对象
function initMaintPositionType(title, url) {
	var url = appUrl + url;
	var WWidth = 600;
	var WHeight = 400;
	var $win = $("#maintMenu")
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
						closable : false,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : false
					});

	$win.window('open');

}
function createPositionType() {
	initMaintMenu('菜单创建', '/menuAction!createMenuPrepare.jspa');
}