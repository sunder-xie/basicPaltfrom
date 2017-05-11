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
								+ '/postAction!getEmpPostList.jspa?ran='
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
									field : 'empPostId',
									align : 'center',
									sortable : true,
									hidden : true
								},
								{
									title : '职务名称',
									field : 'empPostName',
									width : $(this).width() * 0.2,
									align : 'center'
								},
								{
									title : '所属组织ID',
									field : 'orgId',
									width : 80,
									align : 'center',
									hidden : true
									
								},
								{
									title : '所属组织',
									field : 'orgName',
									width : $(this).width() * 0.2,
									align : 'center'
									
								},
//								{
//									title : '用户ID',
//									field : 'empId',
//									width : $(this).width() * 0.15,
//									align : 'center',
//									formatter : function(v) {
//										if(v==null){
//											return "";
//										}else {
//										return v;
//										}
//									}
//								},
								{
									field : 'createDate',
									title : '创建时间',
									align : 'center',
									width : $(this).width() * 0.15,
									sortable : true,
									formatter : function(v) {
										if(v)
										return utcToDate(v.replace(
												/\/Date\((\d+)\+\d+\)\//gi,
												"new Date($1)"));
									}
									
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						},  "-", {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								remove();
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
	queryParams.orgId = encodeURIComponent($("#orgId").val());
	queryParams.empPostName = encodeURIComponent($("#empPostName").val());
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
	var WWidth = 400;
	var WHeight = 420;
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

/**
 * 职务创建
 */
function add() {
	initMaintWindow('职务创建', '/postAction!toCreateEmpPost.jspa');
}



/**
 * 删除职务信息 删除可以批量
 */
function remove() {
	var ids = '';
	var userIds = '';
	var rows = $('#con_list').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids += rows[i].empPostId + ",";
		if (rows[i].empId){
			return $.messager.alert('提示', "职位    <font style='color:green'>"+rows[i].empPostName+"</font> 已经分配人员,请先解除关系", '提示'); 
		}
	}
	if (ids == '') {
		$.messager.alert('提示', '未选中任何职务！', '提示');
		return;
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + '/postAction!deleteEmpPostById.jspa?empPostIds='
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
function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = '01';
	month["Feb"] = '02';
	month["Mar"] = '03';
	month["Apr"] = '04';
	month["May"] = '05';
	month["Jun"] = '06';
	month["Jul"] = '07';
	month["Aug"] = '08';
	month["Sep"] = '09';
	month["Oct"] = '10';
	month["Nov"] = '11';
	month["Dec"] = '12';
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
