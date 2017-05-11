$(document).ready(function() {
			loadGrid();
			$('#hideFrame').bind('load', promgtMsg);
		});

function loadGrid() {
	$('#datagrid1').datagrid({
				iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/station!searchStationUserMore.jspa?userId='+$('#userId').val(),
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : 270,
				columns : [[{
							field : 'ck',
							align : 'center',
							checkbox : true
						},{
							field : 'id',
							title : '职位ID',
							width : 100,
							align : 'center',
							hidden:true
						}, {
							field : 'empCode',
							title : '用户ID',
							width : 100,
							align : 'center'
						}, {
							field : 'userName',
							title : '用户名',
							width : 100,
							align : 'center'
						}
						, {
							field : 'orgName',
							title : '组织',
							width : 170,
							align : 'center'
						}
						, {
							field : 'stationName',
							title : '岗位名称',
							width : 173,
							align : 'center'
						}
						]]
					});

	// 分页控件
	var p = $('#datagrid1').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
				beforePageText : '第',
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			});
}


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if(successResult){
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}

function close() {
	window.parent.closeMaintStation();
}


function search() {
	$("#datagrid1").datagrid('reload');
}



/**
 * 
 * @param {} e
 * @return {Boolean}
 */


