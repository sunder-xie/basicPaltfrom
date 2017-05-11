function setEnv(type) {
	if (type == "ad") {
		$.ajax({
			type : "POST",
			url : appUrl + "/monitorAction!setEnv.jspa",
			data : {
				ldapHost : $("#ldapHost").val(),
				ldapUser : $("#ldapUser").val(),
				ldapPassword : $("#ldapPassword").val(),
				ldapDomain : $("#ldapDomain").val()
			},
			success : function(json) {
				$.messager.alert('Tips', "Set Environment AD Success!", 'info',
						function() {
							self.location.reload();
						});
			},
			error : function(json) {
				$.messager.alert('Tips', "Set Environment AD Fail!Please check",
						'warn');
			}
		});

	} else if (type = "email") {
		$.ajax({
			type : "POST",
			url : appUrl + "/monitorAction!setEnv.jspa",
			data : {
				smtpServer : $("#smtpServer").val(),
				emailAddress : $("#emailAddress").val(),
				emailPassword : $("#emailPassword").val(),
				displayName : $("#displayName").val()
			},
			success : function(json) {
				$.messager.alert('Tips', "Set Environment E-mail Success!", 'info',
						function() {
							self.location.reload();
						});
			},
			error : function(json) {
				$.messager.alert('Tips', "Set Environment E-mail Fail!Please check",
						'warn');
			}
		});
	}
}
