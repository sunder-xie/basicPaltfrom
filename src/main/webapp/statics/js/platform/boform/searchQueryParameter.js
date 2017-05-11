
$(document).ready(function() {
	 if (amount == 2 || amount == 20) {
            loadGrid();
	}else{
	        loadGrid1(); 
	}
	
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						nowrap: false,
						singleSelect : false,
						pagination : true,
						remoteSort : false,
						rownumbers : true,
						url : appUrl
								+ '/boformAction!getQueryParameterJsonList.jspa?tableName='
								+ encodeURIComponent($('#tableName').val())
								+ '&zdid='
								+ encodeURIComponent($('#zdid').val())
								+ '&txt=' + encodeURIComponent($('#txt').val())
								+ '&zdtxt='
								+ encodeURIComponent($('#zdtxt').val()) + '&d='
								+ encodeURIComponent($('#d').val()) +'&pid='
								+ encodeURIComponent($('#pid').val())+ '&ran='
								+ Math.random(),
						loadMsg : '数据远程载入中,请等待...',

						columns : [[ {
							field : 'ck',
							align : 'center',
							checkbox : true
						}, {
							title : "ID",
							field : 'id',
							width : 100,
							sortable : true,
							align : 'center'
						}, {
							title : "描述",
							field : 'text',
							width : 400,
							sortable : true,
							align : 'center',
							formatter : function(value, row, rec) {
								var v = row.text;
								if (v == '') {
									v= row.id;
								}
								return v;
							}
						} ]],
						toolbar : [ "-", {
							text : '确定',
							iconCls : 'icon-add',
							handler : function() {
								save();
							}
						}, "-" ]
					});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize :  30,
		pageList : [ 30, 50, 100 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function loadGrid1() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						nowrap: false,
						singleSelect : true,
						pagination : true,
						remoteSort : false,
						url : appUrl
								+ '/boformAction!getQueryParameterJsonList.jspa?tableName='
								+ encodeURIComponent($('#tableName').val())
								+ '&zdid='
								+ encodeURIComponent($('#zdid').val())
								+ '&txt=' + encodeURIComponent($('#txt').val())
								+ '&zdtxt='
								+ encodeURIComponent($('#zdtxt').val()) + '&d='
								+ encodeURIComponent($('#d').val()) +'&pid='
								+ encodeURIComponent($('#pid').val())+ '&ran='
								+ Math.random(),
						loadMsg : '数据远程载入中,请等待...',

						columns : [[ {
							field : 'ck',
							align : 'center',
							checkbox : true
						}, {
							title : "ID",
							field : 'id',
							width : 100,
							sortable : true,
							align : 'center'
						}, {
							title : "描述",
							field : 'text',
							width : 400,
							sortable : true,
							align : 'center',
							formatter : function(value, row, rec) {
								var v = row.text;
								if (v == '') {
									v= row.id;
								}
								return v;
							}
						} ]],
						toolbar : [ "-", {
							text : '确定',
							iconCls : 'icon-add',
							handler : function() {
								save();
							}
						}, "-" ]
					});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 30,
		pageList : [ 30, 50, 100 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function searchs() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.search = encodeURIComponent($('#search').val());
	$("#datagrid").datagrid('load');
}

function save() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('提示', '  未选中任何信息!');
		return;
	}
	var s = new Array();
	//var i = 0;
	var aa = '';
	var bb = '';
	for ( var j = 0; j < rows.length; j++) {
		if(rows.length==1){
			aa = rows[j].id;
			bb = rows[j].text == '' ? rows[j].id : rows[j].text;
		}else{
			if(j==0){
				aa = rows[j].id;
				bb = rows[j].text == '' ? rows[j].id : rows[j].text;
			}else{
				aa += ';' + rows[j].id;
				bb += ';' + (rows[j].text == '' ? rows[j].id : rows[j].text);
			}
		}
	}
	
	s[0] = aa;
	s[1] = bb;
	window.parent.returnValue0(s);
	window.parent.closeOrgTree();
}
