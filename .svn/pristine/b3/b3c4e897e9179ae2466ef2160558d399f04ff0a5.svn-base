$(document).ready(function() {
			$('#hideFrame').bind('load', promgtMsg);
			loadGrid();
			loadGrid2();
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
		if (type == 'model') {
			search_1();
		} else if (type == 'att') {
			search_2();
		}
	}
}

function loadGrid() {
	$('#datagrid_1').datagrid({
		iconCls : 'icon-list',
		title : '查询结果',
		url : appUrl + '/reportAction!getData.jspa?rptId=5',
		// queryParams: {
		// endDate: $("#endDate").val(),
		// beginDate:$("#beginDate").val(),
		// custId : $("#id").combobox("getValue")
		// },
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : true,
		pagination : true,
		nowrap : true,
		remoteSort : true,
		striped : true,
		height : height,
			rownumbers:true,
			columns: [
			          [
					/*{
						field:'ck6',title:'序号',
						width:setColumnWidth(0.03),
						align:'center', 
						rowspan:2
					},*/
					{
						field:'ck5',
						title:'年',
						width:setColumnWidth(0.045),
						align:'center', 
						rowspan:2,
						formatter:function(value,row,rec){
							return 2013;
						}
		             },
		             {
		            	 field:'ck4',
		            	 title:'半月',
		            	 width:setColumnWidth(0.045),
		            	 align:'center', 
		            	 rowspan:2,
		            	 formatter:function(value,row,rec){
								return '上半月';
							}
		            },
		            {
		            	field:'MATERIALNAME',
		            	title:'物料',
		            	width:setColumnWidth(0.08),
		            	align:'center', 
		            	rowspan:2
		             },
	               {
		            	 field:'',
		            	 title:'区域',
		            	 width:setColumnWidth(0.10),
		            	 align:'center', 
		            	 colspan:3
		           },
	               {
		            	field:'CUSTNAME',
		            	title:'经销商',
		            	width:setColumnWidth(0.13),
		            	align:'center', 
		            	rowspan:2
	               },
	               {title:'期初库存',align:'center', colspan:3},
	               {title:'本期增加',align:'center', colspan:3},
	               {title:'本期出库',align:'center', colspan:3},
	               {title:'期末',align:'center', }
	           ],[
 				   {field:'DISTRICT',title:'大区',align:'center', width:setColumnWidth(0.08),rowspan:1},
				   {field:'PROVINCE',title:'省区',align:'center', width:setColumnWidth(0.08),rowspan:1},
				   {field:'OFFICE',title:'办事处',align:'center', width:setColumnWidth(0.08),rowspan:1},
	               {field:'STOCKBEFORE',title:'系统数量',align:'center', width:setColumnWidth(0.05),rowspan:1},
	               {field:'STOCKBEFORE',title:'上报数量',align:'center', width:setColumnWidth(0.05),rowspan:1},
	               {field:'ck1',title:'差异',align:'center', width:setColumnWidth(0.05),rowspan:2,
	            	   formatter:function(value,row,rec){
	            			   return 0;
	            	   }
	               },
	               {field:'INSTOCK',title:'发货数量',align:'center', width:setColumnWidth(0.05),rowspan:1,
	            	   formatter:function(value,row,rec){
						if(row.instock==null){
							return 0;
						}
					}},
	               {field:'INSTOCKREPORT',title:'上报数量',align:'center', width:setColumnWidth(0.05),rowspan:1},
	               {field:'ck2',title:'差异',align:'center', width:setColumnWidth(0.05),rowspan:2,
	            	   formatter:function(value,row,rec){
	            		   if(row.instock != null&&row.instock != 0){
	            			   return (row.instock-row.instockReport)/row.instock;
	            		   }else{
	            			   return 0;
	            		   }
	            	   }
	               },
	               {field:'OUTSTOCK',title:'系统数量',align:'center', width:setColumnWidth(0.05),rowspan:1,
	            	   formatter:function(value,row,rec){
							if(row.outstock==null){
								return 0;
							}
	            	   }
	               },
	               {field:'OUTBEFOREREPORT',title:'上报数量',align:'center', width:setColumnWidth(0.05),rowspan:1,
	            	   formatter:function(value,row,rec){
							if(row.outbeforereport==null){
								return 0;
							}
	            	   }
	               },
	               {field:'ck3',title:'差异',align:'center', width:setColumnWidth(0.05),rowspan:2,
	            	   formatter:function(value,row,rec){
	            		   if(row.outBeforeReport != 0&&row.outstock != 0&&row.outBeforeReport != null&&row.outstock != null){
	            			   return (row.outstock-row.outBeforeReport)/row.outstock;
	            		   }else{
	            			   return 0;
	            		   }
	            	   }
	               },
	               {field:'STOCKCHECK',title:'上报数量',align:'center', width:setColumnWidth(0.05),rowspan:1}
		       ]/*,[ 
	               {field:'name',title:'大区',align:'center', width:setColumnWidth(0.08),rowspan:1},
	               {field:'name',title:'省区',align:'center', width:setColumnWidth(0.08),rowspan:1},
	               {field:'addr',title:'办事处',align:'center', width:setColumnWidth(0.08),rowspan:1},
	               {field:'col4',title:'数量',align:'center', width:setColumnWidth(0.06),rowspan:1},
	               {field:'name1',title:'数量',align:'center', width:setColumnWidth(0.06),rowspan:1},
	               {field:'col41',title:'数量',align:'center', width:setColumnWidth(0.06),rowspan:1},
	               {field:'addr1',title:'数量',align:'center', width:setColumnWidth(0.06),rowspan:1},
	               {field:'name1',title:'数量',align:'center', width:setColumnWidth(0.06),rowspan:1},
	               {field:'col41',title:'数量',align:'center', width:setColumnWidth(0.06),rowspan:1},
	               {field:'addr1',title:'数量',align:'center', width:setColumnWidth(0.06),rowspan:1}
	           ]*/]
	});

	// 分页控件
	var p = $('#datagrid_1').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
				beforePageText : '第',
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			});
	
}
function loadGrid2() {
	$('#cust_id').combogrid({
		panelHeight : 250,
		panelWidth : 600,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa',
		idField : 'kunnr',
		textField : 'name1',
		// multiple : true,
		columns : [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'kunnr',
					title : '经销商编号',
					width : 60
				}, {
					field : 'name1',
					title : '名称',
					width : 100
				}, {
					field : 'name3',
					title : '名称',
					width : 60
				}, {
					field : 'telNumber',
					title : '联系电话',
					width : 80
				}, {
					field : 'channelName',
					title : '渠道类型',
					width : 80
				}, {
					field : 'street1',
					title : '公司注册地址',
					width : 150
				}]],
				toolbar : '#toolbarKonzs'
		});

}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function search_1() {
	var queryParams = $('#datagrid_1').datagrid('options').queryParams;
	queryParams.custId = encodeURIComponent($("#cust_id").combobox("getValue"));
	$("#datagrid_1").datagrid('reload');
}

function clearValue_1() {
	$("#cust_id").combobox("setValue", '');
}

function search_2() {
	var queryParams = $('#datagrid_2').datagrid('options').queryParams;

	queryParams.recBillId = $("#recBillId").val();
	$("#datagrid_2").datagrid('reload');
}


function searcherKonzs(name1) {
	var queryParams = $('#cust_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#cust_id').combogrid("grid").datagrid('reload');
}


function closeMaintModelAtt() {
	$("#maintModelAtt").window('close');
}

// 创建窗口对象
function initMaintModelAtt(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintModelAtt").window({
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

// 创建窗口对象
function initMaintUi(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintModelAtt").window({
		title : title,
		width : WWidth,
		height : WHeight,
		content : '<iframe frameborder="no" width="100%" height="100%" src='
				+ url + '/>',
		shadow : true,
		modal : true,
		closed : true,
		closable : true,
		fit : true,
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
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2];
	return date;
}
