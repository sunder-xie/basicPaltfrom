package com.kintiger.platform.wfe.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class Leave extends SearchInfo{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String processInstanceId;
	private String userId;
	
	private Date startTime;
	
	private Date endTime;

	private Date realityStartTime;
	
	private Date realityEndTime;
	private Date applyTime;
	private String leaveType;
	private String reason;
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getRealityStartTime() {
		return realityStartTime;
	}
	public void setRealityStartTime(Date realityStartTime) {
		this.realityStartTime = realityStartTime;
	}
	public Date getRealityEndTime() {
		return realityEndTime;
	}
	public void setRealityEndTime(Date realityEndTime) {
		this.realityEndTime = realityEndTime;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}