package com.kintiger.platform.wfe.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 计划申报属性明细表
 * 
 */
public class PlanAttributeDetail extends SearchInfo{

	private static final long serialVersionUID = 1551289535045933171L;
	
	private Long planAttDetailId;
	private Long planAttId;
	private String planAttContent;
	private String planAttKey;
	private String planAttDataType;
	private String planAttIsNull;
	
	public Long getPlanAttDetailId() {
		return planAttDetailId;
	}
	public void setPlanAttDetailId(Long planAttDetailId) {
		this.planAttDetailId = planAttDetailId;
	}
	public Long getPlanAttId() {
		return planAttId;
	}
	public void setPlanAttId(Long planAttId) {
		this.planAttId = planAttId;
	}
	public String getPlanAttContent() {
		return planAttContent;
	}
	public void setPlanAttContent(String planAttContent) {
		this.planAttContent = planAttContent;
	}
	public String getPlanAttKey() {
		return planAttKey;
	}
	public void setPlanAttKey(String planAttKey) {
		this.planAttKey = planAttKey;
	}
	public String getPlanAttDataType() {
		return planAttDataType;
	}
	public void setPlanAttDataType(String planAttDataType) {
		this.planAttDataType = planAttDataType;
	}
	public String getPlanAttIsNull() {
		return planAttIsNull;
	}
	public void setPlanAttIsNull(String planAttIsNull) {
		this.planAttIsNull = planAttIsNull;
	}
	
}
