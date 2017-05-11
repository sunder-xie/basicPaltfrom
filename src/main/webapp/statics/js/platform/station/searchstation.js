$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/station!searchStation.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						remoteSort : true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'stationId',
									title : '岗位编号',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								/*
								 * { field : 'stationName', title : '岗位名称',
								 * width : setColumnWidth(0.3), align : 'center' },
								 */
								{
									field : 'stationName',
									title : '岗位名称',
									width : setColumnWidth(0.2),
									align : 'left',
									formatter : function(value, row, index) {
										/* var id=row.stationName; */
										var cc = encodeURIComponent(row.stationName);
										return '<a href="#" onclick=javascript:getByStation("'
												+ cc
												+ '","'
												+ encodeURIComponent(row.stationId)
												+ '") > ' + value + '</a>';
										/*
										 * return '<img style="cursor:pointer"
										 * onclick="alert()" src=' + imgUrl +
										 * '/images/actions/action_edit.png
										 * align="absMiddle">' + value+ '</img> ';
										 */
									}
								},
								{
									field : 'orgName',
									title : '公司名称',
									width : setColumnWidth(0.2)
								},
								{
									field : 'oatypeName',
									title : '岗位类型',
									width : setColumnWidth(0.2)
								},
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.15),
									align : 'center',
									formatter : function(value, row, index) {
										var rid = encodeURIComponent(row.stationId);
										return '<img style="cursor:pointer"  onclick=updateStation("'
												+ (rid)
												+ '") title="修改资料"  src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  '
												+ ' <img style="cursor:pointer" onclick=getByRoleStation("'
												+ (rid)
												+ '") title="维护角色" src='
												+ imgUrl
												+ '/images/actions/action_roles.png align="absMiddle"></img>'
										/*
										 * + ' <img style="cursor:pointer"
										 * onclick=deletestation("' +rid +'")
										 * title="删除" src=' + imgUrl +
										 * '/images/actions/action_del.png
										 * align="absMiddle"></img>'
										 */;
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createStation();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								deletestation();
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
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.stationId = encodeURIComponent($("#stationId").val());
	queryParams.stationName = encodeURIComponent($("#stationName").val());
	queryParams.orgId = $('#orgId').combobox('getValue');
	$("#datagrid").datagrid('reload');
}
function deletestation() {
	$.messager.confirm('Confirm', '是否批量刪除岗位?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  no selected rows!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].stationId);
			}
			$("#ids").val(ids);

			var form = window.document.forms[0];
			form.action = appUrl + "/station!deleteStation.jspa";
			form.target = "hideFrame";
			form.submit();

			/*
			 * var form = window.document.forms[0]; form.action = appUrl +
			 * "/station!deleteStation.jspa?stationIdStr="+id; form.target =
			 * "hideFrame"; form.submit();
			 */
		}
	});

}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

// 创建窗口对象
function initMaintStation(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintStation")
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
function closeMaintStation() {
	// 关闭创建页面
	$("#maintStation").window('close');
	// $("#maintStation1").window('close');

}
function createStation() {
	initMaintStation('创建岗位', '/station!createStationPagePre.jspa', 500, 230);
}
function updateStation(id) {
	initMaintStation('修改岗位', '/station!updateStationPagePre.jspa?stationId='
			+ (id), 500, 230);
}

function getByStation(name, id) {
	initMaintStation('查看人员',
			"/station!configStationUserPagePre.jspa?stationId=" + id
					+ "&&stationName=" + name, 900, 400);
}

function getByRoleStation(id) {
	initMaintStation('维护角色', '/roleAction!searchSelectedRole.jspa?stationId='
			+ (id), 800, 400);
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