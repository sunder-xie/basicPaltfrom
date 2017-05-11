$(document).ready(function() {
	initTree();
});

function collapseAll() {
	$('#orgTree').tree('collapseAll');
}

function initTree() {
	$('#orgTree')
			.tree(
					{
						animate : true,
						url : appUrl
								+ '/orgTreeAjaxAction!getOrgUserTreeListByAjax.jspa?node=0',
						onBeforeExpand : function(node, param) {
							$('#orgTree').tree('options').url = appUrl
									+ "/orgTreeAjaxAction!getOrgUserTreeListByAjax.jspa?node="
									+ node.id;
						},
						onClick : function(node) {// µ¥»÷ÊÂ¼þ
							$(this).tree('toggle', node.target);
							if (!node.state) {
								$
										.ajax({
											type : "post",
											url : appUrl
													+ "/allUserAction!userInfoInRtx.jspa",
											data : {
												userId : node.id
											},
											success : function(user) {
												$("#userName").val(
														user.userName);
												$("#sex").val(user.sex);
												$("#orgName").val(user.orgName);
												$("#stationNames").val(
														user.stationNames);
												$("#busMobilephone").val(
														user.busMobilephone);
												$("#phone").val(user.phone);
												$("#workFax").val(user.workFax);
												$("#email").val(user.email);
//												$("#address").val(user.address);

											}
										});
							}
						}
					});
}
