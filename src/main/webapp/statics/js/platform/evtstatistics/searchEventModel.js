var eventIdGloab = "";
$(document).ready(function(){
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});

var type=[{'value':'1','text':'协同办公'},
          {'value':'2','text':'客户关系管理'},
          {'value':'3','text':'营销费用管理'},
          {'value':'4','text':'人事档案管理'},
          {'value':'5','text':'经销商管理'}];
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/evtstatistics/evtstatisticsAction!searchEventModelList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
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
						columns : [ [
								{
									field : 'modelName',
									title : '事务模板',
									width : setColumnWidth(0.12),
									align : 'center'

								},
								{
									field : 'modelId',
									title : '模板编号',
									width : setColumnWidth(0.12),
									align : 'center'

								},
								{
									field : 'modelType',
									title : '事务类型',
									width : setColumnWidth(0.12),
									align : 'center',
									formatter : function(value) {
										if (value == 1) {
											return "协同办公";
										}
										if (value == 2) {
											return "客户关系管理";
										}
										if (value == 3) {
											return "营销费用管理";
										}
										if (value == 4) {
											return "人事档案管理";
										}
										if (value == 5) {
											return "经销商管理";
										}
									},
									editor : {
										type : 'combobox',
										options : {
											data : type,
											valueField : 'value',
											textField : 'text'
										}
									}
								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.25),
									align : 'center',
									formatter : function(value, row, index) {
										if(row.editing){ 
											var s = '<a href="#" onclick="saverow(this)">保存</a>';
											var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
											return s+'&nbsp;'+c;
										}else{
											return '<img style="cursor:pointer" onclick="editrow(this)" title="修改" src='
											+ imgUrl
											+ '/images/actions/action_edit.png align="absMiddle"></img>';
										}
									}
								} ] ]
					,toolbar : [ "-",{
									text : '批量导入',
									iconCls : 'icon-add ',
									handler : function() {
										importCsv();
									}
								}, "-"]
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

function cancelrow(target){
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function saverow(target) {
	var rowIndex = getRowIndex(target);
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rows = $('#datagrid').propertygrid('getChanges');
	if(rows.length==0){
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/evtstatistics/evtstatisticsAction!updateEventModel.jspa",
		data : {
			modelId : rows[0].modelId,
			modelType : rows[0].modelType
		},
		success : function(total) {
			if(total>0){
				$.messager.alert('Tips', '修改成功！', 'info');
				loadGrid();
			}else{
				$.messager.alert('Tips', '修改失败！', 'error');
			}
		}
	});
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.modelName = encodeURIComponent($("#modelName").val());
	queryParams.modelType = $("#modelType").combobox('getValue');
	$("#datagrid").datagrid('load'); 
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
			if ($('#excelDialog').length > 0) {
				$('#excelDialog').dialog('close');
			}
			search();
		});
	}
}

function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>模板下载</td>'
			+ '<td><a style="color:blue" onclick=javascript:exportModelCsv();> 1、下载模版</a></td></tr>'
			+ '<tr><td class="head" noWrap>批量导入</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importModelCsv('批量导入', html);
}

//csv下载导入模板
function exportModelCsv() {
	$.messager.confirm('Confirm', '是否确定下载模板?', function(r) {
		if (r) {
			var form =document.getElementById("fileForm");
			form.action = appUrl
					+ '/evtstatistics/evtstatisticsAction!exportEventModelCsv.jspa';
			form.submit();
		}
	});

}

//批量导入信息
function importModelCsv(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '选择上传文件',
					html : "<div id='import'>"
					// + "</br>"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" // +
							// "</div>"
				}).appendTo('body');
	}
	$('#excelDialog')
			.dialog(
					{
						modal : true,
						resizable : false,
						dragable : false,
						closable : false,
						open : function() {
							$('#excelDialog').css('padding', '0.8em');
							$('#excelDialog .ui-accordion-content').css(
									'padding', '0.4em').height(
									$('#excelDialog').height() - 100);
						},
						buttons : [
								{
									text : '确定',
									handler : function() {
										/*
										 * if ($('#orgId01').val() == '' ||
										 * $('#orgId01').val() == undefined) {
										 * $.messager.alert("提示", "请选择所属组织");
										 * return; } if
										 * ($('#stationUserId01').val() == '' ||
										 * $('#stationUserId01').val() ==
										 * undefined) { $.messager.alert("提示",
										 * "请选择业务负责人"); return; }
										 */
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											openTime();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/evtstatistics/evtstatisticsAction!importEventModelCsv.jspa";
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "请导入csv文件");
										}

									}
								}, {
									text : '取消',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.35,
						height : document.documentElement.clientHeight * 0.55
					});
}

function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/evtstatistics/evtstatisticsAction!checkDownLoadOver.jspa?",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
					}
				}
			});
		}, 100);
	}, 500);
}

/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintWindowForOrg('选择组织', '/orgAction!orgTreePage.jspa');
}

// 关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','流程信息查看', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

//创建窗口对象
function initMaintEvent(ffit,widte,height,title, url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ urls + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});

	$win.window('open');
}

function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 460;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}