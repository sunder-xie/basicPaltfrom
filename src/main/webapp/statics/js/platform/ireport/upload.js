var index=0;
var outsideArr = new Array();
$(document).ready(function (){
    $('#hiddentr').hide();
});
/**
 * 检查index是否在在删除行序列之内
 * @param {} index
 */
function checkInOutsideArr(index){
	var flag = false;
	for(var a=0;a<outsideArr.length;a++){
		if(outsideArr[a]==index){
			return true;
		}
	}
	return flag;
}

function addmodle(){
    index=parseInt(index)+1;
$('#modles').append("<tr class='head' noWrap style='text-align: right' id='tr_"+index+"'>" +
		"<td>子报表模板</td>" +
		"<td noWrap style='text-align: left'>" +
		"<input class='easyui-validatebox' name='files' id='file_"+index+"' type='file'/>" +
        	"<input type='button' value='删除' onclick='javascript:deleteModle("+index+")'>" +
        	"</td>" +
        	"</tr>");

}
function deleteModle(i){
    $("#tr_" + i).remove();
    outsideArr.push(i);
}
function save(){
    	if($('#remain')==""||$('#remain').val().length<=0){
    	    $.messager.alert('Tips', '请填写备注', 'error'); 
    	    return;
    	}
    	if($('#upload')==""||$('#upload').val().length<=0){
    	    $.messager.alert('Tips', '请上传附件', 'error'); 
    		return; 
    	}

    	if(parseInt(index)>0){
    	    for ( var i=1; i<=index; i++) {
    		if(!checkInOutsideArr(i)){
                	if($('#file_'+i)==""||$('#file_'+i).val().length<=0){
                    	    $.messager.alert('Tips', '请上传子模板附件', 'error'); 
                    	    return; 
                        } 
    		}

    	    }   	    
    	}

    	
    	if($('#is_pagination').val()=='1'){
    	$('#pageNum').val('');
    	}else if($('#is_pagination').val()=='0'){
    	    if(!checkIsNumber($('#pageNum').val())){
    		 $.messager.alert('Tips', '分页数必须是整数', 'error');
    		 return;
    	    }
    	}
	var form = window.document.forms[0];
	form.action = appUrl + "/IreportAction!saveModle.jspa?time="+new Date();
	form.target = "hideFrame";
	form.submit();
	$.messager.alert('Tips', "模板上传成功!", 'info', function(){
	    window.parent.colsewindow();
	    window.parent.search();
	});
	
}
/**
 * 判断是否为整数
 * @param k
 * @returns
 */
function checkIsNumber(k){
	var regNum = /^\d+$/;
	return regNum.test(k);
}
function selctPagination(){
    if($('#is_pagination').val()==0)
    $('#hiddentr').show();
    else if ($('#is_pagination').val()==1) 
	$('#hiddentr').hide();
   
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame3");
	var failResult = hideFrame.contentWindow.failResult;	
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', "图片上传成功!", 'info', function(){
			window.returnValue = successResult;
			window.close();
		});
	}
}