package com.kintiger.platform.webservice.pojo;

import java.io.Serializable;

public class ProcessEventDetail implements Serializable {
	private static final long serialVersionUID = -1L;

	public ProcessEventDetail() {
		super();
	}

	public ProcessEventDetail(Long eventDtlId, String empName, String roleName,
			String curUserId, String nextUserId, String subTitle, Long eventId,
			String eventDtlFlag, String nextEventdtlId, String time,
			String curStaId, String nextStaId, String currentDetailid,
			String modelId) {
		super();
		this.eventDtlId = eventDtlId;
		this.empName = empName;
		this.roleName = roleName;
		this.curUserId = curUserId;
		this.nextUserId = nextUserId;
		this.subTitle = subTitle;
		this.eventId = eventId;
		this.eventDtlFlag = eventDtlFlag;
		this.nextEventdtlId = nextEventdtlId;
		this.time = time;
		this.curStaId = curStaId;
		this.nextStaId = nextStaId;
		this.currentDetailid = currentDetailid;
		this.modelId = modelId;
	}

	private Long eventDtlId;

	private String empName;

	private String roleName;

	private String curUserId;

	private String nextUserId;

	private String subTitle;

	private Long eventId;

	private String eventDtlFlag;

	private String nextEventdtlId;

	private String time;

	private String curStaId;

	private String nextStaId;

	private String currentDetailid;

	private String modelId;

	public Long getEventDtlId() {
		return eventDtlId;
	}

	public void setEventDtlId(Long eventDtlId) {
		this.eventDtlId = eventDtlId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(String curUserId) {
		this.curUserId = curUserId;
	}

	public String getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventDtlFlag() {
		return eventDtlFlag;
	}

	public void setEventDtlFlag(String eventDtlFlag) {
		this.eventDtlFlag = eventDtlFlag;
	}

	public String getNextEventdtlId() {
		return nextEventdtlId;
	}

	public void setNextEventdtlId(String nextEventdtlId) {
		this.nextEventdtlId = nextEventdtlId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCurStaId() {
		return curStaId;
	}

	public void setCurStaId(String curStaId) {
		this.curStaId = curStaId;
	}

	public String getNextStaId() {
		return nextStaId;
	}

	public void setNextStaId(String nextStaId) {
		this.nextStaId = nextStaId;
	}

	public String getCurrentDetailid() {
		return currentDetailid;
	}

	public void setCurrentDetailid(String currentDetailid) {
		this.currentDetailid = currentDetailid;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

}