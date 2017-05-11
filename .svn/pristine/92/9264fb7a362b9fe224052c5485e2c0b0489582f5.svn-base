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
		title : '查询结果',
		height : height,
		striped : true,
		url : appUrl + '/reportAction!getData.jspa?rptId=5',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		nowrap : true,
		pagination : true,
		rownumbers : true,
		columns : [[{
					field : 'warehouse_name',
					title : '仓库',
					align : 'center',
					width : setColumnWidth(0.1)
				}, {
					field : 'category_id',
					title : '物料编号',
					width : setColumnWidth(0.1),
					align : 'center'
				}, {
					field : 'categoryName',
					title : '物料',
					width : setColumnWidth(0.15),
					align : 'center'
				}, {
					field : 'cargoSpec',
					title : '规格',
					width : setColumnWidth(0.1),
					align : 'center'
				}
				// , {
				// field : 'batch',
				// title : '批次',
				// width : setColumnWidth(0.1),
				// align : 'center'
				// }
				, {
					field : 'stock_quantity',
					title : '数量',
					width : setColumnWidth(0.1),
					align : 'center'
				}, {
					field : 'uptop',
					title : '辅助数量（箱）',
					width : setColumnWidth(0.1),
					align : 'center',
					formatter : function(value, row, rec) {
						var xxx = 0;
						if (row.stock_quantity % row.assistQuan == 0) {// 取%
							// 不够+1
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
					title : '单位',
					width : setColumnWidth(0.06),
					align : 'center'
				}, {
					field : 'premiums',
					title : '是否赠品',
					width : setColumnWidth(0.06),
					align : 'center',
					formatter : function(value, row, rec) {
						if (value == "Y") {
							return "是";
						} else {
							return "否";
						}

					}
				}, {
					field : 'recordTime',
					title : '录入时间',
					width : setColumnWidth(0.1),
					align : 'center',
					formatter : function(value, row, rec) {
						return utcToDate(row.stock_modify_date);
					}
				}]]
			// ,
			// toolbar : ["-", {
			// text : '导入库存数据',
			// iconCls : 'icon-redo',
			// handler : function() {
			// importStockExcel();
			// }
			// }, "-", {
			// text : '库存表模板下载',
			// iconCls : 'icon-excel',
			// handler : function() {
			// downloadStockExcelModel();
			//					}
			//				}, "-"]

	});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
				beforePageText : '第',
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			});

	// 经销商查询
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
							title : '经销商编号',
							align : 'center',
							width : 100
						}, {
							field : 'name1',
							title : '名称',
							align : 'center',
							width : 200
						}]],
				toolbar : '#toolbarKonzs'
			});
	// 品项
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
							title : '物料编号',
							width : 60
						}, {
							field : 'materialName',
							title : '物料名称',
							width : 100
						}, {
							field : 'normt',
							title : '规格',
							width : 60,
							align : 'center',
							sortable : true
						}, {
							field : 'materialSort',
							title : '物料类型',
							width : 60,
							align : 'center',
							sortable : true
						}, {
							field : 'materialUnit',
							title : '基本单位',
							width : 60,
							align : 'center',
							sortable : true
						}, {
							field : 'materialUnitWeight',
							title : '基本重量',
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
							title : '辅助单位',
							width : 60,
							align : 'center',
							sortable : true
						}, {
							field : 'materialUnit1Weight',
							title : '辅助重量',
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
// 查询
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
 * 清空搜索框
 */
function clearValue() {
	document.forms[0].reset();
	$("#custKunnr").combogrid("setValue", '');
	$("#categories").combogrid("setValue", '');
	$("#stock_stock_place").combobox("setValue", '');
}

// 经销商
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}

// 盘点表导入
function importStockCheck() {
	initMainFrame('导入库存盘点', '/stock/stockManageAction!importStockCheck.jspa',
			600, 300);
}

/** 导入库存表* */
function importStockExcel() {
	initMainFrame('导入直营仓库存信息表',
			'/stock/stockManageAction!importStockExcels.jspa', 600, 300);
}

// 创建窗口对象
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

// 关闭上传窗口
function closeMaintFrame() {
	$("#maintFrame").window('close');
}

// 库存模板下载
function downloadStockExcelModel() {
	$.messager.confirm('Confirm', '确认下载库存模版?', function(r) {
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

// 品项
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