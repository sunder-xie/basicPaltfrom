function activenext(modelid, userid) // ��ģ���ҵ���һ�ڵ�
{
	DWREngine.setAsync(false);
	nextUserDwr.getNextUsers(modelid, userid, calback_activenext);
	mainmodel_id = modelid;
	DWREngine.setAsync(true);
}

function activenext(modelid, userid, condition) // ��ģ���ҵ���һ�ڵ�
{
	DWREngine.setAsync(false);
	nextUserDwr.getNextUsers(modelid, userid, condition, calback_activenext);
	mainmodel_id = modelid;
	DWREngine.setAsync(true);
}

var anyFlag;

/**
 * ѡ��ģ��
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

	document.all.modelName.value = modelname; // ��u27169 ��u21517 ��u20889
	// ��u-26507 ��

	// �жϴ������Ƿ�Ϊ���Զ�����
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

	// ������д����
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
					+ "/image/actions/icon_edit.gif\" width=16 align=absMiddle border=0> ά������</A></td>");

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
 * �ύ�ƻ��걨������ݴ�������
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
			// ȡ��һ��������
			if ((modelId != "0") && (modelId != "")) {
				activenext(modelId, userId);
				var nextUser = document.getElementById("nextUser");
				$(nextUser).show();
			}
		} else {
		}

		// �ύ�˱༭����
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
						+ "/image/actions/icon_edit.gif\" width=16 align=absMiddle border=0> ά������</A></td>");

	}
}

/**
 * �ص����� ����ѡ��ģ��
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

function routenext(mdid) // ������д��·�ɾ���������·
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

// ////�ص����� ����·��
function routeDwrReturn(data) {
	document.getElementById("routeReturn").innerHTML = data;
}

// �ύ
function submitTo() {
	checkUser();
	document.all['sform'].action = "add.action";
	document.all['sform'].submit();
}

function checkUser() {
	var ooo = document.getElementsByTagName("select");
	for (i = 0; i < ooo.length; i++) {
		if (ooo[i].name == 'user_id') {
			if (ooo[i].value == '' || ooo[i].value == 'δ����') {
				alert("��ѡ����Ա�����ύ��");
				return fales;
			}
		}

		if (ooo[i].name == 'route') {
			if (ooo[i].value == '' || ooo[i].value == '--��ѡ��--') {
				alert("����ѡ��·�ɣ�");
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