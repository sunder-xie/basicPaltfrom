package com.kintiger.platform.framework.timer;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;

import net.sf.json.JSONObject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kintiger.platform.framework.timer.pojo.WxSendCuruser;
import com.kintiger.platform.framework.timer.pojo.WxSendCc;
import com.kintiger.platform.framework.timer.pojo.WxSendInitator;
import com.kintiger.platform.wfe.pojo.ParamesAPI;
import com.kintiger.platform.wfe.pojo.SendWeChatMessage;
import com.kintiger.platform.wfe.service.IEventService;
import com.opensymphony.xwork2.util.ResolverUtil.IsA;


public class WxSendMessage extends TimerTask{
	private WebApplicationContext springContext;
	private ServletContextEvent event;
	private IEventService eventService;
	private int i=0;
	public WxSendMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WxSendMessage(ServletContextEvent event) {
		super();
		this.event = event;
	}

	@Override
	public void run() {
//		i++;
//		if (i<2) {
//			System.out.println("i:"+i);
//			return;
//			
//		}
//		springContext = WebApplicationContextUtils
//				.getWebApplicationContext(event
//						.getServletContext());
////		if (springContext == null) {
////		System.out.println("springContext is null");	
////		return;
////		}
//		eventService=(IEventService)springContext
//				.getBean("eventService");
//		
//		WxSendCuruser();
//		WxSendCc();
//		WxSendInitator();

	}
	//微信通知当前处理人
public void WxSendCuruser(){
//	try {
//		String myeventurl,wxurl,result;
//		SendWeChatMessage weChat = new SendWeChatMessage();
//		List<WxSendCuruser> wxSendCurusers=eventService.getWxSendCuruserList();
//		for (WxSendCuruser wxSendCuruser : wxSendCurusers) {
//			myeventurl = URLEncoder.encode(ParamesAPI.myevent_URI  .replace("EVENTID", wxSendCuruser.getEvent_id().toString()).replace("MODELID", wxSendCuruser.getModel_id().trim()).replace("TODODETAIL", wxSendCuruser.getEvent_dtl_id().toString()).replace("CURSTAID", wxSendCuruser.getCur_sta_id()), "utf-8");
//			wxurl=ParamesAPI.wx_URI.replace("REDIRECT_URI", myeventurl);
//			result=weChat.sendWeChatMsg1("news", wxSendCuruser.getRtx_code(), "", "", "", ""," 你有一个事务需要处理！","事务ID:"+wxSendCuruser.getEvent_id()+ "\n事务标题:"+wxSendCuruser.getEvent_title(), wxurl, "", "0", ParamesAPI.ReimburseId);	
//            if (!"".equals(result)) {
//                JSONObject jasonObject = JSONObject.fromObject(result);
// 	            Integer errcode=  (Integer) jasonObject.get("errcode");
// 	           wxSendCuruser.setWxcode(errcode.toString());
// 	          eventService.updateWxCuruserStatus(wxSendCuruser);
// 	            System.out.println("请求返回结果:" + result);
//			}
//           
//		}
//	} catch (Exception e) {
//		System.out.println(e);
//	}
}
//微信通知协商处理人
public void WxSendCc(){
//	try {
//		String cceventurl,wxurl,result;
//		SendWeChatMessage weChat = new SendWeChatMessage();
//		List<WxSendCc> wxSendCcs=eventService.getWxSendCcList();
//		for (WxSendCc wxSendCc : wxSendCcs) {
//			cceventurl= URLEncoder.encode(ParamesAPI.ccevent_URI .replace("EVENTID", wxSendCc.getEvent_id().toString()).replace("MODELID", "").replace("TODODETAIL", wxSendCc.getEvent_detail_id().toString()).replace("CURSTAID", "")
//					.replace("CC_ID", wxSendCc.getCc_id()), "utf-8");
//			wxurl=ParamesAPI.wx_URI.replace("REDIRECT_URI", cceventurl);
//			result=weChat.sendWeChatMsg1("news", wxSendCc.getRtx_code(), "", "", "", "",wxSendCc.getEmp_name()+ " 向你发送了一条出差申请的协商事宜!","事务ID:"+wxSendCc.getEvent_id()+ "\n事务标题:"+wxSendCc.getEvent_title(),wxurl, "", "0", ParamesAPI.ReimburseId);
//			if (!"".equals(result)) {
//				 JSONObject jasonObject = JSONObject.fromObject(result);
// 	            Integer errcode=  (Integer) jasonObject.get("errcode");
// 	           wxSendCc.setWxcode(errcode.toString());
// 	          eventService.updateWxCcStatus(wxSendCc);
// 	            System.out.println("请求返回结果:" + result);
//			}
//           
//		}
//	} catch (Exception e) {
//		System.out.println(e);
//	}
}
//微信通知微信通知发起人
public void WxSendInitator(){
//try {
//	String myeventurl,wxurl,result="";
//	SendWeChatMessage weChat = new SendWeChatMessage();
//	List<WxSendInitator> wxSendInitators=eventService.getWxSendInitatorList();
//	for (WxSendInitator wxSendInitator : wxSendInitators) {
//		myeventurl = URLEncoder.encode(ParamesAPI.processeddevent_URI .replace("EVENTID", wxSendInitator.getEvent_id().toString()).replace("MODELID", wxSendInitator.getModel_id().trim()).replace("TODODETAIL", wxSendInitator.getTododetailid().toString()), "utf-8");
//		wxurl=ParamesAPI.wx_URI.replace("REDIRECT_URI", myeventurl);
//		if ("2".equals(wxSendInitator.getStatus())) {
//			result=weChat.sendWeChatMsg1("news", wxSendInitator.getRtx_code(), "", "", "", ""," 你的事务已完成！","事务ID:"+wxSendInitator.getEvent_id()+ "\n事务标题:"+wxSendInitator.getEvent_title(), wxurl, "", "0", ParamesAPI.ReimburseId);
//		} else if("3".equals(wxSendInitator.getStatus())){
//			result=weChat.sendWeChatMsg1("news", wxSendInitator.getRtx_code(), "", "", "", ""," 你的事务已作废！","事务ID:"+wxSendInitator.getEvent_id()+ "\n事务标题:"+wxSendInitator.getEvent_title(), wxurl, "", "0", ParamesAPI.ReimburseId);
//		}else if("4".equals(wxSendInitator.getStatus())){
//			result=weChat.sendWeChatMsg1("news", wxSendInitator.getRtx_code(), "", "", "", ""," 你的事务已被取消！","事务ID:"+wxSendInitator.getEvent_id()+ "\n事务标题:"+wxSendInitator.getEvent_title(), wxurl, "", "0", ParamesAPI.ReimburseId);
//		}
//        if (!"".equals(result)) {
//            JSONObject jasonObject = JSONObject.fromObject(result);
//	            Integer errcode=  (Integer) jasonObject.get("errcode");
//	            wxSendInitator.setWxcode(errcode.toString());
//	          eventService.updateWxInitatorStatus(wxSendInitator);;
//	            System.out.println("请求返回结果:" + result);
//		}
//       
//	}
//} catch (Exception e) {
//	System.out.println(e);
//}
}
	public IEventService getEventService() {
		return eventService;
	}

	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

}
