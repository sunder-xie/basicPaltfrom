package com.kintiger.platform.boform.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;


/**
 * BO���� ����������ѯ
 * 
 * @author 
 * 
 */
public class QueryParameter extends SearchInfo {

	private static final long serialVersionUID = 4757855857330420737L;

	/**
	 * ID
	 */
	private String id;

	/**
	 * ����
	 */
	private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
