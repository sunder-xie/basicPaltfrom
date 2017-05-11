package com.kintiger.platform.wfe.pojo;

import java.io.Serializable;
import java.util.Date;

public class BudgetFileTmp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1335851985601740313L;

	private Long fileId;

	private String fileName;

	private Date createDate;

	private String fileNameNew;

	private Long eventDtlId;

	/**
	 * 上传文件夹名
	 */
	private String subFolders;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFileNameNew() {
		return fileNameNew;
	}

	public void setFileNameNew(String fileNameNew) {
		this.fileNameNew = fileNameNew;
	}

	public Long getEventDtlId() {
		return eventDtlId;
	}

	public void setEventDtlId(Long eventDtlId) {
		this.eventDtlId = eventDtlId;
	}

	public String getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(String subFolders) {
		this.subFolders = subFolders;
	}



}
