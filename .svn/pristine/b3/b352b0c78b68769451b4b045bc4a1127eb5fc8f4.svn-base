$(document).ready(function() {

});
function clearValue() {
	document.forms[0].reset();
}

function createShadeDiv() {
	var exist = document.getElementById("shadeDiv") != null;
	var shadeDiv;
	if (exist) {
		shadeDiv = document.getElementById("shadeDiv");
	} else {
		shadeDiv = document.createElement("div");
	}

	var bh = document.body.scrollHeight;
	var dh = document.documentElement.scrollHeight;

	var bw = document.body.scrollWidth;
	var dw = document.documentElement.scrollWidth;
	var sheight = bh > dh ? bh : dh;
	var swidth = bw > dw ? bw : dw;

	if (exist) {
		var iframe = document.getElementById('shadeIframe');
		iframe.width = swidth;
		iframe.height = sheight;
		window.document.body.focus();
		return;
	}
	shadeDiv.id = "shadeDiv";
	shadeDiv.style.position = "absolute";
	shadeDiv.style.left = 0;
	shadeDiv.style.top = 0;
	shadeDiv.style.zIndex = 100;
	shadeDiv.style.filter = "alpha(opacity=30)";
	shadeDiv.style.backgroundColor = "rgb(69,73,78)";
	shadeDiv.style.width = swidth;
	shadeDiv.style.height = sheight;

	var sb = [];
	sb
			.push('<iframe scrolling="no" width="100%" height="100%" frameborder="0"');
	sb.push('id="shadeIframe" name="shadeIframe" ');
	sb
			.push('style="z-index:99;position:absolute; top:0px;left:0px;filter:alpha(opacity=0);"');
	sb.push('></iframe>');

	shadeDiv.innerHTML = sb.join("");
	document.body.appendChild(shadeDiv);

	document.body.focus();
}

function closeShadeDiv() {
	var loadProceedImg = document.getElementById("loadProceedImg");
	if (loadProceedImg != undefined)
		loadProceedImg.removeNode(true);
	var loadProceedDiv = document.getElementById("loadProceedDiv");
	if (loadProceedDiv != undefined) {
		loadProceedDiv.removeNode(true);
		loadProceedDiv.innerHTML = "";
	}
	var deleteIframe = document.getElementById("shadeIframe");
	if (deleteIframe != undefined)
		deleteIframe.removeNode(true);
	var deleteDiv = document.getElementById("shadeDiv");
	if (deleteDiv != undefined) {
		deleteDiv.removeNode(true);
		deleteDiv.innerHTML = "";
	}
}