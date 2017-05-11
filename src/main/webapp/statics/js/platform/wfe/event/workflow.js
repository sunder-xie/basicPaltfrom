jQuery.support.cors = true;
function graphTrace(eventId) {
	var positionHtml = "<table width='600' border='0' cellspacing='1' cellpadding='0' bgcolor='#000000'>"+"<tr bgcolor='#EAF2FF'><th>操作人类型</th><th>操作人</th><th>操作状态</th></tr>";
	$.ajax({
		type : "post",
		url : appUrl + "/wfe/eventAction!traceProcess.jspa?time="+new Date(),
		data : {
			eventId : eventId
		},
		success : function(stringResultList) {
			$.each(stringResultList, function(i, v) {
				var result= "";
				if(v.code == "0"){
					result = "作废";
				}else if(v.code == "1"){
					result = "同意";
				}else if(v.code == "9"){
					result = "未处理";
				}else if(v.code == "2"){
					result = "已回退";
				}
				if(i == 0){
					positionHtml +="<tr bgcolor='#FFFFFF'><th>发起人</th><th>"+v.result+"</th><th>"+result+"</th></tr>";	
				}else if(v.code == "9"){
					positionHtml += "<tr bgcolor='#FFFD0'><th>"+v.text+"</th><th>"+v.result+"</th><th>"+result+"</th></tr>";
				}else{
					positionHtml += "<tr bgcolor='#FFFFFF'><th>"+v.text+"</th><th>"+v.result+"</th><th>"+result+"</th></tr>";
				}
				
			});
			positionHtml = positionHtml+"</table>";
			
			 if ($('#imgDialog').length > 0) {
		            $('#imgDialog').remove();
		        }
		        $('<div/>', {
		            'id': 'imgDialog',
		            title: '查看流程',
		            html: "<div><div><img src='" + wfeUrl + '/workflow/process/trace/auto/' + eventId + "' />"
		            	  + "<div id='processImageBorder'>" 
				           +positionHtml+
				           "</div>" +
				           "</div>"
		                  
		        }).appendTo('body').dialog({
		            modal: true,
		            resizable: false,
		            dragable: false,
		            width: document.documentElement.clientWidth * 0.95,
		            height: document.documentElement.clientHeight * 0.95
		        });
		}
	});
}