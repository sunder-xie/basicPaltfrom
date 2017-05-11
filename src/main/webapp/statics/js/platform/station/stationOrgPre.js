$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {

	$('#orgTree').tree({
		onContextMenu : function(e, node) {
			e.preventDefault();
			selectedId = node.id;
			selectedName = node.text;
			$('#treeMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}

	});

	$("#treeMenu").menu({
		onClick : function(item) {
			select(selectedId, selectedName);
		}
	});
    var orgTreeUrl = appUrl+ '/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=0';

	$('#orgTree').tree(
					{
						animate : true,
						url : orgTreeUrl,
						onBeforeExpand : function(node, param) {
							$('#orgTree').tree('options').url = appUrl
									+ "/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node="
									+ node.id;
						},
						onClick : function(node) {// 单击事件
							$(this).tree('toggle', node.target);
							if (!node.state) {
								add(node.text, node.attributes);
							}
						}
					});
	
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + ""
		      + new Date(),
		data : {
			claimId : $("#claimId").val()
		},
		success : function(obj) {
			if(obj != null){
				$.each(obj, function(i, v) {
					$("#source").append(
							'<div class="drag-item" userId="'
									+ v.orgId
									+ '"><img src="'
									+ imgUrl
									+ '/images/actions/action_roles.png" align="absMiddle"></img>'
									+ v.orgName
									+ '</div>');
				});
			}
		}
		});
}

function select(selectedId, selectedName) {
	$("#source").append(
			'<div class="drag-item" userId="'
					+ selectedId
					+ '"><img src="'
					+ imgUrl
					+ '/images/actions/action_roles.png" align="absMiddle"></img>'
					+ selectedName
					+ '</div>');
	reloadST();
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			self.location.reload();
		});
	}
}

// ///
function reloadST() {
	$('.drag-item').draggable({
		revert : true,
		deltaX : 0,
		deltaY : 0,
		proxy : 'clone',
		revert : true,
		cursor : 'auto',
		onStartDrag : function() {
			$(this).draggable('options').cursor = 'not-allowed';
			$(this).draggable('proxy').addClass('dp');
		},
		onStopDrag : function() {
			$(this).draggable('options').cursor = 'auto';
		}
	}).droppable({
		onDragOver : function(e, source) {
			$(source).draggable('options').cursor = 'auto';
			$(source).draggable('proxy').css('border', '1px solid red');
			$(this).addClass('over');
		},
		onDragLeave : function(e, source) {
			$(source).draggable('options').cursor = 'not-allowed';
			$(source).draggable('proxy').css('border', '1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop : function(e, source) {
			$(source).insertAfter(this);
			$(source).removeClass('over');
		}
	});
	$('#target,#source').droppable({
		onDragEnter : function(e, source) {
			$(source).draggable('options').cursor = 'auto';
			$(source).draggable('proxy').css('border', '1px solid red');
			$(this).addClass('over');
		},
		onDragLeave : function(e, source) {
			$(source).draggable('options').cursor = 'not-allowed';
			$(source).draggable('proxy').css('border', '1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop : function(e, source) {
			$(this).append(source);
			$(this).removeClass('over');
		}
	});
}
function save() {
	if($("#stationId").val() == ""){
		$.messager.alert('提示', "请先选择岗位！", 'error');
		return;
	}
	var context = "";
	$("#source div").each(function() {
		if (context.length > 0) {
			context = context + "," + $(this).attr("userId");
		} else {
			context = $(this).attr("userId");
		}
	});
	if ("" == context) {
		$.messager.confirm('Confirm', '是否删除该岗位上的组织?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = 'station!ceateStationOrg.jspa';
				form.target = "hideFrame";
				form.submit();
			}
		});
		
	} else {
		$("#orgIds").val(context);
		var form = window.document.forms[0];
		form.action = 'station!ceateStationOrg.jspa';
		form.target = "hideFrame";
		form.submit();
	}
}

function choseStation(){
	initMaintRole('岗位查询 ','/station!toSearchStation.jspa',900,500);
}

function returnStation(id,name){
	$("#stationId").val(id);
	$("#stationName").val(name);
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + "/station!getStationOrgJsonList.jspa?time="+ new Date(),
		data : {
			stationId : $("#stationId").val()
		},
		success : function(obj) {
			if(obj != null){
				$.each(obj, function(i, v) {
					$("#source").append(
							'<div class="drag-item" userId="'
									+ v.orgId
									+ '"><img src="'
									+ imgUrl
									+ '/images/actions/action_roles.png" align="absMiddle"></img>'
									+ v.orgName
									+ '</div>');
				});
				reloadST();
			}
		}
		});
}

function closeStation(){
	 $("#maintRole").window('close');
}
function initMaintRole(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintRole")
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
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}
