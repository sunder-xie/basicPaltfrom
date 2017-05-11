var eventIdGloab = "";
$(document).ready(function() {
	loadGrid();
	//事务状态（0为未处理，1为处理中，2为处理完成，3为已拒绝，4表示取消）
	var data=[{'text':'-请选择-','value':''},{'text':'处理中','value':'1'},{'text':'已完成','value':'2'},{'text':'已作废','value':'3'},{'text':'已取消','value':'4'}];
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/wfe/eventAction!getRoleEventList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'eventId',
									title : '事务编号',
									width : setColumnWidth(0.05),
									align : 'center'
								},{
									field : 'currentDetailid',
									title : 'detailId',
									width : setColumnWidth(0.12),
									align : 'center',
									hidden : true
								},
								{
									field : 'eventTitle',
									title : '事务标题',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'empName',
									title : '提出者',
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
									field : 'curUserName',
									title : '当前处理人',
									width : setColumnWidth(0.10),
									align : 'center'

								},
								{
									field : 'creatdate',
									title : '提出时间',
									width : setColumnWidth(0.14),
									align : 'center'

								},
								{
									field : 'operation',
									title : '操作',
									width : setColumnWidth(0.25),
									align : 'center',
									formatter : function(value, row, index) {
										var strReturn = '<a href= javascript:ontransferEvent('+row.eventId+','+row.currentDetailid+')>接受转移事务 </a>|'
											+'<a href= javascript:searchEventDetail("'
											+ row.eventId
											+ '")>查看审批意见 </a>'
										return strReturn;
									}
								} ] ],
						toolbar : [ "-", {
							text : '批量接受所选事务',
							iconCls : 'icon-cancel',
							handler : function() {
								ontransferEvent();
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

/**
 * 初始化置灰的选项
 */


function search() {
//	var queryParams = $('#datagrid').datagrid('options').queryParams;
//	queryParams.eventId = encodeURIComponent($("#eventId").val());
//	queryParams.eventTitle = encodeURIComponent($("#eventTitle").val());
//	queryParams.initator = encodeURIComponent($("#initator").val());
//	queryParams.curUserName = encodeURIComponent($("#curUserName").val());
//	queryParams.modelName = encodeURIComponent($("#modelName").val());
//	queryParams.status = encodeURIComponent($("#status").combobox('getValue'));
	$('#datagrid').datagrid('reload');
}

//// 创建窗口对象
//function initWindow(title, url, id, WWidth, WHeight) {
//
//	var url = appUrl + url;
//	var $win = $("#" + id)
//			.window(
//					{
//						title : title,
//						width : WWidth,
//						height : WHeight,
//						content : '<iframe frameborder="no" width="100%" height="100%" src='
//								+ url + '/>',
//						shadow : true,
//						modal : true,
//						closed : true,
//						closable : true,
//						minimizable : false,
//						maximizable : false,
//						collapsible : false,
//						draggable : true
//					});
//
//	$win.window('open');
//}

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

//function searchProEventReader(eventId) {
//	initMaintEvent(true,'700','400','事务查阅人管理','/wfe/authorizeEventAction!toSearchEventReader.jspa?eventId='+ eventId, 0, 0);
//}

function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','流程信息查看', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}
//转移事务
function ontransferEvent(event,currentDetailid){
//	$("#maintWindow").window('close');
	var rows = $('#datagrid').datagrid('getSelections');
	var eventIds=rows[0].eventId;
	var currentDetailids=rows[0].currentDetailid;
	for ( var i = 1; i < rows.length; i++) {
		eventIds = eventIds+","+rows[i].eventId;
		currentDetailids=currentDetailids+","+rows[i].currentDetailid;
	}
	//有参数，就使用参数的，没有的话就用勾选的
	 eventIds=event!=null?event:eventIds;
	 currentDetailids=currentDetailid!=null?currentDetailid:currentDetailids;
	$.messager.confirm('Confirm', '确认接受事务编号:'+eventIds+"的事务?", function(r) {
		if (r) {
				$.messager.progress();
				var form = window.document.forms[0];
				form.action = "eventAction!transferEvent.jspa?eventIds=" + currentDetailids;
				form.target = "hideFrame";
				form.submit();
			}}
		);
}
//function cancelEvent(eventId,subFolders) {
//	var rows = $('#datagrid').datagrid('getSelections');
//	if (rows == '') {
//		$.messager.alert('Tips', '请选择数据!');
//		return;
//	}
//	$.messager.confirm('Confirm', '确认取消事务?', function(r) {
//		if (r) {
//			$.messager.progress();
//			var subFolders = "";
//			for ( var i = 0; i < rows.length; i++) {
//				eventIdGloab = rows[i].eventId;
//				subFolders = rows[i].subFolders;
//			}
//			  $.ajax({
//					type : "post",
//					url : appUrl
//							+ "/wfe/eventAction!selectAnyExecuteAction.jspa?time="
//							+ new Date(),
//					data : {
//						eventId : eventId,
//						operation : "N"
//					},
//					success : function(userUtil) {
//						if (userUtil != null&& userUtil.processInstanceId == "success") { // 最后一个处理人时调用
//							if (userUtil.executeAction != null&& userUtil.executeAction != "") {
//							$.ajax({
//									type : "get",
//									url : userUtil.executeAction,
//									data : {
//										eventId : eventId,
//										subFolders : subFolders,
//										operation : "N"
//									  },
//									  	dataType : 'jsonp',
//										jsonpCallback : "callback"
//									});
//						} else {
//							var form = window.document.forms[0];
//							form.action = "eventAction!cancelEvent.jspa?eventIds=" + eventId;
//							form.target = "hideFrame";
//							form.submit();
//						}
//					}
//				},
//				error : function() {
//					$.messager.progress('close');
//					$.messager.alert('Tips',"系统错误！", 'error');
//				}
//			});
//		}
//		;
//	});
//}

//function backProEvent(eventId, currentDetailid, curStaId, modelId,userId) {
//	$.messager.confirm('Confirm', '确认取回该事务事务?', function(r) {
//		if (r) {
//			var form = window.document.forms[0];
//			form.action = "eventAction!backAdminProEvent.jspa?eventIds=" + eventId
//					+ "&toDoDetail=" + currentDetailid + "&curStaId="
//					+ curStaId + "&modelId=" + modelId+"&userId="+userId;
//			form.target = "hideFrame";
//			form.submit();
//		}
//	});
//}

function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}

//function callback(executeResult) {
//	var res = executeResult.result;
//	var msg = executeResult.code;
//
//    if (res == "true") {
//    	var form = window.document.forms[0];
//		form.action = "eventAction!cancelEvent.jspa?eventIds=" + eventIdGloab;
//		form.target = "hideFrame";
//		form.submit();
//	} else {
//		if(msg != null){
//			$.messager.alert('Tips', msg, 'error', function() {
//				$.messager.progress('close');
//			});
//		}else{
//			$.messager.alert('Tips', "处理错误！", 'error', function() {
//					$.messager.progress('close');
//			});
//		}
//	}
//}