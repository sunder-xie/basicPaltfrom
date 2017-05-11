/* upload function */

var fileInputNumber = $("#fileInputNumber").val() == undefined
		? 0
		: $("#fileInputNumber").val();

function addFile() {
	var strFile = "file" + fileInputNumber;
	var filePath = document.getElementById(strFile);
	document.getElementById(strFile).style.display = "none";
	var paths = filePath.value.split("\\");
	var name = paths[paths.length - 1];
	var str1 = '<div style="background-color:#E7EBF7" >&nbsp;<span style="font-size: 12px"> '+ name
			+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img onclick="removeFile(this,'
			+ strFile + ')" src="' + imgUrl
			+ '/images/actions/action_del.png" border="0" alt="删除附件"/></a></div>';
	var _file = document.getElementById("_file");
	_file.insertAdjacentHTML("beforeend", str1);

	addInput();
}

function addInput() {
	fileInputNumber++;
	var strFile = "file" + fileInputNumber;
	var str2 = '<input name="upload" id="' + strFile
			+ '" type="file" value="添加附件" onchange="addFile()" />';
	var _input = document.getElementById("input");
	_input.insertAdjacentHTML("afterbegin", str2);

}

function removeFile(id, strFile) {
	var new_tr = id.parentNode.parentNode;
	try {
		var tmp = new_tr.parentNode;
		// 为了在ie和firefox下都能正常使用,就要用另一个方法代替,最取上一层的父结点,然后remove.
		tmp.removeChild(new_tr);

		removeInput(strFile);
	} catch (e) {
	}
}

function removeInput(strFile) {
	var _input = document.getElementById("input");
	try {
		// 为了在ie和firefox下都能正常使用,就要用另一个方法代替,最取上一层的父结点,然后remove.
		_input.removeChild(strFile);

	} catch (e) {
	}
}

/* end of upload function */

