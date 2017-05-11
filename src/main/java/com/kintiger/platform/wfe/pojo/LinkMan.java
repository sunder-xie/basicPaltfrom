package com.kintiger.platform.wfe.pojo;

import java.math.BigDecimal;

import com.kintiger.platform.base.pojo.SearchInfo;

public class LinkMan extends SearchInfo {

	private static final long serialVersionUID = -9200397151598751419L;

	/**
	 * id
	 */
	private Long linkId;

	/**
	 * 用户id
	 */
	private Long empId;

	/**
	 * 联系人id
	 */
	private Long linkManId;

	/**
	 * 联系人姓名
	 */
	private String linkManName;

	/**
	 * 使用次数
	 */
	private BigDecimal useCount;

	/**
	 * 组织id
	 */
	private Long orgId;

	/**
	 * 组织名称
	 */
	private String orgName;

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public Long getLinkManId() {
		return linkManId;
	}

	public void setLinkManId(Long linkManId) {
		this.linkManId = linkManId;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public BigDecimal getUseCount() {
		return useCount;
	}

	public void setUseCount(BigDecimal useCount) {
		this.useCount = useCount;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
