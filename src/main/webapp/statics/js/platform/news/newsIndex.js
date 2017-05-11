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
 * ���������ʾ�����������
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
//�鿴��������
function searchWorks() {
	var text = $('a.selected').text();
	var url = '';
	if (text == '��������') {
		window.parent.openProcessedTab();
	} else if (text == '�Ѱ�����') {
		window.parent.add('�Ѱ�����', '/wfe/eventAction!toSearchProcessedEvent.jspa', '_self');
	} else if (text == '�ҵ�����') {
		window.parent.add('�ҵ�����', '/wfe/eventAction!toSearchEvent.jspa', '_self');
	}
}

//�鿴���๫��
function searchNews() {
	window.parent.add('ϵͳ����', '/newsAction!getSearchNews.jspa', '_self');
}

//�鿴ͨѶ¼
function searchAddress() {
	window.parent.add('ͨѶ¼', '/addressList/addressListAction!searchAddressList.jspa', '_self');
}

//���ù���
function commonFun(o) {
	var host = window.parent.appUrl;
	switch (o) {
	case 1:
		window.open(host+'/statics/WebHelp/index.htm');
		break;
	case 2:
		window.parent.add('������Ϣ��ѯ��ά��', '/allUserAction!toViewOfUserInfo.jspa', '_self');
		break;
	}
}

//��ѯͨѶ¼
function searchPerson() {
	if ($("#userName").val()=='') return;
	var queryParams = $('#address').datagrid('options').queryParams;
	queryParams.orgId = 50919;
	queryParams.userName = encodeURIComponent($("#userName").val());
	queryParams.bhxjFlag = encodeURIComponent('C');
	$("#address").datagrid('load');
}

//ͨѶ¼չʾ
function loadAddress() {
	$('#address').datagrid({
		url: window.parent.appUrl + '/addressListAction!searchMailLists.jspa?page=1&rows=5',
		fitColumns:true,
		singleSelect:true,
		loadMsg : '����Զ��������,��ȴ�...',
		queryParams:{
			orgId: 0
		},
		columns:[[   
		    {field:'userName',title:'����',width:60,fixed:true},
		    {field:'busMobilePhone',title:'�绰',width:100,fixed:true},
		    {field:'empMobilePhone',title:'˽�˵绰',width:100,fixed:true},
		    {field:'stMobile',title:'�̺�',width:70,fixed:true},
		    {field : 'orgName',title : '��֯',width : 120}
		]]
	});
}

var canEnabled = false;

//�������˼���
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
  	        {field:'operation',title:'����',width:30, align:'center',
	        	formatter : function(value, row, index) {
	        		var strReturn="";
					if(row.eventType==1){
						strReturn = '<a href= javascript:ontransferEvent('
							+row.eventId+','+row.currentDetailid+')>ǩ�� </a>'
					}else if (row.eventType==2){
						strReturn = '<a href= javascript:CountersignEvent("'
							+ row.cc_id + '","'
							+ row.eventId
							+ '")>��ǩ���� </a>';
					}else{
						 strReturn = '<a href= javascript:processEvent("'
							+ row.eventId + '","'+ row.currentDetailid + '","'
							+ row.keys + '","'
							+ row.modelId +'","'
							+ row.curStaId +'")>���� </a>';
					}
					return strReturn;}
	        },
	        {field:'eventId',title:'������',width:50},
	        {field:'modelName',title:'���̷���',width:100},
	        {field:'eventTitle',title:'�������', width: 200},    
	        {field:'orgName',title:'�����֯',width:80},
	        {field:'empName',title:'�����',width:50},
	        {field:'creatdate',title:'���ʱ��',width:100},
	        {field : 'eventType',title : 'eventType',hidden : true},
	        {field : 'keys',title : 'keys',	hidden : true},
	        {field : 'modelId',title : '����ģ��id',hidden : true},
			{field : 'curStaId',title : '��ɫId',hidden : true},
			{field : 'currentDetailid',title : 'detailId',hidden : true}
	    ]],
	    onLoadSuccess: function(){
	    	canEnabled = false;
	    }
	});
}

//�Ѱ�����
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
	        {field:'eventId',title:'������',width:50},    
	        {field:'eventTitle',title:'�������',width:200},    
	        {field:'empName',title:'�����',width:50},
	        {field:'creatdate',title:'���ʱ��',width:100},
	        {field:'curUserName',title:'��ǰ������',width:50},
	        {field:'operation',title:'����',width:30, align:'center',
	        	formatter : function(value, row, index) {
					var strReturn = '<a style="text-decoration:underline;color:blue" href= javascript:searchEventDetail("'
							+ row.eventId + '")>�鿴 </a>';
					return strReturn;}
	        }
	    ]],
	    onLoadSuccess: function(){
	    	canEnabled = false;
	    }
	});
}

//�ҵ�����
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
	        {field:'eventId',title:'������',width:50},    
	        {field:'eventTitle',title:'�������',width:200},    
	        {field:'empName',title:'�����',width:50},
	        {field:'creatdate',title:'���ʱ��',width:100},
	        {field:'curUserName',title:'��ǰ������',width:50},
	        {field:'operation',title:'����',width:30, align:'center',
	        	formatter : function(value, row, index) {
					var strReturn = '<a style="text-decoration:underline;color:blue" href= javascript:searchEventDetail("'
							+ row.eventId + '")>�鿴 </a>';
					return strReturn;}
	        }
	    ]],
	    onLoadSuccess: function(){
	    	canEnabled = false;
	    }
	});
}

//�鿴����������
function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','������Ϣ�鿴', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}
//��ǩ����
function CountersignEvent(cc_id,eventId) {
	initMaintEvent(true,'700','400','��ǩ����', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId+"&cc_id="+cc_id,0,0); 
}
//ת������ǩ��
function ontransferEvent(event,currentDetailid){
	$.messager.confirm('Confirm', 'ȷ�Ͻ���������:'+event+"������?", function(r) {
		if (r) {
				$.messager.progress();
				var form = window.document.forms[0];
				form.action = appUrl+"/wfe/eventAction!transferEvent.jspa?eventIds=" + currentDetailid;
				form.target = "hideFrame";
				form.submit();
			}}
		);
}
//��������
function processEvent(eventId, toDoDetail,keys,modelId,curStaId) {
	initMaintEvent(true,800, 480,'���������',"/wfe/eventAction!toProcessEvent.jspa?event_id="
			+ eventId + "&toDoDetail=" + toDoDetail + "&modelId=" + modelId + "&curStaId=" + curStaId+"&operationType=process",0,0);
	//ת���ļ�
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
//�������ڶ���
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