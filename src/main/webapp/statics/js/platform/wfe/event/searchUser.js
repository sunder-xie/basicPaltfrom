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
								+ '/allUserAction!getUserInfoList.jspa?ran='
								+ Math.random(),

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						fitColumns : true,
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
									width : $(this).width() * 0.08,
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
									field : 'mobile',
									title : '手机号码',
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
									title : '组织名',
									field : 'orgName',
									width : 80,
									align : 'center',
									width : $(this).width() * 0.17,
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
									field : 'address',
									title : '通讯地址',
									align : 'center',
									width : $(this).width() * 0.20,
									sortable : true
								} ] ],
						toolbar : [ "-", {
							text : '确定',
							iconCls : 'icon-add',
							handler : function() {
								returnUserId();
							}
						} ]
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

function returnUserId(){
	var rows = $('#con_list').datagrid('getSelections');
	window.parent.ontransferEvent(rows[0].userId,rows[0].userName);
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
$(window).resize(function () { 
          $('#datagrid').datagrid('resize', {
              width: $(".f_main").width()
          });
});
