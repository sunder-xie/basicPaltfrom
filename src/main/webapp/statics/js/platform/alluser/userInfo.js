$(document).ready(function() {
	loadGrid(); // Ȩ�޵��ѯ
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {

	$('#con_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						height : 370,
						striped : true,
						url : appUrl
								+ '/allUserAction!getUserInfoList.jspa?ran='
								+ Math.random(),

						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						nowrap : true,
						// idField : 'dictTypeId',
						pagination : true,
						rownumbers : true,
						fitColumns : true,
						// frozenColumns : [ [ ] ],
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									id : 'dictTypeId',
									title : '����ID',
									field : 'userId',
									width : 80,
									align : 'center',
									sortable : true,
									hidden : true
								},
								{
									title : '��֯ID',
									field : 'orgId',
									width : 80,
									align : 'center',
									hidden : true,
									formatter : function(value, row, rec) {
										var orgId = row.orgId;
										if (orgId == null) {
											return "";
										} else {
											return orgId;
										}
									}
								},
								{
									title : '��֯��',
									field : 'orgName',
									width : 80,
									align : 'center',
									hidden : true,
									formatter : function(value, row, rec) {
										var orgName = row.orgName;
										if (orgName == null) {
											return "";
										} else {
											return orgName;
										}
									}
								},
								{
									title : '�û�ID',
									field : 'loginId',
									width : $(this).width() * 0.06,
									align : 'center',
									sortable : true
								},
								{
									field : 'userName',
									title : '����',
									align : 'center',
									width : $(this).width() * 0.08,
									sortable : true
								},
								{
									field : 'sex',
									title : '�Ա�',
									align : 'center',
									width : $(this).width() * 0.07,
									sortable : true,
									formatter : function(value, row, rec) {
										var sex_ch = row.sex;
										if (sex_ch == 'M') {
											return "��";
										} else if (sex_ch == 'F') {
											return "Ů";
										}
									}
								},
								{
									field : 'mobile',
									title : '�ֻ�����',
									width : $(this).width() * 0.1,
									align : 'center',
									sortable : true
								},
								{
									field : 'email',
									title : '����',
									align : 'center',
									width : $(this).width() * 0.16,
									sortable : true
								},
								{
									field : 'phone',
									title : '�칫�绰',
									align : 'center',
									width : $(this).width() * 0.13,
									sortable : true
								},
								{
									field : 'address',
									title : 'ͨѶ��ַ',
									align : 'center',
									width : $(this).width() * 0.20,
									sortable : true
								},
								{
									field : 'stations',
									title : '��λ����',
									align : 'center',
									width : $(this).width() * 0.08,
									formatter : function(value, row, rec) {
										var id = row.userId;
										var orgName = row.orgName;
										var orgId = row.orgId;
										var state = row.userState;
										var loginId = row.loginId;
										if (orgName == null) {
											orgName = "";
										} else {
											orgName = encodeURIComponent(orgName);
										}
										if (orgId == null) {
											orgId = "";
										} else {
											orgId = encodeURIComponent(orgId);
										}

										return '<img style="cursor:pointer" onclick=searchStation("'
												+ id
												+ '","'
												+ state
												+ '","'
												+ orgId
												+ '","'
												+ loginId
												+ '","'
												+ orgName
												+ '") title="�鿴��λ/�޸ĸ�λ" src='
												+ imgUrl
												+ '/images/actions/action_dot.png align="absMiddle"></img>';
									}
								},
								{
									field : 'userState',
									title : '״̬',
									width : $(this).width() * 0.08,
									align : 'center',
									sortable : true,
									formatter : function(value, row, rec) {
										var state = row.userState;
										if (state == 'Y') {
											return "<font color='green'>����</font>";
										} else if (state == 'N') {
											return "<font color='red'>����</font>";
										}
									}
								},
								{
									field : 'oper',
									title : '��Ա״̬����',
									width : $(this).width() * 0.1,
									align : 'center',
									//������Ա�˺� ������õ�״̬��Ϊת��������ע����
//									formatter : function(value, row, rec) {
//										var id = row.userId;
//										var state = row.userState;
//										var loginId = row.loginId;
//										if (state == 'Y') {
//											return '<img style="cursor:pointer" onclick=forbidden("'
//													+ id+'","' + loginId 
//													+ '") title="������Ա�˺�" src='
//													+ imgUrl
//													+ '/images/actions/action_del.png align="absMiddle"></img>';
//										} else {
//											return '<img style="cursor:pointer" onclick=startup("'
//													+ id+'","' + loginId 
//													+ '") title="������Ա�˺�" src='
//													+ imgUrl
//													+ '/images/actions/icon_ok.gif align="absMiddle"></img>';
//										}
//									}
								} ] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : '�޸�',
							iconCls : 'icon-edit',
							handler : function() {
								edit();
							}
						}, "-", {
							text : 'ɾ��',
							iconCls : 'icon-remove',
							handler : function() {
								remove();
							}
						}, "-" ]
					});
	// ��ҳ�ؼ�
	var p = $('#con_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function search() {
	var queryParams = $('#con_list').datagrid('options').queryParams;
	queryParams.loginId = encodeURIComponent($("#loginId").val());
	queryParams.userName = encodeURIComponent($("#userName").val());
	queryParams.orgId = encodeURIComponent($("#orgId").val());
	var bhxjFlag;
	$("[name='bhxjFlag']").each(function() {
		if (this.checked) {
			bhxjFlag = this.value;
		} else {
			bhxjFlag = "N";
		}
	});
	queryParams.bhxjFlag = encodeURIComponent(bhxjFlag);
	$("#con_list").datagrid('load');
}

// �������ڶ���
function initMaintWindow(title, url) {
	var url = appUrl + url;
	var WWidth = 800;
	var WHeight = 460;
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
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}
function initMaintWindowForEdit(title, url) {
	var url = appUrl + url;
	var WWidth = 800;
	var WHeight = 450;
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
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}
/**
 * ��֯��
 * 
 * @param {}
 *            title
 * @param {}
 *            url
 */
function initMaintWindowForOrg(title, url) {
	var url = appUrl + url;
	var WWidth = 400;
	var WHeight = 460;
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
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}
function initMaintWindowForStation(title, url) {
	var url = appUrl + url;
	var WWidth = 600;
	var WHeight = 400;
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
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}

/**
 * ��Ա����
 */
function add() {
	initMaintWindow('��Ա����', '/allUserAction!toCreateUser.jspa');
}
/**
 * �鿴��Ա��λ
 * 
 */
function searchStation(id, state, orgId,loginId,orgName) {
	if (state == 'N') {
		$.messager.alert('��ʾ', '����״̬��Աû�и�λ�����ɲ鿴', '��ʾ');
		return;
	}
	initMaintWindowForStation('��λ�鿴',
			'/allUserAction!searchStationUser.jspa?userId=' + id + "&orgId="
					+ orgId + "&orgName=" + orgName+"&loginId="+loginId);
}
/**
 * �޸���Ա��Ϣ
 */
function edit() {
	var ids = '';
	var rows = $('#con_list').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('��ʾ', 'δѡ���κ���Ա��', '��ʾ');
		return;
	} else if (rows.length > 1) {
		$.messager.alert('��ʾ', '�޸�ʱֻ��ѡ��һ����Ա', '��ʾ');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		if (rows[i].userState == 'N') {
			$.messager.alert('��ʾ', 'δ������Ա����Ϣ�����޸ģ���������', '��ʾ');
			return;
		}
		ids = rows[i].userId;
	}
	initMaintWindowForEdit('��Ա��Ϣ�޸�',
			'/allUserAction!toUpdateUserInfo.jspa?ids=' + ids);

}

/**
 * ɾ����Ա��Ϣ ɾ����������
 */
function remove() {
	var ids = '';
	var logins = '';
	var rows = $('#con_list').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids += rows[i].userId + ",";
		logins += rows[i].loginId + ",";
	}
	if (ids == '') {
		$.messager.alert('��ʾ', 'δѡ���κ���Ա��', '��ʾ');
		return;
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + '/allUserAction!deleteUserByEmpId.jspa?ids='
				+ ids + "&logins=" + logins;
		form.target = "hideFrame";
		form.submit();
	}

}
/**
 * ����֯��
 */
function selectOrgTree() {
	initMaintWindowForOrg('ѡ����֯', '/orgAction!orgTreePage.jspa');
}

// �رմ���ҳ��
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('��ʾ', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('��ʾ', successResult, 'info');
		search();
	}
}
/**
 * ��֯���ķ���ֵ����
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}
/**
 * ���� ������Ա�˺�
 * 
 * @param {}
 *            id
 */
function forbidden(id,loginId) {
	$.messager.confirm("��ʾ", "ȷ��Ҫ���ø���Ա�˺�ô��",
			function(data) {
				if (data) {
					var form = window.document.forms[0];
					form.action = appUrl
							+ '/allUserAction!forbidden.jspa?userId=' + id+'&loginId4AD='+loginId;
					form.target = "hideFrame";
					form.submit();
				} else {
					return;
				}
			});
}
function startup(id,loginId) {
	$.messager.confirm("��ʾ", "ȷ��Ҫ���ø���Ա�˺�ô��", function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl + '/allUserAction!startup.jspa?userId=' + id+'&loginId4AD='+loginId;
			form.target = "hideFrame";
			form.submit();
		} else {
			return;
		}
	});
}
/**
 * �س���Ĭ��ʱ���
 * 
 * @param {}
 *            e
 * @return {Boolean}
 */
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
$(window).resize(function () { 
          $('#datagrid').datagrid('resize', {
              width: $(".f_main").width()
          });
});
