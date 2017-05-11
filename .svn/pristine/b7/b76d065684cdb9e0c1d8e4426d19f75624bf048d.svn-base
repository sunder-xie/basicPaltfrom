function activenext(modelid, userid) // 由模板找到下一节点
{
	DWREngine.setAsync(false);
	nextUserDwr.getNextUsers(modelid, userid, calback_activenext);
	mainmodel_id = modelid;
	DWREngine.setAsync(true);
}

function activenext(modelid, userid, condition) // 由模板找到下一节点
{
	DWREngine.setAsync(false);
	nextUserDwr.getNextUsers(modelid, userid, condition, calback_activenext);
	mainmodel_id = modelid;
	DWREngine.setAsync(true);
}

var anyFlag;

/**
 * 选择模板
 * 
 * @param {}
 *            modelidObj
 */
function modifyModel(modelidObj) {
	var modelid = modelidObj.value;
	var modelname = modelidObj.options[modelidObj.selectedIndex].text;
	var userid = document.getElementById("userId").value;

	var condition;
	try {
		condition = document.getElementById("condition").value;
		if ((modelid != "0") && (modelid != "")) {
			if ((condition != "")) {
				activenext(modelid, userid, condition);
			} else {
				activenext(modelid, userid);
			}
		}
	} catch (e) {
		if ((modelid != "0") && (modelid != "")) {
			activenext(modelid, userid);
		}
	}

	document.all.modelName.value = modelname; // 将u27169 板u21517 称u20889
	// 到u-26507 面

	// 判断此流程是否为半自动流程
	$.ajax({
				async : false,
				type : "post",
				url : "/app/pages/isSelfDefineFlow.action",
				data : {
					modelid_new : modelid
				},
				success : function(resultList) {
					var dataObj = eval("(" + resultList + ")");
					anyFlag = dataObj.object[0].anyFlag;
					// document.getElementById("anyFlag0").value = anyFlag;
				},
				error : function() {
				}
			});

	// 可以填写内容
	var addContent = document.getElementById("addContent");
	$(addContent).empty();

	$(addContent)
			.append("<td id=\"addContent\" class=\"even\" colspan=\"3\">"
					+ "<a id=\"addContent\" class=\"icon_but\" href=\"javascript:addContent("
					+ document.getElementById("eventId").value
					+ ","
					+ modelid
					+ ","
					+ userid
					+ ",0)\">"
					+ "<img height=16 src=\""
					+ imgUrl
					+ "/image/actions/icon_edit.gif\" width=16 align=absMiddle border=0> 维护内容</A></td>");

	if (anyFlag == "S") {
		var transactionId = document.getElementById("transactionId").value;
		var actionId = document.getElementById("actionId").value;
		var modelId = modelid;
		var URL = "selfDefineFlowPre.action?transactionId=" + transactionId
				+ "&actionId=" + actionId + "&oaFlag=1&model_id=" + modelId;
		var x = window.showModalDialog(URL, window,
				"dialogWidth=1000px;dialogHeight=600px;scroll:no;status:yes");
		if (x == undefined) {
		} else {
			document.getElementById("jsonStr").value = x;
		}
	}

}

/**
 * 提交计划申报点击内容触发方法
 */
function addContent(eventId, modelId, userId, planAttId) {

	createShadeDiv();
	returnV = window
			.showModalDialog(
					appUrl
							+ "/wfe/eventAction!updateEventContentPrepare.jspa?planAttId="
							+ planAttId + "&modelId=" + modelId + "&eventId="
							+ eventId,
					window,
					"dialogWidth:600px;dialogHeight:300px;status=yes;toolbar=no;menubar=no;location=no");
	if (returnV == undefined) {
		closeShadeDiv();
		return;
	} else {
		closeShadeDiv();
		// var anyFlag = document.getElementById("anyFlag0").value;
		if (anyFlag != "S") {
			// 取下一个处理人
			if ((modelId != "0") && (modelId != "")) {
				activenext(modelId, userId);
				var nextUser = document.getElementById("nextUser");
				$(nextUser).show();
			}
		} else {
		}

		// 提交人编辑内容
		var addContent = document.getElementById("addContent");
		$(addContent).empty();

		$(addContent)
				.append("<td id=\"addContent\" class=\"even\" colspan=\"3\">"
						+ "<a id=\"addContent\" class=\"icon_but\" href=\"javascript:addContent("
						+ eventId
						+ ","
						+ modelId
						+ ","
						+ userId
						+ ","
						+ returnV
						+ ")\">"
						+ "<img height=16 src=\""
						+ imgUrl
						+ "/image/actions/icon_edit.gif\" width=16 align=absMiddle border=0> 维护内容</A></td>");

	}
}

/**
 * 回调函数 用于选择模板
 * 
 * @param {}
 *            data
 */
function calback_activenext(data) {
	document.getElementById("nextinfolist").innerHTML = data;

}

function openNewWindow(url) {
	var WWidth = 400;
	var WHeight = 200;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var WTop = Math.ceil((window.screen.height - WHeight) / 2);

	var winSub = window.open(url, "winSub", "left=" + WLeft + ",top=" + WTop
					+ ",width=" + WWidth + ",height=" + WHeight
					+ ",toolbar=no,menubar=no,scrollbars=yes");

}

function upload(filepath) {
	uploaddwr(filepath);
	if ((!"".equals(filepath)) && (filepath != "")) {
	}
}

function routenext(mdid) // 根据填写的路由决定流程线路
{
	var eventDetailId = document.getElementById("eventDetailId").value;
	var userId = document.getElementById("userId").value;
	DWREngine.setAsync(false);
	nextRouteDwr.nextRoute(mdid, userId, eventDetailId, routeDwrReturn);
	DWREngine.setAsync(true);
}

function routenextfn(route) {
	routenext(route);
}

// ////回调函数 用于路由
function routeDwrReturn(data) {
	document.getElementById("routeReturn").innerHTML = data;
}

// 提交
function submitTo() {
	checkUser();
	document.all['sform'].action = "add.action";
	document.all['sform'].submit();
}

function checkUser() {
	var ooo = document.getElementsByTagName("select");
	for (i = 0; i < ooo.length; i++) {
		if (ooo[i].name == 'user_id') {
			if (ooo[i].value == '' || ooo[i].value == '未定义') {
				alert("请选择人员后再提交！");
				return fales;
			}
		}

		if (ooo[i].name == 'route') {
			if (ooo[i].value == '' || ooo[i].value == '--请选择--') {
				alert("请先选择路由！");
				return fales;
			}
		}
	}

	return true;
}

function linkMan() {
	var URL = "eventAction!searchLinkMan.jspa";
	var x = window.showModalDialog(URL, null,
			"dialogWidth=300px;dialogHeight=400px;scroll:yes;status:yes");
	if (x == undefined) {
	} else {
		document.all.nextOrgId.value = x[3];
		document.all.nextOrgName.value = x[4];
		document.all.nextUserId.value = x[2];
		document.all.nextUserName.value = x[1];
	}
}

function openOrgtree() {
	var URL = appUrl + "/alluser/allUserAction!toShowUserByOrgId.jspa";
	var x = window.showModalDialog(URL, null,
			"dialogWidth=550px;dialogHeight=450px;scroll:no");
	if (x == undefined) {
	} else {
		document.getElementById("nextUserId").value = x[0];
		document.getElementById("nextUserName").value = x[2];
		document.getElementById("nextOrgId").value = x[3];
	}
}