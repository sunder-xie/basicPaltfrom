package com.kintiger.platform.wfe.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class ProEventLookUp extends SearchInfo {

	private static final long serialVersionUID = -1206126562941089868L;

	private Long eventLookUpId;

	private Long eventId;

	private String userId;

	private String state;

	private String creator;

	private Date createDate;

	private String modifyer;

	private Date modifyDate;

	private String userName;

	private String creatorName;

	private String orgName;
	
    private String[] userIds;
    
	public Long getEventLookUpId() {
		return eventLookUpId;
	}

	public void setEventLookUpId(Long eventLookUpId) {
		this.eventLookUpId = eventLookUpId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifyer() {
		return modifyer;
	}

	public void setModifyer(String modifyer) {
		this.modifyer = modifyer;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}


	



}
