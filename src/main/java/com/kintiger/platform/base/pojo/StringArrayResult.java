package com.kintiger.platform.base.pojo;

import java.io.Serializable;

/**
 * 
 * @author xujiakun
 * 
 */
public class StringArrayResult extends BaseResult implements Serializable {

	private static final long serialVersionUID = -8687183805950110367L;

	private String[] result;

	/**
	 * @return the result
	 */
	public String[] getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String[] result) {
		this.result = result;
	}

}
