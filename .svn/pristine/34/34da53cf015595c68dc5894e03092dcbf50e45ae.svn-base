$(document).ready(function() {
			for (var sid = 0; sid < total; sid++) {
				var a = document.all("a_" + sid).value;
//				if (a == 3) {
//					var dateText="cont_" + sid;
//					$('#'+dateText).datebox({
//						id :sid,
//						onSelect : function(d) {
//					    var date = new Date(d).format("yyyyMMdd");
//					    $('#'+dateText).val(date);
//                //  document.all("coni_" + $('#'+dateText).selector.split('_')[1]).value = date;
//						}
//					});
////			document.all("cont_" + 0).onpropertychange = function onpropertychange(){ac(this.value,0)};
////			document.all("cont_" + 2).onpropertychange = function onpropertychange(){ac(this.value,2)};	
//				}
			}
//	   var dateText="cont_" + sid;
//	for (var sid = 0; sid < total; sid++) {
//					$('#'+("cont_" + 0)).datebox({
//						onSelect : function(d) {
//							var date = new Date(d).format("yyyyMMdd");
//							$('#'+("cont_" + 0)).val(date);
//						}
//					});
//						$('#'+("cont_" + 1)).datebox({
//						onSelect : function(d) {
//							var date = new Date(d).format("yyyyMMdd");
//							$('#'+("cont_" + 1)).val(date);
//						}
//					});
//							
//	}			
});

function ac(ci, i) {
	document.all("coni_" + i).value = ci;
}

function acfordate(ci, i) {
	document.all("coni_" + i).value = ci.replace(/\-/g,'');
}
//验证客户对账单,如果选择时间在201506及前，用旧的对账单，其他的不变
function check(siz){
	if($('#bid').val()=='662755'){
		var con0 = document.all("coni_0" ).value;//客户编号
		if (con0 == null ||con0 =='') {
			$.messager.alert('Tips', "客户编号项内容不能为空！", 'warning');
			return;
		}
		var con1 = document.all("coni_1" ).value;//年
		var con2 = document.all("coni_2" ).value;//月
		con2=con2==null||con2==""?document.all("cont_2").value:con;
		con2=con2.length==2?con2:'0'+con2;//10月以前加0
		var con=con1+con2;
		if(con.length==6&&con<='201506'){//改变参数list
			var params =[{
				"amount" : '1',
				"memo" : con0,
				"nickname" : 'vkunnr'
			},{
				"amount" : '0',
				"memo" : con,
				"nickname" : 'vdate'
			}];
		
			document.getElementById("reportParameterList").value = $.toJSON(params);
			document.all.xx.action = "boformAction!showBoRpt.jspa";
			document.all.xx.target = "_blank";

			$('#bid').val('13219');//改变bid参数，13219是旧的对账单
			_gaq.push(['_trackEvent', 'BO', 'View', $('#bid').val()]);
			document.all.xx.submit();
			$('#bid').val('662755');//使用好后改回bid，662755是新的对账单
		}else{//其他情况不变
			si(siz);
		}
	}else{
		si(siz);
	}
	
}

function si(siz) {
	var amount = 0;
	var params = [];
	for (var sid = 0; sid < siz; sid++) {
		var sid2 = sid - 1;

		var ch = document.all("c_" + sid).value;
		
		var con = document.all("coni_" + sid).value;
		con=con==null||con==""?document.all("cont_" + sid).value:con;
		
		var l = document.all("l_" + sid).value;
		var a = document.all("a_" + sid).value;
		var nickname = document.all("nickname_" + sid).value;

		if (a == 3) {
			amount++;
		}
		if (sid2 >= 0) {
			var a2 = document.all("a_" + sid2).value;
			var con2 = document.all("coni_" + sid2).value;

			if (a == 3 && amount % 2 == 0) {
				var bd2 = con2.split("-");
				var date2 = new Date(parseInt(bd2[0]), parseFloat(bd2[1]) - 1,
						parseInt(bd2[2]));
				var bd = con.split("-");
				var date = new Date(parseInt(bd[0]), parseFloat(bd[1]) - 1,
						parseInt(bd[2]));
			}
		}
		if (ch == '1' && con == "") {
			$.messager.alert('Tips', l + "项内容不能为空！", 'warning');
			return;
		}
		if (a == '0') {
			var cw2 = document.all("cw_" + sid).value;
			if (cw2 != '0' && cw2 != 'null' && cw2 != '') {
				if (!gocheck(con, sid))
					return;
			}
		}

		// 拼装reportParameterList
		params.push({
					"amount" : a,
					"memo" : con,
					"nickname" : nickname
				});
	}

	document.getElementById("reportParameterList").value = $
	.toJSON(params);
	document.all.xx.action = "boformAction!showBoRpt.jspa";
	document.all.xx.target = "_blank";

	_gaq.push(['_trackEvent', 'BO', 'View', $('#bid').val()]);
	document.all.xx.submit();
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		si(total) ;
		return false;
	}
	return true;
};
function cc(i) {
	document.all("cont_" + i).value = "";
	document.all("coni_" + i).value = "";
}

function isNumber(s) {
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
		return true;
	} else {
		return false;
	}
}

function isMoney(s) {
	var reg = /^\d+(\.\d{1,2})?$/;
	if (reg.test(s)) {
		return true;
	} else {
		return false;
	}
}

function gocheck(conte, i) {

	var ty = document.all("cw_" + i).value;
	if (ty == 4) {
		if (!isNumber(conte)) {
			$.messager.alert('Tips', "请填数字!", 'warning');
			return false;
		} else {
			return true;
		}
	} else if (ty == 1) {
		if (!IsLetter(conte)) {
			$.messager.alert('Tips', "请填字母!", 'warning');
			return false;
		} else {
			return true;
		}
	} else if (ty == 2) {
		if (!IsString(conte)) {
			$.messager.alert('Tips', l + "请填数字与字母的组合!", 'warning');
			return false;
		} else {
			return true;
		}
	} else if (ty == 3) {
		if (!isMoney(conte)) {
			$.messager.alert('Tips', "请最多保留两位!", 'warning');
			return false;
		} else {
			return true;
		}
	}
}

function newtree1(pid, i) {
	initMaintWindow('报表参数查询','/boformAction!searchQueryParameter.jspa?pid='
			+ pid,600,480);
	$('#countNum0').val(i);
}
/**
 * 弹出框返回值
 */
function returnValue0(s){
	var vs = new Array();
	vs=s;
	var i=$('#countNum0').val();
	document.all("cont_" + i).value = s[1];
	document.all("coni_" + i).value = s[0];
	
}

//创建窗口对象
function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						draggable : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');
}

function closeOrgTree(){
	$("#maintWindow").window('close');
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
	var i=$('#countNum').val();
	document.all("cont_" + i).value = selectedName;
	document.all("coni_" + i).value = selectedId;
}

/**
 * 选组织
 * @param i
 */
function newtree2(i) {
//	initMaintWindow('选择组织', '/orgAction!orgTreePage1.jspa?flag='+'Y&orgBId='+$('#bid').val(),300,400);
	initMaintWindow('选择组织', '/newOrgAction!newOrgTree.jspa',300,400);
    $('#countNum').val(i);
}

function newtree3(i) {
	initMaintWindow('选择SAP组织', '/orgAction!orgSAPTreePage.jspa',300,400);
	//initMaintWindow('选择组织', '/orgTreeAjaxAction!getOrgTree.jspa',300,400);
    $('#countNum').val(i);
}

function cii(i, v) {
	document.all("coni_" + i).value = v;
}

//document.onkeydown = function(e) {
//	var theEvent = e || window.event;
//	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
//	if (code == 13) {
//		return false;
//	}
//	return true;
//};
