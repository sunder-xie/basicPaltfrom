$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl
								+ '/staffAmountAction!getStaffAmountList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : height,
						width : 'auto',
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'PId',
									title : '编制ID',
									width : 100,
									align : 'center',
									sortable : true
								},
								{
									field : 'orgParentName',
									title : '上级组织',
									width : 150,
									align : 'center'
								},
								{
									field : 'orgName',
									title : '组织',
									width : 150,
									align : 'center'
								},
								{
									title : "岗位名称",
									field : 'positionTypeName',
									width : 200,
									sortable : false,
									align : 'center'
								},
								{
									title : "<font color='red'>*</font>人资设定编制人数",
									field : 'rzamount',
									width : 150,
									sortable : false,
									align : 'center',
									editor : {
										type : 'text',
										options : {
											valueField : 'rzamount',
											textField : 'rzamount',
											// data:products,
											required : true
										}
									}
								},
								{
									title : "<font color='red'>*</font>编制人数",
									field : 'amount',
									width : 150,
									sortable : false,
									align : 'center',
									editor : {
										type : 'text',
										options : {
											valueField : 'amount',
											textField : 'amount',
											// data:products,
											required : true
										}
									}
								},
								{
									title : "主岗占编",
									field : 'amountU',
									width : 75,
									sortable : false,
									align : 'center',
									formatter : function(value, row, index) {
										return '<a href="#" onclick=javascript:geStaffUser("'
												+ encodeURIComponent(row.orgId)
												+ '","'
												+ encodeURIComponent(row.stationId)
												+ '","Y") > ' + value + '</a>';
									}
								},
								{
									title : "次岗占编",
									field : 'mountC',
									width : 75,
									sortable : false,
									align : 'center',
									formatter : function(value, row, index) {
										return '<a href="#" onclick=javascript:geStaffUser("'
												+ encodeURIComponent(row.orgId)
												+ '","'
												+ encodeURIComponent(row.stationId)
												+ '","N") > ' + value + '</a>';
									}
								},
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.15),
									align : 'center',
									formatter : function(value, row, index) {
										if (row.editing) {
											var s = '<a href="#" onclick="saverow(this)">保存</a> ';
											var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
											return s + c;
										} else {
											var e = '<img style="cursor:pointer"  title="修改编制数量" onclick="editrow(this)" src='
													+ imgUrl
													+ '/images/actions/action_edit.png align="absMiddle"></img> ';
											return e;
										}
									}
								/*
								 * formatter : function(value, row, index) { var
								 * id=row.id; return '<img
								 * style="cursor:pointer" onclick="updateStaff(' +
								 * id + ')" title="修改编制数量" src=' + imgUrl +
								 * '/images/actions/action_edit.png
								 * align="absMiddle"></img> '; }
								 */
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
								createStaff();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove ',
							handler : function() {
								deleteStaff();
							}
						}, "-" ]
					});

	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}
function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}
function editrow(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}
function saverow(target) {
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rows = $('#datagrid').propertygrid('getChanges');
	var s = '';
	var amount = '';
	var amountU = '';
	var mountC = '';
	for ( var i = 0; i < rows.length; i++) {
		s += rows[i].amount + "," + rows[i].PId;
		amount += rows[i].amount;
		amountU += rows[i].amountU;
		mountC += rows[i].mountC;
	}
	if (amount - amountU - mountC< 0) {
		search();
		return alert("编制数量不能少于已占用数量！");
	} else {
		$("#UpdateNum").val(s);
		var form = window.document.forms[0];
		form.action = appUrl + "/staffAmountAction!updateStaff.jspa";
		form.target = "hideFrame";
		form.submit();
	}
}
function cancelrow(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function renderStyle(value) {
	return '<a tooltip="' + value + '" class="easyui-tips">' + value + '</a>';
}
function search() {
	var bhxj_Flag;
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	/* queryParams.orgName = $("#orgName").val(); */
	queryParams.orgId = $("#orgId").val();
	queryParams.positionTypeName = encodeURIComponent($("#positionTypeName")
			.val());
	$("[name='bhxjFlag']").each(function() {
		if (this.checked) {
			bhxj_Flag = this.value;
		} else {
			bhxj_Flag = "N";
		}
	});
	queryParams.bhxjFlag = bhxj_Flag;
	$("#datagrid").datagrid('load');
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}
function createStaff() {
	initMaintStaff('编制创建', '/staffAmountAction!staffCreateSave.jspa');
}
// 创建窗口对象
function initMaintStaff(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 400;
	var $win = $("#maintStaff")
			.window(
					{
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
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}
function closeMaintStaff() {
	$("#maintStaff").window('close');
}
function deleteStaff() {
	$.messager.confirm('Confirm', '是否批量刪除编制?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  no selected rows!');
				return;
			}
			var PIds = [];
			for ( var i = 0; i < rows.length; i++) {
				PIds.push(rows[i].PId);
			}
			$("#PIds").val(PIds);
			var form = window.document.forms[0];
			form.action = appUrl + "/staffAmountAction!deleteStaff.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function choseOrg() {
	var url = appUrl + '/orgAction!toOrgTreeBySearch.jspa';
	var WWidth = 400;
	var WHeight = 400;
	var $win = $("#maintStaff")
			.window(
					{
						title : '组织选择',
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
						draggable : false
					});
	$win.window('open');
}
function closeOrgTree() {
	$("#maintStaff").window('close');
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
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

function geStaffUser(orgId, stationId, type) {
	initMaintStaff('查看人员', "/staffAmountAction!geStaffUserPre.jspa?orgId="
			+ orgId + "&stationId=" + stationId + "&type=" + type);
}
