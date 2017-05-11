$(document).ready(function() {
	
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
	$('#updatePwd').dialog('close');
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
					$('#custKunnr1').val(record.kunnr);
					
				},
				toolbar : '#toolbarKonzs'
			});
	
	$('#updatePwd').dialog({
		title : "密码修改",
		modal : true,
		buttons : [ {
			text : '确定',
			iconCls : 'icon-ok',
			handler : function() {
				updatePassWord();
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				$('#updatePwd').dialog('close');
			}
		} ]
	});

	$('#sex').combobox({
		valueField : 'id',
		textField : 'text'
	});
	$('#sex').combobox('setValue', $('#sexValue').val());
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
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!updateKunnrUserInfo.jspa?userId="
			+ $('#userId').val();
	form.submit();
}

function close() {
	window.parent.closeMaintWindow();
	window.parent.search();
}

function updatePassWord() {
//	var cc = $("#custKunnr").combobox("getValue");
	var cc =$("#custKunnr1").val();
	if ("" == cc) {
		$.messager.alert('Tips', "请选择所属经销商", 'info');
		return;
	}
	var val = $('#userId').val();
	var repassWd = $('#repassWd').val();
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!updateKunnrPwd.jspa?userId=" + val
			+ "&repassWd=" + repassWd;
	form.submit();
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
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
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
	$('#updatePwd').dialog('open');
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
