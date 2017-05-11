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
						height : 370,
						striped : true,
						url : appUrl
								+ '/allUserAction!getKunnrUserInfoList.jspa?ran='
								+ Math.random(),

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : true,
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
									title : '用户ID',
									field : 'loginId',
									width : $(this).width() * 0.06,
									align : 'center',
									sortable : true
								},
								{
									field : 'userName',
									title : '姓名',
									align : 'center',
									width : $(this).width() * 0.08,
									sortable : true
								},
								{
									field : 'sex',
									title : '性别',
									align : 'center',
									width : $(this).width() * 0.07,
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
									field : 'busMobilephone',
									title : '公务号码',
									width : $(this).width() * 0.1,
									align : 'center',
									sortable : true
								},
								{
									field : 'mobile',
									title : '私人号码',
									width : $(this).width() * 0.1,
									align : 'center',
									sortable : true
								},
								{
									field : 'email',
									title : '邮箱',
									align : 'center',
									width : $(this).width() * 0.16,
									sortable : true
								},
								{
									field : 'phone',
									title : '办公电话',
									align : 'center',
									width : $(this).width() * 0.13,
									sortable : true
								},
								{
									field : 'address',
									title : '通讯地址',
									align : 'center',
									width : $(this).width() * 0.20,
									sortable : true
								},
								{
									field : 'orgName',
									title : '所属经销商',
									align : 'center',
									width : $(this).width() * 0.20,
									sortable : true
								},
								{
									field : 'userState',
									title : '状态',
									width : $(this).width() * 0.08,
									align : 'center',
									sortable : true,
									formatter : function(value, row, rec) {
										var state = row.userState;
										if (state == 'Y') {
											return "<font color='green'>启用</font>";
										} else if (state == 'N') {
											return "<font color='red'>禁用</font>";
										}
									}
								},
								{
									field : 'oper',
									title : '雇员状态操作',
									width : $(this).width() * 0.1,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.userId;
										var state = row.userState;
										var loginId = row.loginId;
										if (state == 'Y') {
											return '<img style="cursor:pointer" onclick=forbidden("'
													+ id
													+ '","'
													+ loginId
													+ '") title="禁用雇员账号" src='
													+ imgUrl
													+ '/images/actions/action_del.png align="absMiddle"></img>';
										} else {
											return '<img style="cursor:pointer" onclick=startup("'
													+ id
													+ '","'
													+ loginId
													+ '") title="启用雇员账号" src='
													+ imgUrl
													+ '/images/actions/icon_ok.gif align="absMiddle"></img>';
										}
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
							}
						}, "-"]
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
	queryParams.orgName = encodeURIComponent($("#orgName").val());
	queryParams.phone = $("#phone").val();
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}
function initMaintWindowForStation(title, url) {
	var url = appUrl + url;
	var WWidth = 600;
	var WHeight = 400;
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

/**
 * 雇员创建
 */
function add() {
	initMaintWindow('雇员创建', '/allUserAction!toCreateKunnrUser.jspa');
}
/**
 * 查看雇员岗位
 * 
 */
function searchStation(id, state, orgId, loginId, orgName) {
	if (state == 'N') {
		$.messager.alert('提示', '禁用状态雇员没有岗位，不可查看', '提示');
		return;
	}
	initMaintWindowForStation('岗位查看',
			'/allUserAction!searchStationUser.jspa?userId=' + id + "&orgId="
					+ orgId + "&orgName=" + orgName + "&loginId=" + loginId);
}
/**
 * 修改雇员信息
 */
function edit() {
	var ids = '';
	var rows = $('#con_list').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '未选中任何雇员！', '提示');
		return;
	} else if (rows.length > 1) {
		$.messager.alert('提示', '修改时只能选择一个雇员', '提示');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		if (rows[i].userState == 'N') {
			$.messager.alert('提示', '未启用雇员的信息不能修改，请先启用', '提示');
			return;
		}
		ids = rows[i].userId;
	}
	initMaintWindowForEdit('雇员信息修改',
			'/allUserAction!toUpdateKunnrUserInfo.jspa?ids=' + ids);

}

/**
 * 删除雇员信息 删除可以批量
 */
function remove() {
	var ids = '';
	var logins = '';
	var rows = $('#con_list').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids += rows[i].userId + ",";
		logins += rows[i].loginId + ",";
	}
	if (ids == '') {
		$.messager.alert('提示', '未选中任何雇员！', '提示');
		return;
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + '/allUserAction!deleteUserByEmpId.jspa?ids='
				+ ids + "&logins=" + logins;
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
 * 禁用 启用雇员账号
 * 
 * @param {}
 *            id
 */
function forbidden(id, loginId) {
	$.messager.confirm("提示", "确认要禁用该雇员账号么？", function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl + '/allUserAction!forKunnrbidden.jspa?userId=' + id
					+ '&loginId4AD=' + loginId;
			form.target = "hideFrame";
			form.submit();
		} else {
			return;
		}
	});
}
function startup(id, loginId) {
	$.messager.confirm("提示", "确认要启用该雇员账号么？", function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl + '/allUserAction!startKunnrup.jspa?userId=' + id
					+ '&loginId4AD=' + loginId;
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
$(window).resize(function() {
	$('#datagrid').datagrid('resize', {
		width : $(".f_main").width()
	});
});
