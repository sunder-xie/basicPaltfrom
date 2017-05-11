package com.kintiger.platform.framework.timer.pojo;

import java.util.Date;

public class WxSendInitator {
	private Long event_id;
	private String initator;
	private String model_id;
	private Date creatdate;
    private Long tododetailid;
    private String status;
    private Date last_modify;
    private String event_title;
    private String wxcode;
    private String rtx_code;
	public WxSendInitator(Long event_id, String initator, String model_id,
			Date creatdate, Long tododetailid, String status, Date last_modify,
			String event_title, String wxcode, String rtx_code) {
		super();
		this.event_id = event_id;
		this.initator = initator;
		this.model_id = model_id;
		this.creatdate = creatdate;
		this.tododetailid = tododetailid;
		this.status = status;
		this.last_modify = last_modify;
		this.event_title = event_title;
		this.wxcode = wxcode;
		this.rtx_code = rtx_code;
	}
	public WxSendInitator() {
	}
	public Long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}
	public String getInitator() {
		return initator;
	}
	public void setInitator(String initator) {
		this.initator = initator;
	}
	public String getModel_id() {
		return model_id;
	}
	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}
	public Date getCreatdate() {
		return creatdate;
	}
	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}
	public Long getTododetailid() {
		return tododetailid;
	}
	public void setTododetailid(Long tododetailid) {
		this.tododetailid = tododetailid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLast_modify() {
		return last_modify;
	}
	public void setLast_modify(Date last_modify) {
		this.last_modify = last_modify;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getWxcode() {
		return wxcode;
	}
	public void setWxcode(String wxcode) {
		this.wxcode = wxcode;
	}
	public String getRtx_code() {
		return rtx_code;
	}
	public void setRtx_code(String rtx_code) {
		this.rtx_code = rtx_code;
	}
	@Override
	public String toString() {
		return "WxSendInitator [event_id=" + event_id + ", initator="
				+ initator + ", model_id=" + model_id + ", creatdate="
				+ creatdate + ", tododetailid=" + tododetailid + ", status="
				+ status + ", last_modify=" + last_modify + ", event_title="
				+ event_title + ", wxcode=" + wxcode + ", rtx_code=" + rtx_code
				+ "]";
	}
    
}
