jQuery.support.cors = true;
var golbalOperation;// 全局变量 定义操作类型
var actionId = $("#actionId").val();
var eventId = $("#eventId").val();
var subFolders = $("#subFolders").val();
var modelId = $("#modelId").val();
var operationType = $("#operationType").val();
var flag = "";
var userUtil;
var curRole=$("#curStaId").val();
var arr=curRole.split('_');
var role=arr[0];
var n=appUrl.lastIndexOf("/");
var appUrl1=appUrl.substring(0,n);

$(document).ready(function() {
	$('#hideFrameWfe').bind('load', promgtMsg);
});
function iframeResize() {
	var iframe = document.getElementById("iframe2");
	try {
		var bHeight = iframe.contentWindow.window.document.body.scrollHeight;
		var dHeight = iframe.contentWindow.window.document.documentElement.scrollHeight;
		iframe.height = Math.max(bHeight, dHeight);
	} catch (e) {
		iframe.height = "350px";
	}
	window.setTimeout("iframeResize()", 200);
}
function selectAddUsers(){
	
	initWindow('半自动化流程绘制', appUrl
			+ '/wfe/eventAction!toSemiAutomatic.jspa',"maintWindow", 750,450);
}
function chooseFlags(flag) {
	if (flag == "M") {
		var showflag = document.getElementById("showflag");
		$(showflag).show();
	} else {
		var showflag = document.getElementById("showflag");
		$(showflag).hide();

	}
}

function refuse() {
	sub('N');
}
function submit(){
	$.ajax({
		url : appUrl + '/wfe/eventAction!completeSign.jspa?eventId='+$('#eventId').val(),
		success:function(data) {
			   if (data==0) {
					$.ajax({
						url : appUrl + '/wfe/eventAction!checkProcessUserId.jspa?eventId='+$('#eventId').val(),
						success:function(data) {
							   if (data==1) {
								   agree();
								}else{
									$.messager.alert('Tips',"此节点已被他人处理！",'info');
								}
							},
					})
				}else{
					$.messager.alert('Tips',"该事务有会签未处理！",'error');
				}
			},
	})

}
function agree() {
	if(role=="purchasingAgent"){
		$.ajax({
			type : "post",
			async : false,
			url : appUrl1
					+ "/officePlatform/extraPurchase/unplanAction!searchCompleteOrder.jspa?time="
					+ new Date(),
			data : {
				eventId : $("#eventId").val()
			},
			success : function(unplanDetail) {
				if (unplanDetail.eventStateId != "D") {
					$.messager.alert('Tips',"请先下采购单！",'error');
					return;
				}else{
					sub('Y');
				}
			},
			error : function() {
				$.messager.alert('Tips',"系统错误！", 'error');
				return;
			}
		});
	}else 
	if(role=="fixedAssetCountersign" ||
			role=="fixedAssetsAccounting" ||
			role=="purchaseJudge" ||
			role=="purchasingAgent" ||
			role=="stockman"){
		$.ajax({
			type : "post",
			async : false,
			url : appUrl1
					+ "/officePlatform/extraPurchase/unplanAction!searchUnplanApplyByeventId.jspa?time="
					+ new Date(),
			data : {
				eventId : $("#eventId").val()
			},
			success : function(unplanDetail) {
			   if (role=="fixedAssetCountersign" && unplanDetail.eventStateId != "A") {
					$.messager.alert('Tips',"请先填写固定资产调剂数量！",'error');
					return;
				}else if (role=="fixedAssetsAccounting" && unplanDetail.eventStateId != "B") {
					$.messager.alert('Tips',"请先填写固定资产编号！",'error');
					return;
				}else if (role=="purchaseJudge" && unplanDetail.eventStateId != "C") {
					$.messager.alert('Tips',"请先完成采购评审！",'error');
					return;
				}else if (role=="stockman" && unplanDetail.eventStateId != "E") {
					$.messager.alert('Tips',"请先完成资产领用！",'error');
					return;
				}else{
					sub('Y');
				}
			},
			error : function() {
				$.messager.alert('Tips',"系统错误！", 'error');
				return;
			}
		});
		
	}else{
		sub('Y');
	}
}

function disagree() {
	sub('D');
}
function sub(operation) {
	try {
		var title = "";
		if (operation == 'Y') {
			title = "是否同意该事务？";
		} else if (operation == 'H') {
			title = "是否回退该事务？";
		} else if (operation == 'D') {
			title = "是否不同意该事务？";
		} else {
			title = "是否作废该事务？";
		}
		$.messager.confirm('Confirm',title,function(r) {
							if (r) {
								golbalOperation = operation;
								var fix_key = modelId.substr(0, 3);
								$.messager.progress();
								var content = $("#memo").val();
								$.ajax({    type : "post",
									url : appUrl
											+ "/wfe/eventAction!selectProcessNexUser.jspa?time="
											+ new Date(),
									data : {
										memo : encodeURIComponent(content),
										operation : operation,
										eventId : $("#eventId").val(),
										keys : $("#keys").val(),
										toDoDetail : $("#toDoDetail")
												.val(),
										modelId : $("#modelId").val(),
										curStaId : $("#curStaId").val()
									},
									success : function(userUtil1) {
									   userUtil = userUtil1;
									   if (userUtil == null || userUtil == "" || userUtil.processInstanceId == undefined) {
											$.messager.progress('close');
											$.messager.alert('Tips',"流程出错，请维护！",'error');
											return;
										}else if (userUtil.processInstanceId == "-1") {
											$.messager.progress('close');
											$.messager.alert('Tips',"流程出错，请维护！",'error');
											return;
										}
										else if (userUtil != null&& userUtil.processInstanceId == "success") { // 最后一个处理人时调用
											if (userUtil.executeAction != null&& userUtil.executeAction != "") {
												flag = "1";
												$.ajax({    type : "get",
															url : userUtil.executeAction,
															data : {
																eventId : eventId,
																subFolders : subFolders,
																operation : operation
															},
															dataType : 'jsonp',
															jsonpCallback : "callback"
														});
											} else {
												var form = window.document.forms[0];
												form.action = appUrl
														+ "/wfe/eventAction!uploadAttachments.jspa";
												form.target = "hideFrameWfe";
												form.submit();
											}
										} else if (userUtil != null&& userUtil.processInstanceId == "last") {
											if (userUtil.executeAction != null&& userUtil.executeAction != "") {
												flag = "2";
												$.ajax({    type : "get",
															url : userUtil.executeAction,
															data : {
																eventId : eventId,
																subFolders : subFolders,
																operation : operation
															},
															dataType : 'jsonp',
															jsonpCallback : "callback"
														});
											} else {
												$.messager.progress('close');
												$.messager.alert("处理成功！",	'info',function() {
													window.parent.search();
													window.parent.closeMaintWindow();
												});
											}
										}else if ($("#curStaId").val() == "start" && userUtil != null 
											&& userUtil.executeAction != null&& userUtil.executeAction != "") {
												flag = "4";
												$.ajax({    type : "get",
															url : userUtil.executeAction,
															data : {
																eventId : eventId,
																subFolders : subFolders,
																operation : operation
															},
															dataType : 'jsonp',
															jsonpCallback : "callback"
														});
										}else{
											if (operation=='D') {//针对特定的流程不同意按钮
												//contineDisagree(operation);
											} else {
												contineProcess();
											}
										}
									},
									error : function() {
										$.messager.progress('close');
										$.messager.alert('Tips',
												"系统错误！", 'error');
									}
								});
							}
						});
	} catch (e) {
		$.messager.alert('ERROR', e.message, 'error');
	}
	
}

function back() {
	initWindow('选择角色', appUrl
			+ '/wfe/eventAction!toSearchStationId.jspa?eventId='
			+ $("#eventId").val(), 'maintWindow', 370, 400);
}

function contineProcess(){
	var total = 0;
	var nextUserId = "";
	$.each(userUtil.result,function(i,	v) {
		total = i+1;
		nextUserId = v.userId;
	});
	if(total == 1){
		var form = window.document.forms[0];
		form.action = appUrl+ "/wfe/eventAction!processCommit.jspa?nextUserId="+ nextUserId;
		form.target = "hideFrameWfe";
		form.submit();
	}else{
		$.messager.progress('close');
		var positionHtml = "<div class='easyui-panel' title='下个处理' data-options='collapsible:true'>"
				+ "<table width='100%' border='0' cellpadding='0' cellspacing='1'>"
				+ "<tr><td class='head' noWrap>处理人</td>"
				+ "<td><select id='nextUserId' name='nextUserId'>";
		$.each(userUtil.result,function(i,	v) {
			positionHtml += "<option value='"
						 + v.userId
						 + "'>"
						 + v.userName
						 + "----"
						 + v.stationName
						 + "</option>";
		});
		positionHtml += "</select></td></tr></table></div>";
		if ($('#nextUserDialog').length < 1) {
			$('<div/>',{
						id : 'nextUserDialog',
						title : '选择下个处理人',
						html : "<div id='nextUser'>"
								+ positionHtml
								+ "</div>"
								+ "</div>"
					}).appendTo('body');
		} else {
			$('#nextUser').html(positionHtml);
		}
		$('#nextUserDialog').dialog(
						{
							modal : true,
							resizable : false,
							dragable : false,
							closable : false,
							open : function() {
								$('#nextUserDialog').css('padding','0.4em');
								$('#nextUserDialog .ui-accordion-content').css('padding','0.4em').height(
								$('#nextUserDialog').height() - 75);
							},
							buttons : [{
										text : '确定',
										handler : function() {
											if ($("#nextUserId").val() == ""|| $("#nextUserId").val() == null) {
												$.messager.alert('Tips',"没有下个处理人，请维护！",'error');
												return;
											}
											$.messager.progress();
											var form = window.document.forms[0];
											form.action = appUrl+ "/wfe/eventAction!processCommit.jspa?nextUserId="+ $("#nextUserId").val();
											form.target = "hideFrameWfe";
											form.submit();
										}
									},
									{
										text : '取消',
										handler : function() {
											$('#nextUserDialog').dialog('close');
											//self.location.reload();
										}
									} ],
									width : document.documentElement.clientWidth * 0.50,
									height : document.documentElement.clientHeight * 0.40
		});
	}

}

function promgtMsg() {
	$.messager.progress('close');
	var hideFrameWfe = document.getElementById("hideFrameWfe");
	var failResult = hideFrameWfe.contentWindow.failResult;
	var successResult = hideFrameWfe.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			$('#nextUserDialog').dialog('close');
			window.parent.search();
			window.parent.closeMaintWindow();
		});
	}
}
/*******************************************************************************
 * 成功后跳转到待办页面
 */
function successRe() {
	if ($("#operationType").val() == "process") {
		/*var form = document.getElementById("processed");
		form.action = appUrl + "/wfe/eventAction!toSearchProcessEvent.jspa ";
		form.target = "_self";
		form.submit();*/
		window.close();
		window.parent.opener.search();
	} else {
		history.go(-1);
	}

}

function openOrgtree() {
	initWindow('选择联系人', appUrl + '/allUserAction!toShowUserByOrgId.jspa',
			'maintWindow', 660, 430);
}

function linkMan() {
	initWindow('常用联系人', appUrl + '/wfe/eventAction!searchLinkMan.jspa',
			'maintWindow', 340, 430);
}


/**
 * 保存联系人
 * 
 * @param {}
 *            x
 */
function saveUser(x) {
	$("#nextUserId").val(x[0]);
	$("#nextUserName").val(x[1]);
	$("#nextOrgId").val(x[2]);
	closeMaintEvent();
}

function saveBackUser(x) {
	$("#nextUserId").val(x[0]);
	$("#nextUserName").val(x[3]);
	$("#nextOrgId").val(x[1]);
	backUserIdList=x[5]+$("#userId").val();
	sub('H');
	closeMaintEvent();
}

function searchAllUserInfo(userId) {
	initWindow("查看联系人", appUrl
			+ "/alluser/allUserAction!searchAllUserInfo.jspa?userId=" + userId,
			"maintWindow", 460, 450);
}
function initWindow(title, url, id, WWidth, WHeight) {
	var $win = $("#" + id)
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

/**
 * 关闭弹出窗口
 */
function closeMaintEvent() {
	$("#maintWindow").window('close');
}
function shortcuts(userName, userId, orgId) {
	document.getElementById("nextUserName").value = userName;
	document.getElementById("nextUserId").value = userId;
	document.getElementById("nextOrgId").value = orgId;
}
function closeStation(){
	clearUnplanEventState();
	
//	window.close();
//	window.opener.location.reload();
}
//清除事务流转状态
function clearUnplanEventState(){
	$.ajax({
		type : "post",
		async : false,
		url : appUrl1
				+ "/officePlatform/extraPurchase/unplanAction!updateUnplanApplyByeventId.jspa?time="
				+ new Date(),
		data : {
			eventId : $("#eventId").val(),
			eventStateId : "",
			eventStateName : ""
		},
		success : function(data) {
			if(data.indexOf("操作成功")!=-1){
				$("#maintWindow").window('close');
				window.parent.closeMaintWindow();
				window.parent.search();
			}else{
				$.messager.alert('Tips',"事务信息修改失败!请联系下个处理人回退后重新处理!", 'error');
			}
		},
		error : function() {
			$.messager.alert('Tips',"事务信息修改失败!请联系下个处理人回退后重新处理!", 'error');
		}
	});
}
function callback(executeResult) {
	var res = executeResult.result;
	var msg = executeResult.code;
	if (flag == "1") {
		if (res == "true") {
			var form = window.document.forms[0];
			form.action = appUrl
					+ "/wfe/eventAction!completeEndTask.jspa?operation="
					+ golbalOperation + "&successResult="
					+ encodeURIComponent(msg);
			form.target = "hideFrameWfe";
			form.submit();
		} else {
			if(msg != null){
				$.messager.alert('Tips', msg, 'error', function() {
					$.messager.progress('close');
				});
			}else{
				$.messager.alert('Tips', "处理错误！", 'error', function() {
					$.messager.progress('close');
				});
			}
			
		}
	} else if (flag == "2") {
		$.messager.alert('Tips', "处理成功！", 'info', function() {
			window.parent.search();
			window.parent.closeMaintWindow();
		});
	}else if (flag == "4"){
		if (res == "true") {
			contineProcess();
		} else {
			if(msg != null){
				$.messager.alert('Tips', msg, 'error', function() {
					$.messager.progress('close');
				});
			}else{
				$.messager.alert('Tips', "处理错误！", 'error', function() {
					$.messager.progress('close');
				});
			}
		}

	}

}
function downLoad(fileId) {
	var form = window.document.forms[0];
	form.action = appUrl + "/file/fileAction!downLoadFile.jspa?fileId="
			+ fileId;
	form.target = "hideFrameWfe";
	form.submit();
}

function preview(fileId){
  	var WWidth = 1000;
	var WHeight = 600;
	var WLeft = Math.ceil((window.screen.width - WWidth)/ 2);
	var WTop = Math.ceil((window.screen.height - WHeight)/ 2 - 30);
	var url = appUrl+"/file/fileAction!toPreViewFile.jspa?fileId=" + fileId;
    window.open(url,"win","left="+ WLeft
						+ ",top="
						+ WTop
						+ ",width="
						+ WWidth
						+ ",height="
						+ WHeight
						+ ",toolbar=no,menubar=no,scrollbars=yes");
}

function getCkeditor() {
	if (!CKEDITOR.instances.memo) { // 判定memo是否存在
		editor = CKEDITOR.replace("memo");
	} else {
		addCkeditor("memo");
	}
}
function addCkeditor(id) {
	var editor2 = CKEDITOR.instances[id];

	if (editor2)
		editor2.destroy(true);// 销毁编辑器 memo,然后新增一个

	editor = CKEDITOR.replace(id);
}
function iFrameHeight() {
	var ifm = document.getElementById("iframepage");
	var subWeb = document.frames ? document.frames["iframepage"].document
			: ifm.contentDocument;
	if (ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight + 130;
	}
}
function cc(){
	initWindow('选择抄送人', appUrl+'/wfe/eventAction!toSemiAutomatic.jspa?eventId='+$("#eventId").val()
			+"&toDoDetail="+$("#toDoDetail").val()+"&type=1","maintWindow",750, 450);
}
function Countersigned(){
	initWindow('选择会签人员', appUrl+'/wfe/eventAction!toSemiAutomatic.jspa?eventId='+$("#eventId").val()
			+"&toDoDetail="+$("#toDoDetail").val()+"&type=2","maintWindow",750, 450);
}
function saveMemo(){
	if($('#memo').val()==null||$('#memo').val()==''){
		$.messager.alert('Tips', "请填写处理意见", 'warning');
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl
			+ "/wfe/eventAction!updateCc.jspa";
	form.target = "hideFrameWfe";
	form.submit();
}
function getRepaly(id){
	initWindow('查看回复信息', appUrl+'/wfe/eventAction!toRepalyMemo.jspa?eventId='+$("#eventId").val()
			+"&toDoDetail="+id,"maintWindow",750, 450);
}
function orderPurchase(){
	$.ajax({
		type : "post",
		async : false,
		url : appUrl1
				+ "/officePlatform/extraPurchase/unplanAction!createOrderPurchase.jspa?time="
				+ new Date(),
		data : {
			eventId : $("#eventId").val(),
			curStaId : "purchaseJudge"
		},
		success : function(data) {
			if(data.indexOf("操作成功")!=-1){
				$("#maintWindow").window('close');
				window.parent.closeMaintWindow();
				window.parent.search();
			}else{
				$.messager.alert('Tips',"生成采购订单失败!请联系下个处理人回退后重新处理!", 'error');
			}
		},
		error : function() {
			$.messager.alert('Tips',"生成采购订单失败!请联系下个处理人回退后重新处理!", 'error');
		}
	});
}