$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function 	loadGrid(){
	var amount = [ {
		'id' : '0',
		'text' : '手动填'
	}, {
		'id' : '1',
		'text' : '单值'
	}, {
		'id' : '2',
		'text' : '多值'
	}, {
		'id' : '3',
		'text' : '选日期'
	}, {
		'id' : '4',
		'text' : 'OA组织树'
	}, {
		'id' : '5',
		'text' : '年'
	}, {
		'id' : '6',
		'text' : '月'
	}, {
		'id' : '7',
		'text' : '水站'
	},{
		'id' : '8',
		'text' : 'SAP组织树'
	}, {
		'id' : '20',
		'text' : '多值一页显示n条记录'
	} ];

	var txt = [ {
		'id' : '0',
		'text' : '无'
	}, {
		'id' : '1',
		'text' : '有'
	} ];

	var che = [ {
		'id' : '0',
		'text' : '不是'
	}, {
		'id' : '1',
		'text' : '必填'
	} ];

	var checkWay = [ {
		'id' : '0',
		'text' : '手动填写的校验'
	}, {
		'id' : '1',
		'text' : '英文'
	}, {
		'id' : '2',
		'text' : '英文数字'
	}, {
		'id' : '3',
		'text' : '金额'
	}, {
		'id' : '4',
		'text' : '数字'
	} ];

	$('#amount').combobox(
			{
				textField : 'text',
				valueField : 'id',
				data: amount,
				onSelect : function(r) {
					$("#amount").val(r.id);
				}
			});
	$('#che').combobox(
			{
				textField : 'text',
				valueField : 'id',
				data: che,
				onSelect : function(r) {
					$("#che").val(r.id);
				}
			});
	$('#txt').combobox(
			{
				textField : 'text',
				valueField : 'id',
				data: txt,
				onSelect : function(r) {
					$("#txt").val(r.id);
				}
			});
	$('#checkWay').combobox(
			{
				textField : 'text',
				valueField : 'id',
				data: checkWay,
				onSelect : function(r) {
					$("#checkWay").val(r.id);
				}
			});
}
function save() {
	//var v=$("#bid").numberbox('isValid');
	//var p = $("#memo").validatebox('isValid');
	var isValid = $('#ff').form('validate');
//	if (!(p&&v)) {
	if(isValid){
		var form = window.document.forms[0];
		form.action = appUrl+"/boformAction!updateReportParameter.jspa";
		form.target = "hideFrame";
		form.submit();
		
	}else{
		$.messager.alert('Tips', '请仔细检查是否还有未填或者格式不正确的项目!', 'warning');
	}
	
}
/**
 * 关闭页面
 */
function cencel() {
	window.parent.closeMaintWindow();
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				cencel();
				window.parent.search();
			}
		});
	}
}
