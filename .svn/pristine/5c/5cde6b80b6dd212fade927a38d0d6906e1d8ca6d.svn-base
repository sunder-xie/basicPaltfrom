$(document).ready(function() {
	$('#hideFrameWfe').bind('load', promgtMsg);
	loadGrid();
	//事务状态（0为未处理，1为处理中，2为处理完成，3为已拒绝，4表示取消）
	var data=[{'text':'未处理','value':'0'},{'text':'处理中','value':'1'},{'text':'已完成','value':'2'},{'text':'已作废','value':'3'},{'text':'已取消','value':'4'}];
	$('#status').combobox(
			{
				data:data,
				valueField : 'value',
				textField : 'text',
				multiple:false,
				onLoadSuccess : function() {
				},
				editable : false
			});
	
	
});
function promgtMsg() {
	$.messager.progress('close');
	var hideFrameWfe = document.getElementById("hideFrameWfe");
	var failResult = hideFrameWfe.contentWindow.failResult;
	var successResult = hideFrameWfe.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
		   
			$('#datagrid').datagrid('reload');
		});
	}
}
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/wfe/eventAction!getProcessedEventJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'eventId',
									title : '事务编号',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'currentDetailid',
									title : 'detailId',
									width : setColumnWidth(0.10),
									align : 'center',
									hidden : true
								},
								{
									field : 'eventTitle',
									title : '事务标题',
									width : setColumnWidth(0.11),
									align : 'center'
									
								},
								{
									field : 'initator',
									title : '提出者id',
									width : setColumnWidth(0.10),
									align : 'center',
									hidden : true
									
								},
								{
									field : 'empName',
									title : '提出者',
									width : setColumnWidth(0.08),
									align : 'center'
									
								},
								{
									field : 'orgId',
									title : '提出者组织Id',
									width : setColumnWidth(0.10),
									align : 'center',
									hidden : true
									
								},
								{
									field : 'orgName',
									title : '提出者组织',
									width : setColumnWidth(0.10),
									align : 'center'
									
								},
								{
									field : 'modelName',
									title : '事务模板',
									width : setColumnWidth(0.12),
									align : 'center'
									
								},
								{
									field : 'status',
									title : '事务状态',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value){
										if (value == 0) {
											return "未处理";
										}
										if (value == 1) {
											return "处理中";
										}
										if (value == 2) {
											return "已完成";
										}
										if (value == 3) {
											return "已作废";
										}
										if (value == 4) {
											return "已取消";
										}
									}
								},
								{
									field : 'curUserName',
									title : '当前处理人',
									width : setColumnWidth(0.08),
									align : 'center'
									
								},
								{
									field : 'creatdate',
									title : '提出时间',
									width : setColumnWidth(0.14),
									align : 'center'
									
								},
								{
									field : 'beforstaid',
									title : '前一个角色ID',
									width : setColumnWidth(0.14),
									align : 'center',
									hidden :true
									
								},{
									field : 'beforuserid',
									title : '前一个userid',
									width : setColumnWidth(0.14),
									align : 'center',
									hidden :true
									
								},{
									field : 'user_id',
									title : '当前审批人userid',
									width : setColumnWidth(0.14),
									align : 'center',
									hidden :true
									
								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.20),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn="";
										if((row.status==1&&$('#userId').val() == row.beforuserid)&&$('#userId').val() != row.user_id){
											strReturn=strReturn+'<a href=javascript:backProcess("'
											+ row.eventId 
											+'","'
											+row.beforstaid
											+'","'
											+row.beforuserid
											+ '","'
											+row.currentDetailid
											+'","'
											+row.user_id
											+'","'
											+row.modelId+'") >撤销</a>|';
										}
										
										 strReturn = strReturn+'<a href= javascript:searchEventDetail("'
												+ row.eventId + '")>查看审批意见 </a>|'
												+'<a href=javascript:graphTrace("'
												+ row.eventId + '") > 流程 </a>|'
												+ '<a href=javascript:searchProEventReader("'
												+ row.eventId + '") > 授权查看</a>';
										
										return strReturn;
									}
								} 
								] ]
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
function backProcess(eventId,beforstaid,beforuserid,tododetail,user_id,modelId){
	
	$.messager.confirm('Confirm', '确认撤销事务?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = "eventAction!backProcessEvent.jspa?curStaIdBack=" + beforstaid+"&key="+eventId+"&toDoDetail="+tododetail+'&modelId='+modelId+"&user_id="+user_id;
			form.target = "hideFrameWfe";
			form.submit();
			
		}
	});
}
function search() {

	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = encodeURIComponent($("#eventId").val());
	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
	queryParams.initator = encodeURIComponent($("#initator").val());
	queryParams.modelName = encodeURIComponent($("#modelName").val());
	queryParams.orgName = encodeURIComponent($("#orgName").val());
	
	queryParams.status = encodeURIComponent($("#status").combobox('getValue'));
	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

//创建窗口对象
function initWindow(title, url, id, WWidth, WHeight) {
	
	var url = appUrl + url;
	var $win = $("#"+id)
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

function searchProEventReader(eventId) {
	initWindow('事务查阅人管理', '/wfe/authorizeEventAction!toSearchEventReader.jspa?eventId='
	+ eventId, 'maintWindow', 800, 480);
}

function searchEventDetail(eventId) {
	initMaintEvent(true,800, 480,'事务处理结果', "/wfe/eventAction!toProcessEvent.jspa?event_id="
			+ eventId,0,0);
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
/**
 * 打开组织树
 */
function selectOrgTree() {
	initWindow('选择组织', '/orgAction!orgTreePage.jspa','maintWindow',300,450);
}

/**
 * 组织树的返回值接受
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}

