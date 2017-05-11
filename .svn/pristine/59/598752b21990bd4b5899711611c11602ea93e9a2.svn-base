$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
	
	$('#planAttFlag').combobox({
		valueField : 'flagValue',
		textField : 'flagText',
		data : [
			{'flagValue' : 'Y', 'flagText' : '启用'},
			{'flagValue' : 'N', 'flagText' : '禁用'}
		],
		editable : false
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
		if(type == 'total'){
			search_1();
		}else if(type == 'detail'){
			search_2();
		}
	}
}

function loadGrid() {
	$('#datagrid_1').datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/wfe/modelAttributeAction!getModelAttributeJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'planAttId',
									title : '属性ID',
									align : 'center',
									hidden: true
								},
								{
									field : 'planTypeName',
									title : '事务类别',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'modelName',
									title : '所属流程模板',
									width : setColumnWidth(0.14),
									align : 'center'
								},
								{
									field : 'planAttMemo',
									title : '备注信息',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'planAttFlag',
									title : '启用/禁用',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(v){
										if(v == 'Y'){
											return "启用";
										}else{
											return "禁用";
										}
									}
								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										var rid=row.planAttId;
										return '<img style="cursor:pointer"  onclick=updatePlanAtt("'
											+rid
											+'") title="修改资料"  src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>'
												+ '&nbsp;&nbsp;<img style="cursor:pointer"  onclick=deletePlanAtt("'
												+ rid
												+ '") title="禁用" src='
												+ imgUrl
												+ '/images/actions/action_del.png align="absMiddle"></img>';
									}
								} ] ],
						 onClickRow:function(rowIndex, rowData){
						 		$("#planAttId").val(rowData.planAttId);
    				            search_2();
    				            var options = $("#datagrid_2").datagrid("options");
        						options.title = rowData.planTypeName + " -- 属性明细";
        						$("#datagrid_2").datagrid(options);
   							},
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createPlanAtt();
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
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	$('#datagrid_2').datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/wfe/modelAttributeAction!getModelAttributeDetailList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'planAttDetailId',
									title : '属性ID',
									align : 'center',
									hidden: true
								},
								{
									field : 'planAttContent',
									title : '属性名',
									width : setColumnWidth(0.12),
									align : 'center'
								},
								{
									field : 'planAttDataType',
									title : '数据类型',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'planAttKey',
									title : '流程Key',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										var rid = row.planAttDetailId;
										return '<img style="cursor:pointer"  onclick=updateAttDetail("'
											+ rid
											+ '") title="修改属性"  src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								createAttDetail();
							}
						}, "-" , {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								deleteAttDetail();
							}
						}, "-"]
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function search_1() {
	var queryParams = $('#datagrid_1').datagrid('options').queryParams;
	queryParams.modelName = encodeURIComponent($("#modelName").val());
//	queryParams.planTypeName =  encodeURIComponent($("#planTypeName").val());
	queryParams.planAttFlag = $('#planAttFlag').combobox('getValue');
	$("#datagrid_1").datagrid('reload');
}

function clearValue_1(){
	$("#modelName").val("");
//	$("#planTypeName").val("");
	$("#planAttFlag").combobox("setValue", '');
}

function search_2() {
	var queryParams = $('#datagrid_2').datagrid('options').queryParams;
	queryParams.planAttContent = encodeURIComponent($("#planAttContent").val());
	queryParams.rid = $("#planAttId").val();
	$("#datagrid_2").datagrid('reload');
}

function clearValue_2(){
	$("#planAttContent").val("");
}

function createPlanAtt(){
	initMaintModelAtt('添加属性模板', '/wfe/modelAttributeAction!createModelAttPrepare.jspa', 740, 430);
}

function updatePlanAtt(rid){
	initMaintModelAtt('修改属性模板', '/wfe/modelAttributeAction!toUpdateModelAtt.jspa?rid='+rid, 740, 430);
}

/**
 * 禁用属性模板
 * @param {} rid
 */
function deletePlanAtt(rid){
	$("#deleteType").val("total");
	$("#ids").val(rid);
	var form = window.document.forms[0];
	form.action = appUrl + "/wfe/modelAttributeAction!deleteModelAtt.jspa";
	form.target = "hideFrame";
	form.submit();		
}

function createAttDetail(){
	var planAttId = $("#planAttId").val();
	if(planAttId=="" || planAttId.length==0){
		$.messager.alert('Tips', "请选择一行模板事务类别查看属性明细!", 'warning');
		return;
	}
	initMaintModelAtt('添加模板属性明细', '/wfe/modelAttributeAction!createAttDetailPrepare.jspa?rid='+planAttId, 740, 430);
}

function updateAttDetail(rid){
	initMaintModelAtt('修改模板属性明细', '/wfe/modelAttributeAction!updateAttDetailPrepare.jspa?rid='+rid, 740, 430);	
}

function deleteAttDetail(){
	$.messager.confirm('Confirm', '是否删除模板属性?', function(r) {
		if (r) {
			var rows = $('#datagrid_2').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '请选择数据!', 'warning');
				return;
			}
			$("#deleteType").val("detail");
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].planAttDetailId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl + '/wfe/modelAttributeAction!deleteAttDetail.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function closeMaintModelAtt(){
	$("#maintModelAtt").window('close');
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
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}
