package com.kintiger.platform.post.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;
/**
 * ְ��ʵ����
 * @author wye1
 *
 */
public class EmpPost extends SearchInfo{

	private static final long serialVersionUID = 1L;
	
	private long empPostId; //����ID
	
	private String empPostName;//ְ����
	
	private String orgId;//ְ��������֯ID
	
	private String orgName;//ְ��������֯��
	
	private String empId ;//�������ԱID
	
	private Date createDate;//��������
	
	private Date modifyDate;//�޸�����
	
	private String state;//״̬  N ����   D ɾ��
	
	private String[] orgIds;//
	
	private String[] empPostIds;

	public long getEmpPostId() {
		return empPostId;
	}

	public void setEmpPostId(long empPostId) {
		this.empPostId = empPostId;
	}

	public String getEmpPostName() {
		return empPostName;
	}

	public void setEmpPostName(String empPostName) {
		this.empPostName = empPostName;
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
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String[] getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String[] orgIds) {
		this.orgIds = orgIds;
	}

	public String[] getEmpPostIds() {
		return empPostIds;
	}

	public void setEmpPostIds(String[] empPostIds) {
		this.empPostIds = empPostIds;
	}

	

}
