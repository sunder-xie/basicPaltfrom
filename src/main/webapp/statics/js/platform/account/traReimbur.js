$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

var i = 1;
var outsideArr = new Array();

function loadGrid(){
	$('#modelId').val("fix_bx");
	$('#reference').combogrid({
		panelWidth : 450,
		panelHight :500,
		idField : 'eventId',
		textField : 'eventId',
		pagination : true,
		rownumbers : true,
		collapsible : false,
		fit : true,
		method : 'post',
		editable : false,
		multiple : false,
		url : appUrl + '/account/accountAction!getHisEventJsonList.jspa',
		columns : [ [ {
			field : 'eventId',
			title : '事务编号',
			width : 100,
			align : 'center'
		}, {
			field : 'eventTitle',
			title : '事务标题',
			width : 140,
			align : 'center'
		}, {
			field : 'creatdate',
			title : '创建日期',
			width : 160,
			align : 'center'
		} ] ],
		onSelect : function(record) {
			var g = $('#reference').combogrid('grid'); 
			var r = g.datagrid('getSelected');
			$.ajax({
						type : 'post',
						url : appUrl + '/account/accountAction!getSingleDetailJsonList.jspa?transactionId=' + r.eventId,
						success : function (singleDetailList) {
							for(var k = 1; k < i; k++){
								$("#tr_" + k).remove();
								$("#toolbar_" + k).remove();
							}
							i = 1;
							$.each(singleDetailList, function(j, v) {
								addRow();
								var j = i - 1;
								$("#expType_"+j).combogrid('setValue', v.cost_type);
								$("#expPurpose_"+j).val(v.cost_purpose);
								$("#invoiceNum_"+j).val(v.invoice_num);
								$("#invoiceAmount_"+j).val(v.invoice_amount);
								$("#memo_"+j).val(v.cost_memo);
							});
							countTotalMoney();
						}
					});
			
		},
		toolbar : '#toolbar_reference'
	});
	$('#costCenter').combogrid({
		panelWidth : 450,
		panelHight : 500,
		idField : 'totalId',
		textField : 'title',
		pagination : true,// 是否分页
		rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		fit : true,// 自动大小
		method : 'post',
		editable : false,
		multiple : false,
		url : appUrl + '/account/accountAction!searchWorkPlan.jspa',
		columns : [ [ {
			field : 'totalId',
			title : 'ID',
			width : 130,
			align : 'center'
		}, {
			field : 'title',
			title : '项目名称',
			width : 250,
			align : 'center'
		} ] ],
		onSelect : function(record) {
			var g = $('#costCenter').combogrid('grid'); // get datagrid object
			var r = g.datagrid('getSelected'); // get the selected row
			$("#projectId").val(r.totalId);
		},
		toolbar : '#toolbar',
		required: true
	});
	
	$('#payee').combogrid({
		panelWidth : 450,
		panelHight : 500,
		idField : 'payee',
		textField : 'payee',
		pagination : true,// 是否分页
		rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		fit : true,// 自动大小
		method : 'post',
		editable : false,
		multiple : false,
		url : appUrl + '/account/accountAction!getPayeeJsonList.jspa',
		columns : [ [ 
		{
			field : 'payee',
			title : '收款单位',
			width : 400,
			align : 'center'
		}] ],
		onLoadSuccess : function() {
			if ($('#num').val() > 0) {
				$('#payee').combogrid('setText', $('#defaultPayee').val());
				$('#payAccount').combobox({
					url : appUrl + '/account/accountAction!getPayAccountJsonList.jspa?payee=' + encodeURIComponent($('#defaultPayee').val()),
					valueField : 'id',  
					textField : 'payAccount',
					editable : false,
					width : 160,
					onLoadSuccess : function() {
						$('#payAccount').combobox('setValue', $('#defaultPayeeId').val());
					}
				});
			}
		},
		onSelect : function(record) {
			var g = $('#payee').combogrid('grid'); // get datagrid object
			var r = g.datagrid('getSelected'); // get the selected row
			$.ajax({
						type : 'post',
						url : appUrl + '/account/accountAction!getDefaultPayAccount.jspa?payee=' + encodeURIComponent(r.payee),
						success : function (id) {
							$('#payAccount').combobox({
								url : appUrl + '/account/accountAction!getPayAccountJsonList.jspa?payee=' + encodeURIComponent(r.payee),
								valueField : 'id',  
								textField : 'payAccount',
								editable : false,
								width : 160,
								onLoadSuccess : function() {
									$('#payAccount').combobox('setValue', id);
								}
							});
						}
					});
			
		},
		toolbar : '#toolbar_payee',
		required: true
	});
	//var t = $('#costCenter').combogrid();
	
//	$(t).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
	
	addRow();
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#costCenter').combogrid({
		url : appUrl + '/account/accountAction!searchWorkPlan.jspa?title=' + val
	});
	$('#costCenter').combogrid("grid").datagrid('reload');
}

function searcher_payee(val) {
	val = encodeURIComponent(val);
	$('#payee').combogrid({
		url : appUrl + '/account/accountAction!getPayeeJsonList.jspa?searchStr=' + val
	});
	$('#payee').combogrid("grid").datagrid('reload');
}

function searcher_reference(val) {
	val = encodeURIComponent(val);
	$('#reference').combogrid({
		url : appUrl + '/account/accountAction!getHisEventJsonList.jspa?searchStr=' + val
	});
	$('#reference').combogrid("grid").datagrid('reload');
}

/**
 * 添加行
 */
function addRow() {
	var htmlHead_1 = "<tr id=\"tr_" + i
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#ffffff\">";
	var htmlHead_2 = "<tr id=\"tr_" + i
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#f2f2f2\">";	
			
	var htmlTr = "<td style=\"text-align: center\"><input id=\"item_"
			+ i
			+ "\" type=\"checkbox\" /></td>"
/*			+ "<td style=\"text-align: center\">"
			+ i
			+ "</td>"*/
			+ "<td style=\"text-align: center\"><select style=\"width:120px;\" id=\"expType_"
			+ i
			+ "\" /></select></td>"
			+ "<td style=\"text-align: center\"><input size=\"14\" id=\"expDate_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"27\" id=\"expPurpose_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"14\" id=\"invoiceNum_"
			+ i
			+ "\" onblur=\"checkIsNumber("+i+", 'num');\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"15\" id=\"invoiceAmount_" 
			+ i
			+ "\" onblur=\"checkIsNumber("+i+", 'amount');\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"40\" id=\"memo_"
			+ i
			+ "\" type=\"text\"/></td>" + "</tr>";
			
	var htmlData = "";
	if (i % 2 == 1) {
		htmlData = htmlHead_1 + htmlTr;
	} else {
		htmlData = htmlHead_2 + htmlTr;
	}
	$('#myTab').append(htmlData);
	addHandler();	
	i++;
}

/**
 * 添加行元素事件
 */
function addHandler(){
	var index = i;
	var divHtml = "<div id='toolbar_"+
		+ i
		+ "' class='datagrid-toolbar' > <input id='search_"
		+ i
		+"' style='width:300px'></input>"	
		+ "</div>";
	$('#expTypeToolBar').append(divHtml);
	$('#search_'+i).searchbox({   
	    searcher:function(value,name){   
	        search(value, index);  
	    },   
	    prompt:'请填写查询条件'  
	}); 
	
	$("#expDate_"+i).datebox({
		required : true,
		editable : false
	});
	
	$("#invoiceNum_"+i).validatebox({   
    	required: true 
	});
	
	$("#invoiceAmount_"+i).validatebox({   
    	required: true
	});
	
	$('#expType_'+i).combogrid({
		panelWidth : 450,
		panelHight : 500,
		idField : 'itemValue',
		textField : 'itemName',
		pagination : true,// 是否分页
		rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		fit : true,// 自动大小
		method : 'post',
		multiple : false,
		editable : false,
		url : appUrl + '/account/accountAction!searchCostType.jspa',
		columns : [ [ {
			field : 'itemId',
			title : 'ID',
			width : 80,
			align : 'center'
		},{
			field : 'itemName',
			title : '费用类型名',
			width : 180,
			align : 'center'
		},{
			field : 'itemValue',
			title : '费用类型值',
			width : 80,
			align : 'center'
		}] ],
		toolbar : "#toolbar_" + i,
		required: true
	});
	var q = $('#expType_'+i).combogrid();
//	$(q).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
	
}

function search(value, index){
	value = encodeURIComponent(value);
	$('#expType_'+index).combogrid({
		url : appUrl + '/account/accountAction!searchCostType.jspa?title=' + value
	});
	$('#expType_'+index).combogrid("grid").datagrid('reload');
}

/**
 * 删除行
 */
function removeRow() {
	for(var k = 1; k < i;k++){
		if ($('#item_' + k).attr('checked') == 'checked') {
			$("#tr_" + k).remove();
			outsideArr.push(k);
		}
	}

/*	var count = 0;
	for ( var k = 1; k < i; k++) {
		if ($('#item_' + k).attr('checked') == 'checked') {
			count = count + 1;
		}
	}
	var index = 0;
	for ( var j = i - 1; j > 0; j--) {
		if (index < count) {
			$("#tr_" + j).remove();
			index = index + 1;
		}
	}
	i = i - count;*/
	
	$('#item_all').attr('checked', false);
	countTotalMoney();
}

/**
 * 选中全部
 * 
 * @param {}
 *            module
 */
function checkAll() {
	for ( var k = 1; k < i; k++) {
		if(!checkInOutsideArr(k)){
			$('#item_' + k).attr('checked',
				($('#item_all').attr('checked') == 'checked'));
		}
	}
}

function payAccountReload() {
	$.ajax({
		type : 'post',
		url : appUrl + '/account/accountAction!getDefaultPayAccount.jspa?payee=' + encodeURIComponent($('#payee').combogrid('getText')),
		success : function (id) {
			$('#payAccount').combobox({
				url : appUrl + '/account/accountAction!getPayAccountJsonList.jspa?payee=' + encodeURIComponent($('#payee').combogrid('getText')),
				valueField : 'id',  
				textField : 'payAccount',
				editable : false,
				onLoadSuccess : function() {
					$('#payAccount').combobox('setValue', id);
				}
			});
		}
	});
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult == "ok") {
		$('#nextUserDialog').dialog('close'); 
	}else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}

function createProEventReader() {
	initMaintWindow('选择联系人', '/allUserAction!toShowUserByOrgId.jspa', 660, 430);
}

//function saveUser(x) {
//	$("#traUserId").val(x[0]);
//	$("#traUserName").val(x[1]);
//	closeMaintWindow();
//}

/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintWindow('选择组织', '/orgAction!orgTreePage.jspa', 400, 460);
}

function addNewAccount() {
	var payee = $('#payee').combobox('getText');
	if(payee == '') {
		$.messager.alert('Tips', '请先选择收款人!', 'warning');
		return;
	}
	initMaintWindow('添加新账号', '/account/accountAction!toAddNewAccount.jspa?payee=' + encodeURIComponent(payee), 500, 400);
}


/**
 * 组织树的返回值接受
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
	closeMaintWindow();
}

// 创建窗口对象
function initMaintWindow(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintDiv")
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
						draggable : false
					});

	$win.window('open');
}

function closeMaintWindow() {
	$("#maintDiv").window('close');
}

/**
 * 校验输入数字
 * @param {} k
 * @param {} type
 */
function checkIsNumber(k, type){
	var regNum = /^\d+$/;
	var regAmount = /^\d+(.\d+)?$/;
	if("num" == type){
		if(!regNum.test($("#invoiceNum_"+k).val())){
			$("#invoiceNum_"+k).val(0);
		}
	}
	if("amount" == type){
		if(!regAmount.test($("#invoiceAmount_"+k).val())){
			$("#invoiceAmount_"+k).val(0);
		}
		countTotalMoney();
	}
}

function countTotalMoney(){
	var total = 0;
	for(var r=1;r<i;r++){
		if(!checkInOutsideArr(r)){
			var value = $("#invoiceAmount_"+r).val();
			if(value.length>0){
				total += Number(value);
			}
		}	
	}
	$("#totalMoney").val(RoundNumber(total, 2));
}

//数字 指定位数后 四舍五入   
function RoundNumber(num, pos)  {  
	return Math.round(num * Math.pow(10, pos)) / Math.pow(10, pos);  
} 

/**
 * 校验Boolean类型数组是否全部为真
 * @param {} arr
 * @return {}
 */
function checkBooleanArr(arr){
	var flag = true;
	for(var t=0;t<arr.length;t++){
		if(!arr[t]){
			flag = false;
			break;
		}
	}
	return flag;
}

/**
 * 检查index是否在在删除行序列之内
 * @param {} index
 */
function checkInOutsideArr(index){
	var flag = false;
	for(var a=0;a<outsideArr.length;a++){
		if(outsideArr[a]==index){
			flag = true;
			break;
		}
	}
	return flag;
}

function submit() {
	var n = $("#title").validatebox('isValid');
	var m = $('#costCenter').combogrid('isValid');
	var o = $('#memo').validatebox('isValid');
	var p = $('#payee').combogrid('isValid');
	
	var key = $('#modelId').val();
	
	var dateArr = new Array();
	var numArr = new Array();
	var amountArr = new Array();
	var expTypeArr = new Array();
	var detailStr = "";
	
	for(var r=1;r<i;r++){
		if(!checkInOutsideArr(r)){
			dateArr.push($("#expDate_"+r).datebox('isValid'));
			numArr.push($("#invoiceNum_"+r).validatebox('isValid'));
			amountArr.push($("#invoiceAmount_"+r).validatebox('isValid'));
			expTypeArr.push($("#expType_"+r).combogrid('isValid'));
			
		}
	}
	if (!(n && m && o && p && checkBooleanArr(dateArr) && checkBooleanArr(numArr) && checkBooleanArr(amountArr) && checkBooleanArr(expTypeArr))) {
		return;
	}
	$("#costCenterName").val($("#costCenter").combobox("getText"));
	$("#pay_ee").val($("#payee").combogrid("getText"));
	$("#pay_account").val($("#payAccount").combobox("getText"));
	
	
/*	var pay_type = '';
	$(":radio").each(function(){
		 if(this.checked==true){
		 	pay_type = this.value;
		 }
	});*/
	var x = 0;
	detailStr += "[";
	for(var k=1;k<i;k++){
		if(!checkInOutsideArr(k)){
			x++;
			detailStr += "{"
				+ "\"cost_type\" : \"" +$("#expType_"+k).combobox("getValue")+ "\","
				+ "\"cost_type_content\" : \"" +$("#expType_"+k).combobox("getText")+ "\","
				+ "\"cost_date\" : \"" +$("#expDate_"+k).datebox("getValue")+ "\","
				+ "\"cost_purpose\" : \"" +$("#expPurpose_"+k).val()+ "\","
				+ "\"invoice_num\" : \"" +$("#invoiceNum_"+k).val()+ "\","
				+ "\"invoice_amount\" : \"" +$("#invoiceAmount_"+k).val()+ "\","
				+ "\"cost_memo\" : \"" +$("#memo_"+k).val()+ "\""
				+ "},";
		}
	}
	if(x>0){
		detailStr = detailStr.substring(0, detailStr.length-1);
	}
	detailStr += ']';
	$("#detailJsonStr").val(detailStr);
	
	$.messager.progress();
	$.ajax({
		type : "post",
		url : appUrl + "/account/accountAction!selectNexUser.jspa?time="+new Date(),
		data : {
			key : key,
			userId   : $("#curUserId").val(),
			projectId : $("#projectId").val()
		},
		success : function(userUtil) {
			$.messager.progress('close');
			var positionHtml = "<div class='easyui-panel' title='下个处理' data-options='collapsible:true'>"
				+"<table width='100%' border='0' cellpadding='0' cellspacing='1'>"
				+"<tr><td class='head' noWrap>处理人</td>"
				+"<td><select id='nextUserId1' name='nextUserId1'>";
			$.each(userUtil.result, function(i, v) {
				positionHtml += "<option value='"+ v.userId +"'>"+v.userName+"----"+v.stationName+"</option>";
			});
			positionHtml +="</select></td></tr></table></div>";
			if ($('#nextUserDialog').length<1) {
				$('<div/>', {
				id: 'nextUserDialog',
				title: '选择下个处理人',
				html: "<div id='nextUser'>" 
					+positionHtml+
					 "</div>" +
			          "</div>"
				}).appendTo('body');
			} else {
				$('#nextUser').html(positionHtml);
			}
			$('#nextUserDialog').dialog({
				modal: true,
				resizable: false,
				dragable: false,
				closable:false,
				open: function() {
					$('#nextUserDialog').css('padding', '0.4em');
					$('#nextUserDialog .ui-accordion-content').css('padding', '0.4em').height($('#nextUserDialog').height() - 75);
				},
				buttons:[{ 
					text:'确定',
					handler:function(){ 
						if($("#nextUserId1").val() == "" || $("#nextUserId1").val() == null){
							$.messager.alert('Tips', "没有下个处理人，请维护！", 'error');
						    return;
						}
						$.messager.progress();
						$("#nextUserId").val($("#nextUserId1").val());
						var form = window.document.forms[0];
						form.action = appUrl + "/account/accountAction!processWorkflowFix.jspa?eventId="+userUtil.processInstanceId;
						form.submit();
					}},{
					text:'取消',
					handler:function(){ 
						var form = window.document.forms[0];
						form.action = appUrl + "/account/accountAction!cancelNextUser.jspa?eventId="+userUtil.processInstanceId;
						form.target = "hideFrame";
						form.submit();
					} 
				}],
				width: document.documentElement.clientWidth * 0.50,
		 		height: document.documentElement.clientHeight * 0.40
			});
		},
		error: function (XMLHttpRequest, textStatus, errorThrown){
			alert(textStatus);
		  
	  	}
	});
}

function reset() {
	$.messager.alert('Tips', "取消", 'error');
}