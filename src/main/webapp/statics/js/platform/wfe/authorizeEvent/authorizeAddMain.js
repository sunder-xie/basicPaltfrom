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

					loadGrid();
					$('#hideFrame').bind('load', promgtMsg);
				});

function loadGrid() {
	$('#datagrid').datagrid({

		iconCls : 'icon-list',
		title : '人员列表',
		striped : true,
		url : appUrl + '/wfe/authorizeEventAction!getEmpListByOrgId.jspa',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		pagination : false,
		nowrap : true,
		remoteSort : true,
		height : height,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'userId',
			title : '人员ID',
			width : setColumnWidth(0.1),
			align : 'center',
			hidden : true
		}, {
			field : 'posName',
			title : '岗位名称',
			width : 150,
			align : 'center'
		}, {
			field : 'userName',
			title : '人员姓名',
			width : 120,
			align : 'center'
		} ] ],
		toolbar : [ "-", {
			text : '确定',
			iconCls : 'icon-save',
			handler : function() {
				createAuthorization();
			}
		}, "-" ]
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function createAuthorization() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!');
		return;
	}
	$.messager.confirm('Confirm', '确认批量授权事务查看?', function(r) {
		if (r) {
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].userId);
			}
			$("#userIds").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl
					+ "/wfe/authorizeEventAction!createAuthorization.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function search(id) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = encodeURIComponent(id);
	$("#datagrid").datagrid('load');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.parent.closeWindow();
			window.parent.search();
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
			$('#datagrid').datagrid('loadData', obj);
		}
	});
}