$(document).ready(function() {
			loadGrid();
			isSelectedOrg();
			$('#hideFrame').bind('load', promgtMsg);
		});

function loadGrid() {
	$('#roleIds').combogrid({
				panelWidth:480,
				idField:'stationId',
				textField:'stationName',
				pagination : true,//
                rownumbers:true,//
                collapsible:false,//
                fit: true,//
                method:'post',  
                multiple:true,
				url:appUrl + '/allUserAction!getSelectedStationsJSON.jspa?orgId='+$('#orgId').val(),  
				columns:[[
					  {	field : 'ck',checkbox : true
						},
					  {field:'id',title:'POSID',width:100,hidden:true},  
                    {field:'stationId',title:'��λID',width:60},  
                    {field:'stationName',title:'��λ����',width:150},  
                    {field:'orgName',title:'��˾��',width:120},
                     {field:'userName',title:'�û���',width:80}
				]],
				toolbar:'#toolbar'
				

			});
	var q = $('#roleIds').combogrid("grid").datagrid();
	$('#roleIds').combo({  
   		 multiple:true  
	});
	$(q).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});	
	$('#datagrid1').datagrid({
				iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/station!searchStationUserMore.jspa?userId='+$('#userId').val(),
						loadMsg : '����Զ��������,��ȴ�...',
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
							title : 'ְλID',
							width : 100,
							align : 'center',
							hidden:true
						}, {
							field : 'empCode',
							title : '�û�ID',
							width : 100,
							align : 'center'
						}, {
							field : 'userName',
							title : '�û���',
							width : 100,
							align : 'center'
						}
						, {
							field : 'orgName',
							title : '��֯',
							width : 170,
							align : 'center'
						}
						, {
							field : 'stationName',
							title : '��λ����',
							width : 173,
							align : 'center'
						}
						]],
						toolbar : [ "-", {
							text : 'ɾ��',
							iconCls : 'icon-remove',
							handler : function() {
								remove();
							}
						}]
					});

	// ��ҳ�ؼ�
	var p = $('#datagrid1').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
				beforePageText : '��',
				afterPageText : 'ҳ    �� {pages} ҳ',
				displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
			});
}

function searcher(val){
	val =encodeURIComponent(val)
	$('#roleIds').combogrid({url : appUrl + '/allUserAction!getSelectedStationsJSON.jspa?stationId='+val+"&orgId="+$('#orgId').val()});
	$('#roleIds').combogrid("grid").datagrid('reload');

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
	$("#datagrid1").datagrid('reload');
}
function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 350;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,///
						closable : true,//
						left: ($(window).width() - 400) * 0.8,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true//
					});

	$win.window('open');
}

function selectOrgTree(){
	if($('#datagrid1').datagrid('getData').rows.length != ""){
		$.messager.alert('Tips', "����Ա����֯���и�λ���ڣ����Ƚ����λ��ϵ�ٸ�����֯", 'info');
		return;
	}
	initMaintWindowForOrg('ѡ����֯', '/orgAction!orgTreePage.jspa');
}
function isSelectedOrg(){
	if(document.getElementById('orgName').value==''){
		$('#roleIds').combo({disabled:true});
	}else{
		$('#roleIds').combogrid({url : appUrl + '/allUserAction!getSelectedStationsJSON.jspa?orgId='+$('#orgId').val()});
		$('#roleIds').combogrid("grid").datagrid('reload');
		$('#roleIds').combo({disabled:false});
	}	
}
function returnValue(selectedId, selectedName){
	document.getElementById('orgId').value =selectedId;
	document.getElementById('orgName').value= selectedName;
	isSelectedOrg();
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}
/**
 * 
 * @param {} e
 * @return {Boolean}
 */
function remove() {
	var ids = '';
	var rows = $('#datagrid1').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids += rows[i].id+",";
	}
	if (ids == '') {
		$.messager.alert('Tips', 'δѡ���κθ�λ��', 'warning');
		return;
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + '/allUserAction!deleteUserStationById.jspa?ids='
		+ ids+"&userId="+$('#userId').val();
		form.target = "hideFrame";
		form.submit();
	}

}
/**
 * ����¸�λ
 * ���ж���֯�Ƿ���Ҫ��� 
 * ����б�� ��ҪȥEMP�����ORG_ID ���ޣ���ֱ�Ӹ���STATION_USER���USER_ID
 */
function saveStationUser(){
	var flag = '';
	var ids = '';
	var rows = $('#roleIds').combogrid("grid").datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('Tips', "��ѡ���λ�ٱ���", 'warning');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		ids += rows[i].id+",";
	}
	var form = window.document.forms[0];
	if($('#orgId1').val() != $('#orgId').val()){
		flag = 'Y';
	}
	form.action = appUrl + '/allUserAction!addUserStationById.jspa?flag='+flag+"&orgId4Update="+$('#orgId').val()+"&ids="+ids+"&userId="+$('#userId').val()+"&loginId="+$("#loginId").val();
//	form.action = appUrl + '/allUserAction!addUserStationById.jspa?orgId='+$('#orgId').val()+"&ids="+ids;
	form.target = "hideFrame";
	form.submit();
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