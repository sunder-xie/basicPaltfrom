$(document).ready(function() {
	loadGrid();
	
	$('#orgTree').tree({
		onContextMenu : function(e, node) {
			e.preventDefault();
			$('#treeMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});

	$('#orgTree').tree({
		method : 'post',
		animate : true,
		url : appUrl + '/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=0',
		onBeforeExpand : function(node, param) {
			$('#orgTree').tree('options').url = appUrl + "/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=" + node.id;
		},
		onClick : function(node) {// 单击事件
			$(this).tree('toggle', node.target);
			if (!node.state) {
				add(node.text, node.attributes);
			}
			search(node.id, node.text);
		}
	});
});

function loadGrid() {
	$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '人员列表',
						striped : true,
						url : appUrl + '/allUserAction!getEmpListByOrgId.jspa',
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
									field : 'userId',
									title : '人员ID',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								{
									field : 'posName',
									title : '岗位名称',
									width : 150,
									align : 'center'
								},
								{
									field : 'userName',
									title : '人员姓名',
									width : 120,
									align : 'center'
								},
								{
									field : 'orgId',
									title : '部门ID',
									align : 'center',
									hidden: true
								}
								 ] ],
						toolbar : [ "-", {
							text : '确定',
							iconCls : 'icon-save',
							handler : function() {
								selectUser();
							}
						}, "-" ]
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function selectUser(){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows == ''){
		$.messager.alert('Tips', '请选择数据!', 'warning');
		return;
	}
	var x = new Array();
	x[0] = rows[0].userId;
	x[1] = rows[0].userName;
	x[2] = rows[0].orgId;
	x[3] = $("#orgName").val();
	window.parent.saveUser(x);
}

function search(id, text) {
	$("#orgName").val(text);
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId =  encodeURIComponent(id);
	$("#datagrid").datagrid('load');
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
