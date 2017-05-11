package com.kintiger.platform.wfe.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class Cc extends SearchInfo{

	private static final long serialVersionUID = 1L;
	
	private Long cc_id;  //主键ID
	private Long event_id; //事务ID
	private Long event_detail_id; //事务明细ID
	private Date create_date; //创建时间
	private String creator; //创建人
	private String replay_memo; //回复内容
	private String cc_user_id; //抄送人ID
	private Date replay_date; //回复时间
	private String type;//类型 1：抄送，2：会签
	private String creator_name; //提报者姓名
	private String flag; //不为空时，获取当前登录人，是否有抄送信息未处理
	private String event_title; //事务标题
	/* rtx_code 人员在rtx的id	 */
	 private String rtx_LoginId;
	 
	public String getRtx_LoginId() {
		return rtx_LoginId;
	}
	public void setRtx_LoginId(String rtx_LoginId) {
		this.rtx_LoginId = rtx_LoginId;
	}
	public Long getCc_id() {
		return cc_id;
	}
	public void setCc_id(Long cc_id) {
		this.cc_id = cc_id;
	}
	public Long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}
	public Long getEvent_detail_id() {
		return event_detail_id;
	}
	public void setEvent_detail_id(Long event_detail_id) {
		this.event_detail_id = event_detail_id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getReplay_memo() {
		return replay_memo;
	}
	public void setReplay_memo(String replay_memo) {
		this.replay_memo = replay_memo;
	}
	public String getCc_user_id() {
		return cc_user_id;
	}
	public void setCc_user_id(String cc_user_id) {
		this.cc_user_id = cc_user_id;
	}
	public Date getReplay_date() {
		return replay_date;
	}
	public void setReplay_date(Date replay_date) {
		this.replay_date = replay_date;
	}
	public String getCreator_name() {
		return creator_name;
	}
	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}