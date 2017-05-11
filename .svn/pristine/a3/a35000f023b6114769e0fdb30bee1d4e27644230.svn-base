var selectedId;
var selectedName;
$(document).ready(function() {
	$('#orgTree').tree({
				onContextMenu : function(e, node) {
					e.preventDefault();
					selectedId = node.id;
					selectedName = node.text;
					$('#treeMenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
				}

			});

	$("#treeMenu").menu({
				onClick : function(item) {
					select(selectedId, selectedName);
				}
			});
	var orgTreeUrl = '';
	if ($('#orgIdIn').val() != '') {
		orgTreeUrl = appUrl
				+ '/orgTreeAjaxAction!getSapOrgTreeListForBo.jspa?node='
				+ $('#orgIdIn').val() + "&flag=" + "Y";
	} else {
		orgTreeUrl = appUrl
				+ '/orgTreeAjaxAction!getSapOrgTreeListForBo.jspa?node=0';
	}
	$('#orgTree').tree({
		animate : true,
		url :  orgTreeUrl,
		onBeforeExpand : function(node, param) {
			$('#orgTree').tree('options').url = appUrl
					+ "/orgTreeAjaxAction!getSapOrgTreeListForBo.jspa?node="
					+ node.id.split(':')[0];
		},
		onClick : function(node) {// 单击事件
			$(this).tree('toggle', node.target);
//			 if (!node.state) {
//			 add(node.text, node.attributes);
//			 }
		}
	});
});

function select(selectedId, selectedName) {
	document.getElementById("orgId").value = selectedId;
	document.getElementById("orgName").value = selectedName;
	if (selectedId.split(':')[1]=="null") {
//		$.messager.alert('Tips', '该组织SAP组织ID为空', 'warning');
//        return ;
		window.parent.closeOrgTree();
	}else{
	window.parent.returnValue(selectedId.split(':')[1], selectedName);
	window.parent.closeOrgTree();
	}
}
