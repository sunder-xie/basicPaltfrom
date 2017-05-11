package com.kintiger.platform.framework.util;

import java.util.List;

public class XMLInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 实体类
	 */
	private Object object;

	/**
	 * 实体类属性
	 */
	private String lineNumber;

	/**
	 * 提交人id
	 */
	private String creatorId;

	/**
	 * 提交人name
	 */
	private String creatorName;

	private String time;

	/**
	 * 修改记录
	 */
	private List<String> modifyRecordList;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<String> getModifyRecordList() {
		return modifyRecordList;
	}

	public void setModifyRecordList(List<String> modifyRecordList) {
		this.modifyRecordList = modifyRecordList;
	}

}