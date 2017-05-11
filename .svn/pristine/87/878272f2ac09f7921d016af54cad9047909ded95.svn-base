$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {

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
	}, {
		'id' : '8',
		'text' : 'SAP组织树'
	},  {
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

	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '报表参数配置',
						singleSelect : false,
						pagination : true,
						nowrap : false,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'oper',
									title : '操作',
									width : 50,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.bid;
										var rec = $('#datagrid').datagrid(
												'getRowIndex', row);
										if (id == '') {
											return '<img style="cursor:pointer" onclick="deleteIndex('
													+ rec
													+ ')" title="删除此行" src='
													+ imgUrl
													+ '/images/actions/action_del.png align="absMiddle"></img>';
										}
									}
								}, {
									title : "报表编号",
									field : 'bid',
									width : 80,
									sortable : true,
									align : 'center',
									editor : {
										type : 'numberbox',
										options : {
											valueField : 'bid',
											textField : 'bid',
											 required : true
										}
									}
								}, {
									title : "表名",
									field : 'tableName',
									width : 100,
									sortable : false,
									align : 'left',
									editor : {
										type : 'validatebox',
										options : {
											valueField : 'tableName',
											textField : 'tableName'
										}
									}
								/*
								 * editor : new Ext.form.TextField({ style : {
								 * padding : '7px 3px 3px 5px' } })
								 */
								}, {
									title : "ID字段",
									field : 'zdid',
									width : 80,
									sortable : false,
									align : 'left',
									editor : {
										type : 'validatebox',
										options : {
											valueField : 'zdid',
											textField : 'zdid'
										}
									}
								}, {
									title : "描述字段",
									field : 'zdtxt',
									width : 100,
									sortable : false,
									align : 'left',
									editor : {
										type : 'validatebox',
										options : {
											valueField : 'tableName',
											textField : 'tableName'
										}
									}
								}, {
									title : "查询Lable",
									field : 'memo',
									width : 100,
									sortable : false,
									align : 'left',
									editor : {
										type : 'validatebox',
										options : {
											valueField : 'memo',
											textField : 'memo',
											required : true
										}
									}
								}, {
									title : "参数可选数量",
									field : 'amount',
									width : 110,
									sortable : false,
									align : 'left',
									// editor : amount,
									editor : {
										type : 'combobox',
										options : {
											valueField : 'id',
											textField : 'text',
											data : amount
										}
									},
									formatter : function(value, row, rec) {
										var v = row.amount;
										if (v == 0) {
											return '手动填';
										}
										if (v == 1) {
											return '单值';
										}
										if (v == 2) {
											return '多值';
										}
										if (v == 3) {
											return '选日期';
										}
										if (v == 4) {
											return 'OA组织树';
										}
										if (v == 5) {
											return '年';
										}
										if (v == 6) {
											return '月';
										}
										if (v == 7) {
											return '水站';
										}
										if (v == 20) {
											return '多值一页显示n条记录';
										}
									}
								}, {
									title : "有无描述",
									field : 'txt',
									width : 60,
									sortable : false,
									align : 'center',
									// editor : txt,
									editor : {
										type : 'combobox',
										options : {
											valueField : 'id',
											textField : 'text',
											data : txt
										}
									},
									formatter : function(value, row, rec) {
										var v = row.txt;
										if (v == 0) {
											return '无';
										}
										if (v == 1) {
											return '有';
										}
									}
								}, {
									title : "是否必填",
									field : 'che',
									width : 60,
									sortable : false,
									align : 'center',
									// editor : che,
									editor : {
										type : 'combobox',
										options : {
											valueField : 'id',
											textField : 'text',
											data : che
										}
									},
									formatter : function(value, row, rec) {
										var v = row.che;
										if (v == 0) {
											return '不是';
										}
										if (v == 1) {
											return '必填';
										}
									}
								}, {
									title : "查询条件",
									field : 'd',
									width : 90,
									sortable : false,
									align : 'left',
									editor : {
										type : 'validatebox',
										options : {
											valueField : 'd',
											textField : 'd'
										}
									}
								}, {
									title : "别名",
									field : 'nickname',
									width : 90,
									sortable : false,
									align : 'left',
									editor : {
										type : 'validatebox',
										options : {
											valueField : 'nickname',
											textField : 'nickname'
										}
									}
								}, {
									title : "校验方式",
									field : 'checkWay',
									width : 90,
									sortable : false,
									align : 'left',
									// editor : checkway,
									editor : {
										type : 'combobox',
										options : {
											valueField : 'id',
											textField : 'text',
											data : checkWay
										}
									},
									formatter : function(value, row, rec) {
										var v = row.checkWay;
										if (v == '0') {
											return '手动填写的校验';
										}
										if (v == '1') {
											return '英文';
										}
										if (v == '2') {
											return '英文数字';
										}
										if (v == '3') {
											return '金额';
										}
										if (v == '4') {
											return '数字';
										}
									}
								} ] ],
						onClickRow : function(rowIndex, rowData) {
							var id = rowData.bid;
							if (id == '') {
								$('#datagrid').datagrid('beginEdit', rowIndex);
							}
							lastIndex = rowIndex;
						},

						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								addDW();
							}
						}, "-", {
							text : '保存',
							iconCls : 'icon-ok ',
							handler : function() {
								save();
							}
						}, "-" ]

					});
}
function addDW() {
	$('#datagrid').datagrid('appendRow', {
		"bid" : '',
		"tableName" : '',
		"zdid" : '',
		"zdtxt" : '',
		"memo" : '',
		"amount" : '0',
		"txt" : '0',
		"che" : '0',
		"d" : '',
		"nickname" : '',
		"checkWay" : '0'
	});
}
function deleteIndex(rec) {
	$('#datagrid').datagrid('deleteRow', rec);
}

function save() {
	$.messager.confirm('Confirm', '是否批量提交?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  没有行被选中!');
				return;
			}
			for ( var i = 0; i < rows.length; i++) {
				var rowIdx = $('#datagrid').datagrid('getRowIndex', rows[i]);
				$('#datagrid').datagrid('endEdit', rowIdx);
				var rows = $('#datagrid').propertygrid('getChanges');
			}
			if (rows.length == 0) {
				$.messager.alert('Tips', '请不要重复保存!');
				return;
			}
			var reportParameterList = [];
			for ( var i = 0; i < rows.length; i++) {
				
				if((rows[i].memo=='')||rows[i].bid==''){
					$.messager.alert('Tips', '第'+i+'行报表编号或查询Label不能为空！', 'info');
					return;
				}
				reportParameterList.push({
				    "bid" : rows[i].bid,
					"tableName" : rows[i].tableName,
					"zdid" : rows[i].zdid,
					"zdtxt" : rows[i].zdtxt,
					"memo" : rows[i].memo,
					"amount" : rows[i].amount,
					"txt" : rows[i].txt,
					"che" : rows[i].che,
					"d" : rows[i].d,
					"nickname" : rows[i].nickname,
					"checkWay" : rows[i].checkWay
				});
			}
			 $("#reportParameterList").val($.toJSON(reportParameterList));
			var form = window.document.forms[0];
			form.action = appUrl + "/boformAction!createReportParameter.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	}
	if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				window.parent.closeMaintWindow();
				window.parent.search();
			}
		});

	}
}
