$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function 	loadGrid(){
	var amount = [ {
		'id' : '0',
		'text' : '�ֶ���'
	}, {
		'id' : '1',
		'text' : '��ֵ'
	}, {
		'id' : '2',
		'text' : '��ֵ'
	}, {
		'id' : '3',
		'text' : 'ѡ����'
	}, {
		'id' : '4',
		'text' : 'OA��֯��'
	}, {
		'id' : '5',
		'text' : '��'
	}, {
		'id' : '6',
		'text' : '��'
	}, {
		'id' : '7',
		'text' : 'ˮվ'
	},{
		'id' : '8',
		'text' : 'SAP��֯��'
	}, {
		'id' : '20',
		'text' : '��ֵһҳ��ʾn����¼'
	} ];

	var txt = [ {
		'id' : '0',
		'text' : '��'
	}, {
		'id' : '1',
		'text' : '��'
	} ];

	var che = [ {
		'id' : '0',
		'text' : '����'
	}, {
		'id' : '1',
		'text' : '����'
	} ];

	var checkWay = [ {
		'id' : '0',
		'text' : '�ֶ���д��У��'
	}, {
		'id' : '1',
		'text' : 'Ӣ��'
	}, {
		'id' : '2',
		'text' : 'Ӣ������'
	}, {
		'id' : '3',
		'text' : '���'
	}, {
		'id' : '4',
		'text' : '����'
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
		$.messager.alert('Tips', '����ϸ����Ƿ���δ����߸�ʽ����ȷ����Ŀ!', 'warning');
	}
	
}
/**
 * �ر�ҳ��
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
