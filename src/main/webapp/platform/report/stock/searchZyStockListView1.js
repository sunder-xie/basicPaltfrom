$(document).ready(function() {
			
			$('#beginDate').datebox({
						onSelect : function(d) {
							$('#beginDate').val(utcToDate(d));
						}
					});
			$('#warehouse').combobox({
						textField : 'warehousename',
						valueField : 'warehouseid',
						url : appUrl + '/warehouse!getWarehouseListLog.jspa',
						onLoadSuccess : function() {
							var val = $(this).combobox("getData");
							for (var item in val[0]) {
								if (item == "warehouseid") {
									$(this).combobox('select', val[0][item]);
								}
							}
						}
					});
					loadGrid();
		});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '��ѯ���',
		height : height,
		striped : true,
		url : appUrl + '/reportAction!getData.jspa?rptId=5',
		loadMsg : '����Զ��������,��ȴ�...',
		singleSelect : false,
		nowrap : true,
		pagination : true,
		rownumbers : true,
		columns : [[{
					field : 'warehouse_name',
					title : '�ֿ�',
					align : 'center',
					width : setColumnWidth(0.1)
				}, {
					field : 'category_id',
					title : '���ϱ��',
					width : setColumnWidth(0.1),
					align : 'center'
				}, {
					field : 'categoryName',
					title : '����',
					width : setColumnWidth(0.15),
					align : 'center'
				}, {
					field : 'cargoSpec',
					title : '���',
					width : setColumnWidth(0.1),
					align : 'center'
				}
				// , {
				// field : 'batch',
				// title : '����',
				// width : setColumnWidth(0.1),
				// align : 'center'
				// }
				, {
					field : 'stock_quantity',
					title : '����',
					width : setColumnWidth(0.1),
					align : 'center'
				}, {
					field : 'uptop',
					title : '�����������䣩',
					width : setColumnWidth(0.1),
					align : 'center',
					formatter : function(value, row, rec) {
						var xxx = 0;
						if (row.stock_quantity % row.assistQuan == 0) {// ȡ%
							// ����+1
							xxx = row.stock_quantity / row.assistQuan;
							return xxx;
						} else {
							xxx = parseInt(row.stock_quantity / row.assistQuan)
									+ 1;
							return xxx;
						}
					}
				}, {
					field : 'unit',
					title : '��λ',
					width : setColumnWidth(0.06),
					align : 'center'
				}, {
					field : 'premiums',
					title : '�Ƿ���Ʒ',
					width : setColumnWidth(0.06),
					align : 'center',
					formatter : function(value, row, rec) {
						if (value == "Y") {
							return "��";
						} else {
							return "��";
						}

					}
				}, {
					field : 'recordTime',
					title : '¼��ʱ��',
					width : setColumnWidth(0.1),
					align : 'center',
					formatter : function(value, row, rec) {
						return utcToDate(row.stock_modify_date);
					}
				}]]
			// ,
			// toolbar : ["-", {
			// text : '����������',
			// iconCls : 'icon-redo',
			// handler : function() {
			// importStockExcel();
			// }
			// }, "-", {
			// text : '����ģ������',
			// iconCls : 'icon-excel',
			// handler : function() {
			// downloadStockExcelModel();
			//					}
			//				}, "-"]

	});
	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
				beforePageText : '��',
				afterPageText : 'ҳ    �� {pages} ҳ',
				displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
			});

	// �����̲�ѯ
	$('#custKunnr').combogrid({
				panelHeight : 365,
				panelWidth : 350,
				pagination : true,
				method : 'post',
				singleSelect : true,
				url : appUrl + '/kunnrAction!kunnrSearch.jspa?channelId=1133',
				idField : 'kunnr',
				textField : 'name1',
				columns : [[{
							field : 'kunnr',
							title : '�����̱��',
							align : 'center',
							width : 100
						}, {
							field : 'name1',
							title : '����',
							align : 'center',
							width : 200
						}]],
				toolbar : '#toolbarKonzs'
			});
	// Ʒ��
	$('#categories').combogrid({
				panelHeight : 250,
				panelWidth : 600,
				pagination : true,
				method : 'post',
				singleSelect : true,
				url : appUrl + '/material!getMaterialJsonList.jspa',
				idField : 'materialNumber',
				textField : 'materialName',
				// multiple : true,
				columns : [[{
							field : 'ck',
							checkbox : true
						}, {
							field : 'materialNumber',
							title : '���ϱ��',
							width : 60
						}, {
							field : 'materialName',
							title : '��������',
							width : 100
						}, {
							field : 'normt',
							title : '���',
							width : 60,
							align : 'center',
							sortable : true
						}, {
							field : 'materialSort',
							title : '��������',
							width : 60,
							align : 'center',
							sortable : true
						}, {
							field : 'materialUnit',
							title : '������λ',
							width : 60,
							align : 'center',
							sortable : true
						}, {
							field : 'materialUnitWeight',
							title : '��������',
							width : 60,
							align : 'center',
							formatter : function(value) {
								if (undefined == value) {
									return 0 + "KG";
								} else {
									return value + "KG";
								}

							}
						}, {
							field : 'materialUnit1',
							title : '������λ',
							width : 60,
							align : 'center',
							sortable : true
						}, {
							field : 'materialUnit1Weight',
							title : '��������',
							width : 60,
							align : 'center',
							formatter : function(value) {
								if (undefined == value) {
									return 0 + "KG";
								} else {
									return value + "KG";
								}
							}
						}]],
				toolbar : '#toolbarCategories'
			});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
//function search_1() {
//	var queryParams = $('#datagrid_1').datagrid('options').queryParams;
//	queryParams.custId = encodeURIComponent($("#id").combobox("getValue"));
//	$("#datagrid_1").datagrid('reload');
//}
// ��ѯ
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.warehouse_id = encodeURIComponent($("#warehouse")
			.combobox("getValue"));
	queryParams.categories = encodeURIComponent($("#categories")
			.combobox("getValue"));
	// queryParams.batch = encodeURIComponent($("#batch").val());
	// queryParams.stock_stock_place =
	// encodeURIComponent($("#stock_stock_place")
	// .combobox("getValue"));
	$("#datagrid").datagrid('load');
}

/**
 * ���������
 */
function clearValue() {
	document.forms[0].reset();
	$("#custKunnr").combogrid("setValue", '');
	$("#categories").combogrid("setValue", '');
	$("#stock_stock_place").combobox("setValue", '');
}

// ������
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}

// �̵����
function importStockCheck() {
	initMainFrame('�������̵�', '/stock/stockManageAction!importStockCheck.jspa',
			600, 300);
}

/** �������* */
function importStockExcel() {
	initMainFrame('����ֱӪ�ֿ����Ϣ��',
			'/stock/stockManageAction!importStockExcels.jspa', 600, 300);
}

// �������ڶ���
function initMainFrame(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintFrame").window({
		title : title,
		width : WWidth,
		height : WHeight,
		content : '<iframe frameborder="no" width="100%" height="100%" src='
				+ url + '/>',
		shadow : true,
		modal : true,
		closed : true,
		closable : true,
		minimizable : false,
		maximizable : false,
		collapsible : false,
		draggable : true
	});
	$win.window('open');
}

// �ر��ϴ�����
function closeMaintFrame() {
	$("#maintFrame").window('close');
}

// ���ģ������
function downloadStockExcelModel() {
	$.messager.confirm('Confirm', 'ȷ�����ؿ��ģ��?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/stock/stockManageAction!downloadExcelModel.jspa?excelModel='
					+ encodeURI("stock.xls");
			form.target = "hideFrame";
			form.submit();
		}
	});
}

// Ʒ��
function searcherCategories(value) {
	var val = encodeURIComponent(value);
	$('#categories').combogrid({
				url : appUrl
						+ '/goal/goalAction!getMaterialJsonList.jspa?value='
						+ val
			});
	$('#categories').combogrid("grid").datagrid('reload');
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};

function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}