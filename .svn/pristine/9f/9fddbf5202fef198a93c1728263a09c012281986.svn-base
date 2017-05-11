$(document).ready(function() {
			loadGrid();
			$('#hideFrame').bind('load', promgtMsg);
		});

function loadGrid() {
	
	$('#datagrid1').datagrid({
				iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/station!searchStationUser.jspa?stationId='+stationId,
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height,
				columns : [[/*{
							field : 'ck',
							checkbox : true
						}, */{
							field : 'userId',
							title : '用户ID',
							width : setColumnWidth(0.4),
							align : 'center'
						}, {
							field : 'userName',
							title : '用户名称',
							width : setColumnWidth(0.6),
							align : 'center'
						}]]/* ,
						toolbar : [ "-" , {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
							deleteUser();
							}
						}, "-", {
							text : '选择人员',
							iconCls : 'icon-ok',
							handler : function() {
							openChooseStation();
							}
						}, "-", {
							text : '保存配置',
							iconCls : 'icon-save',
							handler : function() {
							save();
							}
						}, "-"]*/
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
		document.getElementById("userIdReturn").value = "";
		document.getElementById("userNameReturn").value = "";
		search();
	}
}

function close() {
	window.parent.closeMaintStation();
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	return true;
};
function search() {
	var queryParams = $('#datagrid1').datagrid('options').queryParams;
	queryParams.stationName =  encodeURIComponent($("#stationName").val());
//	queryParams.stationId =  encodeURIComponent($("#stationId").val());
	
	queryParams.userId =  $("#userId").val();
	queryParams.userName =encodeURIComponent($("#userName").val());
	$("#datagrid1").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

//创建窗口对象
function initMaintStation(title, url) {
	var url = appUrl + url;
	var WWidth = 650;
	var WHeight = 350;
	var $win = $("#chooseStation")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : false,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : false
					});
				
	$win.window('open');
}
function closeChooseStation(){
$("#chooseStation").window('close');

}
function openChooseStation() {
	initMaintStation('权限岗位人员选择', '/station!chooseSattionUser.jspa');
}
function save() {
	if ($("#userIdReturn").val() == '') {
		$.messager.alert('Tips', "请选择人员", 'warning');
	} else {
		var form = window.document.forms[0];
		form.action ='station!configStationUser.jspa';
		form.target = "hideFrame";
		form.submit();
	}

}
function deleteUser() {
	$.messager.confirm('Confirm', '是否批量刪除菜单?', function(r) {
		if (r) {
			var rows = $('#datagrid1').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  no selected rows!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].userId);
			}
			/*$("#ids").val(ids);
			alert($("#ids").val());*/
			var form = window.document.forms[0];
		form.action = 'station!deleteStationUser.jspa?userIdStrs='
			+ ids;
			form.target = "hideFrame";
			form.submit();
		}
	});
/*	var form = window.document.forms[0];
	form.action = appUrl + '/station/station!deleteStationUser.jspa?userIdStr='
			+ userId;
	form.target = "hideFrame";
	form.submit();*/
}
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};