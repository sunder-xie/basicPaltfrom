$(document).ready(function() {
	loadGrid();
	$('#hideFrameSemiAutomatic').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '人员列表',
						url : appUrl
								+ '/wfe/authorizeEventAction!getEmpListByOrgId.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : false,
						nowrap : true,
						remoteSort : true,
						height : 380,
						onDblClickRow : function(rowIndex, rowData) {
							// $("#source").append("<div class='drag-item'
							// userId='"+rowData.userId+"'>"+rowData.userName+"</div>");
							if($("#eventId").val() != ""){
								checkCc(rowData.userId,rowData.userName);
							}else{
								$("#source")
								.append(
										'<div class="drag-item" userId="'
												+ rowData.userId
												+ '" + userName="'+rowData.userName+'"><img src="'
												+ imgUrl
												+ '/images/actions/action_roles.png" align="absMiddle"></img>'
												+ rowData.userName
												+ '</div>');
							}
							
							reloadST();
						},
						columns : [ [ {
							field : 'ck',
							checkbox : false
						}, {
							field : 'userId',
							title : '人员ID',
							align : 'center',
							hidden : true
						}, {
							field : 'posName',
							title : '岗位名称',
							width : 100,
							align : 'center'
						},{
							field : 'userName',
							title : '人员姓名',
							width : 80,
							align : 'center'
						} ] ]
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

/*
 * function createAuthorization(rowIndex){ var rows =
 * $('#datagrid').datagrid('getSelected'); $("#source").append("<div
 * class='drag-item' userId='"+rows[0].userId+"'>"+rows[0].userName+"</div>");
 * $("#source").append("<div class='drag-item'><img
 * src='statics/images/actions/action_roles.png'></img></div>"); reloadST(); }
 */

$(document)
		.ready(
				function() {
					$('#orgTree').tree({
						onContextMenu : function(e, node) {
							e.preventDefault();
							$('#treeMenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
						}

					});
					$('#orgTree')
							.tree(
									{
										method : 'post',
										animate : true,
										url : appUrl
												+ '/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=0',
										onBeforeExpand : function(node, param) {
											$('#orgTree').tree('options').url = appUrl
													+ "/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node="
													+ node.id;
										},
										onClick : function(node) {// 单击事件
											$(this).tree('toggle', node.target);
											if (!node.state) {
												add(node.text, node.attributes);
											}
											search(node.id);

										}
									});
				});

// function select(selectedId, selectedName) {
// document.getElementById("orgId").value = selectedId;
// document.getElementById("orgName").value = selectedName;
// window.parent.returnValue(selectedId, selectedName);
// window.parent.closeOrgTree();
// }

function search(id) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	// queryParams.orgId = encodeURIComponent($("#orgId").val());
	queryParams.orgId = encodeURIComponent(id);
	$("#datagrid").datagrid('load');
}
// ///
function reloadST() {
	$('.drag-item').draggable({
		revert : true,
		deltaX : 0,
		deltaY : 0,
		proxy : 'clone',
		revert : true,
		cursor : 'auto',
		onStartDrag : function() {
			$(this).draggable('options').cursor = 'not-allowed';
			$(this).draggable('proxy').addClass('dp');
		},
		onStopDrag : function() {
			$(this).draggable('options').cursor = 'auto';
		}
	}).droppable({
		onDragOver : function(e, source) {
			$(source).draggable('options').cursor = 'auto';
			$(source).draggable('proxy').css('border', '1px solid red');
			$(this).addClass('over');
		},
		onDragLeave : function(e, source) {
			$(source).draggable('options').cursor = 'not-allowed';
			$(source).draggable('proxy').css('border', '1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop : function(e, source) {
			$(source).insertAfter(this);
			$(source).removeClass('over');
		}
	});
	$('#target,#source').droppable({
		onDragEnter : function(e, source) {
			$(source).draggable('options').cursor = 'auto';
			$(source).draggable('proxy').css('border', '1px solid red');
			$(this).addClass('over');
		},
		onDragLeave : function(e, source) {
			$(source).draggable('options').cursor = 'not-allowed';
			$(source).draggable('proxy').css('border', '1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop : function(e, source) {
			$(this).append(source);
			$(this).removeClass('over');
		}
	});
}
function save() {
	var context = "";
	var context1 = "";
	$("#source div").each(function() {
		if (context.length > 0) {
			var str=$(this).attr("userId");
			if(context.indexOf(str)==-1){
				context = context + "," + $(this).attr("userId");
				context1 = context1 + "," + $(this).attr("userName");
			}
		} else {
			context = $(this).attr("userId");
			context1 = $(this).attr("userName");
		}
	});
	if ("" == context) {
		alert("请选择处理人列表！");
		return;
	} else {
		if($("#eventId").val() != ""){
			var type = $('#type').val();
			var txt=type==1?'确认抄送?':'确认会签?';
			$.messager.confirm('Confirm', txt, function(r) {
				if (r) {
					$("#userList").val(context);
					$.messager.progress();
					var form = window.document.forms[0];
					form.action = "eventAction!creatCc.jspa";
					form.target = "hideFrameSemiAutomatic";
					form.submit();
				}
			});
		}else{
			window.parent.document.getElementById("userList").value = context;
			window.parent.document.getElementById("userNameList").value = context1;
			close();
		}
	}
}
function close() {
	window.parent.closeMaintEvent();
}
function promgtMsg() {
	$.messager.progress('close');
	var hideFrameSemiAutomatic = document.getElementById("hideFrameSemiAutomatic");
	var failResult = hideFrameSemiAutomatic.contentWindow.failResult;
	var successResult = hideFrameSemiAutomatic.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.parent.closeMaintEvent();
		});
	}
}
function searchPerson(name) {
	var HrUrl= appUrl.replace('basisPlatform','hrPlatform');
	var url = HrUrl + '/HrAction!getEmpListByUserName.jspa';
	// 下个处理人
	$.ajax({
		type : "post",
		async : false,
		url : url,
		data : {
			userName1: encodeURIComponent(name)
		},
		success : function(obj) {
			console.info(obj);
			$('#datagrid').datagrid('loadData', obj);
		}
	});
	
}

function checkCc(userId,userName){
	$.ajax({
		type : "post",
		async : false,
		url : appUrl+'/wfe/eventAction!checkCc.jspa',
		data : {
			eventId: $("#eventId").val(),
			toDoDetail : $("#toDoDetail").val(),
			userId : userId
		},
		success : function(data) {
			if(data>0){
				$.messager.alert('Tips', '您已经抄送给：'+userName, 'error');
			}else if(data<0){
				alert("检查抄送失败");
			}else{
				$("#source")
				.append(
						'<div class="drag-item" userId="'
								+ userId
								+ '" + userName="'+userName+'"><img src="'
								+ imgUrl
								+ '/images/actions/action_roles.png" align="absMiddle"></img>'
								+ userName
								+ '</div>');
			}
		},
		error:function(e){
			alert("网络错误");
		}
	});
}