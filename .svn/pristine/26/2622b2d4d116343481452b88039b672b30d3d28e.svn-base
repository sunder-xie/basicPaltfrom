var processDefinitionId="";
var processDefinitionName="";
var processDefinitionVariables="";
var _process_def_provided_listeners="";
var is_open_properties_panel = false;
var tid = "";
window.parent.tid="";
var fid = "";
window.parent.fid="";
var allwin=null;
var pid = "";
window.parent.pid="";
var eid="";
window.parent.eid="";
jq(function(){
	try{
		allwin=jq("#maintWindow");
		
		_task_obj = jq('#task');
		_designer = jq('#designer');
		_properties_panel_obj = _designer.layout('panel','east');
		_properties_panel_obj.panel({
			onOpen:function(){
				is_open_properties_panel = true;
			},
			onClose:function(){
				is_open_properties_panel = false;
			}
		});
		_process_panel_obj = _designer.layout('panel','center');
		_task_context_menu = jq('#task-context-menu').menu({});
		_designer.layout('collapse','east');
		
		jq('.easyui-linkbutton').draggable({
					proxy:function(source){
						var n = jq('<div class="draggable-model-proxy"></div>');
						n.html(jq(source).html()).appendTo('body');
						return n;
					},
					deltaX:0,
					deltaY:0,
					revert:true,
					cursor:'auto',
					onStartDrag:function(){
						jq(this).draggable('options').cursor='not-allowed';
					},
					onStopDrag:function(){
						jq(this).draggable('options').cursor='auto';
					}	
		});
		jq('#paintarea').droppable({
					accept:'.easyui-linkbutton',
					onDragEnter:function(e,source){
						jq(source).draggable('options').cursor='auto';
					},
					onDragLeave:function(e,source){
						jq(source).draggable('options').cursor='not-allowed';
					},
					onDrop:function(e,source){
						
						//jq(this).append(source)
						//jq(this).removeClass('over');
						var wfModel = jq(source).attr('wfModel');
						var shape=null;
						if(jq(source).attr('wfModel').contains("Start",'name'))
							 shape = imgUrl+"/images/platform/activitiWebEdit/icons/type.startevent.none.png";
						if(jq(source).attr('wfModel').contains("End",'name'))
							 shape = imgUrl+"/images/platform/activitiWebEdit/icons/type.endevent.none.png";
						if(jq(source).attr('wfModel').contains("ExclusiveGateway",'name'))
							 shape = imgUrl+"/images/platform/activitiWebEdit/icons/type.gateway.exclusive.png";
						if(jq(source).attr('wfModel').contains("UserTask",'name'))
							 shape = jq(source).attr('shape');
						if(wfModel){
							var x=jq(source).draggable('proxy').offset().left;
							var y=jq(source).draggable('proxy').offset().top;
							var xOffset    = workflow.getAbsoluteX();
		                    var yOffset    = workflow.getAbsoluteY();
		                    var scrollLeft = workflow.getScrollLeft();
		                    var scrollTop  = workflow.getScrollTop();
		                  //alert(xOffset+"|"+yOffset+"|"+scrollLeft+"|"+scrollTop);
		                    addModel(wfModel,x-xOffset+scrollLeft,y-yOffset+scrollTop,shape);
						}
					}
				});
		//jq('#paintarea').bind('contextmenu',function(e){
			//alert(e.target.tagName);
		//});
	}catch(e){
		alert(e.message);
	};
	jq('#hideFrame').bind('load', promgtMsg);
	jq(window).unload( function () { 
		//系统报错  先注释
		//window.opener._list_grid_obj.datagrid('reload');
	} );
});

function addModel(name,x,y,icon){
	var model = null;
	
	if(icon!=null&&icon!=undefined)
		model = eval("new draw2d."+name+"('"+icon+"')");
	else
		model = eval("new draw2d."+name+"()");
	//userTask.setContent("DM Approve");
	model.generateId();
	//var id= task.getId();
	//task.id=id;
	//task.setId(id);
	//task.taskId=id;
	//task.taskName=id;
	//var parent = workflow.getBestCompartmentFigure(x,y);
	//workflow.getCommandStack().execute(new draw2d.CommandAdd(workflow,task,x,y,parent));
	workflow.addModel(model,x,y);
}
function openTaskProperties(task){
	window.parent.tid=task.taskId;
	window.parent.tname=task.taskName;
	window.parent.task = task;
	if(!is_open_properties_panel)
	_designer.layout('expand','east');
	_properties_panel_obj.panel('refresh',appUrl + '/activitiWebEdit/deployProcessDefinition!getTaskProperties.jspa');
}
function openProcessProperties(id){
	window.parent.pid=id;
	if(!is_open_properties_panel)
		_designer.layout('expand','east');
	_properties_panel_obj.panel('refresh',appUrl+'/activitiWebEdit/deployProcessDefinition!getProcessProperties.jspa');
}
function openFlowProperties(id){
	window.parent.fid =id;
	if(!is_open_properties_panel)
		_designer.layout('expand','east');
	_properties_panel_obj.panel('refresh',appUrl+'/activitiWebEdit/deployProcessDefinition!getFlowProperties.jspa');
}
function openExclusiveGatewayProperties(id){
	window.parent.eid =id;
	if(!is_open_properties_panel)
		_designer.layout('expand','east');
	_properties_panel_obj.panel('refresh',appUrl+'/activitiWebEdit/deployProcessDefinition!getExclusiveGatewayProperties.jspa');
}
function deleteModel(id){
	
	var task = workflow.getFigure(id);
	workflow.removeFigure(task);
}
function redo(){
	workflow.getCommandStack().redo();
}
function undo(){
	workflow.getCommandStack().undo();
}
function saveProcessDef(){
	var xml = workflow.toXML();
	//alert(workflow.process.getVariablesJSONObject());
	//alert(workflow.process.getVariablesJSONObject());
	//return;
	jq.ajax({
		url:"${ctx}/wf/procdef/procdef!saveProcessDescriptor.action",
		type: 'POST',
		data:{
			processDescriptor:xml,
			processName:workflow.process.name,
			processVariables:workflow.process.getVariablesJSONObject()
		},
		dataType:'json',
		error:function(){
			//$.messager.alert("<s:text name='label.common.error'></s:text>","<s:text name='message.common.save.failure'></s:text>","error");
			return "";
		},
		success:function(data){
			if(data.result){
				jq.messager.alert('提示','保存成功!','info');
			}else{
				jq.messager.alert('Error',data.message,'error');
			}
		}	
	}); 
	
}
function exportProcessDef(obj){
	//obj.href="${ctx}/wf/procdef/procdef!exportProcessDef.action?procdefId="+processDefinitionId+"&processName="+processDefinitionName;
}

function parseProcessDescriptor(data){
						var descriptor = jq(data);
						var definitions = descriptor.find('definitions');
						var process = descriptor.find('process');
						var startEvent = descriptor.find('startEvent');
						var endEvent = descriptor.find('endEvent');
						var userTasks = descriptor.find('userTask');
						var exclusiveGateway = descriptor.find('exclusiveGateway');
						var parallelGateway = descriptor.find('parallelGateway');
						var lines = descriptor.find('sequenceFlow');
						var shapes = descriptor.find('bpmndi\\:BPMNShape');
						var edges = descriptor.find('bpmndi\\:BPMNEdge');
						
						workflow.process.category=definitions.attr('targetNamespace');
						workflow.process.id=process.attr('id');
						workflow.process.name=process.attr('name');
						var documentation = trim(descriptor.find('process > documentation').text());
						if(documentation != null && documentation != "")
							workflow.process.documentation=documentation;
						var extentsion = descriptor.find('process > extensionElements');
						if(extentsion != null){
							var listeners = extentsion.find('activiti\\:executionListener');
							workflow.process.setListeners(parseListeners(listeners,"draw2d.Process.Listener","draw2d.Process.Listener.Field"));
						}
						jq.each(processDefinitionVariables,function(i,n){
								var variable = new draw2d.Process.variable();
								variable.name=n.name;
								variable.type=n.type;
								variable.scope=n.scope;
								variable.defaultValue=n.defaultValue;
								variable.remark=n.remark;
								workflow.process.addVariable(variable);
							});
						startEvent.each(function(i){
								var start = new draw2d.Start(imgUrl+"/images/platform/activitiWebEdit/icons/type.startevent.none.png");
								start.id=jq(this).attr('id');
								start.eventId=jq(this).attr('id');
								start.eventName=jq(this).attr('name');
								shapes.each(function(i){
									var id = jq(this).attr('bpmnElement');
									if(id==start.id){
										var x=parseInt(jq(this).find('omgdc\\:Bounds').attr('x'));
										var y=parseInt(jq(this).find('omgdc\\:Bounds').attr('y'));
										workflow.addFigure(start,x,y);
										return false;
									}
								});
							});
						endEvent.each(function(i){
								var end = new draw2d.End(imgUrl+"/images/platform/activitiWebEdit/icons/type.endevent.none.png");
								end.id=jq(this).attr('id');
								end.eventId=jq(this).attr('id');
								end.eventName=jq(this).attr('name');
								shapes.each(function(i){
									var id = jq(this).attr('bpmnElement');
									if(id==end.id){
										var x=parseInt(jq(this).find('omgdc\\:Bounds').attr('x'));
										var y=parseInt(jq(this).find('omgdc\\:Bounds').attr('y'));
										workflow.addFigure(end,x,y);
										return false;
									}
								});
							});
						
						userTasks.each(function(i){
								var task = new draw2d.UserTask();
								var tid = jq(this).attr('id');
								task.id=tid;
								var tname = jq(this).attr('name');
								var assignee=jq(this).attr('activiti:assignee');
								var candidataUsers=jq(this).attr('activiti:candidateUsers');
								var candidataGroups=jq(this).attr('activiti:candidateGroups');
								var formKey=jq(this).attr('activiti:formKey');
								if(assignee!=null&&assignee!=""){
									task.isUseExpression=true;
									task.performerType="assignee";
									task.expression=assignee;
								}else if(candidataUsers!=null&&candidataUsers!=""){
									task.isUseExpression=true;
									task.performerType="candidateUsers";
									task.expression=candidataUsers;
								}else if(candidataGroups!=null&&candidataGroups!=""){
									task.isUseExpression=true;
									task.performerType="candidateGroups";
									task.expression=candidataGroups;
								}
								if(formKey!=null&&formKey!=""){
									task.formKey=formKey;
								}
								var documentation = trim(jq(this).find('documentation').text());
								if(documentation != null && documentation != "")
									task.documentation=documentation;
								task.taskId=tid;
								task.taskName=tname;
								if(tid!= tname)
									task.setContent(tname);
								var listeners = jq(this).find('extensionElements').find('activiti\\:taskListener');
								task.setListeners(parseListeners(listeners,"draw2d.Task.Listener","draw2d.Task.Listener.Field"));
								var performersExpression = jq(this).find('potentialOwner').find('resourceAssignmentExpression').find('formalExpression').text();
								if(performersExpression.indexOf('user(')!=-1){
									task.performerType="candidateUsers";
								}else if(performersExpression.indexOf('group(')!=-1){
									task.performerType="candidateGroups";
								}
								var performers = performersExpression.split(',');
								jq.each(performers,function(i,n){
									var start = 0;
									var end = n.lastIndexOf(')');
									if(n.indexOf('user(')!=-1){
										start = 'user('.length;
										var performer = n.substring(start,end);
										task.addCandidateUser({
												sso:performer
										});
									}else if(n.indexOf('group(')!=-1){
										start = 'group('.length;
										var performer = n.substring(start,end);
										task.addCandidateGroup(performer);
									}
								});
								shapes.each(function(i){
									var id = jq(this).attr('bpmnElement');
									if(id==task.id){
										var x=parseInt(jq(this).find('omgdc\\:Bounds').attr('x'));
										var y=parseInt(jq(this).find('omgdc\\:Bounds').attr('y'));
										workflow.addModel(task,x,y);
										return false;
									}
								});
							});
						exclusiveGateway.each(function(i){
								var gateway = new draw2d.ExclusiveGateway(imgUrl+"/images/platform/activitiWebEdit/icons/type.gateway.exclusive.png");
								
								var gtwid = jq(this).attr('id');
								var gtwname = jq(this).attr('name');
								gateway.id=gtwid;
								gateway.gatewayId=gtwid;
								gateway.gatewayName=gtwname;
								shapes.each(function(i){
									var id = jq(this).attr('bpmnElement');
									if(id==gateway.id){
										var x=parseInt(jq(this).find('omgdc\\:Bounds').attr('x'));
										var y=parseInt(jq(this).find('omgdc\\:Bounds').attr('y'));
										workflow.addModel(gateway,x,y);
										return false;
									}
								});
							});
						parallelGateway.each(function(i){
							var gateway = new draw2d.ExclusiveGateway(imgUrl+"/images/platform/activitiWebEdit/icons/type.gateway.parallel.png");
							var gtwid = jq(this).attr('id');
							var gtwname = jq(this).attr('name');
							gateway.id=gtwid;
							gateway.gatewayId=gtwid;
							gateway.gatewayName=gtwname;
							shapes.each(function(i){
								var id = jq(this).attr('bpmnElement');
								if(id==gateway.id){
									var x=parseInt(jq(this).find('omgdc\\:Bounds').attr('x'));
									var y=parseInt(jq(this).find('omgdc\\:Bounds').attr('y'));
									workflow.addModel(gateway,x,y);
									return false;
								}
							});
						});
						lines.each(function(i){
								var lid = jq(this).attr('id');
								var name = jq(this).attr('name');
								var condition=jq(this).find('conditionExpression').text();
								var sourceRef = jq(this).attr('sourceRef');
								var targetRef = jq(this).attr('targetRef');
								var source = workflow.getFigure(sourceRef);
								var target = workflow.getFigure(targetRef);
								edges.each(function(i){
										var eid = jq(this).attr('bpmnElement');
										if(eid==lid){
											var startPort = null;
											var endPort = null;
											var points = jq(this).find('omgdi\\:waypoint');
											var startX = jq(points[0]).attr('x');
											var startY = jq(points[0]).attr('y');
											var endX = jq(points[1]).attr('x');
											var endY = jq(points[1]).attr('y');
											var sports = source.getPorts();
											for(var i=0;i<sports.getSize();i++){
												var s = sports.get(i);
												var x = s.getAbsoluteX();
												var y = s.getAbsoluteY();
												if(x == startX&&y==startY){
													startPort = s;
													break;
												}
											}
											var tports = target.getPorts();
											for(var i=0;i<tports.getSize();i++){
												var t = tports.get(i);
												var x = t.getAbsoluteX();
												var y = t.getAbsoluteY();
												if(x==endX&&y==endY){
													endPort = t;
													break;
												}
											}
											if(startPort != null&&endPort!=null){
												var cmd=new draw2d.CommandConnect(workflow,startPort,endPort);
												var connection = new draw2d.DecoratedConnection();
												connection.id=lid;
												connection.lineId=lid;
												connection.lineName=name;
												if(lid!=name)
													connection.setLabel(name);
												if(condition != null && condition!=""){
													connection.condition=condition;
												}
												cmd.setConnection(connection);
												workflow.getCommandStack().execute(cmd);
											}
											return false;
										}
									});
							});
						if(typeof setHightlight != "undefined"){
							setHightlight();
						}
					}
					function parseListeners(listeners,listenerType,fieldType){
						var parsedListeners = new draw2d.ArrayList();
						listeners.each(function(i){
							var listener = eval("new "+listenerType+"()");
							
							listener.event=jq(this).attr('event');
							var expression = jq(this).attr('expression');
							var clazz = jq(this).attr('class');
							if(expression != null && expression!=""){
								listener.serviceType='expression';
								listener.serviceExpression=expression;
							}else if(clazz != null&& clazz!=""){
								listener.serviceType='javaClass';
								listener.serviceExpression=clazz;
							}
							var fields = jq(this).find('activiti\\:field');
							fields.each(function(i){
								var field = eval("new "+fieldType+"()");
								field.name=jq(this).attr('name');
								//alert(field.name);
								var string = jq(this).find('activiti\\:string').text();
								var expression = jq(this).find('activiti\\:expression').text();
								//alert("String="+string.text()+"|"+"expression="+expression.text());
								if(string != null && string != ""){
									field.type='string';
									field.value=string;
								}else if(expression != null && expression!= ""){
									field.type='expression';
									field.value=expression;
								}
								listener.setField(field);
							});
							parsedListeners.add(listener);
						});
						return parsedListeners;
					}
					
					

					var workflow;
					jq('#process-definition-tab').tabs({
						fit:true,
						onSelect:function(title){
							if(title=='Diagram'){
								
							}else if(title=='XML'){
								jq('#descriptorarea').val(workflow.toXML());
								/*
								if(document.body.innerText)
									jq('#xml-area').get(0).innerText=workflow.toXML();
								else if(document.body.textContent)
									jq('#xml-area').get(0).textContent=workflow.toXML();
								*/
							}
						}
					});
					function openProcessDef(){
						jq.ajax({
							url:"../../wf/procdef/procdef!getProcessDefXML.action?procdefId="+processDefinitionId,
							type: 'POST',
							/*
							data:{
										moduleId:"${moduleId}",
										_request_json_fields:json4params
								},
							*/
							dataType:'xml',
							error:function(){
								$.messager.alert("<s:text name='label.common.error'></s:text>","System Error","error");
								return "";
							},
							success:parseProcessDescriptor	
						}); 
					}
				
					function createCanvas(disabled){
						try{
							//initCanvas();
							workflow  = new draw2d.MyCanvas("paintarea");
							workflow.scrollArea=document.getElementById("designer-area");
							if(disabled)
								workflow.setDisabled();
							if(typeof processDefinitionId != "undefined" && processDefinitionId != null &&  processDefinitionId != "null" && processDefinitionId != "" && processDefinitionId != "NULL"){
								openProcessDef();
							}else{
									var id = "process"+Sequence.create();
									//var id = workflow.getId();
									workflow.process.category='genpact_wf_process_def';
									workflow.process.id=id;
									workflow.process.name=id;
								// Add the start,end,connector to the canvas
								  var startObj = new draw2d.Start(imgUrl+"/images/platform/activitiWebEdit/icons/type.startevent.none.png");

								  workflow.addFigure(startObj, 50,50);
								  
								  var endObj   = new draw2d.End(imgUrl+"/images/platform/activitiWebEdit/icons/type.endevent.none.png");

								  workflow.addFigure(endObj,500,50);
							} 
						}catch(e){
							alert(e.message);
						}
					}
	function submit_process(){
		//alert(workflow.toXML());
		var stringxml= workflow.toXML();
		jq('#xmlString').val(stringxml);
		jq('#processname').val(workflow.findprocessid());
		var form = window.document.forms[0];
		form.action = appUrl + "/activitiWebEdit/deployProcessDefinition!deployProcessDefinition.jspa?isOa="+workflow.process.text;
		form.target = "hideFrame";
		form.submit();
	}								
	function promgtMsg() {
		var hideFrame = document.getElementById("hideFrame");
		var failResult = hideFrame.contentWindow.failResult;
		var successResult = hideFrame.contentWindow.successResult;
		if (failResult) {
			jq.messager.alert('Tips', failResult, 'error');
		} else if (successResult) {
			jq.messager.alert('Tips', successResult, 'info', function() {
				window.location.reload();
			});
		}
	}			
					
					
					