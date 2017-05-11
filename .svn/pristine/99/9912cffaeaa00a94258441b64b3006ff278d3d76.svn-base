$(document).ready(function() {
	loadGrid();
});

function loadGrid() {

	$('#datagrid1').datagrid(
			{
				iconCls : 'icon-list',
				url : appUrl + "/staffAmountAction!geStaffUser.jspa?orgId="
						+ $('#orgId').val() + "&stationId="
						+ $('#stationId').val() + "&type=" + $('#type').val(),
				loadMsg : '数据远程载入中,请等待...',
				singleSelect : false,
				nowrap : true,
				remoteSort : true,
				height : height,
				columns : [ [ {
					field : 'userId',
					title : '用户ID',
					width : setColumnWidth(0.2),
					align : 'center'
				}, {
					field : 'userCode',
					title : '登录账号',
					width : setColumnWidth(0.2),
					align : 'center'
				}, {
					field : 'empUserCode',
					title : '员工编号',
					width : setColumnWidth(0.25),
					align : 'center'
				}, {
					field : 'userName',
					title : '用户名称',
					width : setColumnWidth(0.25),
					align : 'center'
				} ] ]
			});

}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
