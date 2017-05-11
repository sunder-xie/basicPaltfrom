$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);

	$('#modelId').combobox({
		url : appUrl + '/wfe/processChooseAction!getModels.jspa?from=fix_bx',
		valueField : 'key',
		textField : 'name',
		onLoadSuccess : function() {
			$('#modelId').combobox("setText", '--请选择--');
		}
	});
	addRow();
});

var i = 1;

/**
 * 添加行
 */
function addRow() {
	var htmlHead_1 = "<tr id=\"tr_" + i
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#ffffff\" >";
	var htmlHead_2 = "<tr id=\"tr_" + i
			+ "\" style=\"height:25px;BACKGROUND-COLOR:#f4f4f4\" >";

	var userName = $('#userName').val();

	var htmlTr = "<td style=\"text-align: center\"><input id=\"item_"
			+ i
			+ "\" type=\"checkbox\" /></td>"
			+ "<td style=\"text-align: center\">"
			+ i
			+ "</td>"
			+ "<td style=\"text-align: center\"><input size=\"30\" id=\"summary_"
			+ i
			+ "\" type=\"text\" value=\""
			+ userName
			+ "\" /></td>"
			+ "<td style=\"text-align: center\"><input size=\"8\" id=\"sum_"
			+ i
			+ "\" type=\"text\" /></td>"
			+ "<td style=\"text-align: center\"><select id=\"invoice_"
			+ i
			+ "\" >"
			+ "<option value=\"1\" selected>其他发票</option><option value=\"2\">增值税发票</option><option value=\"3\">运费发票</option>"
			+ "</select></td>"
			+ "<td style=\"text-align: center\"><select id=\"tariff_"
			+ i
			+ "\" >"
			+ "<option value=\"0\">01%税率</option></select></td>"
			+ "<td style=\"text-align: center\"><input size=\"13\" id=\"cost_"
			+ i
			+ "\" type=\"text\" readonly /></td>"
			+ "<td style=\"text-align: center\"><input size=\"8\" id=\"tax_"
			+ i
			+ "\" type=\"text\" readonly /></td>"
			+ "<td style=\"text-align: center\"><input size=\"11\" id=\"center_"
			+ i
			+ "\" type=\"text\" value=\"信息部\" readonly />"
			+ "<a class=\"icon_but\" href=\"javascript:addContent("
			+ i
			+ ")\"><img height=16 width=16 src=\""
			+ imgUrl
			+ "/images/actions/bt_detail.gif\" align=absMiddle border=0></a></td>"
			+ "<td style=\"text-align: center\"><input size=\"14\" id=\"subject_"
			+ i
			+ "\" type=\"text\" readonly />"
			+ "<a class=\"icon_but\" href=\"javascript:addContent("
			+ i
			+ ")\"><img height=16 width=16 src=\""
			+ imgUrl
			+ "/images/actions/bt_detail.gif\" align=absMiddle border=0></a></td>"
			+ "<td style=\"text-align: center\"><input size=\"10\" id=\"over_"
			+ i + "\" type=\"text\" readonly /></td>" + "</tr>";

	var htmlData = "";
	if (i % 2 == 1) {
		htmlData = htmlHead_1 + htmlTr;
	} else {
		htmlData = htmlHead_2 + htmlTr;
	}
	$('#myTab').append(htmlData);
	i++;
	addHandler();
}

/**
 * 绑定行元素的控件事件
 */
function addHandler() {
	// var obj = document.getElementById('invoice_'+i);
	// alert(obj.type);

	// $("#invoice_"+i).bind('change', function(){
	// alert(i);
	// });
}

/**
 * 删除行
 */
function removeRow() {
	var count = 0;
	for ( var k = 1; k < i; k++) {
		if ($('#item_' + k).attr('checked') == 'checked') {
			count = count + 1;
		}
	}
	var index = 0;
	for ( var j = i - 1; j > 0; j--) {
		if (index < count) {
			$("#tr_" + j).remove();
			index = index + 1;
		}
	}
	i = i - count;
	$('#item_all').attr('checked', false);
	checkAll();
}

/**
 * 选中全部
 * 
 * @param {}
 *            module
 */
function checkAll() {
	for ( var k = 1; k < i; k++) {
		$('#item_' + k).attr('checked',
				($('#item_all').attr('checked') == 'checked'));
	}
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
	}
}

function addContent(key) {
	initMaintEvent('内容创建',
			'/wfe/eventAction!updateEventContentPrepare.jspa?key=' + key);
}

// 创建窗口对象
function initMaintEvent(title, url) {
	var url = appUrl + url;
	var WWidth = 600;
	var WHeight = 400;
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
						draggable : false
					});

	$win.window('open');
}

function closeMaintEvent() {
	$("#maintEvent").window('close');
}

function submit() {
	var n = $("#title").validatebox('isValid');
	var t = $('#modelId').combobox('getValue');
	if (!n) {
		return;
	}
	if (t == null || t.length == 0) {
		$.messager.alert('Tips', '请选择模板', 'warning');
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/account/accountAction!createSingle.jspa";
	form.submit();
}

function reset() {
	alert(1);
}
