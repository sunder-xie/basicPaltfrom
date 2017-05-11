$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	isSelectedOrg();
	loadGrid();
});
function loadGrid() {
	$('#roleIds').combogrid({
				panelWidth:580,
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
                    {field:'stationId',title:'岗位ID',width:100},  
                    {field:'stationName',title:'岗位名称',width:150},  
                    {field:'orgName',title:'公司名',width:150},
                     {field:'userName',title:'用户名',width:100}
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
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});	
//	
//	$('#roleIds1').combogrid({
//				panelWidth:380,
//				idField:'stationId',
//				textField:'stationName',
//				pagination : true,//
//                rownumbers:true,//
//                collapsible:false,//
//                fit: true,//
//                method:'post',  
//                multiple:true,
//				url:appUrl + '/allUserAction!getSelectedStationsJSON.jspa?orgId='+$('#orgId').val(),  
//				columns:[[
//					  {	field : 'ck',checkbox : true
//						},
//					  {field:'id',title:'POSID',width:100,hidden:true},  
//                    {field:'stationId',title:'岗位ID',width:100},  
//                    {field:'stationName',title:'岗位名称',width:150}
//				]],
//				toolbar:'#toolbar'
//				
//
//			});
//	var p = $('#roleIds1').combogrid("grid").datagrid();
//	$('#roleIds1').combo({  
//   		 multiple:true  
//	});
//	$(p).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});	
	
	$('#sex').combobox({
				valueField:'id',
				textField:'text'
			});
	$(".datebox :text").attr("readonly","readonly");
	var curr_time = new Date();   
	var strDate = curr_time.getFullYear()+"-";   
	strDate += curr_time.getMonth()+1+"-";   
	strDate += curr_time.getDate();
	$("#startDate").datebox("setValue", strDate); 
	$('#postButton').linkbutton({disabled:true}); 
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
		document.getElementById('loginId').value='';
	} else if("人员创建成功." == successResult){
		$.messager.confirm("提示","创建成功，确认要关闭当前页面么？", function (data) {
	if (data){
	window.parent.closeOrgTree();
	window.parent.search();
	}
	else {
	return;
	}
	});
	}else
	{
		$.messager.alert('Tips', successResult, 'info');
	}
}



function submit() {
	var a = $("#loginId").validatebox('isValid');
	var b = $("#userName").validatebox('isValid');
	var c = $("#passWd").validatebox('isValid');
	var d = $("#email").validatebox('isValid');
	var e = $("#busMobilephone").validatebox('isValid');
	var f = $("#repassWd").validatebox('isValid');
	var g = $("#orgName").validatebox('isValid');
	if (!(a&&b&&c&&d&&e&&f&&g)) {
		return;
	}
	var ids = $('#roleIds').combogrid('grid').datagrid('getSelections');
	var idd='';
	for(i=0;i<=ids.length-1;i++){
	 idd +=ids[i].id+',';
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!creatUser.jspa?roleIds="+idd;
	form.submit();
}

function close() {
	window.parent.closeMaintWindow();
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

function initMaintWindow4Post(title, url) {
	var url = appUrl + url;
	var WWidth =600;
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
						left: $(window).width() * 0.2,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true//
					});

	$win.window('open');
}

function selectOrgTree(){
	initMaintWindowForOrg('选择组织', '/orgAction!orgTreePage.jspa');
}

function selectOrgTree4Post(){
	initMaintWindow4Post('选择职务', '/orgAction!orgTreePage4Post.jspa');
}

function closeOrgTree() {
	$("#maintWindow").window('close');
}
function isSelectedOrg(){
	if(document.getElementById('orgName').value==''){
		$('#roleIds').combo({disabled:true});
	}else{
		$('#roleIds').combogrid({url : appUrl + '/allUserAction!getSelectedStationsJSON.jspa?orgId='+$('#orgId').val()});
		$('#roleIds').combogrid("grid").datagrid('reload');
		$('#roleIds').combo({disabled:false});
		$('#postButton').linkbutton({disabled:false}); 
	}	
}
function returnValue(selectedId, selectedName){
	document.getElementById('orgId').value =selectedId;
	document.getElementById('orgName').value= selectedName;
	isSelectedOrg();
}
function testId(val){
	if(val==""){
		$.messager.alert('Tips', "用户ID为空", 'warning');
		return ;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/allUserAction!isLoginIdExist.jspa?loginId4Check="+val;
	form.submit();
}
$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
});
