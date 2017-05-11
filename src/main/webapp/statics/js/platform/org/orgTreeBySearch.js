var selectedId;
var selectedName;
$(document).ready(function() {
	loadGrid();
	
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

	$('#orgTree').tree({
		method : 'post',
		animate : true,
		url : appUrl + '/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=0',
		onBeforeExpand : function(node, param) {
			$('#orgTree').tree('options').url = appUrl + "/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=" + node.id;
		},
		onClick : function(node) {// �����¼�
			$(this).tree('toggle', node.target);
			if (!node.state) {
				add(node.text, node.attributes);
			}
			//search(node.id, node.text);
		}
	});
});

function loadGrid() {
	$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '��֯�б�',
						striped : true,
						url : appUrl + '/orgTreeAjaxAction!searchOrgTreeList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						pagination : false,
						nowrap : true,
						remoteSort : true,
						height : 250,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'orgId',
									title : '��֯ID',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								{
									field : 'orgName',
									title : '��֯����',
									width : 120,
									align : 'center'
								},
								{
									field : 'adGroupName',
									title : 'group����',
									width : 160,
									align : 'center'
								}
								 ] ],
						toolbar : [ "-", {
							text : 'ȷ��',
							iconCls : 'icon-save',
							handler : function() {
								selectOrg();
							}
						}, "-" ]
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function selectOrg(){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows == ''){
		$.messager.alert('Tips', '��ѡ������!', 'warning');
		return;
	}
	select(rows[0].orgId,rows[0].orgName);
}

function searchOrg(name) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	//queryParams.orgId =  encodeURIComponent(id);
	queryParams.orgName =  encodeURIComponent(name);
	$("#datagrid").datagrid('load');
}
function select(selectedId, selectedName) {
	document.getElementById("orgId").value = selectedId;
	document.getElementById("orgName").value = selectedName;
	window.parent.returnValue(selectedId, selectedName);
	window.parent.closeOrgTree();
}