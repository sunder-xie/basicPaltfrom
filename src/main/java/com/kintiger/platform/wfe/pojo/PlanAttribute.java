package com.kintiger.platform.wfe.pojo;

import java.io.File;
import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 计划申报属性表
 * 
 */
public class PlanAttribute extends SearchInfo {

	private static final long serialVersionUID = -3027653124221925123L;

	/**
	 * 属性id
	 */
	private Long planAttId;

	/**
	 * 对应模板总表key
	 */
	private String modelId;

	/**
	 * 属性内容
	 */
	private String planAttContent;
	
	private String planKeyContent;

	/**
	 * 操作用户
	 */
	private String planAttUser;

	/**
	 * 属性备注
	 */
	private String planAttMemo;

	/**
	 * Y：正常；N：删除
	 */
	private String planAttFlag;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 修改时间
	 */
	private Date modifyDate;

	/**
	 * 模板对应的类型名
	 */
	private String planTypeName;

	private String modelName;

	/**
	 * 附件
	 */
	private File[] uploadFiles;

	/**
	 * 上传附件名称
	 */
	private String[] uploadFileNames;

	/**
	 * 时间戳
	 */
	private String timestamp;

	public Long getPlanAttId() {
		return planAttId;
	}

	public void setPlanAttId(Long planAttId) {
		this.planAttId = planAttId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getPlanAttContent() {
		return planAttContent;
	}

	public void setPlanAttContent(String planAttContent) {
		this.planAttContent = planAttContent;
	}

	public String getPlanAttUser() {
		return planAttUser;
	}

	public void setPlanAttUser(String planAttUser) {
		this.planAttUser = planAttUser;
	}

	public String getPlanAttMemo() {
		return planAttMemo;
	}

	public void setPlanAttMemo(String planAttMemo) {
		this.planAttMemo = planAttMemo;
	}

	public String getPlanAttFlag() {
		return planAttFlag;
	}

	public void setPlanAttFlag(String planAttFlag) {
		this.planAttFlag = planAttFlag;
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

	public String getPlanTypeName() {
		return planTypeName;
	}

	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public File[] getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(File[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String[] getUploadFileNames() {
		return uploadFileNames;
	}

	public void setUploadFileNames(String[] uploadFileNames) {
		this.uploadFileNames = uploadFileNames;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPlanKeyContent() {
		return planKeyContent;
	}

	public void setPlanKeyContent(String planKeyContent) {
		this.planKeyContent = planKeyContent;
	}

}