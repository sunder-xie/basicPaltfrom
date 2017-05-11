var selectedId;
$(document).ready(function() {

	// 绑定tree的右键菜单
	$('#orgTree').tree({
		onContextMenu : function(e, node) {
			e.preventDefault();
			selectedId = node.id;
			$('#treeMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}

	});

	$("#treeMenu").menu({
		onClick : function(item) {
			createOrUpdate(selectedId, item.name);
		}
	});
	$('#hideFrame').bind('load', promgtMsg);

	// async加载orgtree
	initTree();
});

function collapseAll() {
	$('#orgTree').tree('collapseAll');
}
function expandAll() {
	$('#orgTree').tree('expandAll');
}

function createOrUpdate(selectedId, type) {
	if (type == "update") {
		$.ajax({
			type : "post",
			url : appUrl + "/orgAction!showOrgDetail.jspa",
			data : {
				orgId : selectedId
			},
			success : function(borg) {
				$("#orgId_updateForm").val(borg.orgId);
				$("#orgName_updateForm").val(borg.orgName);
				$('#orgCity_updateForm').combobox('setValue', borg.orgCity);
				$("#orgLevel_updateForm").val(borg.orgLevel);
				$('#orgRange_updateForm').combobox('setValue', borg.orgRange);
				$("#orgParentId_updateForm").val(borg.orgParentId);
				$("#orgParentName_updateForm").val(borg.orgParentName);
				$("#shortName_updateForm").val(borg.shortName);
				$("#jianPing_updateForm").val(borg.jianPing);
				$("#costCenter_updateForm").val(borg.costCenter);
				$("#sapOrgId_updateForm").val(borg.sapOrgId);	

			}

		});
		$('#actionAccordion').accordion('select', '修改组织节点');
	}

	else if (type == "delete") {
		$.messager.confirm('Confirm', '认真检查本组织下是否有子组织,删除?', function(r) {
			if (r) {
				$("#sourceId").val(selectedId);
				var form = document.forms['dropForm'];
				form.action = appUrl + "/orgAction!deleteOrg.jspa?orgId="
						+ selectedId;
				form.target = "hideFrame";
				form.submit();
			}
		});
	} else {
		$.ajax({
			type : "post",
			url : appUrl + "/orgAction!showOrgDetail.jspa",
			data : {
				orgId : selectedId
			},
			success : function(borg) {
				$("#orgLevel_createForm").val(parseInt(borg.orgLevel) + 1);
				$("#orgParentId_createForm").val(borg.orgId);
				$("#orgParentName_createForm").val(borg.orgName);
			}
		});
		$('#actionAccordion').accordion('select', '新增组织节点');
	}

}

function submit(type) {
	if (type == 'update') {
		var v = $("#orgId_updateForm").validatebox('isValid');
		if (!v) {
			$.messager.alert('Tips', '请在右边组织树选中组织,右键点击修改组织信息.', 'warning');
			return;
		}

		var v1 = $("#orgName_updateForm").validatebox('isValid');
		var v2 = $("#orgCity_updateForm").combobox('isValid');
		var v3 = $("#orgRange_updateForm").combobox('isValid');
		var v4 = $("#shortName_updateForm").validatebox('isValid');
		var v5 = $("#jianPing_updateForm").validatebox('isValid');
		var v6 = $("#costCenter_updateForm").validatebox('isValid');

		if (!(v1 && v2 && v3 && v4 && v5 && v6)) {
			return;
		}
		var form = document.forms['updateForm'];
		form.action = appUrl + "/orgAction!updateOrg.jspa";
		form.target = "hideFrame";
		form.submit();
	} else if (type == 'create') {
		var v = $("#orgParentId_createForm").val();
		if (!v) {
			$.messager.alert('Tips', '请在右边组织树选中组织,右键点击创建下级组织.', 'warning');
			return;
		}
		var v1 = $("#orgName_createForm").validatebox('isValid');
		var v2 = $("#orgCity_createForm").combobox('isValid');
		var v3 = $("#orgRange_createForm").combobox('isValid');
		var v4 = $("#shortName_createForm").validatebox('isValid');
		var v5 = $("#jianPing_createForm").validatebox('isValid');
		var v6 = $("#costCenter_createForm").validatebox('isValid');
		if (!(v1 && v2 && v3 && v4 && v5 && v6)) {
			return;
		}
		var form = document.forms['createForm'];
		form.action = appUrl + "/orgAction!CreatOrg.jspa";
		form.target = "hideFrame";
		form.submit();

	} else {
		var v1 = $("#orgName_createRootForm").validatebox('isValid');
		var v2 = $("#orgRange_createRootForm").combobox('isValid');
		var v3 = $("#shortName_createRootForm").validatebox('isValid');
		var v4 = $("#jianPing_createRootForm").validatebox('isValid');
		var v5 = $("#costCenter_createRootForm").validatebox('isValid');
		if (!(v1 && v2 && v3 && v4 && v5)) {
			return;
		}
		var form = document.forms['createRootForm'];
		form.action = appUrl + "/orgAction!CreatOrg.jspa";
		form.target = "hideFrame";
		form.submit();
	}
}

function cancel(type) {
	if (type == 'update') {
		document.forms['updateForm'].reset();
	} else if (type == 'create') {
		document.forms['createForm'].reset();
	} else if (type == 'createRoot') {
		document.forms['createRootForm'].reset();
	} else {
		document.forms['updateForm'].reset();
		document.forms['createForm'].reset();
		document.forms['lookForm'].reset();
	}
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			self.location.reload();
		});
	} else if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	}
}

function initTree() {
	$('#orgTree')
			.tree(
					{
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
							$.ajax({
								type : "post",
								url : appUrl + "/orgAction!showOrgDetail.jspa",
								data : {
									orgId : node.id
								},
								success : function(borg) {
									$("#orgId_lookForm").val(borg.orgId);
									$("#orgName_lookForm").val(borg.orgName);
									$('#orgCity_lookForm').combobox('setValue',
											borg.orgCity);
									$("#orgLevel_lookForm").val(borg.orgLevel);
									$('#orgRange_lookForm').combobox(
											'setValue', borg.orgRange);
									$("#orgParentId_lookForm").val(
											borg.orgParentId);
									$("#orgParentName_lookForm").val(
											borg.orgParentName);
									$("#shortName_lookForm")
											.val(borg.shortName);
									$("#jianPing_lookForm").val(borg.jianPing);
									$("#costCenter_lookForm").val(
											borg.costCenter);
									$("#sapOrgId_lookForm").val(
											borg.sapOrgId);		
								}
							});
							$('#actionAccordion').accordion('select', '组织节点信息');

						},
						onDrop : function(target, source, point) {// 拖拽事件
							$.messager
									.confirm(
											'Confirm',
											'是否调动组织结构?',
											function(r) {
												if (r) {
													var targetId = $(target)
															.tree('getNode',
																	target).id;
													$("#targetId")
															.val(targetId);
													$("#sourceId").val(
															source.id);
													var form = document.forms['dropForm'];
													form.action = appUrl
															+ "/orgAction!dropOrg.jspa";
													form.target = "hideFrame";
													form.submit();
												}
											});
						},
						onLoadSuccess : function(node, data) {// 第一次载入判断是否已有root
							if (node == null && data.length > 0) {
								$('#actionAccordion').accordion('remove',
										'创建组织跟节点');

							}
						}
					});
}
