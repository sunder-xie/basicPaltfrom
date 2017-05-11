
$(document).ready(function() {
	loadGrid(); 
	
});

function loadGrid() {
	
	$('#processdefinition_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl + '/allRoleAction!serachRoles.jspa',

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									title : '序号',
									field : 'roleid',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'rolename',
									title : '类型名称',
									align : 'center',
									width : 200,
									sortable : true
								},{
									field : 'lastmodify',
									title : '最后修改时间',
									align : 'center',
									width : 140,
									sortable : true						
								} ] ]
					});
					
	// 分页控件
	var p = $('#processdefinition_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function onSelectRole(){
	var rows = $('#processdefinition_list').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '  no selected rows!');
		return;
	}

	window.parent.settask_id(rows,window.parent.task,window.parent.win);
	
}
function utcToDate(utcCurrTime) {
    utcCurrTime = utcCurrTime + "";
    var date = "";
    var month = new Array();
    month["Jan"] = 1;
    month["Feb"] = 2;
    month["Mar"] = 3;
    month["Apr"] = 4;
    month["May"] = 5;
    month["Jun"] = 6;
    month["Jul"] = 7;
    month["Aug"] = 8;
    month["Sep"] = 9;
    month["Oct"] = 10;
    month["Nov"] = 11;
    month["Dec"] = 12;
    var week = new Array();
    week["Mon"] = "一";
    week["Tue"] = "二";
    week["Wed"] = "三";
    week["Thu"] = "四";
    week["Fri"] = "五";
    week["Sat"] = "六";
    week["Sun"] = "日";


    str = utcCurrTime.split(" ");
    date = str[5] + "-";
    date = date + month[str[1]] + "-" + str[2] + " " + str[3];
    return date;
}
