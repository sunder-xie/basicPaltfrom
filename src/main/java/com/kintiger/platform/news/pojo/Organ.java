package com.kintiger.platform.news.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;
/**
 * Title: 通知公告发送至多个可选的组织（中间表）
 * Description: basisPlatform
 * @author: xg.chen
 * @date:2017年1月19日 下午2:38:24
 */
public class Organ extends SearchInfo{
	
	private static final long serialVersionUID = -415697482420047174L;
	
	private Long id;
	private Long delailId;//明细表id
	private String orgId;//组织Id
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDelailId() {
		return delailId;
	}
	public void setDelailId(Long delailId) {
		this.delailId = delailId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	

}
