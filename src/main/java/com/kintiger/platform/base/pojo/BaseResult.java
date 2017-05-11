package com.kintiger.platform.base.pojo;

import java.io.Serializable;

/**
 * 返回结果基类
 * 
 * @author xujiakun
 * 
 */
public class BaseResult implements Serializable {

	private static final long serialVersionUID = -4398337558212309016L;

	private String code;

	/**
	 * 返回调用异常代码等信息
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
