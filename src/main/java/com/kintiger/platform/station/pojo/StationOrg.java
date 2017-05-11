package com.kintiger.platform.station.pojo;

import java.io.Serializable;
import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

public class StationOrg extends SearchInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long stationOrgId; //主键ID
	private String stationId;  //岗位编号
	private String orgIds;     //岗位所对应的组织ID组合
	private String creator;    //创建人
	private String modifier;   //修改人
	private Date  modifyDate; //修改时间
	private String orgId; //组织ID
	private String orgName;//组织名称

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public Long getStationOrgId() {
		return stationOrgId;
	}

	public void setStationOrgId(Long stationOrgId) {
		this.stationOrgId = stationOrgId;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
