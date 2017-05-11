$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});
function loadGrid() {

	$('#custKunnr').combogrid(
			{
				panelHeight : 250,
				panelWidth : 600,
				pagination : true,
				method : 'post',
				singleSelect : true,
				url : appUrl + '/allUserAction!kunnrSearch.jspa?kunnrId='
						+ $('#custKunnr1').val(),
				idField : 'kunnr',
				textField : 'name1',
				// multiple : true,
				columns : [ [ {
					field : 'ck',
					checkbox : true
				}, {
					field : 'kunnr',
					title : '经销商编号',
					width : 60
				}, {
					field : 'name1',
					title : '名称',
					width : 100
				}, {
					field : 'name3',
					title : '名称',
					width : 60
				}, {
					field : 'telNumber',
					title : '联系电话',
					width : 80
				}, {
					field : 'channelName',
					title : '渠道类型',
					width : 80
				}, {
					field : 'street1',
					title : '公司注册地址',
					width : 150
				} ] ],
				onSelect : function(index, record) {
					$('#orgId').val(record.orgId);
				},
				toolbar : '#toolbarKonzs'
			});
	$('#sex').combobox({
		valueField : 'id',
		textField : 'text'
	});
	$(".datebox :text").attr("readonly", "readonly");
	var curr_time = new Date();
	var strDate = curr_time.getFullYear() + "-";
	strDate += curr_time.getMonth() + 1 + "-";
	strDate += curr_time.getDate();
	$("#startDate").datebox("setValue", strDate);
	$('#postButton').linkbutton({
		disabled : true
	});
}
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
		document.getElementById('loginId').value = '';
	} else if ("人员创建成功." == successResult) {
		$.messager.confirm("提示", "创建成功，确认要关闭当前页面么？", function(data) {
			if (data) {
				window.parent.closeOrgTree();
				window.parent.search();
			} else {
				return;
			}
		});
	} else {
		$.messager.alert('Tips', successResult, 'info');
	}
}

function submit() {
	var a = $("#loginId").validatebox('isValid');
	var b = $("#userName").validatebox('isValid');
	var c = $("#passWd").validatebox('isValid');
	var d = $("#email").validatebox('isValid');
	var e = $("#busMobilephone").validatebox('isValid');
	var f = $("#repassWd").validatebox('isValid');
	var cc = $("#custKunnr").combobox("getValue");
	if ("" == cc) {
		$.messager.alert('Tips', "请选择所属经销商", 'info');
		return;
	}
	if (!(a && b && c && d && e && f)) {
		return;
	}

	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!creatKunnrUser.jspa";
	form.submit();
}

function close() {
	window.parent.closeMaintWindow();
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
						left : $(window).width() * 0.2,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true
					//
					});

	$win.window('open');
}

function selectOrgTree() {
	initMaintWindowForOrg('选择组织', '/orgAction!orgTreePage.jspa');
}

function selectOrgTree4Post() {
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
		$('#postButton').linkbutton({
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
$.extend($.fn.validatebox.defaults.rules, {
	/* 必须和某个字段相等 */
	equalTo : {
		validator : function(value, param) {
			return $(param[0]).val() == value;
		},
		message : '字段不匹配'
	}
});
