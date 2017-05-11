$(document).ready(function() {

	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#gridList')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/newsAction!searchNewsDetailList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'delail_id',
									title : 'id',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'delail_title',
									title : '����',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'total_name',
									title : '������Ŀ',
									width : setColumnWidth(0.15),
									align : 'center'
								},
								{
									field : 'optionOrgName',
									title : '�ɼ���֯',
									width : setColumnWidth(0.15),
									align : 'center',
									formatter : function(value, row, index) {
										if (!value)
											return "����";
										else
											return value;
									}
								},
								{
									field : 'delail_operator',
									title : '������',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'detail_date',
									title : '����ʱ��',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										return utcToDate(value);
									}

								},
								{
									field : 'clicks_ratio',
									title : '�����',
									width : setColumnWidth(0.05),
									align : 'center'
								},
								{
									field : 'price',
									title : '����',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										var rid = row.delail_id;
										return '<img style="cursor:pointer"  onclick=updateStation("'
												+ rid
												+ '") title="�޸�����"  src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  ';
									}
								} ] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								createNewsDetail();
							}
						}, "-", {
							text : 'ɾ��',
							iconCls : 'icon-remove',
							handler : function() {
								deleteNewsDetail();
							}
						}, "-" ]
					});

	// ��ҳ�ؼ�
	var p = $('#gridList').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning');
		search();
	}
}
function search() {
	var queryParams = $('#gridList').datagrid('options').queryParams;

	queryParams.delail_title = encodeURIComponent($("#delail_title").val());
	queryParams.total_Name = encodeURIComponent($("#total_Name").val());
	// queryParams.orgId = $('#orgId').combobox('getValue');
	$("#gridList").datagrid('reload');
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
function deleteNewsDetail() {
	$.messager.confirm('Confirm', '�Ƿ������h���˵�?', function(r) {
		if (r) {
			var rows = $('#gridList').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  no selected rows!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].delail_id);
			}
			$("#delail_ids").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl + "/newsAction!deleteNewsDetail.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});

}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

// �������ڶ���
function initNewsDetail(title, url) {
	var url = appUrl + url;
	var WWidth = 850;
	var WHeight = 500;
	var $win = $("#newsDetail")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : false,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});
	$win.window('open');
}

/*
 * //�������ڶ��� function initMaintStationUser(title, url) { var url = appUrl + url;
 * var WWidth = 600; var WHeight = 400; var $win = $("#maintStation1") .window( {
 * title : title, width : WWidth, height : WHeight, content : '<iframe
 * frameborder="no" width="100%" height="100%" src=' + url + '/>', shadow :
 * true, modal : true, closed : true,/// closable : true,// minimizable :
 * false,// maximizable : true,// collapsible : false,// draggable : false });
 * 
 * $win.window('open'); }
 */
function closeNewsDetail() {
	// �رմ���ҳ��
	$("#newsDetail").window('close');
	// $("#maintStation1").window('close');

}
function createNewsDetail() {
	initNewsDetail('������Ŀ��ϸ', '/newsAction!newsDetail_add.jspa');
}
/**
 * �޸���Ŀ��ϸ
 * @param id
 */
function updateStation(id) {
	initNewsDetail('�޸���Ŀ��ϸ', '/newsAction!updateNewsDetailPre.jspa?detailId='
			+ id);
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