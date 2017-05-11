$(document).ready(function() {
	loadGrid();
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
							if($("#source div[userId="+rowData.userId+"]").length > 0){
								return;
							}
							$("#source")
									.append(
											'<div class="drag-item" userId="'
													+ rowData.userId
													+ '"><img src="'
													+ imgUrl
													+ '/images/actions/action_roles.png" align="absMiddle"></img>'
													+ rowData.userName
													+ '</div>');
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
							field : 'userName',
							title : '人员姓名',
							width : 150,
							align : 'center'
						} ] ]
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}


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


function search(id) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
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
	$("#source div").each(function() {
		if (context.length > 0) {
			context = context + "," + $(this).attr("userId");
		} else {
			context = $(this).attr("userId");
		}
	});
	if ("" == context) {
		alert("请选择处理人列表！");
		return;
	} else {
		$.messager.confirm('Confirm', '确认抄送?', function(r) {
			if (r) {
				window.parent.document.getElementById("ccList").value = context;
				close();
			}
		});
	}
}
function close() {
	window.parent.closeMaintEvent();
}
