var panelWidth = document.getElementById("dd").offsetWidth;
$(document).ready(function() {
	$(".easyui-panel").panel({
		width : panelWidth*0.98
	});
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
//	loadGrid1();
//	loadGrid2();
	
	$('#beginDate').datebox({
		onSelect : function(d) {
				$('#beginDate').val(utcToDate(d));
		}
	});
	$('#endDate').datebox({
		onSelect : function(d) {
				$('#endDate').val(utcToDate(d));
		}
	});
	
	
});


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		var type = $("#deleteType").val();
		if(type == 'model'){
			search_1();
		}else if(type == 'att'){
			search_2();
		}
	}
}

function loadGrid() {
	$('#datagrid_1').datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/boformAction!getBORoleList.jspa',
//						queryParams: {
//                    		endDate: $("#endDate").val(),
//		                    beginDate:$("#beginDate").val(),
//	                    	custId : $("#id").combobox("getValue")
//	                          },
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						idField : 'roleId',
						nowrap : true,
						remoteSort : true,
						striped : true,
						width : panelWidth*0.98,
						height : height,
						columns : [[{
							field : 'ck',
							checkbox : true
						},{
					field : 'roleId',
					title : '��ɫID',
					width : setColumnWidth(0.07),
					align : 'center',
					sortable : true
				}, {
					field : 'roleName',
					title : '��ɫ����',
					width : setColumnWidth(0.1),
					align : 'center'
					
				},{
					field : 'pid',
					title : '����ID',
					width : setColumnWidth(0.1),
					align : 'center'
					
				},{
					field : 'descn',
					title : '����',
					width : setColumnWidth(0.1),
					align : 'center'
					
				},{
					field : 'price',
					title : '����',
					width : setColumnWidth(0.07),
					align : 'center',
					formatter : function(value, row, index) {
						     var id = row.roleId;
							return '<img style="cursor:pointer" onclick=updateBORole("'
												+ id
												+ '") title="�޸ı����ɫ" src='
									+ imgUrl
									+ '/images/actions/action_edit.png align="absMiddle"></img>';			
					}
				}]],
						      onClickRow:function(rowIndex, rowData){
						 	    $("#roleId").val(rowData.roleId);
//						 		$("#instock_total_id").val(rowData.instock_total_id);
//						 		$("#instock_provide_id").val(rowData.instock_provide_id);
//						 		$("#instock_good_place").val(rowData.instock_good_place);
    				            search_2();
    				            var options = $("#datagrid_2").datagrid("options");
        						options.title = "��ɫ-- "+rowData.roleId + " -- ��ϸ";
        						$("#datagrid_2").datagrid(options);
   							},
   						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								createBORole();
							}
						},"-", {
							text : 'ɾ��',
							iconCls : 'icon-add',
							handler : function() {
								deleteBORole();
							}
						}, "-" ]
					});

	// ��ҳ�ؼ�
	var p = $('#datagrid_1').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
	$('#datagrid_2').datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/boformAction!getBORoleDetailList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						striped : true,
						width : panelWidth*0.98,
						height : height,
						columns : [ [{
							field : 'ck',
							checkbox : true
						},{
									field : 'bid',
									title : 'ID',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable : true
								},{   
								   field : 'memo',
									title : '�ֶ�',
									width : setColumnWidth(0.2),
									align : 'center'
								},{   
								   field : 'value',
									title : 'ֵ',
									width : setColumnWidth(0.15),
									align : 'center'
								}
								] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								createBORoledet();
							}
						},"-", {
							text : '�h��',
							iconCls : 'icon-add',
							handler : function() {
								deleteBORoledet();
							}
						},"-"]
					});

	// ��ҳ�ؼ�
	var r = $('#datagrid_2').datagrid('getPager');
	$(r).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '�� {total} ����¼'
	});
}

//function loadGrid2() {
//	$('#id').combogrid({
//		panelWidth : 400,
//		panelHight : 600,
//		idField : 'kunnr',
//		textField : 'name1',
//		pagination : true,// �Ƿ��ҳ
//		// rownumbers : true,// ���
//		collapsible : false,// �Ƿ���۵���
//		// fit : true,// �Զ���С
//		method : 'post',
//		// multiple : true,
//		url : appUrl + '/goal/goalAction!getKunner.jspa',
//		columns : [ [ {
//			field : 'ck',
//			checkbox : true,
//			hidden : true
//		},  {
//			field : 'id',
//			title : '�ͻ�ID',
//			hidden : true,
//			width : 60
//		},
//			{
//			field : 'kunnr',
//			title : '�ͻ����',
//			align : 'center',
//			width : 120
//		}, {
//			field : 'name1',
//			title : '�ͻ�����',
//			align : 'center',
//			width : 100
//		}, {
//			field : 'mobNumber',
//			title : '�ֻ�',
//			align : 'center',
//			width : 150
//		}] ],
//		toolbar : '#toolbar2'
//	});

//}

//function loadGrid1() {
//	$('#wid').combogrid({
//		panelWidth : 400,
//		panelHight : 600,
//		idField : 'matnr',
//		textField : 'bezei',
//		pagination : true,// �Ƿ��ҳ
//		// rownumbers : true,// ���
//		collapsible : false,// �Ƿ���۵���
//		// fit : true,// �Զ���С
//		method : 'post',
//		// multiple : true,
//		url : appUrl + '/goal/goalAction!getMatList.jspa',
//		columns : [ [ {
//			field : 'ck',
//			checkbox : true,
//			hidden : true
//		},  {
//			field : 'matnr',
//			title : '���Ϻ�',
//			width : 120
//		},
//			{
//			field : 'mvgr1',
//			title : '������',
//			align : 'center',
//			width : 120
//		}, {
//			field : 'bezei',
//			title : '����������',
//			align : 'center',
//			width : 100
//		}] ],
//		toolbar : '#toolbar1'
//	});
//}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function search_1() {
	var queryParams = $('#datagrid_1').datagrid('options').queryParams;
    queryParams.roleId = $("#roleId1").val();
    queryParams.roleName = $("#roleName").val();
	$("#datagrid_1").datagrid('reload');
}
function deleteBORole() {
	$.messager.confirm('Confirm', '�Ƿ������h����ɫ?', function(r) {
		if (r) {
			var rows = $('#datagrid_1').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  û���б�ѡ��!');
				return;
			}
			$("#deleteType").val("model");
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].roleId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "roleAction!deleteRole.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function deleteBORoledet() {
	$.messager.confirm('Confirm', '�Ƿ������h����ɫ?', function(r) {
		if (r) {
			var roleId = $("#roleId").val();
			var rows = $('#datagrid_2').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  û���б�ѡ��!');
				return;
			}
				if(roleId=="" || roleId.length==0){
		   $.messager.alert('Tips', "��ѡ��һ���ܵ�!", 'warning');
		     return;
	 }
			$("#deleteType").val("att");
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].bid);
			}
			$("#ids1").val(ids);
			var form = window.document.forms[0];
			form.action = "roleAction!deleteRoledt.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function clearValue_1(){
	$('#beginDate').val("");
    $('#endDate').val("");
	$("#beginDate").datebox("setValue", '');
	$("#endDate").datebox("setValue", '');
	$("#id").combobox("setValue", '');
}

function search_2() {
	var queryParams = $('#datagrid_2').datagrid('options').queryParams;
	queryParams.type = 'Y';
	queryParams.roleId = $("#roleId").val();
	queryParams.memo =$("#memo").val();
	queryParams.value =$("#value").val();
	$("#datagrid_2").datagrid('reload');
}

function clearValue_2(){
	$("#instockdetBatch").val("");
	$("#wid").combobox("setValue", '');
//	$("#mprAttDescript").val("");
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#id').combogrid({
		url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value=' + val
	});
	$('#id').combogrid("grid").datagrid('reload');

}

function searcher1(val) {
	val = encodeURIComponent(val);
	$('#wid').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#wid').combogrid("grid").datagrid('reload');

}


function createBORole(){
	initMaintModelAtt('���������ɫ','/boformAction!createBoRolePrepare.jspa', 600, 350);
}

function updateBORole(id){
	var id = encodeURIComponent(id);
   initMaintModelAtt('�޸ı����ɫ',  '/boformAction!updateBORolePrepare.jspa?roleId=' + id,
			600, 350);

}

function createBORoledet(){
//	var instock_total_id = $("#instock_total_id").val();
	var roleId = $("#roleId").val();
//	var instock_provide_id=$("#instock_provide_id").val();
//	var instock_good_place= $("#instock_good_place").val();
	if(roleId=="" || roleId.length==0){
		$.messager.alert('Tips', "��ѡ��һ���ܵ�!", 'warning');
		return;
	}
	initMaintModelAtt('�����ϸ', '/boformAction!createBORoledtPrepare.jspa?roleId='+ roleId, 600, 350);
}

//function updateMprAtt(rid){
//	initMaintModelAtt('�޸��ܵ�', '/instockAction!updateStockPrepare1.jspa?instock_total_id='+ instock_total_id, 450, 400);
//}

function closeMaintModelAtt(){
	$("#maintModelAtt").window('close');
}

function deleteMprModel(rid){
	$("#deleteType").val("model");
	$("#ids").val(rid);
	var form = window.document.forms[0];
	form.action = appUrl + '/activityPlan/miaModelAction!deleteMprModel.jspa';
	form.target = "hideFrame";
	form.submit();	
}

function deleteMprAtt(){
	$.messager.confirm('Confirm', '�Ƿ�ɾ��ģ������?', function(r) {
		if (r) {
			var rows = $('#datagrid_2').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '��ѡ������!', 'warning');
				return;
			}
			$("#deleteType").val("att");
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].mprAttId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl + '/activityPlan/miaModelAction!deleteMprAttribute.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});		
}

//�������ڶ���
function initMaintModelAtt(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintModelAtt")
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
						maximizable : false,
						collapsible : false,
						draggable : true
					});
				
	$win.window('open');
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
	date = date + month[str[1]] + "-" + str[2] ;
	return date;
}
