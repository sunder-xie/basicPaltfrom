package com.kintiger.platform.wfe.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class BusinessTripApply extends SearchInfo {
	private static final long serialVersionUID = 3690598034302973646L;
	
	private String eventId;
	
	private String orgName;
	
	private String orgId;
	
	private String costCenter;
	
	private String costCenterName;
	
	private String place;
	
	private String tripWay;
	
	private String tripWayName;
	
	private String peopleNames;
	
	private String distance;
	
	private Date beginDate;
	
	private Date endDate;
	
	private String reason;

	private String status;
	
	private String eventTitle;
	
	private String userName;
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostCenterName() {
		return costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTripWay() {
		return tripWay;
	}

	public void setTripWay(String tripWay) {
		this.tripWay = tripWay;
	}

	public String getTripWayName() {
		return tripWayName;
	}

	public void setTripWayName(String tripWayName) {
		this.tripWayName = tripWayName;
	}

	public String getPeopleNames() {
		return peopleNames;
	}

	public void setPeopleNames(String peopleNames) {
		this.peopleNames = peopleNames;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
