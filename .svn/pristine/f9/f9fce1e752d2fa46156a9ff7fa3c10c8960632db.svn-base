$(document).ready(function() {
	$('#showflag1').hide();
	$('#showflag2').hide();
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid(){
	$('#beginDate').datebox({
			required : true,
			editable : false
	});
	$('#endDate').datebox({
			required : true,
			editable : false
	});
	$('#costCenter').combogrid({
		panelWidth : 450,
		panelHight : 500,
		idField : 'totalId',
		textField : 'title',
		pagination : true,// 是否分页
		rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		fit : true,// 自动大小
		method : 'post',
		multiple : false,
		editable : false,
		url : appUrl + '/account/accountAction!searchWorkPlan.jspa',
		columns : [ [ {
			field : 'totalId',
			title : 'ID',
			width : 150,
			align : 'center'
		}, {
			field : 'title',
			title : '项目名称',
			width : 250,
			align : 'center'
		} ] ],
		onSelect : function(record) {
			var g = $('#costCenter').combogrid('grid');
			var r = g.datagrid('getSelected'); 
			$("#projectId").val(r.totalId);
		},
		toolbar : '#toolbar',
		required: true
	});
	
	$('#tripWay').combogrid({
		panelWidth : 450,
		panelHight : 500,
		idField : 'value',
		textField : 'name',
		pagination : true,// 是否分页
		rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		fit : true,// 自动大小
		method : 'post',
		multiple : false,
		editable : false,
		url : appUrl + '/wfe/eventAction!getTripWayJsonList.jspa',
		columns : [ [ {
			field : 'value',
			title : '值',
			width : 150,
			align : 'center'
		}, {
			field : 'name',
			title : '名称',
			width : 250,
			align : 'center'
		} ] ],
		onSelect : function(record) {
			var g = $('#tripWay').combogrid('grid');
			var r = g.datagrid('getSelected'); 
			if("selfDriving" == r.value) {
				$('#showflag1').show();
				$('#showflag2').show();
			} else {
				$('#showflag1').hide();
				$('#showflag2').hide();
				$('#peopleNames').val("");
				$('#distance').val("");
			}
		},
		toolbar : '#toolbar1',
		required: true
	});
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#costCenter').combogrid({
		url : appUrl + '/account/accountAction!searchWorkPlan.jspa?title=' + val
	});
	$('#costCenter').combogrid("grid").datagrid('reload');
}

function searcher1(val) {
	val = encodeURIComponent(val);
	$('#tripWay').combogrid({
		url : appUrl + '/wfe/eventAction!getTripWayJsonList.jspa?text=' + val
	});
	$('#tripWay').combogrid("grid").datagrid('reload');
}

function submit() {
	if(!($('#costCenter').combogrid('isValid') && $('#tripWay').combogrid('isValid') && $('#beginDate').datebox('isValid') && $('#endDate').datebox('isValid'))) {
		return;
	}
	$("#costCenterName").val($('#costCenter').combobox("getText"));
	$("#tripWayName").val($('#tripWay').combobox("getText"));
	var form = window.document.forms[0];
	form.action = appUrl+"/wfe/eventAction!updateBusinessTripApplyContent.jspa";
	form.submit();
	
}

function close() {
	window.parent.closeMaintEvent();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$("#xmlTemp_FileName", window.parent.document).val($("#xmlTemp_FileName").val());	
		$("#projectId", window.parent.document).val($('#costCenter').combobox("getValue"));
		window.parent.closeContent(1);
	}
}
