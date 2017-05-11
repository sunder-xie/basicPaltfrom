$(document).ready(function() {
	loadGrid();
});
function loadGrid() {
	$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/positionTypeAction!searchStationTypeList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'positionTypeId',
									title : '��λ���',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'positionTypeName',
									title : '��λ����',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								/*{
									field : 'positionTypeName',
									title : '��λ����',
									width : setColumnWidth(0.3),
									align : 'left',
									formatter : function(value, row, index) {
									return	"<a href='#' onclick=alert();> " +
											value+"</a>";
										return '<img style="cursor:pointer" onclick="alert()"  src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle">' +
												value+
														'</img>  ';
									}
								},*/
								{
									field : 'positionProperty',
									title : '��λ����',
									width : setColumnWidth(0.2)
								},{
									field : 'companyName',
									title : '��˾',
									width : setColumnWidth(0.2)
								},
								{
									field : 'price',
									title : '����',
									width : setColumnWidth(0.2),
									align : 'center',
									formatter : function(value, row, index) {
										return '<img style="cursor:pointer" onclick="alert()" title="�޸�����" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  '
												+ ' <img style="cursor:pointer" onclick="alert()" title="ά����ɫ" src='
												+ imgUrl
												+ '/images/actions/action_view.png align="absMiddle"></img>'
												+ ' <img style="cursor:pointer" onclick="alert()" title="��ѯְλ" src='
												+ imgUrl
												+ '/images/actions/action_view.png align="absMiddle"></img>'
												+ ' <img style="cursor:pointer" onclick="alert()" title="ɾ��" src='
												+ imgUrl
												+ '/images/actions/action_del.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								alert(1);
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


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.stationId = $("#stationId").val();
	queryParams.stationName =  $("#stationName").val();
	queryParams.orgId = $("#orgId").val();
	$("#datagrid").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}


//�������ڶ���
function initMaintPositionType(title, url) {
	var url = appUrl + url;
	var WWidth = 600;
	var WHeight = 400;
	var $win = $("#maintMenu")
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
						closable : false,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : false
					});

	$win.window('open');

}
function createPositionType() {
	initMaintMenu('�˵�����', '/menuAction!createMenuPrepare.jspa');
}