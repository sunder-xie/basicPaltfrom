$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	isSelectedOrg();
	loadGrid();
	$('#updatePwd').dialog('close');
});
function loadGrid() {
	$('#roleIds').combogrid(
			{
				panelWidth : 580,
				idField : 'stationId',
				textField : 'stationName',
				pagination : true,//
				rownumbers : true,//
				collapsible : false,//
				fit : true,//
				method : 'post',
				multiple : true,
				url : appUrl
						+ '/allUserAction!getSelectedStationsJSON.jspa?orgId='
						+ $('#orgId').val(),
				columns : [ [ {
					field : 'ck',
					checkbox : true
				}, {
					field : 'id',
					title : 'POSID',
					width : 100,
					hidden : true
				}, {
					field : 'stationId',
					title : '岗位ID',
					width : 100
				}, {
					field : 'stationName',
					title : '岗位名称',
					width : 150
				}, {
					field : 'orgName',
					title : '公司名',
					width : 150
				}, {
					field : 'userName',
					title : '用户名',
					width : 100
				} ] ],
				toolbar : '#toolbar'

			});
	$('#updatePwd').dialog({
		title:"密码修改",
		modal : true,
		buttons:[{
			text:'确定',
			iconCls:'icon-ok',
			handler:function(){
				updatePassWord();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$('#updatePwd').dialog('close');
			}
		}]
	});

	var q = $('#roleIds').combogrid("grid").datagrid();
	$('#roleIds').combo({
		multiple : true
	});
	$(q).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	$('#sex').combobox({
		valueField : 'id',
		textField : 'text'
	});
	$(".datebox :text").attr("readonly", "readonly");
	$('#roleIds').combogrid('setValues', [ $('#stationNames').val() ]);
	$('#roleIds').combo({
		disabled : true
	});
	$('#sex').combobox('setValue', $('#sexValue').val());
}

function searcher(val) {
	val = encodeURIComponent(val)
	$('#roleIds')
			.combogrid(
					{
						url : appUrl
								+ '/allUserAction!getSelectedStationsJSON.jspa?stationId='
								+ val
					});
	$('#roleIds').combogrid("grid").datagrid('reload');

}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
	}
}

function submit() {
	var a = $("#loginId").validatebox('isValid');
	var b = $("#userName").validatebox('isValid');
	var d = $("#email").validatebox('isValid');
	var e = $("#busMobilephone").validatebox('isValid');
	if (!(a && b && d && e)) {
		return;
	}
	var ids = $('#roleIds').combogrid('grid').datagrid('getSelections');
	var idd = '';
	for (i = 0; i <= ids.length - 1; i++) {
		idd += ids[i].id + ',';
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!updateUserInfo.jspa?roleIds=" + idd
			+ "&userId=" + $('#userId').val();
	form.submit();
}

function close() {
	window.parent.closeMaintWindow();
	window.parent.search();
}

function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
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
						left : ($(window).width() - 400) * 0.8,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true
					//
					});

	$win.window('open');
}
function initMaintWindow4Post(title, url) {
	var url = appUrl + url;
	var WWidth =600;
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
						closed : true,///
						closable : true,//
						left: $(window).width() * 0.2,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true//
					});

	$win.window('open');
}

function selectOrgTree() {
	if ($('#roleIds').val() != "") {
		$.messager.alert('Tips', "该人员现组织下有岗位存在，请先解除岗位关系再更换组织", 'info');
		return;
	}
	initMaintWindowForOrg('选择组织', '/orgAction!orgTreePage.jspa');
}

function selectOrgTree4Post(){
	initMaintWindow4Post('选择职务', '/orgAction!orgTreePage4Post.jspa');
}

function closeOrgTree() {
	$("#maintWindow").window('close');
}
function isSelectedOrg() {
	if (document.getElementById('orgName').value == '') {
		$('#roleIds').combo({
			disabled : true
		});
	} else {
		$('#roleIds')
				.combogrid(
						{
							url : appUrl
									+ '/allUserAction!getSelectedStationsJSON.jspa?orgId='
									+ $('#orgId').val()
						});
		$('#roleIds').combogrid("grid").datagrid('reload');
		$('#roleIds').combo({
			disabled : false
		});
	}
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
	isSelectedOrg();
}
function testId(val) {
	if (val == "") {
		$.messager.alert('Tips', "用户ID为空", 'warning');
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!isLoginIdExist.jspa?loginId4Check="
			+ val;
	form.submit();
}
function resetPwd(val) {
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!resetPWd.jspa?userId=" + val;
	form.submit();
}

$.extend($.fn.validatebox.defaults.rules, {
	/* 必须和某个字段相等 */
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '字段不匹配'
	}
});
