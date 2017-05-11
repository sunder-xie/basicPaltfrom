
var selectedId;
var selectedName;
var userId = $('#userId').val();
$(document)
		.ready(
				function() {
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
										onClick : function(node) {// µ¥»÷ÊÂ¼þ
											$(this).tree('toggle', node.target);
											if (!node.state) {
												add(node.text, node.attributes);
											}
										}
									});
				});

function select(selectedId, selectedName) {
	document.getElementById("orgId").value = selectedId;
	document.getElementById("orgName").value = selectedName;
	window.parent.returnValue(selectedId, selectedName);
	window.parent.closeOrgTree();
}