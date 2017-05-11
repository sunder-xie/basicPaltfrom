$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);

	$('#planTypeName').combobox({
		url : appUrl + '/wfe/processChooseAction!getPlanAttribute.jspa?key=' + modelId,
		valueField : 'planAttId',
		textField : 'planTypeName',
		onLoadSuccess : function() {
			if (planAttId != '0') {
				$('#planTypeName').combobox("setValue", planAttId);
				eventType(planAttId, 'Y');
			} else {
				$('#planTypeName').combobox("setText", '--请选择--');
			}
		},
		onSelect : function(re) {
			eventType(re.planAttId, 'N');
		}
	});
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		var planAttId = $('#planTypeName').combobox('getValue');
		$("#xmlTemp_FileName", window.parent.document).val($("#xmlTemp_FileName").val());		
		window.parent.closeContent(planAttId);
	}
}

function eventType(planAttId, flag) {
	var tempFielName = $("#xmlTemp_FileName").val();
	$.ajax({
		async : false,
		type : "post",
		url : appUrl
				+ '/wfe/eventAction!updateEventAttributeContentPrepare.jspa',
		data : {
			'planAttId' : planAttId,
			'flag' : flag,
			'xmlTemp_FileName' : tempFielName
		},
		dataType : 'html',
		success : function(v) {
			if (v.indexOf("error") > -1) {
				$('#result').html(
						"<FONT color=\"#ff0000\">该事务类别尚未维护，请联系管理员！</FONT>");
			} else {
				$('#result').html(v);
				$('#bot a').each(function(index, v) {
					$(v).linkbutton('enable');
				});
				addHandler();
			}	
		},
		error : function() {
		}
	});
}

function addHandler(){
	for(var i = 1;i <= $('#size').val(); i++) {
		if($('#dataType_'+i).val() == 'date'){
			$('#text_'+i).datebox({
				editable : false
			});
		}else if($('#dataType_'+i).val() == 'timestamp'){
			$('#text_'+i).datetimebox({
				showSeconds:false,
				editable : false
			});
		}else if($('#dataType_'+i).val() == 'number'){
			$('#text_'+i).numberbox({
				min:0,
				precision:1
			});
		}else if($('#dataType_'+i).val() == 'radio'){
			$('#text_'+i).combobox({  
			    required:true,  
			    multiple:false,
			    valueField: 'value',
				textField: 'label',
			    data: [{
					label: '是',
					value: 'Y'
				},{
					label: '否',
					value: 'N'
				}],
				onLoadSuccess : function() {
					$('#text_'+i).combobox("setValue", "N");
				}
			});  
		}else{
			$('#text_'+i).validatebox({});
		}
	}
}

function close() {
	window.parent.closeMaintEvent();
}

function save() {
	var xml = "";
	var news = "";
	var planTypeName = $('#planTypeName').combobox('getText');
	var planAttId = $('#planTypeName').combobox('getValue');
	
	xml += '<?xml version="1.0" encoding="GBK"?><oaplan name="' + planTypeName
			+ '" id="' + planAttId + '">';
	var size = $('#size').val();
	window.parent.document.getElementById("modelKey").value="";
	window.parent.document.getElementById("modelValues").value="";
	for ( var i = 1; i <= size; i++) {
		var name = $('#name_' + i).val();
		
		var text = '';
		if($('#dataType_'+i).val() == 'date'){
			text = $('#text_'+i).datebox('getValue');
		}else if($('#dataType_'+i).val() == 'timestamp'){
			text = $('#text_'+i).datetimebox('getValue');
		}else if($('#dataType_'+i).val() == 'number'){
			text = $('#text_'+i).numberbox('getValue');
		}else if($('#dataType_'+i).val == 'blob'){
			text = $('#text_'+i).html();
		}else if($('#dataType_'+i).val() == 'radio'){
			text = $('#text_'+ i).combobox('getValues');
		}else{
			text = $('#text_' + i).val();
		}
		var modelKey = $('#modelKey_' + i).val();
		if ($('#isNull_'+i).val()=='N' && text == '') {
			$.messager.alert('Tips', name + '不能为空!', 'warning');
			return;
		}
		news += '<new><id>' + i + '</id><name>' + name + '</name><text>' + text
				+ '</text></new>';
		if(modelKey!=""){
			 window.parent.document.getElementById("modelKey").value =
				 modelKey+","+window.parent.document.getElementById("modelKey").value;
			 window.parent.document.getElementById("modelValues").value = 
				 text +","+ window.parent.document.getElementById("modelValues").value;
		}
	}
	xml += news;
	xml += '</oaplan>';
	$("#eventContent").val(xml);

	var form = window.document.forms[0];
	form.action = appUrl+"/wfe/eventAction!updateEventContent.jspa";
	form.target = "hideFrame";
	form.submit();
}