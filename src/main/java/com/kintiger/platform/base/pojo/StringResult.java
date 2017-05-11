package com.kintiger.platform.base.pojo;

import java.io.Serializable;

/**
 * 
 * 
 */
public class StringResult implements Serializable {

	private static final long serialVersionUID = -8743217325411553037L;

	private String result;
	private String code;
	private String text;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
