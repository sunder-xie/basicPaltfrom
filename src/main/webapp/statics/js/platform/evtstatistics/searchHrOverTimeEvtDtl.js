var eventIdGloab = "";
$(document).ready(function(){
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});

var columns;
var stations;
var stationNames;
function loadGrid() {
	addColumns();
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/evtstatistics/evtstatisticsAction!searchHrOverTimeEvtDtlList.jspa?stations='+stations+'&stationNames='+encodeURIComponent(stationNames),
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						queryParams : {
							eventId : $("#eventId").val(),
							eventTitle : encodeURIComponent($("#eventTitle").val()),
							initatorName : encodeURIComponent($("#initatorName").val()),
							overFlag : $("#overFlag").combobox('getValue'),
							startDate : $("#startDate").val(),
							endDate : $("#endDate").val(),
							overDate : $("#overDate").val(),
							modelId : $("#modelId").combobox('getValue')
						},
						columns : [
							columns
						],
						toolbar : [ "-",{
							text : '批量导出',
							iconCls : 'icon-excel',
							handler : function() {
								exportForExcel();
							}
						}, "-"]
					});

	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function addColumns(){
	addFrozenColumns();
	stations="";
	stationNames="";
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/evtstatistics/evtstatisticsAction!searchEventModelHrDetailList.jspa",
		data : {
			modelId : $("#modelId").combobox('getValue')
		},
	    success : function(data) {
		    $.each(data,function(key,value){
		    	if(value.roleId=="total"){
		    		$("#overDate").val(value.overDate);
		    	}
		    	stations+=value.roleId+",";
		    	stationNames+=value.roleName + '(' + value.overDate + '天),';
		    	var column={};  
                column["title"]=value.roleName + '(' + value.overDate + '天)';  
                column["field"]='column'+(key+1);  
                column["width"]=120;
                column["align"]='center';
                column["formatter"]=function(v){
                	                        if(v>value.overDate){
                	                        	return '<span style="color:red;">'+v+'</span>';
                	                        }else{
                	                        	return v;
                	                        }
                	                 };
                columns.push(column);
		    });
		    
		    //添加操作列
		    var column={};  
            column["title"]='操作';  
            column["field"]='operation';  
            column["width"]=150;
            column["align"]='center';
            column["formatter"]=function(v,r,i){
            	                         var strReturn = '';
				                         strReturn = '<a href= javascript:searchEventDetail("'
					                         + r.eventId
					                         + '")>查看审批意见 </a>|'
					                         + '<a href=javascript:graphTrace("'
					                         + r.eventId
					                         + '") > 流程 </a>|';
				return strReturn;
            	                 };
            columns.push(column);
	    }
	});
}

function addFrozenColumns(){
	columns=new Array();
	
	var column1={};  
    column1["title"]='事务模板';  
    column1["field"]='modelName';  
    column1["width"]=150;
    column1["align"]='center';
    columns.push(column1);
    
    var column2={};  
    column2["title"]='事务编号';  
    column2["field"]='eventId';  
    column2["width"]=100;
    column2["align"]='center';
    columns.push(column2);
    
    var column3={};  
    column3["title"]='事务标题';  
    column3["field"]='eventTitle';  
    column3["width"]=100;
    column3["align"]='center';
    columns.push(column3);
    
    var column4={};  
    column4["title"]='提出者';  
    column4["field"]='initatorName';  
    column4["width"]=100;
    column4["align"]='center';
    columns.push(column4);
}

function search() {
	loadGrid();
}

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/evtstatistics/evtstatisticsAction!exportHrOverTimeEvtDtl.jspa?stations='+stations+'&stationNames='+encodeURIComponent(encodeURIComponent(stationNames));
	form.target = "hideFrame";
	form.submit();
}

function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/evtstatistics/evtstatisticsAction!checkDownLoadOver.jspa",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
					}
				}
			});
		}, 100);
	}, 500);
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

function searchEventDetail(eventId) {
	initMaintEvent(true,'700','400','流程信息查看', "/wfe/eventAction!toProcessEvent.jspa?event_id="+ eventId,0,0); 
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function clearValue(){
	$("#eventId").val('');
	$("#eventTitle").val('');
	$("#initatorName").val('');
	$("#overFlag").combobox('select','');
	$("#startDate").val('');
	$("#endDate").val('');
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