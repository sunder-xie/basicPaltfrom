$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '人员列表',
						url : appUrl + '/allUserAction!getEmpPostListJSON.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : false,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'empPostId',
									title : '职务ID',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								{
									field : 'empPostName',
									title : '职务名称',
									width : 90,
									align : 'center'
								},
								{
									field : 'orgId',
									title : '组织ID',
									width : 90,
									align : 'center',
									hidden: true
								},
								{
									field : 'orgName',
									title : '组织',
									width : 90,
									align : 'center'
								}
								 ] ],
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

function createAuthorization(){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows == ''){
		$.messager.alert('Tips', '没有行项目被选中!');
				return;
	}
	if(rows.length > 1){
			$.messager.alert('Tips', '只能选择一行!', 'warning');
			return;
		}
		var empPostId = rows[0].empPostId;
		var empPostName = rows[0].empPostName;
		var orgId1 = rows[0].orgId;
	    window.parent.document.getElementById("empPostId").value = empPostId;
	    window.parent.document.getElementById("empPostName").value = empPostName;
	    window.parent.document.getElementById("orgId1").value = orgId1;
	    var orgId = window.parent.document.getElementById("orgId").value ;
	    var orgName = window.parent.document.getElementById("orgName").value ;
	    if(orgId != orgId1){
	    	$.messager.alert('Tips', '请选择 '+orgName+' 下的职务', 'warning');
			return;
	    }
		window.parent.closeOrgTree();
}

//var selectedId;
//var selectedName;
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
//

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
	queryParams.orgId4Post =  encodeURIComponent(id);
	$("#datagrid").datagrid('load');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		$.messager.alert('Tips', failResult, 'error');
	} else {
		$.messager.alert('Tips', successResult, 'info', function() {
				window.parent.close();
				window.parent.search();
			});
	}
}
