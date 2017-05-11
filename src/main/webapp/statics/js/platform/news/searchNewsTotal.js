$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl
								+ '/news/newsAction!searchNewsTotalList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : height,
						width : 'auto',
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'total_id',
									title : '��ĿID',
									width : 100,
									align : 'center',
									sortable : true
								},
								{
									field : 'total_name',
									title : '��Ŀ����',
									width : 200,
									align : 'center',
									sortable : true
								},
								{
									field : 'total_code',
									title : '������',
									width : 100,
									align : 'center',
									sortable : true
								},
								{
									field : 'total_show',
									title : 'ͼƬ��ʾ',
									width : 150,
									align : 'center',
									formatter : function(value, row, rec) {
										var state = row.total_show;
										if (state == 'Y') {
											return "<font color='green'>��</font>";
										} else if (state == 'N') {
											return "<font color='red'>��</font>";
										}
									}
								},
								{
									field : 'total_sign',
									title : '�����״̬',
									width : 150,
									align : 'center',
									formatter : function(value, row, rec) {
										var state = row.total_sign;
										if (state == 'Y') {
											return "<font color='green'>��</font>";
										} else if (state == 'N') {
											return "<font color='red'>��</font>";
										}
									}
								},
								{
									title : "����ʱ��",
									field : 'total_date',
									width : 200,
									sortable : true,
									align : 'center',
									formatter : function(v) {
//										return utcToDate(v.replace(
//												/\/Date\((\d+)\+\d+\)\//gi,
//												"new Date($1)"));
										return utcToDate(v);
									}
								},
								{
									field : 'price',
									title : '����',
									width : setColumnWidth(0.15),
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.total_id;
										return '<img style="cursor:pointer" onclick="editrow('
												+ id
												+ ')" title="�޸�������Ŀ��Ϣ" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								createNewsTot();
							}
						}, "-", {
							text : 'ɾ��',
							iconCls : 'icon-remove ',
							handler : function() {
								deleteNewsTot();
							}
						}, "-" ]
					});

	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function editrow(id) {
	initMaintNews('�޸���Ŀ��Ϣ', '/newsAction!updateNewsTotalPre.jspa?totalId=' + id);
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function renderStyle(value) {
	return '<a tooltip="' + value + '" class="easyui-tips">' + value + '</a>';
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	/* queryParams.orgName = $("#orgName").val(); */
	queryParams.total_Name = encodeURIComponent($("#total_Name").val());
	$("#datagrid").datagrid('load');
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}
function createNewsTot() {
	initMaintNews('��Ŀ����', '/newsAction!newsTot_add.jspa');
}
// �������ڶ���
function initMaintNews(title, url) {
	var url = appUrl + url;
	var WWidth = 560;
	var WHeight = 260;
	var $win = $("#maintNews")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}
function closeCreateNewsTotal() {
	$("#maintNews").window('close');

}
function deleteNewsTot() {
	$.messager.confirm('Confirm', '�Ƿ������h����Ŀ?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('��ʾ', '��ѡ����Ҫɾ���������ٵ��ɾ��!');
				return;
			}
			var total_ids = [];
			for ( var i = 0; i < rows.length; i++) {
				total_ids.push(rows[i].total_id);
			}
			$("#total_ids").val(total_ids);
			var form = window.document.forms[0];
			form.action = appUrl + "/newsAction!deleteNewsTotal.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function choseOrg() {
	var url = appUrl + '/orgAction!orgTreePage.jspa';
	var WWidth = 560;
	var WHeight = 260;
	var $win = $("#maintNews")
			.window(
					{
						title : '��֯ѡ��',
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : false
					});
	$win.window('open');
}
function closeOrgTree() {
	$("#maintNews").window('close');
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
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
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
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