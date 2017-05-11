//Ä£ÄâÌá½»
function formSubmit() {
	var logonForm = window.document.logonForm;
	var params = $("#params").val().split('&');
	for ( var i = 0; i < params.length; i++) {
		var p = params[i];
		var myInput = document.createElement("input");
		var kv = p.split('=');
		myInput.setAttribute("type", 'hidden');
		myInput.setAttribute("name", kv[0]);
		myInput.setAttribute("value", kv[1]);
		logonForm.appendChild(myInput);
	}
	logonForm.submit();
}