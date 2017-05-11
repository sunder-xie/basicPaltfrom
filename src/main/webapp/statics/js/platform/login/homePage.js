var frameHeight;
var currentDir;// 当前目录
var navSize;// 导航个数

$(function(){
	$('#menuTree').prev().css("background-color", "#153540");
	$('#menuTree').parent().css("border-right", "5px solid #3D92D9");
	$('#work').prev().css("background-color", "#153540");
	$.ajax({
	     url:"http://xppfntest.zjxpp.com:7176/FReport/ReportServer?op=fs_load&cmd=sso",//单点登录的报表服务器  
	     dataType:"jsonp",//跨域采用jsonp方式  
	     data:{"fr_username":"admin","fr_password":"5c4b347f8609b24c9ffc964dc19f5be1"},  
	     jsonp:"callback",
	     timeout:5000,//超时时间（单位：毫秒）  
	     success:function(data) {  
	            if (data.status === "success") {  
	                  //登录成功     
	            } else if (data.status === "fail"){  
	                 //登录失败（用户名或密码错误）  
	            }  
	     },  
	     error:function(){  
	           // 登录失败（超时或服务器其他错误）  
	     }  
	});
});

$(document)
		.ready(
				function() {

					frameHeight = '100%';

					// welcome
					//$.growlUI('Welcome EXP!', 'Have a nice day...');

					// 加载首页公告tab
					add('首页公告', '/newsAction!newsIndex.jspa', '_self');

					// 绑定tabs的右键菜单
					$("#tabs").tabs({
						onContextMenu : function(e, title) {
							e.preventDefault();
							$('#tabsMenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							}).data("tabTitle", title);
						}
					});

					// 待办tip
					$.ajax({
								type : "post",
								url : appUrl
										+ '/wfe/eventAction!getProcessedCount.jspa',
								success : function(total) {
									if (total.code > 0) {
										$("#oContent")
												.html(
														'<img align="absMiddle" src='
																+ imgUrl
																+ '/css/easyui/themes/icons/tip.png><a  href="javascript:openProcessedTab();"><font class=fontStyle>您有'
																+ total.code
																+ '条待办事宜需要处理......<font></a></img>');
									}
								}
							});
					
					// 待办tip
					$.ajax({	type : "post",
								url : appUrl
										+ '/wfe/eventAction!getProcessedCount.jspa',
								success : function(result) {
									//增加对账单反馈通知
									$.ajax({	type : "post",
										url : appUrl
										+ '/wfe/eventAction!searchOrderCheckNotRead.jspa',
										success : function(total) {
											var title = '';
											var count = 0;
											if(result.result > 0){
												if(count > 0){
													title += '<br>';
												}
												title += '<a href="#" onclick="toCcSearchInit()"><font class=fontStyle>您有'
													+ result.result
													+ '条协商事宜,点击进入!<font></a>';
												count += 1;
											}
											if(result.text > 0){
												if(count > 0){
													title += '<br>';
												}
												title += '<a href="#" onclick="toCaSearchInit()"><font class=fontStyle>您有'
													+ result.text
													+ '条事宜被退回,点击进入!<font></a>';
												count += 1;
											}
											if(total>0){
												if(count > 0){
													title += '<br>';
												}
												title += '<font class=fontStyle>有'
													+ total
													+ '家经销商新增对账单反馈!<font></a>';
												count += 1;
											}
											if(result.result > 0 || result.text > 0 || total>0) {
												$.messager.show({
													title : '待办事宜',
													msg : title,
													width : 230,
													height : 40 + count*40,
													timeout : 0,
													showSpeed : 2000
												});
											}
											
										}
									});
									
									
									
//									if (result.code > 0 && result.result > 0) {
//										$.messager.show({
//													title : '待办事宜',
//													msg : '<a href="#" onclick="openProcessedTab()"><font class=fontStyle>您有'
//															+ result.code
//															+ '条待办事宜,点击进入!<font></a><br>'
//															+ '<a href="#" onclick="toCcSearchInit()"><font class=fontStyle>您有'
//															+ result.result
//															+ '条协商事宜,点击进入!<font></a><br>'
//															+ '<a href="#" onclick="toCcSearchInit()"><font class=fontStyle>您有'
//															+ result.text
//															+ '条事宜被退回,点击进入!<font></a>',
//													width : 230,
//													height : 160,
//													timeout : 0,
//													showSpeed : 2000
//												});
//									}else if (result.code > 0) {
//										$.messager.show({
//											title : '待办事宜',
//											msg : '<a href="#" onclick="openProcessedTab()"><font class=fontStyle>您有'
//													+ result.code
//													+ '条待办事宜,点击进入!<font></a>',
//											width : 230,
//											height : 80,
//											timeout : 0,
//											showSpeed : 2000
//										});
//									}else if(result.result > 0){
//										$.messager.show({
//											title : '待办事宜',
//											msg : '<a href="#" onclick="toCcSearchInit()"><font class=fontStyle>您有'
//													+ result.result
//													+ '条协商事宜,点击进入!<font></a>',
//											width : 230,
//											height : 80,
//											timeout : 0,
//											showSpeed : 2000
//										});
//									}	
								}
							});

					setTimeout(
							function() {// 加载2级导航菜单
								$
										.ajax({
											type : "post",
											async : false,
											url : appUrl
													+ '/menuAjaxAction!getMenuTreeListByAjax.jspa?node=1',
											success : function(treeList) {
												var html = '';
												navSize = treeList.length;
												for ( var i = 0; i < navSize; i++) {
													html = '<li id="navigation_'
															+ i
															+ '" class="system"><a href="#" onclick=navigation4MenuTree('
															+ treeList[i].id
															+ ',"'
															+ treeList[i].text
															+ '",'
															+ i
															+ ')><span style="font-weight: bold;color:black">'
															+ treeList[i].text
															+ '</span></a></li>';
													$("#navigation").append(
															html);
													if (treeList[i].isFirst
															&& treeList[i].isFirst == 'Y') {
														// async加载tree
														navigation4MenuTree(
																treeList[i].id,
																treeList[i].text,
																i);
													}

												}

												// 隐藏导航事件
												$(function() {
													var d = 300;
													$('#navigation a')
															.each(
																	function() {
																		$(this)
																				.stop()
																				.animate(
																						{
																							'marginTop' : '-80px'
																						},
																						d += 150);
																	});

													$('#navigation > li')
															.hover(
																	function() {
																		$(
																				'a',
																				$(this))
																				.stop()
																				.animate(
																						{
																							'marginTop' : '-2px'
																						},
																						200);
																	},
																	function() {
																		$(
																				'a',
																				$(this))
																				.stop()
																				.animate(
																						{
																							'marginTop' : '-80px'
																						},
																						200);
																	});
												});
											}
										});
							}, 500);

				});

function collapseAll() {
	$('#rootTree').tree('collapseAll');
}
function expandAll() {
	$('#rootTree').tree('expandAll');
}

function add(text, attributes, target) {
	var flag = true;
	var tabs = $("#tabs");
	var allTabs = tabs.tabs("tabs");
	var opt = '';
	var check = attributes.indexOf('http://') == -1 ? false : true;
	var url = check ? attributes : (appUrl + attributes);
	if (target == '_blank') {
		window.open(url, '_blank');

	} else {
		$.each(allTabs, function() {
			opt = $(this).panel("options");
			if (opt.title == text) {
				flag = false;
			}
		});
		// 已存在的 选中并刷新
		if (flag) {
			if (text == '首页公告') {
				$('#tabs')
						.tabs(
								'add',
								{
									title : text,
									content : '<iframe frameborder="no" width="100%" height="'
											+ frameHeight
											+ '" src=' + url + '></iframe>',
									closable : false
								});

			} else {
				$('#tabs')
						.tabs(
								'add',
								{
									title : text,
									content : '<iframe frameborder="no" width="100%" height="'
											+ frameHeight
											+ '" src='
											+ url
											+ '></iframe>',
									closable : true
								});

				$.ajax({
					type : "post",
					data : {
						name : encodeURIComponent(text),
						redirectUrl : url
					},
					url : appUrl + '/menuAction!menuClickLog.jspa'
				});

			}
		} else {
			$('#tabs').tabs('select', text);
			// var currTab = $('#tabs').tabs('getTab', text);
			// currTab.panel('refresh');
		}
	}
}

$("#tabsMenu").menu({
	onClick : function(item) {
		CloseTab(this, item.name);
	}
});

// 关闭事件的实现
function CloseTab(menu, type) {
	var curTabTitle = $(menu).data("tabTitle");
	var tabs = $("#tabs");
	if (type === "close") {
		tabs.tabs("close", curTabTitle);
		return;
	}
	var allTabs = tabs.tabs("tabs");
	var closeTabsTitle = [];
	$.each(allTabs, function() {
		var opt = $(this).panel("options");
		if (opt.closable && opt.title != curTabTitle && type === "other") {
			closeTabsTitle.push(opt.title);
		} else if (opt.closable && type === "all") {
			closeTabsTitle.push(opt.title);
		}
	});
	for ( var i = 0; i < closeTabsTitle.length; i++) {
		tabs.tabs("close", closeTabsTitle[i]);
	}
}

// menutree
function initMenuTree(node) {
	$('#rootTree')
			.tree(
					{
						animate : true,
						url : appUrl
								+ '/menuAjaxAction!getMenuTreeListByAjax.jspa?node='
								+ node,
						onBeforeExpand : function(node, param) {
							$('#rootTree').tree('options').url = appUrl
									+ "/menuAjaxAction!getMenuTreeListByAjax.jspa?node="
									+ node.id;
						},
						onClick : function(node) {// 单击事件
							$(this).tree('toggle', node.target);
							if (!node.state) {
								if (node.attributes.split('#')[1] == '_blank') {
									add(node.text,
											node.attributes.split('#')[0],
											'_blank');
								} else {
									add(node.text,
											node.attributes.split('#')[0],
											'_self');
								}
							} else {
								var arrayNode = $('#rootTree').tree('getRoots');
								var parent = $('#rootTree').tree('getParent',
										node.target);
								for ( var i = 0; i < arrayNode.length; i++) {
									if (!parent
											&& arrayNode[i].target != node.target) {
										$('#rootTree').tree('collapse',
												arrayNode[i].target);
									}
								}
							}
						}
					});
}

function navigation4MenuTree(node, text, id) {
	for ( var j = 0; j < navSize; j++) {
		if (j == id) {
			$("#navigation_" + j).removeClass("system");
			$("#navigation_" + j).addClass("systemSelect");
		} else {
			$("#navigation_" + j).removeClass("systemSelect");
			$("#navigation_" + j).addClass("system");
		}
	}

	initMenuTree(node);
	var work = $("#work").panel("options").title;
	if (text) {
		$("#work").panel('setTitle', "工作台 【" + text + "】");
	}
	currentDir = $("#work").panel("options").title;
}

function reloadOA() {
	var currTab = $('#tabs').tabs('getSelected');
	currTab.panel('refresh');
}

function openProcessedTab() {
	add('待办事宜', '/wfe/eventAction!toSearchProcessEvent.jspa', '_self');

}

function toCcSearchInit(){
	add('协商事宜', '/wfe/eventAction!toCcSearchInit.jspa', '_self');
}

function toCaSearchInit(){
	add('作废事宜', '/wfe/eventAction!toCaSearchInit.jspa', '_self');
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 8) {
		window.location.href = "loginAction!logout.jspa?action=logout";
		return false;
	}
	return true;
};

//-------在线客服开始--------------
var ismove = false;
$(function(){
    /*--------------拖曳效果----------------
    *原理：标记拖曳状态dragging ,坐标位置iX, iY
    *         mousedown:fn(){dragging = true, 记录起始坐标位置，设置鼠标捕获}
    *         mouseover:fn(){判断如果dragging = true, 则当前坐标位置 - 记录起始坐标位置，绝对定位的元素获得差值}
    *         mouseup:fn(){dragging = false, 释放鼠标捕获，防止冒泡}
    */
    var dragging = false;
    var iX, iY;
    $("#online_qq_layer").mousedown(function(e) {
        dragging = true;
        iX = e.clientX - this.offsetLeft;
        iY = e.clientY - this.offsetTop;
        this.setCapture && this.setCapture();
        return false;
    });
    document.onmousemove = function(e) {
        if (dragging) {
        ismove = true;
        var e = e || window.event;
        var oX = e.clientX - iX;
        var oY = e.clientY - iY;
        $("#online_qq_layer").css({"top":oY + "px"});
        return false;
        }
    };
    $(document).mouseup(function(e) {
    	if (dragging) {
    		dragging = false;
    		$("#online_qq_layer")[0].releaseCapture;
    		e.cancelBubble = true;
    	}
    })
})

function changeOnline(num) {
	if (isNaN(num) && num == "")
		return;
	for (var i = 1; i <=6 ; i++)
	{
		if (i == num)
		{
			document.getElementById("onlineSort" + i).className = "online_bar expand";
			document.getElementById("onlineType" + i).style.display = "block";
		}
		else
		{
			document.getElementById("onlineSort" + i).className = "online_bar collapse";
			document.getElementById("onlineType" + i).style.display = "none";
		}
	}
}

$(document).ready(function(){
  $("#floatShow").bind("click",function(){
	if (ismove) {
		ismove = false;
		return false;
	}
    $('#onlineService').animate({width: 'show', opacity: 'show'}, 'normal',function(){ $('#onlineService').show(); });$('#floatShow').attr('style','display:none');$('#floatHide').attr('style','display:block');
	return false;
  });
  $("#floatHide").bind("click",function(){
	$('#onlineService').animate({width: 'hide', opacity: 'hide'}, 'normal',function(){ $('#onlineService').hide(); });$('#floatShow').attr('style','display:block');$('#floatHide').attr('style','display:none');
  });
  $(document).bind("click",function(event){
	if ($(event.target).isChildOf("#online_qq_layer") == false)
	{
	 $('#onlineService').animate({width: 'hide', opacity: 'hide'}, 'normal',function(){ $('#onlineService').hide(); });$('#floatShow').attr('style','display:block');$('#floatHide').attr('style','display:none');
	}
  });
jQuery.fn.isChildAndSelfOf = function(b){
    return (this.closest(b).length > 0);
};
jQuery.fn.isChildOf = function(b){
    return (this.parents(b).length > 0);
};
  //$(window).scroll(function(){ 
	//$('#online_qq_layer').stop().animate({top:$(document).scrollTop() + $("#online_qq_layer").height()}, 100) 
  //}); 
});
//-------在线客服结束--------------