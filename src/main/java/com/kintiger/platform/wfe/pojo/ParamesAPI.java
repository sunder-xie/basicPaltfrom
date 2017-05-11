package com.kintiger.platform.wfe.pojo;

public class ParamesAPI {
	// token
		public static String token = "chinaxpp";  
		// 随机戳
		public static String encodingAESKey = "35G3ce9FDujV0ERwiKTk2bfa0SWJRPaTaxI1maaJgoo";  
		 //你的企业号ID
		public static String corpId = "wx1769b4e5e23bc635";
		// 管理组的凭证密钥，每个secret代表了对应用、通讯录、接口的不同权限；不同的管理组拥有不同的secret
		public static String corpsecret = "OwYX5NLZxIlTHFltQmPTavLeVd_Roa9lyFu7o-h0UCBMqLTuu7VFMtHA4BvNgNng";
		// OAuth2 回调地址
		public static String REDIRECT_URI = "http://wxtest.zjxpp.com/xppweixin/platform/weixin/Auth2Action!auth2myinfo.jspa";
		//个人工资AgentId
		public static String payrollPersonalAgentId = "51";
		//经销商互动平台管理
		public static String jxsAdminAgentId = "58";
		//经销商互动平台
		public static String jxsAgentId = "57";
		//事务审批
		public static String swspId = "79";
		public static String jxs_URI = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1769b4e5e23bc635&redirect_uri=http://wxtest.zjxpp.com/xppweixin/platform/weixin/interactiveAction!getOneInteractiveDelail.jspa?delail_id=DELAIL_ID&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		public static String jxsAdmin_URI = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1769b4e5e23bc635&redirect_uri=http://wxtest.zjxpp.com/xppweixin/platform/weixin/interactiveAction!getOneInteractiveDelailAdmin.jspa?delail_id=DELAIL_ID&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		public static String wx_URI = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1769b4e5e23bc635&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		public static String myevent_URI = "http://wxtest.zjxpp.com/wxworkflow/wfe/eventAction!getOneEvent.jspa?eventId=EVENTID&operationType=process&modelId=MODELID&toDoDetail=TODODETAIL&curStaId=CURSTAID";
		public static String ccevent_URI = "http://wxtest.zjxpp.com/wxworkflow/wfe/eventAction!getOneEvent.jspa?eventId=EVENTID&operationType=ccprocess&modelId=MODELID&toDoDetail=TODODETAIL&cc_id=CC_ID&curStaId=CURSTAID";
		public static String processeddevent_URI = "http://wxtest.zjxpp.com/wxworkflow/wfe/eventAction!getOneEvent.jspa?eventId=EVENTID&modelId=MODELID&toDoDetail=TODODETAIL";
		public static String ReimburseId="79";
}
