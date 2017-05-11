function print(){
	var eventId=$('#eventId').val();
	var xmlFilePath=$('#xmlFilePath').val();
	var subFolders=$('#subFolders').val();
	var WWidth = 950;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var win = window.open(appUrl + '/wfe/eventAction!toEventPrintPre.jspa?eventId='+eventId+'&subFolders='+subFolders+'&printType='+'apply',"eventPrint","left=" + WLeft + ",top=20" + ",width=" + WWidth + 
",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}