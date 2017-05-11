$(document).ready(function() {
	loadGrid(); // 权限点查询
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#con_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : 350,
						striped : true,
						url : appUrl
								+ '/addressListAction!searchAddressLists.jspa?',

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true,
									hidden : true
								},
								{
									title : '组织ID',
									field : 'orgId',
									width : 80,
									align : 'center',
									hidden : true,
									formatter : function(value, row, rec) {
										var orgId = row.orgId;
										if (orgId == null) {
											return "";
										} else {
											return orgId;
										}
									}
								},
								{
									field : 'adgroupName',
									title : '多级组织',
									align : 'left',
									width : 260
								},
								{
									title : '组织名',
									field : 'orgName',
									width : 120,
									align : 'center',
									formatter : function(value, row, rec) {
										var orgName = row.orgName;
										if (orgName == null) {
											return "";
										} else {
											return orgName;
										}
									},
									hidden : true
								},
								{
									title : '用户ID',
									field : 'userCode',
									width : 90,
									align : 'center'
								},
								{
									field : 'userName',
									title : '姓名',
									align : 'center',
									width : 90
								},
								{
									field : 'stationName',
									title : '主岗位',
									align : 'center',
									width : 100
								},
								{
									field : 'empPostName',
									title : '职务',
									align : 'center',
									width : 80
								},
								{
									field : 'userEmail',
									title : '邮箱',
									align : 'center',
									width : 150
								},
								{
									field : 'empMobilePhone',
									title : '私人手机号码',
									width : 100,
									align : 'center'
								},
								{
									field : 'busMobilePhone',
									title : '公务手机号码',
									width : 100,
									align : 'center'
								},
								{
									field : 'stMobile',
									title : '手机短号',
									width : 80,
									align : 'center'
								},
								{
									field : 'stations',
									title : '岗位查看',
									align : 'center',
									width : 80,
									hidden : true,
									formatter : function(value, row, rec) {
										var id = row.userId;
										var orgName = row.orgName;
										var orgId = row.orgId;
										var state = row.userState;
										if (orgName == null) {
											orgName = "";
										} else {
											orgName = encodeURIComponent(orgName);
										}
										if (orgId == null) {
											orgId = "";
										} else {
											orgId = encodeURIComponent(orgId);
										}

										return '<img style="cursor:pointer" onclick=searchStation("'
												+ id
												+ '","'
												+ state
												+ '","'
												+ orgId
												+ '","'
												+ orgName
												+ '") title="查看岗位/修改岗位" src='
												+ imgUrl
												+ '/images/actions/action_dot.png align="absMiddle"></img>';
									}
								}

						] ],
						toolbar : [ "-", {
							text : '数据导出',
							iconCls : 'icon-download',
							handler : function() {
								exportAddressList();
							}
						}, "-" ]
					});
	// 分页控件
	var p = $('#con_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function search() {
	var queryParams = $('#con_list').datagrid('options').queryParams;
	queryParams.userCode = encodeURIComponent($("#userCode").val());
	queryParams.userName = encodeURIComponent($("#userName").val());
	queryParams.orgId = encodeURIComponent($("#orgId").val());
	var bhxjFlag;
	$("[name='bhxjFlag']").each(function() {
		if (this.checked) {
			bhxjFlag = this.value;
		} else {
			bhxjFlag = "";
		}
	});
	queryParams.bhxjFlag = encodeURIComponent(bhxjFlag);
	$("#con_list").datagrid('load');
}

function exportAddressList() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = 'addressListAction!searchAddressListDownLoad.jspa';
	form.target = "hideFrame";
	form.submit();
}

function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/addressListAction!downLoadOver.jspa",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
					}
				}
			});
		}, 100);
	}, 500);
}

// 创建窗口对象
function initMaintWindow(title, url) {
	var url = appUrl + url;
	var WWidth = 800;
	var WHeight = 460;
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
						draggable : false
					//
					});

	$win.window('open');
}
function initMaintWindowForEdit(title, url) {
	var url = appUrl + url;
	var WWidth = 800;
	var WHeight = 450;
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
						draggable : false
					//
					});

	$win.window('open');
}
/**
 * 组织数
 * 
 * @param {}
 *            title
 * @param {}
 *            url
 */
function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 460;
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
function initMaintWindowForStation(title, url) {
	var url = appUrl + url;
	var WWidth = 600;
	var WHeight = 350;
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

/**
 * 查看人员岗位
 * 
 */
function searchStation(id, state, orgId, orgName) {
	if (state == 'N') {
		$.messager.alert('提示', '禁用状态人员没有岗位，不可查看', '提示');
		return;
	}
	initMaintWindowForStation('岗位查看',
			'/allUserAction!searchStationUser4Book.jspa?userId=' + id
					+ "&orgId=" + orgId + "&orgName=" + orgName);
}

/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintWindowForOrg('选择组织', '/orgAction!orgTreePage.jspa');
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
		$.messager.alert('提示', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('提示', successResult, 'info');
		search();
	}
}
/**
 * 组织树的返回值接受
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}


/**
 * 回车键默认时间绑定
 * 
 * @param {}
 *            e
 * @return {Boolean}
 */
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
