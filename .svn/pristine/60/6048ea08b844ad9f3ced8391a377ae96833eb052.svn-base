$(document).ready(function() {
	loadGrid();
	for(var i=0; i<$("#size").val(); i++){
		addHandler(i);
	}
	$('#hideFrame1').bind('load', promgtMsg);
});

var i = $("#size").val()-1;
var outsideArr = new Array();

function loadGrid(){
	$('#costCenter').combogrid({
		panelWidth : 450,
		panelHight : 500,
		idField : 'totalId',
		textField : 'title',
		pagination : true,
		rownumbers : true,
		collapsible : false,
		striped : true,
		fit : true,
		method : 'post',
		multiple : false,
		url : appUrl + '/account/accountAction!searchWorkPlan.jspa?type=process&pro_manager_id=' + $("#creatorId").val(),
		columns : [ [ {
			field : 'totalId',
			title : 'ID',
			width : 180,
			align : 'center'
		}, {
			field : 'title',
			title : '项目或部门名',
			width : 210,
			align : 'center'
		} ] ],
		editable : false,
		onLoadSuccess : function() {
			$('#costCenter').combogrid("setText", $("#costCenterText").val());
			$('#costCenter').combogrid("setValue", $("#costCenterValue").val());
		},
//		onChange : function() {
//			$("#costCenterType").val($('#costCenter').combobox("getValue").split(",")[1]);
//		},
		toolbar : '#toolbar'
	});
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#costCenter').combogrid({
		url : appUrl + '/account/accountAction!searchWorkPlan.jspa?type=process&pro_manager_id=' + $("#creatorId").val() + '&title=' + val
	});
	$('#costCenter').combogrid("grid").datagrid('reload');
}

function addRow() {
	i++;
	var htmlHead_1 = "<tr id=\"tr_" + i
			+ "\" style=\"height:23px;BACKGROUND-COLOR:#f4f4f4\" >";
	var htmlHead_2 = "<tr id=\"tr_" + i
			+ "\" style=\"height:23px;BACKGROUND-COLOR:#ffffff\" >";	
			
	var htmlTr = "<td style=\"text-align: center\"><input id=\"item_"
			+ i
			+ "\" type=\"checkbox\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"expType_"
			+ i
			+ "\" type=\"text\" /><input type=\"hidden\" id=\"detailId_"+i+"\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"costDate_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"costPurpose_"
			+ i
			+ "\" type=\"text\" style=\"width:130px\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"invoiceNum_"
			+ i
			+ "\" onblur=\"checkIsNumber("+i+", 'num');\" type=\"text\" style=\"width:106px\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"invoiceAmount_" 
			+ i
			+ "\" onblur=\"checkIsNumber("+i+", 'amount');\" type=\"text\" style=\"width:130px\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"auditMoney_" 
			+ i
			+ "\" onblur=\"checkIsNumber("+i+", 'auditMoney');\" type=\"text\" style=\"width:130px\" /></td>"
			+ "<td style=\"text-align: center\"><input id=\"costMemo_"
			+ i
			+ "\" type=\"text\" style=\"width:210px\" /></td>" + "</tr>";
			
	var htmlData = "";
	if (i % 2 == 1) {
		htmlData = htmlHead_1 + htmlTr;
	} else {
		htmlData = htmlHead_2 + htmlTr;
	}
	$('#myTab').append(htmlData);
	addHandler(i);	
}

function removeRow() {
	for(var k = 0; k <= i;k++){
		if ($('#item_' + k).attr('checked') == 'checked') {
			$("#tr_" + k).remove();
			outsideArr.push(k);
		}
	}
	
	$('#item_all').attr('checked', false);
	countTotalMoney();
	countAuditMoney();
}

function checkAll() {
	for ( var k = 0; k <= i; k++) {
		if(!checkInOutsideArr(k)){
			$('#item_' + k).attr('checked',
				($('#item_all').attr('checked') == 'checked'));
		}
	}
}

/**
 * 添加行元素事件
 */
function addHandler(i){
	var divHtml = "<div id='toolbar_"+
		+ i
		+ "' class='datagrid-toolbar' > <input id='search_"
		+ i
		+"' style='width:300px'></input>"	
		+ "</div>";
	$('#expTypeToolBar').append(divHtml);
	
	$('#search_'+i).searchbox({   
	    searcher:function(value,name){  
	        search(value, i);  
	    },   
	    prompt:'请填写查询条件'  
	}); 
	if(curStaId=="start"){
		$("#costDate_"+i).datebox({
			required: true
		});
	}
	
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
		pagination : true,
		rownumbers : true,
		collapsible : false,
		striped : true,
		fit : true,
		method : 'post',
		multiple : false,
		url : appUrl + '/account/accountAction!searchCostType.jspa',
		columns : [ [ {
			field : 'itemId',
			title : 'ID',
			width : 100,
			align : 'center'
		},{
			field : 'itemName',
			title : '费用类型名',
			width : 190,
			align : 'center'
		},{
			field : 'itemValue',
			title : '费用类型值',
			width : 100,
			align : 'center'
		}] ],
		editable : false,
		onLoadSuccess : function() {
			$('#expType_'+i).combogrid("setText", $("#costTypeText_"+i).val());
			$('#expType_'+i).combogrid("setValue", $("#costTypeValue_"+i).val());
		},
		toolbar : "#toolbar_" + i
	});
	
}

function search(value, index){
	value = encodeURIComponent(value);
	$('#expType_'+index).combogrid({
		url : appUrl + '/account/accountAction!searchCostType.jspa?title=' + value
	});
	$('#expType_'+index).combogrid("grid").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame1");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}

function createProEventReader() {
	initMaintEvent('选择联系人', '/allUserAction!toShowUserByOrgId.jspa', 660, 430);
}

function saveUser(x) {
	$("#traUserId").val(x[0]);
	$("#traUserName").val(x[1]);
	closeMaintEvent();
}

/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintEvent('选择组织', '/orgAction!orgTreePage.jspa', 400, 360);
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
	closeMaintEvent();
}

function initMaintEvent(title, url, WWidth, WHeight) {
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

function closeMaintEvent() {
	$("#maintDiv").window('close');
}

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
	if(curStaId=="start"){
		var m = $('#costCenter').combogrid('isValid');
		var dateArr = new Array();
		var numArr = new Array();
		var amountArr = new Array();	
		for(var r=0;r<=i;r++){
			if(!checkInOutsideArr(r)){
				dateArr.push($("#costDate_"+r).datebox('isValid'));
				numArr.push($("#invoiceNum_"+r).validatebox('isValid'));
				amountArr.push($("#invoiceAmount_"+r).validatebox('isValid'));
			}
		}
		if (!(m && checkBooleanArr(dateArr) && checkBooleanArr(numArr) && checkBooleanArr(amountArr))) {
			return;
		}
	}
	
	var detailStr = "";
	detailStr += "[";
	var x = 0;
	for(var j=0; j<=i; j++){
		if(!checkInOutsideArr(j)){
			x++;
			detailStr += "{"
				+ "\"detail_id\" : \"" +$("#detailId_"+j).val()+ "\","
				+ "\"plan_id\" : \"" +$("#planId").val()+ "\","
				+ "\"cost_type\" : \"" +$("#expType_"+j).combobox("getValue")+ "\","
				+ "\"cost_type_content\" : \"" +$("#expType_"+j).combobox("getText")+ "\",";
			if(curStaId=="start"){
				detailStr += "\"cost_date\" : \"" +$("#costDate_"+j).datebox("getValue")+ "\",";
			}else{
				detailStr += "\"cost_date\" : \"" +$("#costDate_"+j).val()+ "\",";
			}
			detailStr += "\"cost_purpose\" : \"" +$("#costPurpose_"+j).val()+ "\","
				+ "\"invoice_num\" : \"" +$("#invoiceNum_"+j).val()+ "\","
				+ "\"invoice_amount\" : \"" +$("#invoiceAmount_"+j).val()+ "\","
				+ "\"audit_money\" : \"" +$("#auditMoney_"+j).val()+ "\","
				+ "\"cost_memo\" : \"" +$("#costMemo_"+j).val()+ "\""
				+ "},";
		}
	}
	if(x>0){
		detailStr = detailStr.substring(0, detailStr.length-1);
	}
	detailStr += ']';
	$("#detailJsonStr").val(detailStr);
	$("#costCenterText").val($('#costCenter').combobox("getText"));
	var form = window.document.forms[0];
		form.action = appUrl + "/account/accountAction!updateExpenseForm.jspa";
		form.target = "hideFrame1";
		form.submit();
}

function reset() {
	window.parent.closeMaintEvent();
}

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
	if("auditMoney" == type){
		if(!regAmount.test($("#auditMoney_"+k).val())){
			$("#auditMoney_"+k).val('');
		}
		countAuditMoney();
	}
}

function countTotalMoney(){
	var total = 0;
	for(var r=0;r<=i;r++){
		if(!checkInOutsideArr(r)){
			var value = $("#invoiceAmount_"+r).val();
			if(value.length>0){
				total += Number(value);
			}
		}	
	}
	$("#totalMoney").val(RoundNumber(total, 2));
}

function countAuditMoney(){
	var total = 0;
	var check = false;
	for(var r=0;r<=i;r++){
		if(!checkInOutsideArr(r)){
			var value = $("#auditMoney_"+r).val();
			if(value != '') {
				check = true	
			}
			if(value.length>0){
				total += Number(value);
			}
		}	
	}
	if(check) {
		$("#auditMoney").val(RoundNumber(total, 2));
	} else {
		$("#auditMoney").val('');
	}
	
}

function RoundNumber(num, pos)  {  
	return Math.round(num * Math.pow(10, pos)) / Math.pow(10, pos);  
} 
