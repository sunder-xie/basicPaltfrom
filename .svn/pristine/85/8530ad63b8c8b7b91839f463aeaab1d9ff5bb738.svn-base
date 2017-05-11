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
								+ '/allUserAction!getUserInfoList.jspa?ran='
								+ Math.random(),

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						// frozenColumns : [ [ ] ],
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									id : 'dictTypeId',
									title : '数字ID',
									field : 'userId',
									width : 80,
									align : 'center',
									sortable : true,
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
									title : '用户ID',
									field : 'loginId',
									width : 120,
									align : 'center',
									sortable : true
								},
								{
									field : 'userName',
									title : '姓名',
									align : 'center',
									width : 120,
									sortable : true
								},
								{
									field : 'sex',
									title : '性别',
									align : 'center',
									width : 80,
									sortable : true,
									formatter : function(value, row, rec) {
										var sex_ch = row.sex;
										if (sex_ch == 'M') {
											return "男";
										} else if (sex_ch == 'F') {
											return "女";
										}
									}
								},
								{
									title : '组织名',
									field : 'orgName',
									width : 160,
									align : 'center',
									formatter : function(value, row, rec) {
										var orgName = row.orgName;
										if (orgName == null) {
											return "";
										} else {
											return orgName;
										}
									}
								},
								{
									field : 'email',
									title : '邮箱',
									align : 'center',
									width : 150,
									sortable : true
								},
								{
									field : 'mobile',
									title : '手机号码',
									width : 140,
									align : 'center',
									sortable : true
								},
								{
									field : 'phone',
									title : '办公电话',
									align : 'center',
									width : 150,
									sortable : true
								},
//								{
//									field : 'address',
//									title : '通讯地址',
//									align : 'center',
//									width : 180,
//									sortable : true
//								},
								{
									field : 'stations',
									title : '岗位查看',
									align : 'center',
									width : 80,
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
						// {
						// field : 'userState',
						// title : '状态',
						// width : 70,
						// align : 'center',
						// sortable : true,
						// formatter : function(value,row, rec) {
						// var state=row.userState;
						// if(state == 'Y'){
						// return "<font color='green'>启用</font>";
						// }else if(state == 'N'){
						// return "<font color='red'>禁用</font>";
						// }
						// }
						// },
						// {
						// field : 'oper',
						// title : '人员状态操作',
						// width : 90,
						// align : 'center',
						// formatter : function(value,row, rec) {
						// var id=row.userId;
						// var state = row.userState;
						// if(state=='Y'){
						// return '<img style="cursor:pointer"
						// onclick=forbidden("'+id+'") title="禁用人员账号" src='
						// + imgUrl
						// + '/images/actions/action_del.png
						// align="absMiddle"></img>';
						// }else{
						// return '<img style="cursor:pointer"
						// onclick=startup("'+id+'") title="启用人员账号" src='
						// + imgUrl
						// + '/images/actions/icon_ok.gif
						// align="absMiddle"></img>';
						// }
						// }
						// }
						] ]
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
	queryParams.loginId = encodeURIComponent($("#loginId").val());
	queryParams.userName = encodeURIComponent($("#userName").val());
	queryParams.orgId = encodeURIComponent($("#orgId").val());
	var bhxjFlag;
	$("[name='bhxjFlag']").each(function() {
		if (this.checked) {
			bhxjFlag = this.value;
		} else {
			bhxjFlag = "N";
		}
	});
	queryParams.bhxjFlag = encodeURIComponent(bhxjFlag);
	$("#con_list").datagrid('load');
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
 * 人员创建
 */
function add() {
	initMaintWindow('人员创建', '/allUserAction!toCreateUser.jspa');
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
 * 修改人员信息
 */
function edit() {
	var ids = '';
	var rows = $('#con_list').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '未选中任何人员！', '提示');
		return;
	} else if (rows.length > 1) {
		$.messager.alert('提示', '修改时只能选择一个人员', '提示');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		if (rows[i].userState == 'N') {
			$.messager.alert('提示', '未启用人员的信息不能修改，请先启用', '提示');
			return;
		}
		ids = rows[i].userId;
	}
	initMaintWindowForEdit('人员信息修改', '/allUserAction!updateUserInfo.jspa?ids='
			+ ids);

}

/**
 * 删除人员信息 删除可以批量
 */
function remove() {
	var ids = '';
	var rows = $('#con_list').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids += rows[i].userId + ",";
	}
	if (ids == '') {
		$.messager.alert('提示', '未选中任何人员！', '提示');
		return;
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + '/allUserAction!deleteUserByEmpId.jspa?ids='
				+ ids;
		form.target = "hideFrame";
		form.submit();
	}

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
 * 禁用 启用人员账号
 * 
 * @param {}
 *            id
 */
function forbidden(id) {
	$.messager.confirm("提示", "确认要禁用该人员账号么？",
			function(data) {
				if (data) {
					var form = window.document.forms[0];
					form.action = appUrl
							+ '/allUserAction!forbidden.jspa?userId=' + id;
					form.target = "hideFrame";
					form.submit();
				} else {
					return;
				}
			});
}
function startup(id) {
	$.messager.confirm("提示", "确认要启用该人员账号么？", function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl + '/allUserAction!startup.jspa?userId=' + id;
			form.target = "hideFrame";
			form.submit();
		} else {
			return;
		}
	});
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
