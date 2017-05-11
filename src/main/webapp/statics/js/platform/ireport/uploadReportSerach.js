$(document).ready(function() {
	loadGrid(); 

});

function loadGrid() {
	$('#processdefinition_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl + '/IreportAction!seachModleList.jspa?random='
								+ Math.random(),

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									id : 'ID',
									title : '序号',
									field : 'ID',
									width : 100,
									align : 'center'
								},
								{
									field : 'FILENAME',
									title : '文件名',
									width : 100,
									align : 'center'
								},
								{
									field : 'UPLOADER',
									title : '上传人',
									width : 100,
									align : 'center'
								},
								{
									field : 'REMAN',
									title : '备注',
									width : 100,
									align : 'center'
								},{
									field : 'is_pagination',
									title : '是否分页',
									width : 100,
									align : 'center',
									
									formatter : function(v, row) {
									    if(row.is_pagination=="0"){
										return "是";
									    }else{
										return "否";
									    }
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)")); 
									}
								},{
									field : 'pageNum',
									title : '分页数',
									width : 100,
									align : 'center'
								
								},
								{
									field : 'UPLOADTIME',
									title : '上传时间',
									align : 'center',
									width : 140,
							
									formatter : function(v) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)")); 
									}
									
								} ,{
									field : 'operation',
									title : '操作',
									width : 150,
									align : 'center',
									
									formatter : function(value, row) {
									
									    return "<input type='button' onclick=midifymodle("+row.ID+") value='修改'/>"
									    		+"<input type='button' onclick=deletemodle("+row.ID+") value='删除'/>";
										
									}
								} ] ]
					});
					
	// 分页控件
	var p = $('#processdefinition_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function midifymodle(rid){
    initMaintWindow("模板修改","/IreportAction!toModifyReport.jspa?bid="+rid,600,400);
}
function deletemodle(rid){
	$.ajax({
		url:appUrl+"/IreportAction!deleteModle.jspa?bid="+rid,
		type: 'POST',
		dataType:'json',
		error:function(){
			//$.messager.alert("<s:text name='label.common.error'></s:text>","<s:text name='message.common.save.failure'></s:text>","error");
			return "";
		},
		success:function(data){
				jq.messager.alert('提示','保存成功!','info');

		}	
	}); 
	search();
}
function createProcessDefinition(){
	initMaintWindow("上传模板","/IreportAction!toUploadReport.jspa",600,400);
	
}
function colsewindow(){
    $("#maintWindow")
	.window("close");
}
//创建窗口对象
function initMaintWindow(title, url,width,height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						fit : false,
						width : width,
						height : height,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,///
						closable : true,//
						minimizable : false,//
						maximizable : true,//
						collapsible : false,//
						draggable : true//
					});

	$win.window('open');
}

function  search(){
	var queryParams = $('#processdefinition_list').datagrid('options').queryParams;
	queryParams.uploadFileName = encodeURIComponent($("#uploadFileName").val());
	$("#processdefinition_list").datagrid('reload');
	
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
    week["Mon"] = "一";
    week["Tue"] = "二";
    week["Wed"] = "三";
    week["Thu"] = "四";
    week["Fri"] = "五";
    week["Sat"] = "六";
    week["Sun"] = "日";


    str = utcCurrTime.split(" ");
    date = str[5] + "-";
    date = date + month[str[1]] + "-" + str[2] + " " + str[3];
    return date;
}
