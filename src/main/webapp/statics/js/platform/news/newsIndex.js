$(function() {
	waitWork();
	loadAddress();
	$('#hideFrame').bind('load', promgtMsg);
	winLoad();
});

function winLoad(){
	$('#win').window({    
	    width:700,    
	    height:400,
	    title:" ",
	    collapsible:false, 
	    minimizable:false, 
	    maximizable:false, 
	    draggable:false,
	    modal:true   
	}); 
}

/*window.onresize = function() {
	location.reload();
}*/

/**
 * 点击公告显示公告具体内容
 * @param detailId
 * @param totalShow
 */
function oneNews(detailId, totalShow) {
	var d1 = new Date().getTime();
	var WWidth = 950;
	var WHeight = 650;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);
	var url = appUrl + "/newsAction!getOneNews.jspa?detailId=" + detailId
			+ "&totalShow=" + totalShow + "&rtime=" + d1;
	window.open(url, "OneNews", "left=" + WLeft + ",top=" + WTop
			+ ",width=" + WWidth + ",height=" + WHeight
			+ ",toolbar=no,menubar=no,scrollbars=yes");
}
//查看更多事务
function searchWorks() {
	var text = $('a.selected').text();
	var url = '';
	if (text == '待办事宜') {
		window.parent.openProcessedTab();
	} else if (text == '已办事宜') {
		window.parent.add('已办事宜', '/wfe/eventAction!toSearchProcessedEvent.jspa', '_self');
	} else if (text == '我的事务') {
		window.parent.add('我的事务', '/wfe/eventAction!toSearchEvent.jspa', '_self');
	}
}

//查看更多公告
function searchNews() {
	window.parent.add('系统公告', '/newsAction!getSearchNews.jspa', '_self');
}

//查看通讯录
function searchAddress() {
	window.parent.add('通讯录', '/addressList/addressListAction!searchAddressList.jspa', '_self');
}

//常用功能
function commonFun(o) {
	var host = window.parent.appUrl;
	switch (o) {
	case 1:
		window.open(host+'/statics/WebHelp/index.htm');
		break;
	case 2:
		window.parent.add('个人信息查询及维护', '/allUserAction!toViewOfUserInfo.jspa', '_self');
		break;
	}
}

//查询通讯录
function searchPerson() {
	if ($("#userName").val()=='') return;
	var queryParams = $('#address').datagrid('options').queryParams;
	queryParams.orgId = 50919;
	queryParams.userName = encodeURIComponent($("#userName").val());
	queryParams.bhxjFlag = encodeURIComponent('C');
	$("#address").datagrid('load');
}

//通讯录展示
function loadAddress() {
	$('#address').datagrid({
		url: window.parent.appUrl + '/addressListAction!searchMailLists.jspa?page=1&rows=5',
		fitColumns:true,
		singleSelect:true,
		loadMsg : '数据远程载入中,请等待...',
		queryParams:{
			orgId: 0
		},
		columns:[[   
		    {field:'userName',title:'姓名',width:60,fixed:true},
		    {field:'busMobilePhone',title:'电话',width:100,fixed:true},
		    {field:'empMobilePhone',title:'私人电话',width:100,fixed:true},
		    {field:'stMobile',title:'短号',width:70,fixed:true},
		    {field : 'orgName',title : '组织',width : 120}
		]]
	});
}

var canEnabled = false;

//待办事宜加载
function waitWork() {
	if (canEnabled) return;
	canEnabled = true;
	for (var i=1; i<5; i++) {
		$('#link'+i).removeClass('selected');
	}
	$('#link1').addClass('selected');
	$('#dg').datagrid({
		url:'/basisPlatform/wfe/eventAction!getProcessEventJsonList.jspa?page=1&rows=5',
		fitColumns:true,
		singleSelect:true,
		striped: true,
	    columns:[[    
  	        {field:'operation',title:'操作',width:30, align:'center',
	        	formatter : function(value, row, index) {
	        		var strReturn="";
					if(row.eventType==1){
						strReturn = '<a href= javascript:ontransferEvent('
							+row.eventId+','+row.currentDetailid+')>签收 </a>'
					}else if (row.eventType==2){
						strReturn = '<a href= javascript:CountersignEvent("'
							+ row.cc_id + '","'
							+ row.eventId
							+ '")>会签处理 </a>';
					}else{
						 strReturn = '<a href= javascript:processEvent("'
							+ row.eventId + '","'+ row.currentDetailid + '","'
							+ row.keys + '","'
							+ row.modelId +'","'
							+ row.curStaId +'")>处理 </a>';
					}
					return strReturn;}
	        },
	        {field:'eventId',title:'事务编号',width:50},
	        {field:'modelName',title:'流程分类',width:100},
	        {field:'eventTitle',title:'事务标题', width: 200},    
	        {field:'orgName',title:'提出组织',width:80},
	        {field:'empName',title:'提出人',width:50},
	        {field:'creatdate',title:'提出时间',width:100},
	        {field : 'eventType',title : 'eventType',hidden : true},
	        {field : 'keys',title : 'keys',	hidden : true},
	        {field : 'modelId',title : '事务模板id',hidden : true},
			{field : 'curStaId',title : '角色Id',hidden : true},
			{field : 'currentDetailid',title : 'detailId',hidden : true}
	    ]],
	    onLoadSuccess: function(){
	    	canEnabled = false;
	    }
	});
}

//已办事宜
function completeWork() {
	if (canEnabled) return;
	canEnabled = true;
	for (var i=1; i<5; i++) {
		$('#link'+i).removeClass('selected');
	}
	$('#link2').addClass('selected');
	$('#dg').datagrid({
		url:'/basisPlatform/wfe/eventAction!getProcessedEventJsonList.jspa?page=1&rows=5',
		fitColumns:true,
		singleSelect:true,
	    columns:[[    
	        {field:'eventId',title:'事务编号',width:50},    
	        {field:'eventTitle',title:'事务标题',width:200},    
	        {field:'empName',title:'提出者',width:50},
	        {field:'creatdate',title:'提出时间',width:100},
	        {field:'curUserName',title:'当前处理人',width:50},
	        {field:'operation',title:'操作',width:30, align:'center',
	        	formatter : function(value, row, index) {
					var strReturn = '<a style="text-decoration:underline;color:blue" href= javascript:searchEventDetail("'
							+ row.eventId + '")>查看 </a>';
					return strReturn;}
	        }
	    ]],
	    onLoadSuccess: function(){
	    	canEnabled = false;
	    }
	});
}

//我的事务
function myWork() {
	if (canEnabled) return;
	canEnabled = true;
	for (var i=1; i<5; i++) {
		$('#link'+i).removeClass('selected');
	}
	$('#link3').addClass('selected');
	$('#dg').datagrid({
		url:'/basisPlatform/wfe/eventAction!getEventJsonList.jspa?page=1&rows=5',
		fitColumns:true,
		singleSelect:true,
	    columns:[[    
	        {field:'eventId',title:'事务编号',width:50},    
	        {field:'eventTitle',title:'事务标题',width:200},    
	        {field:'empName',title:'提出者',width:50},
	        {field:'creatdate',title:'提出时间',width:100},
	        {field:'curUserName',title:'当前处理人',width:50},
	        {field:'operation',title:'操作',width:30, align:'center',
	        	formatter : function(value, row, index) {
					var strReturn = '<a style="text-decoration:underline;color:blue" href= javascript:searchEventDetail("'
							+ row.eventId + '")>查看 </a>';
					return strReturn;}
	        }
	    ]],
	    onLoadSuccess: function(){
	    	canEnabled = false;
	    }
	});
}

//查看事务处理过程
function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','流程信息查看', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}
//会签处理
function CountersignEvent(cc_id,eventId) {
	initMaintEvent(true,'700','400','会签处理', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId+"&cc_id="+cc_id,0,0); 
}
//转移事务签收
function ontransferEvent(event,currentDetailid){
	$.messager.confirm('Confirm', '确认接受事务编号:'+event+"的事务?", function(r) {
		if (r) {
				$.messager.progress();
				var form = window.document.forms[0];
				form.action = appUrl+"/wfe/eventAction!transferEvent.jspa?eventIds=" + currentDetailid;
				form.target = "hideFrame";
				form.submit();
			}}
		);
}
//处理事务
function processEvent(eventId, toDoDetail,keys,modelId,curStaId) {
	initMaintEvent(true,800, 480,'事务处理结果',"/wfe/eventAction!toProcessEvent.jspa?event_id="
			+ eventId + "&toDoDetail=" + toDoDetail + "&modelId=" + modelId + "&curStaId=" + curStaId+"&operationType=process",0,0);
	//转换文件
	$.ajax({
		async : true,
		type : "post",
		url : appUrl
				+ '/file/fileAction!fileChange.jspa',
		data: {eventId:eventId},
		success : function() {
		}
	});
}

function search() {
	$("#dg").datagrid('load');
}

function closeMaintWindow(){
	$("#maintWindow").window("close");
}
//创建窗口对象
function initMaintEvent(ffit,widte,height,title, url,l,t) {
	var urls = appUrl + url;
	var WWidth = widte;
	var WHeight = height;
	var FFit = ffit;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ urls + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						fit:FFit,
						draggable : true,
						left : l,
						top: t
					});
	$win.window('open');
}
function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}