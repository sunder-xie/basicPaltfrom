$(document).ready(function() {
	loadGrid();
	addRow();
	$('#hideFrame').bind('load', promgtMsg);
});

var k = 0;

var dataTypeJson = [ {
	value : 'varchar2',
	text : 'varchar2'
}, {
	value : 'number',
	text : 'number'
}, {
	value : 'date',
	text : 'date'
} ];

var constraintJson = [ {
	value : 'primary key',
	text : 'primary key'
}, {
	value : 'not null',
	text : 'not null'
} ];

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '表结构',
		singleSelect : false,
		pagination : false,
		nowrap : true,
		striped : true,
		height : height * 0.96,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'fieldName',
			title : '字段名称',
			width : setColumnWidth(0.15),
			align : 'center',
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		}, {
			field : 'dataType',
			title : '数据类型',
			width : setColumnWidth(0.15),
			align : 'center',
			editor : {
				type : 'combobox',
				options : {
					valueField : 'value',
					textField : 'text',
					data : dataTypeJson,
					panelHeight : 'auto',
					multiple : false,
					editable : false,
					required : true,
					onSelect : function(record) {
						var index = getRowIndex(this);
						var editor = $('#datagrid').datagrid('getEditor', {
							index : index,
							field : 'dataLength'
						});

						if (record.value == "varchar2") {
							editor.target.numberbox({
								min : 1,
								max : 4000,
								disabled : false,
								required : true
							});
						} else if (record.value == "number") {
							editor.target.numberbox({
								value : '',
								disabled : true,
								required : false
							});
						} else {
							editor.target.numberbox({
								value : '',
								disabled : true,
								required : false
							});
						}
						editor.target.focus();
					}
				}
			}
		}, {
			field : 'dataLength',
			title : '长度',
			width : setColumnWidth(0.15),
			align : 'center',
			editor : {
				type : 'numberbox',
				options : {
					required : false
				}
			}
		}, {
			field : 'dataConstraint',
			title : '约束条件',
			width : setColumnWidth(0.15),
			align : 'center',
			editor : {
				type : 'combobox',
				options : {
					valueField : 'value',
					textField : 'text',
					data : constraintJson,
					panelHeight : 'auto',
					multiple : false,
					editable : false,
					required : false
				}
			}
		}, {
			field : 'fieldComment',
			title : '字段说明',
			width : setColumnWidth(0.35),
			align : 'center',
			editor : {
				type : 'text'
			}
		} ] ],
		onBeforeEdit : function(index, row) {
			row.editing = true;
			updateActions(index);
		},
		onAfterEdit : function(index, row) {
			row.editing = false;
			updateActions(index);
		},
		onCancelEdit : function(index, row) {
			row.editing = false;
			updateActions(index);
		},
		toolbar : [ "-", {
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				addRow();
			}
		}, "-", {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				removeRow();
			}
		}, "-" ]
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function addRow() {
	$('#datagrid').datagrid('appendRow', {
		fieldName : '',
		dataType : '',
		dataLength : '',
		dataConstraint : '',
		fieldComment : ''
	});
	$('#datagrid').datagrid('beginEdit', k);
	k++;
}

function removeRow() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		var index = $('#datagrid').datagrid('getRowIndex', rows[i]);
		$('#datagrid').datagrid('deleteRow', index);
		k--;
	}
}

$.extend($.fn.validatebox.defaults.rules, {
	tableNameValidate : {
		validator : function(value) {
			var rules = $.fn.validatebox.defaults.rules;
			if (value.indexOf('.') == -1) {
				rules.tableNameValidate.message = '请添加表的所有者!';
				return false;
			} else {
				var result = $.ajax({
					async : false,
					type : 'post',
					url : appUrl
							+ '/data/dataManageAction!tableNameValidate.jspa',
					data : {
						tableName : value
					}
				}).responseText;

				if (result == '1') {
					rules.tableNameValidate.message = '用户名不存在!';
					return false;
				} else if (result == '2') {
					rules.tableNameValidate.message = '表名已存在!';
					return false;
				} else if (result == '3') {
					rules.tableNameValidate.message = '验证失败!';
					return false;
				}
			}
			return true;
		},
		message : ''
	}
});

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}

function createTable() {
	var isValid = $('#ff').form('validate');
	if (!isValid) {
		$.messager.alert('Tips', '验证未通过,请仔细检查!', 'error');
		return;
	}
	var rows = $('#datagrid').datagrid('getRows');
	if (rows == '') {
		$.messager.alert('Tips', '请添加表结构!', 'error');
		return;
	}
	$.messager.confirm('Confirm', '确认新建表?', function(r) {
		if (r) {
			for ( var i = 0; i < rows.length; i++) {
				$('#datagrid').datagrid('endEdit', i);
			}
			var fields = [];
			for ( var i = 0; i < rows.length; i++) {
				fields.push({
					"columnName" : rows[i].fieldName,
					"dataType" : rows[i].dataType,
					"dataLength" : rows[i].dataLength,
					"dataConstraint" : rows[i].dataConstraint,
					"comments" : rows[i].fieldComment
				});
			}
			$.messager.progress();
			var form = window.document.forms[0];
			$('#tableColumnList').val($.toJSON(fields));
			form.action = appUrl + "/data/dataManageAction!createTable.jspa";
			form.target = 'hideFrame';
			form.submit();
		}
	});
}

function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}
