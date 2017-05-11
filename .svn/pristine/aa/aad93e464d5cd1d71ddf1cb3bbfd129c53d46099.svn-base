var panelWidth = document.getElementById("dd").offsetWidth;
$(document).ready(function() {
	$(".easyui-panel").panel({
		width : panelWidth*0.98
	});
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
//	loadGrid1();
//	loadGrid2();
	
	$('#beginDate').datebox({
		onSelect : function(d) {
				$('#beginDate').val(utcToDate(d));
		}
	});
	$('#endDate').datebox({
		onSelect : function(d) {
				$('#endDate').val(utcToDate(d));
		}
	});
	
	
});


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		var type = $("#deleteType").val();
		if(type == 'model'){
			search_1();
		}else if(type == 'att'){
			search_2();
		}
	}
}

function loadGrid() {
	$('#datagrid_1').datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/boformAction!getBORoleList.jspa',
//						queryParams: {
//                    		endDate: $("#endDate").val(),
//		                    beginDate:$("#beginDate").val(),
//	                    	custId : $("#id").combobox("getValue")
//	                          },
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						idField : 'roleId',
						nowrap : true,
						remoteSort : true,
						striped : true,
						width : panelWidth*0.98,
						height : height,
						columns : [[{
							field : 'ck',
							checkbox : true
						},{
					field : 'roleId',
					title : '角色ID',
					width : setColumnWidth(0.07),
					align : 'center',
					sortable : true
				}, {
					field : 'roleName',
					title : '角色名称',
					width : setColumnWidth(0.1),
					align : 'center'
					
				},{
					field : 'pid',
					title : '参数ID',
					width : setColumnWidth(0.1),
					align : 'center'
					
				},{
					field : 'descn',
					title : '描述',
					width : setColumnWidth(0.1),
					align : 'center'
					
				},{
					field : 'price',
					title : '操作',
					width : setColumnWidth(0.07),
					align : 'center',
					formatter : function(value, row, index) {
						     var id = row.roleId;
							return '<img style="cursor:pointer" onclick=updateBORole("'
												+ id
												+ '") title="修改报表角色" src='
									+ imgUrl
									+ '/images/actions/action_edit.png align="absMiddle"></img>';			
					}
				}]],
						      onClickRow:function(rowIndex, rowData){
						 	    $("#roleId").val(rowData.roleId);
//						 		$("#instock_total_id").val(rowData.instock_total_id);
//						 		$("#instock_provide_id").val(rowData.instock_provide_id);
//						 		$("#instock_good_place").val(rowData.instock_good_place);
    				            search_2();
    				            var options = $("#datagrid_2").datagrid("options");
        						options.title = "角色-- "+rowData.roleId + " -- 明细";
        						$("#datagrid_2").datagrid(options);
   							},
   						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createBORole();
							}
						},"-", {
							text : '删除',
							iconCls : 'icon-add',
							handler : function() {
								deleteBORole();
							}
						}, "-" ]
					});

	// 分页控件
	var p = $('#datagrid_1').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	$('#datagrid_2').datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/boformAction!getBORoleDetailList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						striped : true,
						width : panelWidth*0.98,
						height : height,
						columns : [ [{
							field : 'ck',
							checkbox : true
						},{
									field : 'bid',
									title : 'ID',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable : true
								},{   
								   field : 'memo',
									title : '字段',
									width : setColumnWidth(0.2),
									align : 'center'
								},{   
								   field : 'value',
									title : '值',
									width : setColumnWidth(0.15),
									align : 'center'
								}
								] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createBORoledet();
							}
						},"-", {
							text : '刪除',
							iconCls : 'icon-add',
							handler : function() {
								deleteBORoledet();
							}
						},"-"]
					});

	// 分页控件
	var r = $('#datagrid_2').datagrid('getPager');
	$(r).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '共 {total} 条记录'
	});
}

//function loadGrid2() {
//	$('#id').combogrid({
//		panelWidth : 400,
//		panelHight : 600,
//		idField : 'kunnr',
//		textField : 'name1',
//		pagination : true,// 是否分页
//		// rownumbers : true,// 序号
//		collapsible : false,// 是否可折叠的
//		// fit : true,// 自动大小
//		method : 'post',
//		// multiple : true,
//		url : appUrl + '/goal/goalAction!getKunner.jspa',
//		columns : [ [ {
//			field : 'ck',
//			checkbox : true,
//			hidden : true
//		},  {
//			field : 'id',
//			title : '客户ID',
//			hidden : true,
//			width : 60
//		},
//			{
//			field : 'kunnr',
//			title : '客户编号',
//			align : 'center',
//			width : 120
//		}, {
//			field : 'name1',
//			title : '客户名称',
//			align : 'center',
//			width : 100
//		}, {
//			field : 'mobNumber',
//			title : '手机',
//			align : 'center',
//			width : 150
//		}] ],
//		toolbar : '#toolbar2'
//	});

//}

//function loadGrid1() {
//	$('#wid').combogrid({
//		panelWidth : 400,
//		panelHight : 600,
//		idField : 'matnr',
//		textField : 'bezei',
//		pagination : true,// 是否分页
//		// rownumbers : true,// 序号
//		collapsible : false,// 是否可折叠的
//		// fit : true,// 自动大小
//		method : 'post',
//		// multiple : true,
//		url : appUrl + '/goal/goalAction!getMatList.jspa',
//		columns : [ [ {
//			field : 'ck',
//			checkbox : true,
//			hidden : true
//		},  {
//			field : 'matnr',
//			title : '物料号',
//			width : 120
//		},
//			{
//			field : 'mvgr1',
//			title : '物料组',
//			align : 'center',
//			width : 120
//		}, {
//			field : 'bezei',
//			title : '物料组描述',
//			align : 'center',
//			width : 100
//		}] ],
//		toolbar : '#toolbar1'
//	});
//}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function search_1() {
	var queryParams = $('#datagrid_1').datagrid('options').queryParams;
    queryParams.roleId = $("#roleId1").val();
    queryParams.roleName = $("#roleName").val();
	$("#datagrid_1").datagrid('reload');
}
function deleteBORole() {
	$.messager.confirm('Confirm', '是否批量刪除角色?', function(r) {
		if (r) {
			var rows = $('#datagrid_1').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  没有行被选中!');
				return;
			}
			$("#deleteType").val("model");
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].roleId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "roleAction!deleteRole.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function deleteBORoledet() {
	$.messager.confirm('Confirm', '是否批量刪除角色?', function(r) {
		if (r) {
			var roleId = $("#roleId").val();
			var rows = $('#datagrid_2').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  没有行被选中!');
				return;
			}
				if(roleId=="" || roleId.length==0){
		   $.messager.alert('Tips', "请选择一行总单!", 'warning');
		     return;
	 }
			$("#deleteType").val("att");
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].bid);
			}
			$("#ids1").val(ids);
			var form = window.document.forms[0];
			form.action = "roleAction!deleteRoledt.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function clearValue_1(){
	$('#beginDate').val("");
    $('#endDate').val("");
	$("#beginDate").datebox("setValue", '');
	$("#endDate").datebox("setValue", '');
	$("#id").combobox("setValue", '');
}

function search_2() {
	var queryParams = $('#datagrid_2').datagrid('options').queryParams;
	queryParams.type = 'Y';
	queryParams.roleId = $("#roleId").val();
	queryParams.memo =$("#memo").val();
	queryParams.value =$("#value").val();
	$("#datagrid_2").datagrid('reload');
}

function clearValue_2(){
	$("#instockdetBatch").val("");
	$("#wid").combobox("setValue", '');
//	$("#mprAttDescript").val("");
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#id').combogrid({
		url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value=' + val
	});
	$('#id').combogrid("grid").datagrid('reload');

}

function searcher1(val) {
	val = encodeURIComponent(val);
	$('#wid').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#wid').combogrid("grid").datagrid('reload');

}


function createBORole(){
	initMaintModelAtt('创建报表角色','/boformAction!createBoRolePrepare.jspa', 600, 350);
}

function updateBORole(id){
	var id = encodeURIComponent(id);
   initMaintModelAtt('修改报表角色',  '/boformAction!updateBORolePrepare.jspa?roleId=' + id,
			600, 350);

}

function createBORoledet(){
//	var instock_total_id = $("#instock_total_id").val();
	var roleId = $("#roleId").val();
//	var instock_provide_id=$("#instock_provide_id").val();
//	var instock_good_place= $("#instock_good_place").val();
	if(roleId=="" || roleId.length==0){
		$.messager.alert('Tips', "请选择一行总单!", 'warning');
		return;
	}
	initMaintModelAtt('添加明细', '/boformAction!createBORoledtPrepare.jspa?roleId='+ roleId, 600, 350);
}

//function updateMprAtt(rid){
//	initMaintModelAtt('修改总单', '/instockAction!updateStockPrepare1.jspa?instock_total_id='+ instock_total_id, 450, 400);
//}

function closeMaintModelAtt(){
	$("#maintModelAtt").window('close');
}

function deleteMprModel(rid){
	$("#deleteType").val("model");
	$("#ids").val(rid);
	var form = window.document.forms[0];
	form.action = appUrl + '/activityPlan/miaModelAction!deleteMprModel.jspa';
	form.target = "hideFrame";
	form.submit();	
}

function deleteMprAtt(){
	$.messager.confirm('Confirm', '是否删除模板属性?', function(r) {
		if (r) {
			var rows = $('#datagrid_2').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '请选择数据!', 'warning');
				return;
			}
			$("#deleteType").val("att");
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].mprAttId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl + '/activityPlan/miaModelAction!deleteMprAttribute.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});		
}

//创建窗口对象
function initMaintModelAtt(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintModelAtt")
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
						maximizable : false,
						collapsible : false,
						draggable : true
					});
				
	$win.window('open');
}

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
	date = date + month[str[1]] + "-" + str[2] ;
	return date;
}
