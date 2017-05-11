$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);

//	addUploadButton(editor);
});

function loadGrid(){
/*	$('#stationId').combobox({
		url : appUrl + '/wfe/processChooseAction!getStations.jspa',
		valueField : 'stationId',
		textField : 'stationName',
		onLoadSuccess : function() {
			$('#stationId').combobox("setText", '--请选择--');
		},
		editable : false,
		onSelect : function(re) {
			choiceOld(re.stationId);
		}
	});
*/
	selectModelCombox();
//	$("#nextinfolist").hide();
}

function addUploadButton(editor){     
	CKEDITOR.on('dialogDefinition', function(ev){         
		var dialogName = ev.data.name;         
		var dialogDefinition = ev.data.definition;         
		if ( dialogName == 'image' ){             
			var infoTab = dialogDefinition.getContents( 'info' );             
			infoTab.add({                 
				type : 'button',                 
				id : 'upload_image',                 
				align : 'center',                 
				label : '上传',                 
				onClick : function( evt ){
					var thisDialog = this.getDialog();                     
					var txtUrlObj = thisDialog.getContentElement('info', 'txtUrl');                     
					var txtUrlId = txtUrlObj.getInputElement().$.id;                     
					addUploadImage(txtUrlId);                 
				}             
			}, 'browse'); 
		}
	});
}

function addUploadImage(theURLElementId){
	//这是处理文件/图片上传的页面URL ;scroll:yes;status:yes
	var uploadUrl = appUrl + '/file/fileAction!uploadImagPrepare.jspa?Rnd='+Math.random();	
	//在upload结束后通过js代码window.returnValue=...可以将图片url返回给imgUrl变量
	var imgUrl = window.showModalDialog(uploadUrl, null, "dialogWidth=400px;dialogHeight=200px");
	
	var urlObj = document.getElementById(theURLElementId);     
	urlObj.value = imgUrl;     
	urlObj.fireEvent("onchange"); //触发url文本框的onchange事件，以便预览图片 
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
	} else if (successResult == "写入XML文件成功!") {
		submit();
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}

/**
 * 一级下来列表联动
 * 
 * @param {}
 *            id
 */
/*function choiceOld(stationId) {
	var dataHtml = "<input id='curStationId' name='curStationId' readonly />"
			+ "<div id='modelReturn' name='modelReturn'></div>";
	document.getElementById("roleReturn").innerHTML = dataHtml;
	bindSecondCombox('curStationId');
}
*/
/**
 * 绑定二级下拉列表
 *//*
function bindSecondCombox(docId) {
	$('#' + docId).combobox({
		url : appUrl + '/station!getCompanyJsonList.jspa',
		valueField : 'orgId',
		textField : 'orgName',
		onLoadSuccess : function() {
			$('#' + docId).combobox("setText", '--请选择--');
		},
		editable : false,
		onSelect : function(re) {
			choiceNew(re.orgId);
		}
	});
}*/

/**
 * 二级下拉列表联动
 * 
 * @param {}
 *            id
 *//*
function choiceNew(orgId) {
	var dataHtml = "<input id='modelId' name='key' readonly/>";
	document.getElementById("modelReturn").innerHTML = dataHtml;
	bingThirdCombox('modelId');
}
*/
/**
 * 绑定三级下拉列表
 * 
 * @param {}
 *            id
 */
function selectModelCombox() {
	$('#modelId').combobox(
			{
				url : appUrl + '/wfe/processChooseAction!getModels.jspa?from=',
				valueField : 'key',
				textField : 'name',
				onLoadSuccess : function() {
					modifyModel('anyProcess');
					$('#modelId').combobox("setText", '任意流程');
					$('#modelId').combobox("setValue", 'anyProcess');
				},
				editable : false,
				onSelect : function(re) {
					modifyModel(re.key);
					if ("semiautomatic" == re.key) {
						initMaintEvent('半自动化流程绘制', '/wfe/eventAction!toSemiAutomatic.jspa', 750,450);
					}
				}
			});
}

function modifyModel(key) {
//	$('#addContent').html(
//					"<A class=\"icon_but\" href=\"javascript:addContent("
//							+ "'"
//							+ key
//							+ "'"
//							+ ",0"
//							+ ",0)\">"
//							+ "<img height=16 width=16 src=\""
//							+ imgUrl
//							+ "/images/actions/action_edit.png\" align=absMiddle border=0>添加内容</A>");
//	$('#hideFrame').bind('load', promgtMsg1);
	
	addContent(key,0,0);
	
	if ("any" == key.substr(0,3)) {
		$("#nextinfolist").show();
	} else {
		$("#nextinfolist").hide();
	}
	flag = false;
}

/**
 * 添加内容
 * 
 * @param {}
 *            key
 * @param {}
 *            planAttId
 */
function addContent(key, planAttId, tempFielName) {
	if("fix_travel" == key) {
		initMaintEvent('内容创建',
			'/wfe/eventAction!toBusinessTripApply.jspa?key=' + key
					+ "&planAttId=" + planAttId + "&xmlTemp_FileName=" + tempFielName, 580, 400);
	}else {
//		initMaintEvent('内容创建',
//			'/wfe/eventAction!updateEventContentPrepare.jspa?key=' + key
//					+ "&planAttId=" + planAttId + "&xmlTemp_FileName=" + tempFielName, 580, 400);
	
		
		var modelId = key;
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
					$('#result').html("");
				}
			},
			onSelect : function(re) {
				eventType(re.planAttId, 'N');
			}
		});
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
						"<FONT color=\"#ff0000\">该签呈类别尚未维护，请联系管理员！</FONT>");
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


function saveXML() {

	$.ajax({
		async : false,
		type : "post",
		url : appUrl + '/wfe/eventAction!getTemp_FileName.jspa',
		dataType : "json",
		success : function(v) {
			$("#xmlTemp_FileName").val(v);
			var xml = "";
			var news = "";
			var planTypeName = $('#planTypeName').combobox('getText');
			var planAttId = $('#planTypeName').combobox('getValue');
			var tempFielName = $('#xmlTemp_FileName').val();
			var txtNum = 1;
			if (tempFielName == "") {
				$.messager.alert('Tips', '获取临时文件名失败，请重试！', 'warning');
				return;
			}
			if ($('#planTypeName').combobox("getValue") == "") {
				$.messager.alert('Tips', '请选择签呈类别', 'warning');
				return;
			}

			xml += '<?xml version="1.0" encoding="GBK"?><oaplan name="'
					+ planTypeName + '" id="' + planAttId + '">';
			var size = $('#size').val();
			document.getElementById("modelKey").value = "";
			document.getElementById("modelValues").value = "";
			for (var i = 1; i <= size; i++) {
				var name = $('#name_' + i).val();

				var text = '';
				if ($('#dataType_' + i).val() == 'date') {
					text = $('#text_' + i).datebox('getValue');
				} else if ($('#dataType_' + i).val() == 'timestamp') {
					text = $('#text_' + i).datetimebox('getValue');
				} else if ($('#dataType_' + i).val() == 'number') {
					text = $('#text_' + i).numberbox('getValue');
				} else if ($('#dataType_' + i).val() == 'blob') {
					text = $('#text_' + i).val();
				} else if ($('#dataType_' + i).val() == 'editor') {
					// text = $('#text_'+i).html();
					var test = 'editor_' + i;
					var n = txtNum++;
					text = window[test].document.getBody().getHtml();
					if ("" == text && $('#isNull_' + i).val() == 'N') {
						$.messager.alert('Tips', name + '不能为空!', 'warning');
						return;
					} else {
						$.ajax({
							async : false,
							type : "post",
							url : appUrl + '/wfe/eventAction!getTxt.jspa',
							dataType : "json",
							data : {
								xmlTemp_FileName : tempFielName + '_' + n,
								text : encodeURI(encodeURI(text))
							},
							success : function(txt) {
								text = txt + '.txt';
							}
						});
					}
				} else if ($('#dataType_' + i).val() == 'radio') {
					text = $('#text_' + i).combobox('getValues');
				} else {
					text = $('#text_' + i).val();
				}
				var modelKey = $('#modelKey_' + i).val();
				if ($('#isNull_' + i).val() == 'N' && text == '') {
					$.messager.alert('Tips', name + '不能为空!', 'warning');
					return;
				}
				news += '<new><id>' + i + '</id><name>' + name
						+ '</name><text>' + text + '</text></new>';
				if (modelKey != "") {
					document.getElementById("modelKey").value = modelKey + ","
							+ document.getElementById("modelKey").value;
					document.getElementById("modelValues").value = text + ","
							+ document.getElementById("modelValues").value;
				}
			}
			xml += news;
			xml += '</oaplan>';
			$("#eventContent").val(xml);

			var form = window.document.forms[0];
			form.action = appUrl + "/wfe/eventAction!updateEventContent.jspa";
			form.target = "hideFrame";
			form.submit();
		},
	    error :function() {
	    	$.messager.alert('Tips', '获取临时文件名失败，请重试！', 'warning');
	    }
	});
}

/**
 * 选择联系人
 */
function createProEventReader() {
	initMaintEvent('选择联系人', '/allUserAction!toShowUserByOrgId.jspa', 660, 430);
}

function searchLinkMan() {
	initMaintEvent('常用联系人', '/wfe/eventAction!searchLinkMan.jspa', 340, 430);
}

/**
 * 保存联系人
 * 
 * @param {}
 *            x
 */
function saveUser(x) {
	$("#nextUserId").val(x[0]);
	$("#nextUserName").val(x[1]);
	$("#nextOrgId").val(x[2]);
	closeMaintEvent();
}
function cc(){
	initMaintEvent('选择抄送人', '/wfe/eventAction!toCc.jspa', 800, 450);
}

// 创建窗口对象
function initMaintEvent(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintEvent")
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
						draggable : true
					});
	$win.window('open');
}

/**
 * 关闭弹出窗口
 */
function closeMaintEvent() {
	$("#maintEvent").window('close');
}

/**
 * 内容填写完成之后
 */
function closeContent(planAttId) {
	var key = $('#modelId').combobox('getValue');
	var tempFielName = $("#xmlTemp_FileName").val();
	flag = true;
	$('#addContent').html(
					"<A class=\"icon_but\" href=\"javascript:addContent("
							+ "'"
							+ key
							+ "'"
							+ ","
							+ planAttId
							+ ",'"
							+ tempFielName
							+ "')\">"
							+ "<img height=16 width=16 src=\""
							+ imgUrl
							+ "/images/actions/action_edit.png\" align=absMiddle border=0>编辑内容</A>");
	closeMaintEvent();
}

function submit() {
	var key = $('#modelId').combobox('getValue');
	if (key == 'fix_weekPlan') {
		$('#file0').validatebox({
			required : true,
			missingMessage:'请添加附件'
		});
	} else {
		$('#file0').validatebox({
			required : false
		});
	}
	var isValid = $('#ff').form('validate');
	if (!isValid) {
		return;
	}

//	if (!flag) {
//		$.messager.alert('Tips', '请填写内容', 'warning');
//		return;
//	}
	
//	$("#memo").val(editor.document.getBody().getText()); // 取得纯文本
	$("#memo").val($("#editor1").val());
	if ("any" == key.substr(0,3)) {
		var nextuser = $("#nextUserId").val();
		if (nextuser == '' || nextuser.length == 0) {
			$.messager.alert('Tips', '请选择处理人', 'warning');
			return;
		}
	}

	
	var fix_key = key.substr(0,3);
	if ("fix" == fix_key) {
		$.messager.progress();
		$.ajax({
			type : "post",
			url : appUrl + "/wfe/eventAction!selectNexUser.jspa?time="+new Date() + "&projectId=" + $("#projectId").val(),
			data : {
				key : key,
				userId   : $("#curUserId").val(),
				modelKey : $("#modelKey").val(),
				modelValues:$("#modelValues").val()
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
						    		   	$.messager.progress();
						    		   	if($("#nextUserId1").val() == "" || $("#nextUserId1").val() == null){
											$.messager.alert('Tips', "没有下个处理人，请维护！", 'error');
										   return;
										}
						    		    $("#nextUserId").val($("#nextUserId1").val());
						    			var form = window.document.forms[0];
						    			form.action = appUrl + "/wfe/eventAction!processWorkflowFix.jspa?eventId="+userUtil.processInstanceId;
						    			form.submit();
						    	   }},{
						    		   text:'取消',
						    		   handler:function(){ 
						    			   	var form = window.document.forms[0];
						    				form.action = appUrl + "/wfe/eventAction!cancelNextUser.jspa?eventId="+userUtil.processInstanceId;
						    				form.target = "hideFrame";
						    				form.submit();
								    	} 
						    	   } 
						    	   ],

						       width: document.documentElement.clientWidth * 0.50,
						       height: document.documentElement.clientHeight * 0.40
						   });
					},
				error: function (XMLHttpRequest, textStatus, errorThrown){
					$.messager.alert('Tips', textStatus, 'error');
			  	}
				});
		
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + "/wfe/eventAction!createEvent.jspa";
		form.submit();
	}
}

function reset() {
	$.messager.alert('Tips', "取消", 'error');
}

