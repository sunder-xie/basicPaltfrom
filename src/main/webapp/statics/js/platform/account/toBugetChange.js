$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});

function submit() {
	if($("#comment").val() == ""){
		$.messager.alert('Tips', "备注不能为空！", 'warning');
	}
	$("#key").val($("#modelId").val());
	$.messager.progress();
	$.ajax({
			type : "post",
			url : appUrl+ "/account/accountAction!selectBugetChangeNexUser.jspa?time="
						+ new Date(),
			data : {
				key : $("#modelId").val(),
				flag : $("#isChangeMoney").val()
			},
			success : function(userUtil) {
					$.messager.progress('close');
					if (userUtil == null || userUtil == "") {
						$.messager.alert('Tips', "没有下个处理人，请维护！", 'error');
						return;
					}
					if (userUtil != null && userUtil.processInstanceId != '-2'
							&& userUtil.processInstanceId != undefined) {
						var nextUser1 = "";
						var total = 0;
						$.each(userUtil.result, function(i, v) {
							total = i + 1;
							nextUser1 = v.userId;
						});
						if (total == 1) {
							$("#nextUserId").val(nextUser1);
							var form = window.document.forms[0];
							form.action = appUrl
									+ "/account/accountAction!bugetChangeApplay.jspa?eventId="
									+ userUtil.processInstanceId;
							form.submit();
						} else if (total == 0) {
							$.messager.alert('Tips', "没有维护下个处理人！请联系管理员",
							'error');
							return;
						} else {
							if (userUtil.processInstanceId == "-1") {
								$.messager.alert('Tips', "没有维护下个处理人！请联系管理员",
										'error');
								return;
							}
							var positionHtml = "<div class='easyui-panel' title='下个处理' data-options='collapsible:true'>"
									+ "<table width='100%' border='0' cellpadding='0' cellspacing='1'>"
									+ "<tr><td class='head' noWrap>处理人</td>"
									+ "<td><select id='nextUserId1' name='nextUserId1'>";
							$.each(userUtil.result, function(i, v) {
								positionHtml += "<option value='" + v.userId
										+ "'>" + v.userName + "----"
										+ v.stationName + "</option>";
							});
							positionHtml += "</select></td></tr></table></div>";
							if ($('#nextUserDialog').length < 1) {
								$(
										'<div/>',
										{
											id : 'nextUserDialog',
											title : '选择下个处理人',
											html : "<div id='nextUser'>"
													+ positionHtml + "</div>"
													+ "</div>"
										}).appendTo('body');
							} else {
								$('#nextUser').html(positionHtml);
							}
							$('#nextUserDialog')
									.dialog(
											{
												modal : true,
												resizable : false,
												dragable : false,
												closable : false,
												open : function() {
													$('#nextUserDialog').css(
															'padding', '0.4em');
													$('#nextUserDialog .ui-accordion-content').css('padding','0.4em').height(
													$('#nextUserDialog').height() - 75);
												},
												buttons : [
														{
															text : '确定',
															handler : function() {
																if ($("#nextUserId1").val() == ""|| $("#nextUserId1").val() == null) {
																	$.messager.alert('Tips',"没有下个处理人，请维护！",'error');
																	return;
																}
																$.messager.progress();
																$("#nextUserId").val($("#nextUserId1").val());
																var form = window.document.forms[0];
																form.action = appUrl
																		+ "/account/accountAction!bugetChangeApplay.jspa?eventId="
																		+ userUtil.processInstanceId;
																form.submit();
															}
														},
														{
															text : '取消',
															handler : function() {
																$('#nextUserDialog').dialog('close');
															}
														} ],
												width : document.documentElement.clientWidth * 0.50,
												height : document.documentElement.clientHeight * 0.40
											});
						}
					} else {
						$.messager.alert('Tips', "流程出错！请联系管理员",
						'error');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}