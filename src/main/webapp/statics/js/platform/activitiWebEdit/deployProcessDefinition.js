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
						url : appUrl + '#'
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
									id : 'dictTypeId',
									title : '序号',
									field : 'dictTypeId',
									width : 80,
									align : 'center',
									sortable : true
								},
								{
									field : 'dictTypeName',
									title : '类型名称',
									align : 'center',
									width : 200,
									sortable : true
								},
								{
									field : 'dictTypeValue',
									title : '值',
									width : 180,
									align : 'center',
									sortable : true
								},
								{
									field : 'remark',
									title : '备注',
									width : 150,
									align : 'center',
									sortable : true
								},
								{
									field : 'lastModify',
									title : '最后修改时间',
									align : 'center',
									width : 140,
									sortable : true,
									formatter : function(v) {
										return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi, "new Date($1)")); 
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
function createProcessDefinition(){
	initMaintWindow("新建流程","/activitiWebEdit/deployProcessDefinition!createProcessDefinition.jspa",800,400);
	
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
						fit : true,
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
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.processDefinitionName = encodeURIComponent($("#processDefinitionName").val());
	$("#datagrid").datagrid('load');
	
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
